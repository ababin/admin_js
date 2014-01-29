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
/* === R E N D E R E R   ====================================================================================================================================== */
if(typeof Admin == 'undefined'){Admin = {};}


/* ------------------------------------------------------------------------------------------------------------------------------------- */
/* ------------------------------------------------------------------------------------------------------------------------------------- */
/* ------------------------------------------------------------------------------------------------------------------------------------- */
/* ------------------------------------------------------------------------------------------------------------------------------------- */
/* ------------------------------------------------------------------------------------------------------------------------------------- */
/* ------------------------------------------------------------------------------------------------------------------------------------- */
/* ------------------------------------------------------------------------------------------------------------------------------------- */
/* ------------------------------------------------------------------------------------------------------------------------------------- */
/* ------------------------------------------------------------------------------------------------------------------------------------- */
/* ----------------------- УТИЛИТЫ ДЛЯ КОНТЕКСТНОЙ ТАБЛИЦЫ ----------------------------------------------------------------------------- */
/* ------------------------------------------------------------------------------------------------------------------------------------- */
/* ------------------------------------------------------------------------------------------------------------------------------------- */
/* ------------------------------------------------------------------------------------------------------------------------------------- */
/* ------------------------------------------------------------------------------------------------------------------------------------- */
/* ------------------------------------------------------------------------------------------------------------------------------------- */
/* ------------------------------------------------------------------------------------------------------------------------------------- */
/* ------------------------------------------------------------------------------------------------------------------------------------- */
/* ------------------------------------------------------------------------------------------------------------------------------------- */
Admin.utils = {};
Admin.utils.table = {};
Admin.utils.table.Context = {};

Admin.utils.table.Context.prepareTHEAD = function(_vtObject , _fieldsCount, _mode){
	var caption = Admin.lib.getLocaleMes(_vtObject.entityClass + '.' + _vtObject.fieldName);
		
	// готовим кнопки
	var btns = '';
	
	// если НЕ чтение то:
	if('RM'.indexOf(_mode) < 0){
		
		// если сущность - композиция, то конопчку ДОБАВИТЬ, иначе конопочку РЕДАКТИРВОАТЬ
		var isComposition = Admin.lib.model.isComposition(_vtObject.entityClass,_vtObject.fieldName);
		
		if(isComposition){
			btns += '<i title="Добавить" class="glyphicon glyphicon-plus-sign" onclick="viewManager.modifyCollectionFor(\'' + _vtObject.entityClass + '\', \'' + _vtObject.fieldName + '\')"></i>';
		}else{
			btns += '<i title="Редактировать" class="glyphicon glyphicon-edit" onclick="viewManager.modifyCollectionFor(\'' + _vtObject.entityClass + '\', \'' + _vtObject.fieldName + '\')"></i>';
		}
				
	}
	
	if(btns.length > 0){
		btns = '<td class="for-icon">' + btns + '</td>';
	}
	
	/* если данных нет, то возвращаем пустую таблицу с заголовком и кнопками */
	if(_fieldsCount == 0){
		return '<thead><tr><td>' + caption + '</td>' + btns +'</tr></thead>';
	}
	
	/* если данные есть... */
	var realFieldsCount = _fieldsCount;
	if('RM'.indexOf(_mode) < 0){
		realFieldsCount += 1;
	}
	return '<thead><tr><td colspan="' + realFieldsCount + '">' + caption + '</td>' + btns + '</tr></thead>';
};


Admin.utils.table.Context.prepareFieldCaptionsTR = function(_fields,_type, _mode){
	var res = '';
	
	/* если данных нет */
	if(_fields.length == 0){
		return res;
	}
	
	/* если данные есть */
	
	// для порядкового номера записи 				
	res += '<tr><th>#</th>';
	
	// поля
	for(var f in _fields){
		if(typeof _fields[f] == 'string'){
			res += '<th>' + Admin.lib.getLocaleMes(_type + '.' + _fields[f]) + '</th>';
		}else{
			var fDescriptor = _fields[f];
			res += '<th>' + Admin.lib.getLocaleMes(_type + '.' + fDescriptor.name) + '</th>';
		}
	}
	
	// если НЕ ЧТЕНИЕ, то добавляем колонку для кнопок редактирования и удаления
	if('CMU'.indexOf(_mode) >= 0){
		res += '<th></th>';
	}
		
	return res + '</tr>';
};


/* prepare data row for table */
Admin.utils.table.Context.prepareDataRow = function(_rowCounter , _vtObject, _ob, _mode){
	
	// тип объекта
	var type = _ob.c || _vtObject.fDesc.type;
	
	// поля для вывода
	var fields = Admin.lib.getObjectByPriority('model.CLASSES.' + type + '.contextTable.' + _vtObject.entityClass + '.fields', 'model.CLASSES.' + type + '.contextTable.common.fields');
	
	// является ли объект композицией ---------------------
	var isComposition = Admin.lib.model.isComposition(_vtObject.entityClass, _vtObject.fieldName);
			
	// нужно ли подгружать данные --------------------------
	var isDetached = Admin.lib.model.isDetached(_vtObject.entityClass, _vtObject.fieldName);
		
	// является ли идентификатором
	var isIdentifier = Admin.lib.model.isIdentifier(_vtObject.entityClass, _vtObject.fieldName);
			
	/* определяем, является ли запись новой */
	var isNew = false;
	if(_vtObject.addedAr.indexOf(_ob.id) >= 0){
		isNew = true;
	}
	var isRemoved = false;
	if(_vtObject.removedAr.indexOf(_ob.id) >= 0){
		isRemoved = true;
	}
			
	/* класс стиля */
	var trClass = 'data-mode-' + _mode;
	//--------------------------------------------
	
	if(isNew){
		trClass += ' rec-added';
	}else if(isRemoved){
		trClass += ' rec-removed';
	}
	
	// готовим метод onclick
	var onclick = '';
	/* если R, то  */
	switch(_mode){
	case 'R' : 
	case 'C' : 
	case 'U' : onclick = ' onclick="viewManager.' + (isDetached && !isIdentifier ? 'showDetachedRecord' : 'showRecord') + '(\'' + type + '\', ' + _ob.id + ',\'R\')"'; break;
	case 'M' : onclick = ' onclick="jms.notify(\'' + jms.events.onRemoveElement + '\', ' +  (typeof _ob.id == 'string' ? '\'' + _ob.id + '\'' : _ob.id)  + ')"'; break;	
	}
	//-------------------------------------------
	
	var res = '<tr class="' + trClass + '"'+ onclick + '><td>' + _rowCounter +'</td>';
		
	for(var f in fields){
		if(typeof fields[f] == 'string'){
			res += '<td>' + _ob[fields[f]] + '</td>';
		}else{
			var fDescriptor = fields[f];
			
			var calcValue = '';
			if(!fDescriptor.val){
				alert('Не задано выражение для вычисления поля ' + fDescriptor.name);
			}else{
				calcValue = Admin.lib.calcEvalForObject(_ob, fDescriptor.val);
			}
			res += '<td>' + calcValue + '</td>';
		}
	}
	
	// готовим кнопки
	var btns = '';
	
	// 1. кнопка РЕДАКТИРОВАТЬ
	// если:  isComposition
	if('U'.indexOf(_mode)>=0 && isComposition){
		btns += '<i class="glyphicon glyphicon-edit" title="редактировать" onclick="viewManager.' + (isDetached && !isIdentifier ? 'showDetachedRecord' : 'showRecord') + '(\'' + type + '\', ' + _ob.id + ',\'U\')"></i>';
	}
	
	// 2. кнопка УДАЛИТЬ
	// если: создание или редактирование
	if('CU'.indexOf(_mode) >= 0){
		if(isComposition){
			btns += '<i class="glyphicon glyphicon-remove" title="удалить" onclick="viewManager.deleteRecord(\'' + type + '\', ' + _ob.id+')"></i>';
		}
		
	}else if(_mode == 'M'){
		//btns += '<i class="glyphicon glyphicon-remove" title="удалить" onclick="viewManager.deleteRecord(\'' + type + '\', ' + _ob.id+')"></i>';
	}
	
	// оборачиваем
	if(btns.length > 0){
		btns = '<td class="for-icon">' + btns + '</td>';
	}
		
	res += btns + '</tr>';
	return res;
};






Admin.render = {
				
	/*
	 * рендеринг 
	 */
	doRendering : function(_vtObject, _mode, _locale){
		switch(_vtObject.type){
		case 'String' : return this.doRenderingForString(_vtObject, _mode);
		case 'I18nString' : return this.doRenderingForI18nString(_vtObject, _mode, _locale);
		case 'I18nArea' : return this.doRenderingForI18nArea(_vtObject, _mode, _locale);
		case 'Integer' : return this.doRenderingForString(_vtObject, _mode);
		case 'Identifier' : return this.doRenderingForIdentifier(_vtObject, _mode);
		case 'Boolean' : return this.doRenderingForBoolean(_vtObject, _mode);
		case 'Enum' : return this.doRenderingForEnum(_vtObject, _mode);
		case 'Alone' : return this.doRenderingForAlone(_vtObject, _mode);
		case 'Set' : return this.doRenderingForSet(_vtObject, _mode);
		case 'List' : return this.doRenderingForSet(_vtObject, _mode);
		default : log.error('Не удалось сделать рендеринг для неизвестного типа: ' + _vtObject.type); return '';
		}
	},
	
	getRequiredSpan : function(_vtObject){
		return Admin.lib.model.isNullable(_vtObject.entityClass,_vtObject.fieldName) ? '' : '<span class="required">*</span>'; 
	},
	
	getUndefinedSpan : function(){
		return '<span class="undefined">не определено</span>';
	},
	
	/*
	 * рендеринг для String
	 */
	doRenderingForString : function(_vtObject , _mode){
		switch(_mode){
		case 'R' : 	var v = _vtObject.internalObj == null ? this.getUndefinedSpan() : '<span>' + Admin.lib.echoString(_vtObject.internalObj) + '</span>';
					return '<div class="clearfix">' + 
								'<label class="for-form">' + Admin.lib.getLocaleMes(_vtObject.entityClass + "." + _vtObject.fieldName) + '</label>' +
								v + 
							'</div>';
		case 'U' :
		case 'C' : 	var v = _vtObject.internalObj == null ? '' : Admin.lib.echoString(_vtObject.internalObj);
			        var id = _vtObject.entityClass + '_' + _vtObject.fieldName;
					var validateString = '';
					var isValidateError = !(_vtObject.lastValidateResult === true);
					var validateErrorClass = '';
					if(isValidateError){
						validateString = '<div class="validate-error">' + Admin.lib.getLocaleMes('error.' +  _vtObject.lastValidateResult.error) + '</div>';
						validateErrorClass = ' class="validate-error"';
					}
		 			return '<div class="clearfix' +  validateErrorClass  + '">' + 
								'<label class="for-form'+validateErrorClass+'" for="' + id + '">' + Admin.lib.getLocaleMes(_vtObject.entityClass + "." + _vtObject.fieldName) + this.getRequiredSpan(_vtObject) + '</label>' +
								'<input'+validateErrorClass+' type="text" id="' + id + '" value="' + v + '">'+
								validateString + 
							'</div>'; 
		}
	},
	
	/*
	 * рендеринг для I18nString
	 */
	doRenderingForI18nString : function(_vtObject , _mode, _locale){
		if(!_locale){
			_locale = settings.locale;
		}
		var val = (_vtObject.internalObj[_locale]) ? _vtObject.internalObj[_locale] : '';
		switch(_mode){
		case 'R' : 	return '<div class="clearfix">' + 
							'<label class="for-form">' + Admin.lib.getLocaleMes(_vtObject.entityClass + "." + _vtObject.fieldName) + '</label>' + 
							'<span>' +  Admin.lib.echoString(val) +'</span>' +	
							'</div>';
		case 'U' :
		case 'C' : 	var id = _vtObject.entityClass + '_' + _vtObject.fieldName + '_' + _locale;
					return '<div class="clearfix">' + 
							'<label class="for-form" for="' + id + '">' + Admin.lib.getLocaleMes(_vtObject.entityClass + "." + _vtObject.fieldName) + this.getRequiredSpan(_vtObject) + '</label>' +
							'<input type="text" id="' + id + '" value="' + val + '">'+  	
							'</div>';
		}
	},
	
	/*
	 * рендеринг для I18nArea
	 */
	doRenderingForI18nArea : function(_vtObject , _mode, _locale){
		if(!_locale){
			_locale = settings.locale;
		}
		var val = (_vtObject.internalObj[_locale]) ? _vtObject.internalObj[_locale] : '';
		switch(_mode){
		case 'R' : 	return '<div class="clearfix">' + 
							'<label class="for-form">' + Admin.lib.getLocaleMes(_vtObject.entityClass + "." + _vtObject.fieldName) + '</label>' +  
							'<span>' + Admin.lib.echoString(val) +'</span>' +
							'</div>';
		case 'U' :
		case 'C' : 	var id = _vtObject.entityClass + '_' + _vtObject.fieldName + '_' + _locale;
					return '<div class="clearfix">' + 
							  '<label class="for-form" for="' + id + '">' + Admin.lib.getLocaleMes(_vtObject.entityClass + "." + _vtObject.fieldName) + this.getRequiredSpan(_vtObject) + '</label>' +
							  '<textarea id="' + id + '">'+ val + '</textarea>' +  	
							'</div>';
		}
	},
	
	
	/*
	 * рендеринг для Identifier
	 */
	doRenderingForIdentifier : function(_vtObject , _mode){
		var val = _vtObject.internalObj == null ? '' : _vtObject.internalObj;
		var caption = Admin.lib.getLocaleMes(_vtObject.entityClass + "." + _vtObject.fieldName);
		switch(_mode){
		case 'R' : 	return '<div class="clearfix">' + 
								'<label class="for-form">' + caption + '</label>' +
								'<span class="disabled">' + val + '</span>'+
							'</div>';
		case 'U' :
		case 'C' : 	var id = _vtObject.entityClass + '_' + _vtObject.fieldName;
					return '<div class="clearfix">' + 
								'<label class="for-form" for="' + id + '">' + caption + this.getRequiredSpan(_vtObject) + '</label>' +
								'<input type="text" id="' + id + '" value="' + val + '">'+
							'</div>'; 
		}
	},
	
	/*
	 * рендеринг для Boolean
	 */
	doRenderingForBoolean : function(_vtObject , _mode){
		var caption = Admin.lib.getLocaleMes(_vtObject.entityClass + "." + _vtObject.fieldName);
		switch(_mode){
		case 'R' : 	var val = this.internalObj ? Admin.lib.getLocaleMes("Boolean.true") : Admin.lib.getLocaleMes("Boolean.false");
					return '<div class="clearfix"><label class="for-form">' + caption + '</label><span>' + val + 	'</span></div>'; 	
		case 'U' :
		case 'C' :	var id = _vtObject.entityClass + '_' + _vtObject.fieldName;
					var checked = _vtObject.internalObj ? ' checked' : ''; 
					return '<div class="clearfix"><label class="for-form" for="'+id+'">' + caption + this.getRequiredSpan(_vtObject) + '</label>' +
								'<input type="checkbox" id="' + id + '"' + checked + '>' +
							'</div>'; 
			
		}
	},
	
	/*
	 * рендеринг для Boolean
	 */
	doRenderingForEnum : function(_vtObject , _mode){
		var type = _vtObject.fDesc.type.substring(2);	
		switch(_mode){
		case 'R' : 	return '<div class="clearfix">' + 
								'<label class="for-form">' + Admin.lib.getLocaleMes(_vtObject.entityClass + "." + _vtObject.fieldName) + '</label>' +
								'<span>' + Admin.lib.getLocaleMes(type + '.' + _vtObject.internalObj)+ '</span>' +
							'</div>';
		case 'U' :
		case 'C' : 	var id = _vtObject.entityClass + '_' + _vtObject.fieldName;
					var res = '<select class="combobox" id="' + id + '">';
					
					if(Admin.lib.model.isNullable(_vtObject.entityClass,_vtObject.fieldName)){
						res += '<option value=""></option>';
					}
					
					var selected = '';
					for(var i=0; i< model.ENUMS[type].length; i++){
						if(model.ENUMS[type][i] == _vtObject.internalObj){
							selected = ' selected';
						}else{
							selected = '';
						};
						res += '<option value="' + model.ENUMS[type][i] + '"' + selected + '>'+Admin.lib.getLocaleMes(type + '.' + model.ENUMS[type][i])+'</option>';
					}
					res += '</select>';
					return '<div class="clearfix">' + 
								'<label class="for-form" for="' + id + '">' + Admin.lib.getLocaleMes(_vtObject.entityClass + "." + _vtObject.fieldName) + this.getRequiredSpan(_vtObject) + '</label>' +
								res +
							'</div>'; 
		}
	},
	
	
	/*
	 * рендеринг для Alone
	 */
	doRenderingForAlone : function(_vtObject, _mode){
		var resultVal = null;
		if(_vtObject.fDesc.isIdentifier){
			// если в качестве данных принимается идентификатор, то необходимо получить данные
			var controller = controllerManager.getController(_vtObject.fDesc.type);
			var data = controller.getData();
			// находим с соответствующим идентификатором
			var needVal = null;
			for(var i in data){
				if(data[i].id == _vtObject.internalObj){
					// нашли
					needVal = data[i];
					break;
				}
			}
			if(needVal != null){
				// получем поля, которые нужно выводить:
				var field = model.CLASSES[_vtObject.fDesc.type].contextValue[_vtObject.entityClass] || model.CLASSES[_vtObject.fDesc.type].contextValue.common;
				if(!field){
					alert("Не могу найти дескриптор contextRecord для " + _vtObject.fDesc.type);
					return '';
				}
				resultVal = Admin.lib.calcEvalForObject(needVal, field.val);
			}else{
				log.warn("Admin.render.doRenderingForAlone() : Значение не найдено! Сущность: " + _vtObject.fDesc.type + " идентификатор:" + _vtObject.internalObj );
				//resultVal = this.getUndefinedSpan();
			}
		}
			
		var caption = Admin.lib.getLocaleMes(_vtObject.entityClass + "." + _vtObject.fieldName);
		switch(_mode){
		case 'R' : 	return '<div class="clearfix"><label class="for-form">' + caption + '</label>' + 
							(resultVal == null ? this.getUndefinedSpan() : '<span>' + resultVal + '</span>') + '</div>'; 	
		case 'U' :	
		case 'C' :	// если данная сущность paged, то необходимо выводить кнопочку, через которую показывается форма
					// форма с выбором нужного значения
					// Если НЕ PAGED , то выводить просто в виде комбобокса, по аналогии с типом enum
					var paged = Admin.lib.model.pagedLoading(_vtObject.fDesc.type);
										
					if(paged){
						// формируем кнопку для возможности задания значения
					
					}else{
						// Список конечный ! формируем значение для СЕЛЕКТОРА !!!
						var id = _vtObject.entityClass + '_' + _vtObject.fieldName;
						var res = '<select class="combobox" id="' + id + '">';
						
						// поддерживается ли пустое значение
						var nullable = Admin.lib.model.isNullable(_vtObject.entityClass,_vtObject.fieldName);
						
						if(nullable){
							res += '<option value=""></option>';
						}
											
						// определяем ЧЕМ является объект.
						var isId = false;
						if(_vtObject.fDesc.isIdentifier != undefined){
							isId = _vtObject.fDesc.isIdentifier;
						}
						
						// достаем коллекцию из контроллера:
						var ctrl = controllerManager.getController(_vtObject.fDesc.type);
						// загружаем данные 
						var datas = ctrl.getData();
						
						
						var selected = '';
						for(var i in datas){
							var curRec = datas[i];
							selected = '';						
							
							if(isId){
								if(curRec.id == _vtObject.internalObj){
									selected = 'selected';
								}
							}else{
								if(curRec.id == _vtObject.internalObj.id){
									selected = 'selected';
								}
							}
							var evalString = Admin.lib.getObjectByPriority('model.CLASSES.'+_vtObject.fDesc.type+'.contextValue.'+_vtObject.entityClass+'.val','model.CLASSES.'+_vtObject.fDesc.type+'.contextValue.common.val');
							res += '<option value="' +  curRec.id + '" ' + selected + '>'+Admin.lib.calcEvalForObject(curRec, evalString)+'</option>';
							
						}
						res += '</select>';
						return '<div class="clearfix">' + 
									'<label class="for-form" for="' + id + '">' + Admin.lib.getLocaleMes(_vtObject.entityClass + "." + _vtObject.fieldName) + this.getRequiredSpan(_vtObject) + '</label>' +
									res +
								'</div>'; 
						
					}
									
			
		}
	},
	
	/*
	 * Рендеринг для SET
	 */
	doRenderingForSet : function(_vtObject,_mode){
		
		// набор если он композиционный, то в режиме СОЗДАНИЯ не формируем
		if(Admin.lib.model.isComposition(_vtObject.entityClass, _vtObject.fieldName) && _mode == 'C'){
			return '';
		}
		//------------------------
		
		var _datas = _vtObject.getContextDataArray();
		_datas = _datas || new Array();
		var res ='<table class="commonTable">';
		
		/* если данных нет */
		if(Admin.lib.isEmpty(_datas)){
			 res += Admin.utils.table.Context.prepareTHEAD(_vtObject , 0 , _mode) + 
			 		Admin.utils.table.Context.prepareFieldCaptionsTR(new Array() , _mode) +
				  '</table>';
			return res;	  
		}
			
		/* Если данные есть */
		var isPreparedCaption = false;
		var rowCounter = 1;
		for(var index in _datas){
			ob = _datas[index];
			    		
			/* изначально тип берем из дескриптора поля  */
			var type = _vtObject.fDesc.type;
			
			/* если в данных пришло название класса сущности, то его и берем в качестве типа */
			if(ob.c != undefined){
				type = ob.c;
			}
			
			if(model.CLASSES[type].contextTable == undefined){
				log.error("Не определена contextTable для" + type);
				return '';
			}
			
			var fields = Admin.lib.getObjectByPriority('model.CLASSES.' + type + '.contextTable.' + _vtObject.entityClass + '.fields', 'model.CLASSES.' + type + '.contextTable.common.fields');
			
			if(!isPreparedCaption){
				res += 	Admin.utils.table.Context.prepareTHEAD(_vtObject , fields.length , _mode) +
						Admin.utils.table.Context.prepareFieldCaptionsTR(fields, type, _mode);
				isPreparedCaption = true;
			}
							
			res += Admin.utils.table.Context.prepareDataRow(rowCounter , _vtObject , ob, _mode);
			
			rowCounter ++;
		}
		
		res += '</table>';
		
		return res;
		
	},
	
	/*
	 * Fill data in vtObject from DOM
	 */
	fillData : function(_vtObject){
		if(['Identifier','Integer','String','Enum'].indexOf(_vtObject.type) >= 0){
			var id = _vtObject.entityClass + '_' + _vtObject.fieldName;
			_vtObject.internalObj = $('#'+id).val();
			return;
		};
		if(_vtObject.type == 'Boolean'){
			var id = _vtObject.entityClass + '_' + _vtObject.fieldName;
			_vtObject.internalObj = $('#'+id).is(':checked');
			return;
		};
		if(['I18nString','I18nArea'].indexOf(_vtObject.type) >= 0){
			var val = {};
			for(var i in ref.LOCALES){
				var id = _vtObject.entityClass + '_' + _vtObject.fieldName + '_' + ref.LOCALES[i];
				val[ref.LOCALES[i]]= $('#'+id).val();
			}
			_vtObject.internalObj = val;
			return;
		};
		if(_vtObject.type == 'Alone'){
			var id = _vtObject.entityClass + '_' + _vtObject.fieldName;
			_vtObject.setId($('#'+id).val());
		};
				
		
		
		
	},
	
};

Admin.render.errors = {
		REQUIRED:'requiredError',
		NOT_INTEGER:'notIntegerError',
		TOO_LONG : 'tooLongError',
		TOO_SHORT : 'tooShortError',
		TOO_BIG : 'tooBigError',
		TOO_SMALL : 'tooSmallError',
		
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

Admin.table = {};
Admin.table.Context = {};

Admin.table.Main = {};

/* =========================================================================================================================================================== */
/* создает основную таблицу !!! */
Admin.table.Main.create = function(_controller, _pageNum){
	
	/* получаем поля, которые необходимы для отображения */
	var fields = model.CLASSES[_controller.entityClass].table.fields;
	
	/* кнопка добавления новой сущности */
	var addButton = _controller.havePermission('C') ? '<i title="СОЗДАТЬ НОВУЮ ЗАПИСЬ" class="glyphicon glyphicon-plus-sign icon-for-header" onclick="viewManager.showRecord(\'' + _controller.entityClass + '\', null, \'C\', true)"></i>' : '';  
	
	var d = '<h2>' + Admin.lib.getLocaleMes(_controller.entityClass + '.plural') + addButton  + '<i title="ОБНОВИТЬ ДАННЫЕ" class="glyphicon glyphicon-refresh icon-for-header" onclick="viewManager.refresh(\'' + _controller.entityClass + '\', null, \'C\')"></i>' +  '</h2>';
	
	d += '<table class="table table-hover table-striped table-main-customize"><tr>';

	
	// column sizes
	var columnSizes = model.CLASSES[_controller.entityClass].table.columnSizes == undefined ? null : model.CLASSES[_controller.entityClass].table.columnSizes ; 
		
	// prepare header
	var size = '';
	var fieldName = '';
	var fieldObject = '';
	for(var i in fields){
		fieldObject = fields[i];
		fieldName = typeof fieldObject == 'string' ? fieldObject : fieldObject.name; 
		// check size
		if(columnSizes != null && columnSizes[fieldName] != undefined){
			size = ' width="' + columnSizes[fieldName] +'"';
		}else{
			size = '';
		}
		
		d += '<th' + size+'>' + Admin.lib.getLocaleMes(_controller.entityClass + '.' + fieldName) + '</th>';
	}
	
	var footerAdd = '';
	var footerAddCounter = 0;
	// поле для обновления
	if(_controller.havePermission('U')){
		d += '<th>U</th>';
		footerAdd += '<td></td>';
		footerAddCounter ++;
	}
	if(_controller.havePermission('D')){
		d += '<th>D</th>';
		footerAdd += '<td>=</td>';
		footerAddCounter ++;
	}

	d += '</tr>';

	// prepare data
	var data = _controller.getData(_pageNum);
	for(var i in data){
		rec = data[i];
		d += '<tr id="' + _controller.entityName + '_record_' + rec.id + '">';
		for(var j in fields){
			var v = Admin.table.getValue(_controller, rec, fields[j]);
			v = v == null ? Admin.render.getUndefinedSpan() : Admin.lib.echoString(v);
			d += '<td class="pointed" onclick="viewManager.showRecord(\'' + 
			_controller.entityClass + '\', ' + rec.id + ',\'R\', true)">' +  
			     v  + '</td>';	
		}
		
		// дополнительные кнопки
		if(_controller.havePermission('U')){
			d += '<td><i class="glyphicon glyphicon-edit pointed" onclick="viewManager.showRecord(\'' + _controller.entityClass + '\', ' + rec.id + ',\'U\',true)"></i></td>';
		}
		if(_controller.havePermission('D')){
			d += '<td><input type="checkbox" id="' + _controller.entityClass + '_delete_' + rec.id + '"></td>';
		}

		d += '</tr>';
	}
	
	// prepare footer
	d += '<tr class="footer"><td colspan="' + fields.length + '"></td>' + footerAdd + '</tr>';
	
	// prepare paginator
	d += Admin.utils.table.Main.preparePaginatorTR(_controller, fields.length + footerAddCounter);
	

	d += '</table>';
	
	return d;
};


/* ===================================================================================================================================== */
/* ===================================================================================================================================== */
/* ===================================================================================================================================== */
/* ===================================================================================================================================== */
/* ===================================================================================================================================== */
/* ===================================================================================================================================== */
/* ===================================================================================================================================== */
/* ===================================================================================================================================== */
/* ===================================================================================================================================== */
/* ===================================================================================================================================== */



/*
 * Создает таблицу контекстной таблицы (отображается при полном просмотре родительской сущности.
 * В этом случае все зависимости с типом 'set' будут отображаться как контекстные таблицы )
 * _controller - контроллер, через который загружаем данные
 * _pageNum - номер загружаемой страницы
 * _eventName - название события, которое генирируется при выборе соответсвующей записи 
 */
Admin.table.Context.createColumn2 = function(_controller, _pageNum){
	/* получаем поля, которые необходимы для отображения */
	if(model.CLASSES[_controller.entityClass].table == undefined){
		log.error("Не заданы данные по таблице для сущности " + _controller.entityClass + ' (model.CLASSES.' + _controller.entityClass + '.table');
		return "";
	}else if(model.CLASSES[_controller.entityClass].table.fields == undefined){
		log.error("Не заданы данные по таблице для сущности " + _controller.entityClass + ' (model.CLASSES.' + _controller.entityClass + '.table.fields');
		return "";
	}
	
	var fields = model.CLASSES[_controller.entityClass].table.fields;
	
	var d = '<span>' + Admin.lib.getLocaleMes('reference') + '</span>';
	
	d += '<table class="commonTable"><tr>';

	// prepare header
	for(var i in fields){
		var fieldObject = fields[i];
		d += '<th>' + Admin.lib.getLocaleMes(_controller.entityClass + '.' +  (typeof fieldObject == 'string' ? fieldObject : fieldObject.name)) + '</th>';
	}
		
	d += '</tr>';
	
	// prepare data
	var data = _controller.getData(_pageNum);
	for(var i in data){
		rec = data[i];
		d += '<tr id="' + _controller.entityName + '_record_' + rec.id + '">';
		for(var j in fields){
			var v = Admin.table.getValue(_controller, rec, fields[j]);
			v = v == null ? Admin.render.getUndefinedSpan() : Admin.lib.echoString(v);
			
			d += '<td class="pointed" onclick="jms.notify(\'' + jms.events.onSelectElement + '\' , ' + (typeof rec.id == 'string' ? '\'' + rec.id + '\'' : rec.id) + ')">' + 	v  + '</td>';	
		}
				
		d += '</tr>';
	}
	
	// prepare footer
	d += '<tr class="footer"><td colspan="' + fields.length + '"></td></tr>';
	
	// prepare paginator if need
	if(Admin.lib.model.pagedLoading(_controller.entityClass) == true){
		d += Admin.utils.table.Main.preparePaginatorTR(_controller, fields.length);
	}

	d += '</table>';
	
	return d;
};


//======================================================================================  
Admin.table.getValue = function(_controller, _record, _fieldName){
		
	var value = '';
	    	
	if(typeof _fieldName == 'string'){
		
		// check type
    	if(model.CLASSES[_controller.entityClass].fields[_fieldName] == undefined){
    		alert('Поле ' + _fieldName + ' для сущности ' + this.entityClass + ' не определено');
    		return '$undefinedField$';
    	}
		
		value =  _record[_fieldName];
    	
		// check null
    	if(value == null){
    		return null;
    	}
		
		var field = model.CLASSES[_controller.entityClass].fields[_fieldName];
    	if(field.type == 't:Identifier' || field.type == 't:String' || field.type == 't:Integer'){
    		return value.toString();
    	}
    	// i18n
    	if(field.type == 't:I18nString' || field.type == 't:I18nArea'){
    		return value[settings.locale];
    	}
    	
    	// Boolean
    	if(field.type == 't:Boolean'){
    		if(value == null){
    			return '';
    		}else{
    			if(value === true){
    				return Admin.lib.getLocaleMes('Boolean.true');
    			}else{
    				return Admin.lib.getLocaleMes('Boolean.false');
    			}
    		}
    	}
    	
    	// check empty
    	if(value.toString().length == 0){
    		return '';
    	}
    	      
    	// find for ENUM
    	if(model.ENUMS[field.type]){
    		return Admin.lib.getLocaleMes(field.type + '.' + value);
    	}
	}else{
		/* если поле представлено в виде объекта */
		value =  Admin.lib.calcEvalForObject(_record, _fieldName.val);
	}
	
	return value;
	
  
  
};




Admin.utils.table.Main = {};


/* ------------------------------------------------------------------------------------------------------------------------------------- */
/* ------------------------------------------------------------------------------------------------------------------------------------- */
/* ------------------------------------------------------------------------------------------------------------------------------------- */
/* ------------------------------------------------------------------------------------------------------------------------------------- */
/* ------------------------------------------------------------------------------------------------------------------------------------- */
/* ------------------------------------------------------------------------------------------------------------------------------------- */
/* ------------------------------------------------------------------------------------------------------------------------------------- */
/* ------------------------------------------------------------------------------------------------------------------------------------- */
/* ------------------------------------------------------------------------------------------------------------------------------------- */
/* ----------------------- УТИЛИТЫ ДЛЯ ОСНОВНОЙ ТАБЛИЦЫ -------------------------------------------------------------------------------- */
/* ------------------------------------------------------------------------------------------------------------------------------------- */
/* ------------------------------------------------------------------------------------------------------------------------------------- */
/* ------------------------------------------------------------------------------------------------------------------------------------- */
/* ------------------------------------------------------------------------------------------------------------------------------------- */
/* ------------------------------------------------------------------------------------------------------------------------------------- */
/* ------------------------------------------------------------------------------------------------------------------------------------- */
/* ------------------------------------------------------------------------------------------------------------------------------------- */
/* ------------------------------------------------------------------------------------------------------------------------------------- */

/* 
 * подготовка TR с пейджинацией для главной таблицы 
 */
Admin.utils.table.Main.preparePaginatorTR = function(_controller, _sumFieldsCount){
	var totalCount = _controller.tableParams.totalCount;
	var page = _controller.tableParams.page;
	var pageSize = _controller.tableParams.pageSize;
			
	var pages = 0;
	if(totalCount % pageSize == 0){
		pages = totalCount / pageSize;
	}else{
		pages = parseInt(totalCount / pageSize) + 1;
	}
		
	var dCounter = '<span class="counter">' + 'Найдено: <b>' + totalCount +  '</b> записей на <b>' + pages + '</b> страницах</span>';
	
	// шаблон
	//   1  2  3   4  5  6  7   8   9  
	//  <<  1 ... 23 24 25 ... 47  >>
	
	var dPaginator = '<ul class="pagination">';
	
	// 1 
	if(page == 1){
		dPaginator += '<li class="disabled"><a>&lt;&lt;</a></li>';
	}else{
		dPaginator += '<li><a onclick="viewManager.showPage(' + parseInt(page-1) + ')">&lt;&lt;</a></li>';
	}
	
	
	// 2
	if(page >= 3){
		dPaginator += '<li><a onclick="viewManager.showPage(1)">1</a></li>';
	}
	
	// 3
	if(page >= 4){
		dPaginator += '<li><a class="doted">...</a></li>';
	}
	
	// 4
	if(page >= 2){
		dPaginator += '<li><a onclick="viewManager.showPage(' + parseInt(page-1) + ')">' + parseInt(page-1) + '</a></li>';
	}
	
	// 5 
	dPaginator += '<li class="active"><a>' + page + '</a></li>';
		
	// 6
	if(pages - page >= 1){
		dPaginator += '<li><a onclick="viewManager.showPage(' + parseInt(page+1) + ')">' + parseInt(page + 1) + '</a></li>';
	}
	
	
	// 7
	if(pages - page > 2){
		dPaginator += '<li><a class="doted">...</a></li>';
	}
	
	// 8
	if(pages - page >= 2){
		dPaginator += '<li><a onclick="viewManager.showPage(' + pages + ')">' + pages + '</a></li>';
	}
	
	// 9 
	if(page == pages){
		dPaginator += '<li class="disabled"><a>&gt;&gt;</a></li>';
	}else{
		dPaginator += '<li><a onclick="viewManager.showPage(' + parseInt(page+1) + ')">&gt;&gt;</a></li>';
	}
	
	dPaginator += '</ul>';
		
	return '<tr class="paginator"><td colspan="' + _sumFieldsCount + '">' + dCounter +  dPaginator +'</td></tr>';
};

/* ------------------------------------------------------------------------------------------------------------------------------------- */
/*
 *  готовит заголовок для основной таблицы
 */
Admin.utils.table.Main.prepareTHEAD = function(_vtObject, _fieldsCount, _mode){
	var caption = Admin.lib.getLocaleMes(_vtObject.entityClass + '.' + _vtObject.fieldName);
	var add = '';
	if(_mode == 'U' || _mode == 'C'){
		/* добавляем кнопки для добавления */
		add += '<td class="for-icon"><i title="Добавить" class="glyphicon glyphicon-plus-sign" onclick="viewManager.modifyCollectionFor(\'' + _vtObject.entityClass + '\', \'' + _vtObject.fieldName + '\')"></i></td>';
	}
	
	/* если данных нет */
	if(_fieldsCount == 0){
		return '<thead><tr><td>' + caption + '</td>' + add +'</tr></thead>';
	}
	
	/* если данные есть... */
	var realFieldsCount = _fieldsCount;
	if(_mode == 'U'){
		realFieldsCount += 2;
	}
	return '<thead><tr><td colspan="' + realFieldsCount + '">' + caption + '</td>' + add + '</tr></thead>';
};



