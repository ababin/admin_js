
with(tool.test){

testProc.add({
		
	name: 'TestController',
	desc: 'Тестирование функционала контроллера',
			
	
	
	/* CONTROLLER ------------------------------------------------------------------------------------------------ */ 
	test_creating : function(){
		var c = new BaseController('TestControllerEntity');
		assertNotNull(c);
	},
	
	test_prepareJsonFromAdaptedRecord : function(){
		var c = new BaseController('TestControllerEntity');
		
		// create new record
		c.initRec(c.createNewRecord());
		c.getAdaptedRec('C');
				
		var q = c.prepareJsonFromAdaptedRecord();
		assertEqual(Admin.lib.objectToString(q), '={id:NULL, fString:NULL, fI18nString:={ru_RU:NULL, en_EN:NULL}, fI18nArea:={ru_RU:NULL, en_EN:NULL}, fInteger:NULL, fIntegerRequired:NULL, fBoolean:NULL, fImageUrl:NULL, fAloneObject:NULL, fAloneIdentifier:NULL, fEnum:NULL, fEnumRequired:NULL, fSet:NULL}');
		
	},
	
	
	
	
	
	
	
	
});


}



