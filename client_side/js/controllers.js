//==================================================================================================================
//==================================================================================================================

Admin.ControllerManager = function(){
	
	/* список контроллеров */ 
	var controllers = {};
		
	/* контролллеры, подлежащие удалению */
	var controllersRem = new Array();
	
	/*/////////////////////////////////////////////////////////////////////////////////////////////////////////
	 * Получить контроллер по имени класса сущности
	 * 
	 * _name - имя класса сущности, например 'BtvChannel', 'Movie'
	 */
	this.getController = function(_name){
		if(controllers[_name]){
		}else{
			/* CREATE new controller */
			clear();
			var fullControllerName = 'Controller' + _name;
			var controller = null;
			try{
				controller = eval('new ' + fullControllerName + '()');
			}catch(e){
				controller = eval('new BaseController(\'' + _name + '\')');
			}
			controllers[_name] = controller;
		}
		return controllers[_name];
	};
	
	
	/*/////////////////////////////////////////////////////////////////////////////////////////////////////////
	 * удалить контроллер по имени класса сущности.
	 * На самом деле контроллер, если он есть, только помечается на удаление
	 * Удаление само происходит при запросе контроллера.
	 * 
	 * _name - имя класса сущности, например 'BtvChannel', 'Movie'
	 */
	this.removeController = function(_name){
		this.controllersRem[this.controllersRem.length] = _name;
	};
	
	/*/////////////////////////////////////////////////////////////////////////////////////////////////////////
	 * PRIVATE
	 * 
	 * Очищает контроллеры, помеченные к удалению.
	 * 
	 */
	function clear(){
		for(i in controllersRem){
			controllers.splice(i,1);
		}
		controllersRem.splice(0, controllersRem.length);
	};
};

//==================================================================================================================
//==================================================================================================================
//==================================================================================================================
//==================================================================================================================
//==================================================================================================================
//==================================================================================================================
// BASE CONTROLLER =================================================================================================
function BaseController(_class){
    
	/* Класс представляемых объектов, например BtvChannel, Videoserver ... */
    this.entityClass = _class;
        
    /* имя контроллера */
    this.name = Admin.lib.prepareControllerName(this.entityClass); 
    
    /* URL-ы для загрузки данных */
    this.urls = model.URLS[this.entityClass];
    
    /* параметры таблицы. Cтраницы начинаются с единицы !!! */
    this.tableParams = {totalCount:0,pageSize:10,page:1};
    
    /* private fields ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    /* данные, загружаемые через AJAX */
    var loadedData = null;
    /* сброс загруженных данных */
    this.resetLoadedData = function(){loadedData = null;};
    this.initLoadedData = function(_data){loadedData = _data;};
    this.getLoadedData = function(){return loadedData;};
    
    /* Данные !!!! */
    var data = null;
    /* Метод вызывается при загрузке данных из AJAX-компонента. */
    this.initData = function(_data){data = _data;};
            
    /* Getter для данных. Если данных нет, то происходит их загрузка */
    this.getData = function(_page, _pageSize){
    	if(_page == undefined){
    		_page = this.tableParams.page;
    	}
    	if(_pageSize == undefined){
    		_pageSize = this.tableParams.pageSize;
    	}
    	
    	if(data == null || _pageSize != this.tableParams.pageSize || _page != this.tableParams.page){
    		if(this.urls == undefined || this.urls == false){
    			alert("URL загрузки данных для " + this.entityClass + " не задан!");
    			return null;
    		}
    		// prepareUrl:
    		var url = this.urls.table;
    		var isPagedLoading = Admin.lib.model.pagedLoading(this.entityClass); 
    		if(isPagedLoading){
    			
    		}
    		url = url.replace('{page}', _page ).replace('{pageSize}', _pageSize);
    		    		
    		this.requestRead(url);
    		
    		if(isPagedLoading){
    			this.tableParams.totalCount = loadedData.totalCount;
    			/* а это возможно лишнее, так как запрос делаем с этими параметрами */
    			this.tableParams.page = loadedData.page;
    			this.tableParams.pageSize = loadedData.pageSize;
    			/* -----------------------------------------------------------------ы */
    			this.initData(loadedData.list);
    		}else{
    			//this.initData(loadedData);
    			this.tableParams.totalCount = loadedData.totalCount;
    			/* а это возможно лишнее, так как запрос делаем с этими параметрами */
    			this.tableParams.page = loadedData.page;
    			this.tableParams.pageSize = loadedData.pageSize;
    			/* -----------------------------------------------------------------ы */
    			this.initData(loadedData.list);
    		}
    		
    	}
    	return data;
    };
    
    this.getAllData = function(){
    	return this.getData(1,1000);
    };
            
    var rec = null;
    this.initRec = function(_rec){rec = _rec;};
    
    /* Инициализация записи по идентификатору записи в таблице.
     * Если таблица не загружена, то загружается (метод getData инициирует загрузку таблицы при необходимости)
     */
    this.initRecById = function(_id){
    	// clear rec
    	this.initRec(null);
    	var datas = this.getData();
    	for(var i in datas){
    		if(datas[i].id == _id){
    			this.initRec(datas[i]);
    			break;
    		}
    	}
    };
    
    this.loadRec = function(_id){
    	if(this.urls === undefined){
    		throw {message : "model.URLS." + this.entityClass + " не задан. Не возможно загрузить данные для требуемой записи."};
    	}
    	
    	// prepare url
    	var url = this.urls.record.replace('{id}', _id);
    	
    	this.requestRead(url);
    	rec = loadedData;
    };
    
    this.getRec = function(){
    	return rec;
    };
            
    /* чтение данных. AJAX-компонент вызывает метод initLoadedData 
     * После этого загруженные данные доступны через this.getLoadedData() */
    this.requestRead = function(_url){
    	if(!this.havePermission('R')){
    		return;
    	}
    	this.resetLoadedData();
    	requestManager.doGet(_url, null, this);
    };
        
    /* определяет наличие затребованных прав. Доступны значения:
     * C - создание
     * R - чтение
     * U - обновление
     * D - удаление 
     */
    this.havePermission = function(perm){
    	if(!model.PERMS[this.entityClass]){
    		alert("Права для сущности '" + this.entityClass + "' не заданы  !");
    		return false;
    	}
    	
    	if(model.PERMS[this.entityClass].indexOf(perm) >= 0){
    		return true;
    	}else{
    		alert("Права для операции '" + ref.MSGS['permission.'+perm]  + "'(" + perm + ") для сущности '" + this.entityClass + "' отсутствуют !");
    		return false;
    	}
    };
    
    this.adaptedRec = null;
    
    this.resetAdaptedRec = function(){this.adaptedRec = null;};
    
    this.getAdaptedRec = function(_mode){
    	if(this.adaptedRec != null){
    		return this.adaptedRec;
    	}
    	var r = {};
    	// проходимся по всем полям
    	for(var fName in model.CLASSES[this.entityClass].fields){
    		fDescriptor = model.CLASSES[this.entityClass].fields[fName];
    		var fieldValue = null ;
    		if(rec[fName] != undefined){
    			fieldValue = rec[fName];
    		}
    		var obj = Admin.types.Factory.createType(fieldValue, this.entityClass, fName,_mode);
    		r[fName] = obj;
    		
    	}
    	
    	/*
    	for(var fName in rec){
    		var obj = Admin.types.Factory.createType(rec[fName], this.entityClass, fName,_mode);
    		if(obj != null){
    			r[fName] = obj;
    		}else{
    			r[fName] = rec[fName];
    		}
    	}
    	*/
    	this.adaptedRec = r;
    	return this.adaptedRec;
    };
            
    this.createNewRecord = function(){
    	var newRec = {};
    	var fields = model.CLASSES[this.entityClass].fields;
    	for(var fieldName in fields){
    		    		
    		fieldDescriptor = fields[fieldName];
    		if(fieldDescriptor.defaultValue != undefined){
    			newRec[fieldName] = fieldDescriptor.defaultValue;
    		}else{
    			newRec[fieldName] = null;
    		}
    	}
    	
    	return newRec;
    };
    
    
    this.persist = function(){
    	if(this.urls.persist == undefined){
    		log.error('Не найден URL для создания записи сущности ' + this.entityClass);
    		return {error: 'Не удалось выполнить сохранение! Подробная информация находится в логе'};
    	};
    	var url = this.urls.persist;
    	var data = this.prepareJsonFromAdaptedRecord();
    	requestManager.doPost(url, data, this);
    	
    	// готовим данные для сохранения
    	
    	
    };
    
    this.prepareJsonFromAdaptedRecord = function(){
    	var ob = {};
    	for(var i in this.adaptedRec){
    		var vtObject = this.adaptedRec[i];
    		ob[vtObject.fieldName] = vtObject.internalObj;
    	};
    	return ob;
    };
                    
}





//==================================================================================================================
//==================================================================================================================
// BASE TABLE CONTROLLER ===========================================================================================
//==================================================================================================================
//==================================================================================================================
function BaseTableController(_class){
    BaseController.call(this, _class, 'table');  
    
    /* контроллер обработки строк существующего контроллера таблицы 
     * Не использовать напрямую !!!!! Использовать getter
     */
    var recordController = null;
    
    /* вернуть название соответсвующего RecordController-а */
    this.getRecordControllerName = function(){return this.entityName + 'RecordController';};
    
    /* вернуть recordController. */
    this.getRecordController = function(){
    	if(recordController == null){
    		recordController = controllerManager.getRecordController(this.entityClass) ; 
    	}
    	return recordController;
    };
            
}

//==================================================================================================================
//==================================================================================================================
// BASE RECORD CONTROLLER ===========================================================================================
//==================================================================================================================
//==================================================================================================================
function BaseRecordController(_class){
    BaseController.call(this,_class, 'record');    
    
    /* TABLE контроллер соответсвующей сущности */
    var tableController = null;
    
    this.getTableController = function(){
    	if(tableController == null){
    		tableController = controllerManager.getTableController(this.entityClass);
    	}
    	return tableController;
    };
    
}








