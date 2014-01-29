
with(tool.test){

testProc.add({
		
	name: 'JMS',
	desc: 'методы для организации событийной архитектуры',
			
	test_subscribe : function(){
		
		obForNotify = {notified : false};
		
		var ob = {onEdit : function(_data){obForNotify.notified = _data;}};
		
		jms.subscribe(ob , 'onEdit','onEdit');
		
		jms.notify('onEdit',1122);
		assertTrue(obForNotify.notified == 1122);
		
		jms.notify('onEdit',1133);
		assertTrue(obForNotify.notified == 1133);
		
		jms.unsubscribe(ob);
		jms.notify('onEdit',4444);
		assertTrue(obForNotify.notified == 1133);
		
	},

	

});


}



