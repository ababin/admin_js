/* =========================================================================================================================================================== */
/* =========================================================================================================================================================== */
/* =========================================================================================================================================================== */
/* =========================================================================================================================================================== */
/* =========================================================================================================================================================== */
/* =========================================================================================================================================================== */
/* =========================================================================================================================================================== */
/* =========================================================================================================================================================== */
/* =========================================================================================================================================================== */
/* =========================================================================================================================================================== */
/* =========================================================================================================================================================== */
/* =========================================================================================================================================================== */
/* === F A C T O R Y ========================================================================================================================================== */
if(typeof Admin == 'undefined'){Admin = {};}

Admin.types = {};

Admin.types.Factory = {};

Admin.types.Factory.createType = function(_value, _entityClass, _fieldName, _mode){
	/* get fieldDescriptor */
	if(!model.CLASSES[_entityClass].fields[_fieldName]){
		return null;
	}
	var fd = model.CLASSES[_entityClass].fields[_fieldName];
	
	// анализируем на встроенный тип:
	var i = fd.type.indexOf(':');
	if(i > 0){
		// встроенный тип !
		i = fd.type.indexOf('t:');
		if(i == 0){
			// встроенный ПРОСТОЙ тип
			var type = fd.type.substring(i + 2);
			var vo = eval('new Admin.types.' + type + '(_value, _entityClass,_fieldName,_mode)');
			return vo;
		}
		i = fd.type.indexOf('e:');
		if(i == 0){
			// встроенный ENUM тип
			var vo = new Admin.types.Enum(_value, _entityClass,_fieldName,_mode); 
			return vo; 
		}
		alert('Неизвестный тип данных: ' + fd.type);
		return;
	}else{
		// external тип
		var multitude = fd.multitude;
		if(!multitude){
			// это Alone object
			var vo = new Admin.types.Alone(_value, _entityClass, _fieldName,_mode); 
			return vo; 
		}else{
			if(multitude == 'set'){
				// это не alone объект
				var vo = new Admin.types.Set(_value, _entityClass, _fieldName,_mode);
				return vo;
			}
			if(multitude == 'list'){
				// это не alone объект
				var vo = new Admin.types.List(_value, _entityClass, _fieldName,_mode);
				return vo;
			}
			 
		}
		
	}
};


/* =========================================================================================================================================================== */
/* =========================================================================================================================================================== */
/* =========================================================================================================================================================== */
/* =========================================================================================================================================================== */
/* =========================================================================================================================================================== */
/* =========================================================================================================================================================== */
/* =========================================================================================================================================================== */
/* =========================================================================================================================================================== */
/* =========================================================================================================================================================== */
/* =========================================================================================================================================================== */
/* =========================================================================================================================================================== */
/* =========================================================================================================================================================== */
/* === T Y P E S ============================================================================================================================================= */


/* BaseType */
/*
 * _internalObj - внутреннее представление объекта
 * _origObj - оригинальное значение, передаваемое снаружи в конструктор
 * _entityClass - имя сущности, для которой рассматривается данное поле (например BtvChannel, Videoserver)
 * _fieldName - имя поля
 * _className - имя класса реализации ТЕКУЩЕГО типа (например ImageUrl, String, Number ...) (нужно подумать, как от него можно избавиться и получать автоматически...)
 */
Admin.types.BaseType = function(_internalObj , _originalObj , _entityClass, _fieldName, _type){
	
	// текущее значение
	this.internalObj = _internalObj;
	
	// оригинальный объект, переданный в конструкторе
	this.originalObj = _originalObj;
		
	// название класса объекта, данные которого обрабатываются
	this.entityClass = _entityClass;
	
	// название поля, для которого формируется значение
	this.fieldName = _fieldName;
	
	// название текущего типа (имя класса текущего ТИПА данных)
	this.type = _type;
	
	// поддерживает ли тип значение NULL
	this.fDesc = null;
	
	this.lastValidateResult = true;
	
	if(model.CLASSES[_entityClass] != undefined 
			&& model.CLASSES[_entityClass].fields != undefined 
			&& model.CLASSES[_entityClass].fields[_fieldName] != undefined){
		this.fDesc = model.CLASSES[_entityClass].fields[_fieldName];
	}
						
};

Admin.types.BaseType.prototype.validate = function(){
	log.warn('validate() : Implementation not found for type ' + this.type);
	return true;
};

Admin.types.BaseType.prototype.isModified = function(){
	if((this.internalObj == null || this.internalObj.toString() == '') && (this.originalObj == null || this.originalObj.toString() == '')){
		return false;
	}
	return this.internalObj != this.originalObj;
};

/* =========================================================================================================================================================== */
/* TYPE imageUrl определяет тип ========================================================================================================================= */
/* =========================================================================================================================================================== */
Admin.types.ImageUrl = function(_ob, _entityClass, _fieldName){
	Admin.types.BaseType.call(this, Admin.lib.copyObject(_ob) , _ob , _entityClass, _fieldName, 'ImageUrl');
};
Admin.types.ImageUrl.prototype = new Admin.types.BaseType();

/* =========================================================================================================================================================== */
/* TYPE String определяет тип ========================================================================================================================= */
/* =========================================================================================================================================================== */
Admin.types.String = function(_ob,_entityClass, _fieldName){
	Admin.types.BaseType.call(this, Admin.lib.copyObject(_ob) , _ob , _entityClass, _fieldName, 'String');
};
Admin.types.String.prototype = new Admin.types.BaseType();

Admin.types.String.prototype.validate = function(){
	this.lastValidateResult = true;
	// проверяем на заполненность
	//log.error(this.entityClass + '.' + this.fieldName + ': ' + this.internalObj);
	if(!Admin.lib.model.isNullable(this.entityClass,this.fieldName) && this.internalObj.length == 0){
		this.lastValidateResult = {error : Admin.render.errors.REQUIRED}; 
		return this.lastValidateResult;
	}
	// проверяем максимальный размер
	var maxSize = Admin.lib.model.maxSize(this.entityClass,this.fieldName);
	if(this.internalObj.length > maxSize){
		this.lastValidateResult = {error : Admin.render.errors.TOO_LONG, value: maxSize}; 
		return this.lastValidateResult;
	};
	// проверяем минимальный размер
	var minSize = Admin.lib.model.minSize(this.entityClass,this.fieldName);
	if(this.internalObj.length < minSize){
		this.lastValidateResult = {error : Admin.render.errors.TOO_SHORT, value: minSize}; 
		return this.lastValidateResult;
	};
	return this.lastValidateResult;
};

/* =========================================================================================================================================================== */
/* TYPE I18nString определяет тип ========================================================================================================================= */
/* =========================================================================================================================================================== */
Admin.types.I18nString = function(_ob,_entityClass, _fieldName){
	if(_ob === undefined){
		return;
	}
	var internal = {};
	for(var i in ref.LOCALES){
		var locale = ref.LOCALES[i];
		if(_ob != null && _ob[locale] != null){
			internal[locale] = _ob[locale];
		}else{
			internal[locale] = null;
		}
	}
	Admin.types.BaseType.call(this, internal, _ob , _entityClass, _fieldName, 'I18nString');
};
Admin.types.I18nString.prototype = new Admin.types.BaseType();

Admin.types.I18nString.prototype.isModified = function(){
	if((this.internalObj == null || Admin.lib.isEmpty(this.internalObj)) && (this.originalObj == null || Admin.lib.isEmpty(this.originalObj)) ){
		return false;
	}
	// check all locales
	for(var i in ref.LOCALES){
		var locale = ref.LOCALES[i];
		if( (this.internalObj == null || this.internalObj[locale] == undefined || this.internalObj[locale]==null || this.internalObj[locale] == '') 
				&&
				(this.originalObj == null || this.originalObj[locale] == undefined || this.originalObj[locale]==null || this.originalObj[locale] == '')	
			){
			continue;
		}
				
		if(this.internalObj[locale] != this.originalObj[locale]){
			return true;
		};
	}
	return false;
		
};

/* =========================================================================================================================================================== */
/* TYPE I18nArea определяет тип ========================================================================================================================= */
/* =========================================================================================================================================================== */
Admin.types.I18nArea = function(_ob,_entityClass, _fieldName){
	Admin.types.I18nString.call(this,  _ob , _entityClass, _fieldName);
	this.type = 'I18nArea';
};
Admin.types.I18nArea.prototype = new Admin.types.I18nString();


/* =========================================================================================================================================================== */
/* TYPE Number определяет тип ========================================================================================================================= */
/* =========================================================================================================================================================== */
Admin.types.Integer = function(_ob,_entityClass, _fieldName){
	Admin.types.BaseType.call(this, Admin.lib.copyObject(_ob) , _ob , _entityClass, _fieldName, 'Integer');
};
Admin.types.Integer.prototype = new Admin.types.BaseType();

Admin.types.Integer.prototype.validate = function(){
	this.lastValidateResult = true;
		
	if(this.internalObj.toString().length > 0){
		// проверяем, что это число:
		if (!(/^-{0,1}\d+$/.test(this.internalObj))) {
			this.lastValidateResult = {error : Admin.render.errors.NOT_INTEGER}; 
			return this.lastValidateResult;
		}
		
		// проверяем максимальный размер
		var max = Admin.lib.model.max(this.entityClass,this.fieldName);
		if(parseInt(this.internalObj) > max){
			this.lastValidateResult = {error : Admin.render.errors.TOO_BIG, value: max}; 
			return this.lastValidateResult;
		};
		// проверяем минимальный размер
		var min = Admin.lib.model.min(this.entityClass,this.fieldName);
		if(parseInt(this.internalObj) < min){
			this.lastValidateResult = {error : Admin.render.errors.TOO_SMALL, value: min}; 
			return this.lastValidateResult;
		};
		
	}else{
		// проверяем на возможность нулевого значения
		if(!Admin.lib.model.isNullable(this.entityClass,this.fieldName)){
			this.lastValidateResult = {error : Admin.render.errors.REQUIRED}; 
			return this.lastValidateResult;
		}
		
	}

	
	return this.lastValidateResult;
};

/* =========================================================================================================================================================== */
/* TYPE Number определяет тип ========================================================================================================================= */
/* =========================================================================================================================================================== */
Admin.types.Float = function(_ob,_entityClass, _fieldName){
	Admin.types.BaseType.call(this, Admin.lib.copyObject(_ob) , _ob , _entityClass, _fieldName, 'Float');
};
Admin.types.Float.prototype = new Admin.types.BaseType();

Admin.types.Float.prototype.validate = function(){
	this.lastValidateResult = true;
	
	
	if(this.internalObj.length > 0){
		// проверяем, что это число:
		if (this.internalObj.match(/^[-\+]?\d+/) === null) {
			this.lastValidateResult = {error : Admin.render.errors.NOT_INTEGER}; 
			return this.lastValidateResult;
		}
		
		// проверяем максимальный размер
		var max = Admin.lib.model.max(this.entityClass,this.fieldName);
		if(parseInt(this.internalObj) > max){
			this.lastValidateResult = {error : Admin.render.errors.TOO_BIG, value: max}; 
			return this.lastValidateResult;
		};
		// проверяем минимальный размер
		var min = Admin.lib.model.min(this.entityClass,this.fieldName);
		if(parseInt(this.internalObj) < min){
			this.lastValidateResult = {error : Admin.render.errors.TOO_SMALL, value: min}; 
			return this.lastValidateResult;
		};
		
	}else{
		// проверяем на возможность нулевого значения
		if(!Admin.lib.model.isNullable(this.entityClass,this.fieldName) && this.internalObj.length == 0){
			this.lastValidateResult = {error : Admin.render.errors.REQUIRED}; 
			return this.lastValidateResult;
		}
		
	}

	
	return this.lastValidateResult;
};

/* =========================================================================================================================================================== */
/* TYPE Identifier определяет тип ========================================================================================================================= */
/* =========================================================================================================================================================== */
Admin.types.Identifier = function(_ob,_entityClass, _fieldName){
	Admin.types.BaseType.call(this, Admin.lib.copyObject(_ob) , _ob , _entityClass, _fieldName, 'Identifier');
};
Admin.types.Identifier.prototype = new Admin.types.BaseType();

/* =========================================================================================================================================================== */
/* TYPE Boolean определяет тип ========================================================================================================================= */
/* =========================================================================================================================================================== */
Admin.types.Boolean = function(_ob,_entityClass, _fieldName){
	var internal = _ob;
	Admin.types.BaseType.call(this, internal, _ob , _entityClass, _fieldName, 'Boolean');
};
Admin.types.Boolean.prototype = new Admin.types.BaseType();

/* =========================================================================================================================================================== */
/* TYPE ENUM определяет тип ========================================================================================================================= */
/* =========================================================================================================================================================== */
Admin.types.Enum = function(_ob,_entityClass, _fieldName){
	this.fDesc = null;
	if(model.CLASSES[_entityClass].fields[_fieldName]){
		this.fDesc = model.CLASSES[_entityClass].fields[_fieldName];
	}else{
		log.error('Дескриптор поля не задан! Класс: ' + _entityClass + ', поле: ' + _fieldName);
	}
	Admin.types.BaseType.call(this, Admin.lib.copyObject(_ob) , _ob , _entityClass, _fieldName, 'Enum');
};
Admin.types.Enum.prototype = new Admin.types.BaseType();

/* =========================================================================================================================================================== */
/* TYPE Alone определяет тип ========================================================================================================================= */
/* =========================================================================================================================================================== */
Admin.types.Alone = function(_ob,_entityClass, _fieldName, _mode){
	var internal = Admin.lib.copyObject(_ob);
	this.fDesc = null;
	if(model.CLASSES[_entityClass].fields[_fieldName]){
		this.fDesc = model.CLASSES[_entityClass].fields[_fieldName];
	}else{
		log.error('Дескриптор поля не задан для класса: ' + _entityClass + ', поле: ' + _fieldName);
	}
	
	Admin.types.BaseType.call(this, internal, _ob , _entityClass, _fieldName, 'Alone', _mode);
};
Admin.types.Alone.prototype = new Admin.types.BaseType();

/*
 * Установка значения по идентификатору снаружи, используется при наполнении данных из DOM-модели
 * _id - идентификатор
 */
Admin.types.Alone.prototype.setId = function(_id){
	var isIdentifier = Admin.lib.model.isIdentifier(this.entityClass , this.fieldName);
	if(isIdentifier){
		this.internalObj = _id;
	}else{
		log.warn('Admin.types.Alone.prototype.setId() : случай для НЕ ИДЕНТИФИКАТОРОВ еще не реализован !');
	}
};

Admin.types.Alone.prototype.isModified = function(){
	var isIdentifier = Admin.lib.model.isIdentifier(this.entityClass , this.fieldName);
	if(isIdentifier){
		if((this.internalObj == null || this.internalObj.toString() == '') && (this.originalObj == null || this.originalObj.toString() == '')){
			return false;
		}
		return this.internalObj != this.originalObj;
	}else{
				
		// если ОБЪЕКТЫ не нулевые , то сравниваем их идентификаторы
		if(this.internalObj != null && this.originalObj != null){
			return this.internalObj.id != this.originalObj.id;
		}
		
		// если оба нулеыве, то БЕЗ ИЗМЕНЕНИЙ
		if(this.internalObj == null && this.originalObj == null){
			return false;
		}
		
		// если они разные, то модифицировыны 
		return true;
	}
	
};

/* =========================================================================================================================================================== */
/* TYPE Set определяет тип ========================================================================================================================= */
/* =========================================================================================================================================================== */
Admin.types.Set = function(_ob,_entityClass, _fieldName, _mode){
	
	if(_entityClass == undefined){
		return;
	}
	
	/* внутренний объект */
	var internal = Admin.lib.copyObject(_ob);
	
	/* дескриптор поля */
	this.fDesc = null;
	
	if(model.CLASSES[_entityClass] == undefined){
		log.error('Не определен тип ' + _entityClass);
		return;
	}
	
	if(model.CLASSES[_entityClass].fields == undefined){
		log.error('Не определены поля для типа ' + _entityClass);
		return;
	}
	
	if(model.CLASSES[_entityClass].fields[_fieldName]){
		this.fDesc = model.CLASSES[_entityClass].fields[_fieldName];
	}else{
		alert('Дескриптор поля не задан для класса: ' + _entityClass + ', поле: ' + _fieldName);
	}
	
	/* массив добавленных значений при редактировании */
	this.addedAr = new Array();
	/* массив удаленных значений при редактировании */
	this.removedAr = new Array();
	
	Admin.types.BaseType.call(this, internal, _ob , _entityClass, _fieldName, 'Set', _mode);
};
Admin.types.Set.prototype = new Admin.types.BaseType(null);

/* =========================================================================================================================================================== */
/*
 * Запрашивает объекты по имеющимся идентификаторам в объекте this.internalObj
 */
Admin.types.Set.prototype.getContextDataArray = function (){
	if(this.fDesc.isIdentifier){
		var contextDataArray = new Array();
		var rawDataArray = null;
		// получаем контроллер
		var controller = controllerManager.getController(this.fDesc.type);
		// проверяем параметр paged
		if(Admin.lib.model.pagedLoading(this.fDesc.type)){
			// запрашиваем по одной записи
			for(var i in this.internalObj){
				controller.loadRec(this.internalObj[i]);
				var d = controller.getRec();
				if(d){
					contextDataArray[contextDataArray.length] = d;
				}
			}
			
		}else{
			
			// запрашиваем сразу все данные
			rawDataArray = controller.getData();
			for(var i in this.internalObj){
				for(var j in rawDataArray){
					if(this.internalObj[i] == rawDataArray[j].id){
						contextDataArray[contextDataArray.length] = rawDataArray[j]; 
					}
				}
			}
			// добавляем из addedAr , то есть те, которые добавились пользователем
			for(var i in this.addedAr){
				for(var j in rawDataArray){
					if(this.addedAr[i] == rawDataArray[j].id){
						contextDataArray[contextDataArray.length] = rawDataArray[j]; 
					}
				}
			}
			
		}
		return contextDataArray;
		
	}else{
		return this.internalObj;
	}
	
};

Admin.types.Set.prototype.onAddElement = function(_id){
	if(this.fDesc.isIdentifier){
		
		// удаляем его из удаленных , если есть
		if(this.removedAr.indexOf(_id) >=0){
			this.removedAr.splice(this.removedAr.indexOf(_id), 1);
			return true;
		}
		
		// добавляем в наш список поступивший идентификатор, если его нет
		if(this.internalObj.indexOf(_id) < 0 && this.addedAr.indexOf(_id) < 0){
			this.addedAr.push(_id);
			return true;
		}
				
	}else{
		alert('Not realized ! Admin.types.Set.prototype.onSelectElement');
	}
	return false;
};

Admin.types.Set.prototype.onRemoveElement = function(_id){
	var needRefresh = false;
	if(this.fDesc.isIdentifier){
						
		// пробуем удалить среди тех, которые добавились
		if(this.addedAr.indexOf(_id) >= 0){
			this.addedAr.splice(this.addedAr.indexOf(_id), 1);
			needRefresh = true;
			
		// удаляем или добавляем среди имеющихся, если есть...	
		}else {
			if(this.internalObj.indexOf(_id) >= 0){
				if(this.removedAr.indexOf(_id) >= 0){
					this.removedAr.splice(this.removedAr.indexOf(_id),1);
				}else{
					this.removedAr.push(_id);
				}
				needRefresh = true;
			}
		}
	}else{
		alert('Not realized ! Admin.types.Set.prototype.onRemoveElement');
	}
	return needRefresh;
};

/* =========================================================================================================================================================== */
/* TYPE List определяет тип ========================================================================================================================= */
/* =========================================================================================================================================================== */
Admin.types.List = function(_ob,_entityClass, _fieldName, _mode){
	Admin.types.Set.call(this, _ob , _entityClass, _fieldName, _mode);
	
	if(_ob == null){
		this.internalObj = new Array();
	}
	
	this.type = 'List';
};
Admin.types.List.prototype = new Admin.types.Set();

