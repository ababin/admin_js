if(typeof Admin == 'undefined'){Admin = {};}


Admin.ViewController = function(){
	
	/* главное окно с табличными данными. Появляется при нажатии соответсвующего пункта меню  */
	this.mainWindow = null;
		
	/* список модальных окон */  
	this.windows = new Array();
};

Admin.ViewController.prototype.getTopWindow = function(){
	return this.windows[this.windows.length-1];
};

Admin.ViewController.prototype.removeTopWindow = function(){
	this.windows.splice(this.windows.length-1, 1);
};


/* ========================================================================================================================================== */
Admin.ViewController.prototype.closeWindow = function(){
	this.getTopWindow().close();
	this.removeTopWindow();
	this.wall();
};

/* ========================================================================================================================================== */
Admin.ViewController.prototype.wall = function(){
	if(this.windows.length == 0){
		$('#wall').hide();
		return;
	}
	var win = this.getTopWindow();
		
	$('#wall').css("z-index", win.zIndex - 1);
	$('#wall').show();
};

/* ========================================================================================================================================== */
/*
 * Показать запись
 * 
 * параметры: _controller - контроллер записи, например
 * btvChannelRecordController _index - индекс данных соответсвующего
 * table-контроллера
 */
Admin.ViewController.prototype.showDetachedRecord = function(_className, _id,_mode){
	var controller = controllerManager.getController(_className);
	// 1. create new window
	var dw = new Admin.DetachedRecordDataWindow(controller, _id, _mode , this.windows.length);
	try{
		this.showWindow(dw);
	}catch(e){
		log.warn("Ошибка при отображении DetachedRecordWindow: "  + e);
	}
};

/* ========================================================================================================================================== */
Admin.ViewController.prototype.showWindow = function(_window){
	_window.initRec();
	// 2. add to array
	this.windows[this.windows.length] = _window;
	// 3. show wall 
	this.wall();
	// 4. show window
	_window.show();
};

Admin.ViewController.prototype.windowGoOnPage = function(_page){
	var controller = this.windows[this.windows.length - 1];
	controller.
	
	log.warn('Запрошена страница ' + _page);
};

/* ========================================================================================================================================== */
/*
 * Показать запись
 * 
 * параметры: 
 * _className - имя класса сущности, для которой производится операция
 * _id - идентификатор сущности
 * _mode - режим открытия окна: 'R' - для чтения, 'C' - для создания, 'U' - для модификации
 * _needResetRecord - нужно ли сбрасывать запись в контроллере
 */
Admin.ViewController.prototype.showRecord = function(_className, _id,_mode, _needResetRecord){
	var controller = controllerManager.getController(_className);
	// 1. create new window
	var dw = new Admin.RecordDataWindow(controller, _id, _mode , this.windows.length);
	if(_needResetRecord === true){
		dw.controller.resetAdaptedRec();
	}
	this.showWindow(dw);
};

/* ========================================================================================================================================== */			
/* показать данные в основном диве
 * 
 */
Admin.ViewController.prototype.showMainDatas = function(_className){
	_controller = controllerManager.getController(_className);
	this.mainWindow = new Admin.MainDataWindow(_controller);
	this.mainWindow.show();
};

/* ========================================================================================================================================== */
/*
 * Отобразить указанную страницу для текущего модального окна
 */
Admin.ViewController.prototype.showPage = function(_pageNum){
	var needWin = null;
	if(this.windows.length > 0){
		needWin = this.windows[this.windows.length-1];
	}else{
		needWin = this.mainWindow;
	}
	needWin.show(_pageNum);
};


/* ========================================================================================================================================== */			
/* вызывается при попытке добавления значений из коллекции справочника в нужный класс.
 * Например: при добавлении жанров в Btv-канал
 * 
 */
Admin.ViewController.prototype.modifyCollectionFor = function(_forClassName, _fieldName){
	var controllerFor = controllerManager.getController(_forClassName); 
	
	// создаем новое окно
	var nw = new Admin.TableDataWindow(controllerFor , _fieldName , null, 'M' , this.windows.length);
	nw.parentWindow = this.windows[this.windows.length-1];
	
	this.showWindow(nw);
	
	
};


/* ========================================================================================================================================== */			
/* вызывается при попытке добавления значений из коллекции справочника в нужный класс.
 * Например: при добавлении жанров в Btv-канал
 * 
 */
Admin.ViewController.prototype.save = function(){
	var win = this.windows[this.windows.length-1];
	if(win.save() === true){
		this.closeWindow();
	};
};

/* ========================================================================================================================================== */			
/* вызывается при попытке добавления значений из коллекции справочника в нужный класс.
 * Например: при добавлении жанров в Btv-канал
 * 
 */
Admin.ViewController.prototype.goToEditMode = function(){
	var win = this.windows[this.windows.length-1];
	win.show('U');
};

/* ========================================================================================================================================== */			
/* Нажата кнопка ОТМЕНА в окне
 * 
 */
Admin.ViewController.prototype.cancel = function(){
	var win = this.windows[this.windows.length-1];
	if(win.mode == 'U'){
		win.show('R');
	};
};

/* ==============================================================================================================================================
 * ==============================================================================================================================================
 * ============================================================================================================================================== 
 * ============================================================================================================================================== 
 * ============================================================================================================================================== 
 */
/*
 * Базовый класс для всех информационных окон. Включает в себя соответсвующий контроллер данных. Это может быть
 * табличный контроллер (TableController) или контроллер записи (RecordController)
 */
Admin.DataWindow = function(_controller){
	  
	// контроллер, с которым оперирует окно
	this.controller = _controller;
	
	/*
	if(this.controller != undefined){
		this.controller.resetAdaptedRec();
	}
	*/
};

Admin.DataWindow.prototype.close = function(){
	this.closeInternal();
};

Admin.DataWindow.prototype.closeInternal = function(){
	// get DIV object:
	var div = $('#modal_' + this.level);
	div.html('');
	div.hide();
};


Admin.DataWindow.prototype.show = function(){
	alert('Not realized!!! ' + this.constructor.name + '.show()');
};

/* ==============================================================================================================================================
 * ==============================================================================================================================================
 * ============================================================================================================================================== 
 * ============================================================================================================================================== 
 * ============================================================================================================================================== 
 */
/*
 * Представляет реализацию окна, которое отображается в главном фрейме табличных данных
 */
Admin.MainDataWindow = function(_controller){
    Admin.DataWindow.call(this, _controller);
};
Admin.MainDataWindow.prototype = new Admin.DataWindow(); 
/* ========================================================================================================================================== */
/*
 * Функция отображения данных
 */
Admin.MainDataWindow.prototype.show = function(_page){
	var table = Admin.table.Main.create(this.controller , _page);
	$('#MAIN_MENU_CONTENT').html(table);
};

/* ==============================================================================================================================================
 * ==============================================================================================================================================
 * ============================================================================================================================================== 
 * ============================================================================================================================================== 
 * ============================================================================================================================================== 
 */
/*
 * Реализация для окна, представляющая данные по конкретной строке таблицы
 */
Admin.RecordDataWindow = function(_controller , _id, _mode ,_level){
    Admin.DataWindow.call(this, _controller);
    
    this.id = _id;
    this.level = _level;
    this.zIndex = 3000 + this.level * 10;
    this.mode = _mode;
};
Admin.RecordDataWindow.prototype = new Admin.DataWindow();

/* ========================================================================================================================================== */
Admin.RecordDataWindow.prototype.prepareViewForLocalize = function(_record, _fields){
	var result = '';
 
	if(Admin.lib.isEmpty(_fields)){
		return result;
	}
	 
	/*
	* готовим сами вкладки. Дефолтная локаль должна быть первая
	* (settings.default.locale) готовим последовательность локалей
	*/
	var locales = new Array();
	locales[0] = settings.def.locale;
	for(var i = 0; i < ref.LOCALES.length ; i++){
		 if(ref.LOCALES[i] != settings.def.locale){
			 locales[locales.length] = ref.LOCALES[i];
		 }
	 }
       
	 result += '<div class="tabbable underline">';
 
	 /* готовим шапку для вкладок */
	 result += '<ul class="nav nav-tabs">';
	 for(var i =0; i < locales.length; i++){
		 if(i == 0){
			 result += '<li class="active"><a href="#tab'+ this.controller.entityClass + i + '" data-toggle="tab"><img src="img/locale/' + locales[i] + '.gif"> '+ Admin.lib.getLocaleMes('lang_'+locales[i], locales[i]) + '</a></li>';
		 }else{
			 result += '<li><a href="#tab' + this.controller.entityClass + i + '" data-toggle="tab"><img src="img/locale/' + locales[i] + '.gif"> '+ Admin.lib.getLocaleMes('lang_'+locales[i], locales[i]) + '</a></li>';
		 }
	 }
	 result += '</ul>' + 
	 		  '<div class="tab-content">';
 
	 /* готовим контент для вкладок ----------------- */
	 for(var i =0; i < locales.length; i++){
		 result += '<div class="tab-pane ' + (i == 0 ? 'active' : '') + '" id="tab' + this.controller.entityClass + i + '">';

		 /* контент. Проходимся по полям */
		 for(var j=0; j < _fields.length; j++){
			 var fieldObject = _record[_fields[j]];
			 result +=  Admin.render.doRendering(fieldObject, this.mode);
		 }
		 /* ---------------------------- */

		 result += '</div>';
	 }
	 /* ---------------------------------------------- */
 
	 /* закрываем tab-content  и tabbable */
	 result += '</div></div>';
	 return result;
};

/* ========================================================================================================================================== */
Admin.RecordDataWindow.prototype.initRec = function(){
	/* инициализируем данные самого контроллера
	 * Для обычной записи просто берем запись из уже готовой таблицы 
	*/
	this.controller.initRecById(this.id);
};

/* ========================================================================================================================================== */
Admin.RecordDataWindow.prototype.show = function(_mode){
	
	if(_mode != undefined && this.controller.havePermission(_mode)){
		this.mode = _mode;
	}
	
	if(this.mode == 'C'){
		var newRec = this.controller.createNewRecord();
		this.controller.initRec(newRec);
	}
	 
	/* данные */
	var rec = this.controller.getAdaptedRec(this.mode);
	 
	this.showRec(rec);
};

/* ========================================================================================================================================== */
Admin.RecordDataWindow.prototype.showRec = function(_rec){
			 
	/* заголовок */
	var header = Admin.lib.getLocaleMes(this.controller.entityClass);
	
	/* если разрешено редактирование И не находимся в режиме редактирования или создания, то добавляем кнопку редактирования */
	if( 'CU'.indexOf(this.mode) < 0 && this.controller.havePermission('U')){
		header += '<i class="glyphicon glyphicon-edit pointed" title="Редактировать" onclick="viewManager.goToEditMode()"></i>';
	}else{
		if(this.mode == 'C'){
			header += '<span class="mes-create">создание ...</span>';
		}else if(this.mode == 'U'){
			header += '<span class="mes-update">редактирование ...</span>';
		}
	}
	
	       
	/* тело */
	var body = '';
  
	/*
	* разбиваем все поля на 3 части: 1. которые отоброжаются нормально
	* (fields_normal_1) 2. отображаются с возможностью локализации
	* (fields_localize) 3. оставшиеся, которые отображаются нормально
	* (fields_normal_2)
	*/
	var fields_normal_1 = new Array();
	var fields_normal_2 = new Array();
	var fields_localize = new Array();
	 
		
	var fields =  Admin.lib.getObjectByPriority('model.CLASSES.' + this.controller.entityClass + '.record.fields');  
	var is18nFound = false;
	for(var i=0; i<fields.length ;i++){
		var curFieldDescriptor = model.CLASSES[this.controller.entityClass].fields[fields[i]];
		if(curFieldDescriptor == undefined){
			alert("Поле " + fields[i] + " для сущности " + this.controller.entityClass + " не определено (Набор полей для записи)");
		}
		if(curFieldDescriptor.type == 't:I18nString' || curFieldDescriptor.type == 't:I18nArea'){
			is18nFound = true;
			fields_localize[fields_localize.length] = fields[i];
		}else{
			if(is18nFound){
				fields_normal_2[fields_normal_2.length] = fields[i];
			 }else{
				fields_normal_1[fields_normal_1.length] = fields[i];
			 }
		}
	}
	// --------------------------------------------------------------------------
  
	/* body для первой части полей: */
	for(var i=0; i<fields_normal_1.length ;i++){
		var vtObject = _rec[fields_normal_1[i]];
		var divId = Admin.lib.prepareDivIdForVtObject(this.level, vtObject.entityClass, vtObject.fieldName);
		body += '<div id="' + divId + '">' + Admin.render.doRendering(vtObject, this.mode) + '</div>';  
	}
  
	/* body для локализации : */
	body += this.prepareViewForLocalize(_rec, fields_localize);
  
	/* body для последней части полей: */
	for(var i=0; i<fields_normal_2.length ;i++){
		var vtObject = _rec[fields_normal_2[i]];
		var divId = Admin.lib.prepareDivIdForVtObject(this.level, vtObject.entityClass, vtObject.fieldName);
		body += '<div id="' + divId + '">' + Admin.render.doRendering(vtObject, this.mode) + '</div>'; 
	}
  
	// footer
	var footer = '';
  
	// btn Save
	if(this.mode == 'U' || this.mode == 'C'){
		footer+= '<button class="btn btn-success" onclick="viewManager.save()"><i class="icon-white icon-ok"></i> Сохранить</button>';
	}
	// btn OK
	if(this.mode == 'R'){
		footer += '<button class="btn btn-info" onclick="viewManager.closeWindow()"><i class="icon-white icon-ok"></i> OK</button>';
	}
	/* btn CANCEL */
	if(this.mode == 'U' || this.mode == 'C'){
		footer += '<button class="btn btn-warning" onclick="viewManager.cancel()"><i class="icon-white icon-share-alt"></i>Отмена</button>';
	}
	  
	this.showCompleteView(header, body, footer);
};



Admin.RecordDataWindow.prototype.refresh = function(){
	// update only fields which needed
		
};


/* ========================================================================================================================================== */
/* ПОКАЗАТЬ ОКНО С ДАННЫМИ (финальный метод) 
 * 
 */
Admin.RecordDataWindow.prototype.showCompleteView = function(_header, _body, _footer){
	// get DIV object:
    		
	var div = $('#modal_' + this.level);
	var content = '<div class="modal-dialog">' +
    				'<div class="modal-content">' +
    					'<div class="modal-header">' +
    						'<h4 class="modal-title">' + _header + '</h4>' +
    					'</div>' +
    					'<div class="modal-body">' +
    					_body +
    					'</div>' +
    					'<div class="modal-footer">' +
    						_footer +
    					'</div>' +
    				'</div><!-- /.modal-content -->' +
    			'</div><!-- /.modal-dialog -->';
	div.html(content);
    div.css("z-index", this.zIndex);
    div.show();
};

/* ========================================================================================================================================== */
/* Сохранить данные !!!!!
 */
Admin.RecordDataWindow.prototype.save = function(){
	var rec = this.controller.getAdaptedRec();
	
	// fill data and validate
	var validateOK = true;
	for(var i in rec){
		Admin.render.fillData(rec[i]);
		if(rec[i].validate() !== true){
			validateOK = false;
		};
	};
	
	this.showRec(rec);
	
	if(validateOK){
		// save data !!!
		return this.controller.persist();
	}	
	
};


/* ==============================================================================================================================================
 * ==============================================================================================================================================
 * ============================================================================================================================================== 
 * ============================================================================================================================================== 
 * ============================================================================================================================================== 
 */
/*
 * Определяет сущность, которую необходимо ЗАГРУЗИТЬ !!! , а не взять из таблицы.
 * Для этого перегружается метод initRec()
 */
Admin.DetachedRecordDataWindow = function(_controller , _id, _mode ,_level){
	Admin.RecordDataWindow.call(this , _controller , _id, _mode ,_level);
};
Admin.DetachedRecordDataWindow.prototype = new Admin.RecordDataWindow();

/* Для detached записи загружаем ее полностью с сервера !!!!
 */
Admin.DetachedRecordDataWindow.prototype.initRec = function(){
	this.controller.loadRec(this.id);
};


/* ==============================================================================================================================================
 * ==============================================================================================================================================
 * ============================================================================================================================================== 
 * ============================================================================================================================================== 
 * ============================================================================================================================================== 
 */
/*
 * Представляет реализацию окна, которое отображается в главном фрейме табличных данных
 */
Admin.TableDataWindow = function(_controllerFor, _fieldName, _id, _mode ,_level){
	Admin.RecordDataWindow.call(this , _controllerFor , _id, _mode ,_level);
    
    this.multipleSelect = false;
    this.fieldName = _fieldName;
    
    // берем значение, подлежащее редактированию
	/* BaseType */
    this.vtObject = this.controller.getAdaptedRec(this.mode)[this.fieldName];
                
    // подписываемся на изменения данных
    jms.subscribe(this, jms.events.onSelectElement);
    jms.subscribe(this, jms.events.onRemoveElement);
    	    
    this.parentWindow = null;
    
    /* divId, где находятся данные */
    this.divId = null;
};
Admin.TableDataWindow.prototype = new Admin.RecordDataWindow(); 

/* ==========================================================================================================================================
 * Do nothing 
 */
Admin.TableDataWindow.prototype.initRec = function(){};

/* ========================================================================================================================================== */
/*
 * Функция отображения данных
 */
Admin.TableDataWindow.prototype.show = function(){
	
	// заголовок
	var header = Admin.lib.getLocaleMes(this.controller.entityClass) + 
					' -> ' + 
					Admin.lib.getLocaleMes(this.vtObject.fDesc.type + '.plural');
	
	// первая колонка
	var column1Table = Admin.render.doRendering(this.vtObject , 'M'); 
	
	// вторая колонка с данными
	var contextController = controllerManager.getController(this.vtObject.fDesc.type);
	// название события, которе будет генерироваться при выборе записи в справочнике
	var column2Table = Admin.table.Context.createColumn2(contextController , 1);
	
	// готовим идентификатор для дива 1-ой колонки, так как ее необходимо будет обновлять после каждого клика
	this.divId = 'div_column1_' + this.vtObject.fDesc.type;
			
	var body = 	'<div class="row">' +
					'<div class="col-md-6" id="' + this.divId +'">' + column1Table + '</div>' +
					'<div class="col-md-6">' + column2Table + '</div>' +
				'</div>';
		
	
	// footer
	var footer = '';
  
	// btn Save
	if(this.mode != 'R'){
		footer+= '<button class="btn btn-success" onclick="viewManager.save()"><i class="icon-white icon-ok"></i> Сохранить</button>';
	}
	// btn OK
	if(this.mode == 'R'){
		footer += '<button class="btn btn-info" onclick="viewManager.closeWindow()"><i class="icon-white icon-ok"></i> OK</button>';
	}
	/* btn CANCEL */
	if(this.mode != 'R'){
		footer += '<button class="btn btn-warning" onclick="viewManager.closeWindow()"><i class="icon-white icon-share-alt"></i>Отмена</button>';
	}
	
	this.showCompleteView(header,body, footer);
	
	return;
	
};
Admin.TableDataWindow.prototype.close = function(){
	jms.unsubscribe(this);
	this.closeInternal();
};

Admin.TableDataWindow.prototype.save = function(){
	var div = $('#' + Admin.lib.prepareDivIdForVtObject(this.parentWindow.level, this.vtObject.entityClass, this.vtObject.fieldName));
	div.html(Admin.render.doRendering(this.vtObject));
	//this.closeInternal();
	return true;
};

Admin.TableDataWindow.prototype.onSelectElement = function(_data){
	if(this.vtObject.onAddElement(_data)){
		var div = $('#' + this.divId);
		div.html(Admin.render.doRendering(this.vtObject, 'M'));
	}
	
	
};

Admin.TableDataWindow.prototype.onRemoveElement = function(_data){
	if(this.vtObject.onRemoveElement(_data)){
		var div = $('#' + this.divId);
		div.html(Admin.render.doRendering(this.vtObject, 'M'));
	}
};




