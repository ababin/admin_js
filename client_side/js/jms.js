if(Admin == undefined){Admin = {};};
/* =========================================================================================================================================================== */
/* =========================================================================================================================================================== */
/* =========================================================================================================================================================== */
/* =========================================================================================================================================================== */
/* === J M S ========================================================================================================================================== */

Admin.Jms = function(){
	var events = {};
	this.getEvents = function(){return events;};
	
	this.getEvent = function(_name){
		if(events[_name] == undefined){
			return null;
		}
		return events[_name];
	};
	
	this.addEvent = function(_event,_eventName){
		events[_eventName] = _event;
	};
};

Admin.Jms.prototype.subscribe = function(_ob, _eventName , _methodName){
	// if method not init, method name and event name are the same
	if(_methodName == undefined){
		_methodName = _eventName;
	}
	
	var event = this.getEvent(_eventName);
	if(event == null){
		event = new Array();
		this.addEvent(event, _eventName);
	}
	/* провряем, нет ли объекта уже в подписке */
	for(var i in event){
		if(event[i].object === _ob && event[i].methodName == _methodName){
			return;
		}
	}
	/* подписываем объект */
	event.push({object:_ob,methodName:_methodName});
};

Admin.Jms.prototype.unsubscribe = function(_ob, _eventName){
	if(_eventName == undefined){
		/* проходимся по всем ивентам */
		for(var e in this.getEvents()){
			/*  проверяем конкретный ивент */
			var event = this.getEvent(e);
			removeForEvent(event , _ob);
		}
	}else{
		var event = this.getEvent(_eventName);
		removeForEvent(event , _ob);
	}
	
		
	function removeForEvent(_event, _ob){
		var forRem = new Array();
		for(var i in _event){
			if(_event[i].object === _ob){
				forRem.push(i);
			}
		}
		/* удаляем */
		forRem.reverse();
		for(var i in forRem){
			_event.splice(forRem[i],1);
		}
	}
	
};

Admin.Jms.prototype.notify = function(_eventName, _data){
	var event = this.getEvent(_eventName);
	if(event == null){
		return;
	}
	for(var i in event){
		var ev = event[i];
		var ob = ev.object;
		try{
			eval('ob.' + ev.methodName + '(_data)');
		}catch(e){}
	}
			
};

Admin.Jms.prototype.events = {
		onSelectElement : 'onSelectElement',
		onRemoveElement : 'onRemoveElement',
};

