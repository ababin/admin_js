
with(tool.test){

testProc.add({
		
	name: 'IsModified',
	desc: 'Проверка типа на предмет изменений',
			
	
	
	/* STRING ------------------------------------------------------------------------------------------------ */ 
	test_String : function(){
		var ob = new Admin.types.String('val1', 'Entity', 'fString');
		var res = ob.isModified();
		assertFalse(res);
	},
	
	test_String_2 : function(){
		var ob = new Admin.types.String('val1', 'Entity', 'fString');
		ob.internalObj = 'val2';
		var res = ob.isModified();
		assertTrue(res);
	},
	
	test_String_3 : function(){
		var ob = new Admin.types.String(null, 'Entity', 'fString');
		ob.internalObj = '';
		var res = ob.isModified();
		assertFalse(res);
	},
	
	/* I18NSTRING ------------------------------------------------------------------------------------------------ */ 
	test_I18nString : function(){
		var val = {'ru_RU':'val1','en_EN':'val2'};
		var ob = new Admin.types.I18nString(val, 'Entity', 'fI18nString');
		var res = ob.isModified();
		assertFalse(res);
	},
	
	test_I18nString_2 : function(){
		var val = {'ru_RU':'val1'};
		var ob = new Admin.types.I18nString(val, 'Entity', 'fI18nString');
		var res = ob.isModified();
		assertFalse(res);
	},
	
	test_I18nString_3 : function(){
		var val = {'ru_RU':'val1'};
		var ob = new Admin.types.I18nString(val, 'Entity', 'fI18nString');
		ob.internalObj = {'en_EN' : 'en'};
		var res = ob.isModified();
		assertTrue(res);
	},
	
	test_I18nString_4 : function(){
		var val = {'ru_RU':'val1'};
		var ob = new Admin.types.I18nString(val, 'Entity', 'fI18nString');
		ob.internalObj['en_EN'] = 'enValue';
		var res = ob.isModified();
		assertTrue(res);
	},
	
	/* I18NAREA ------------------------------------------------------------------------------------------------ */ 
	test_I18nArea : function(){
		var val = {'ru_RU':'val1','en_EN':'val2'};
		var ob = new Admin.types.I18nArea(val, 'Entity', 'fI18nArea');
		var res = ob.isModified();
		assertFalse(res);
	},
	
	/* INTEGER ------------------------------------------------------------------------------------------------ */ 
	test_Integer : function(){
		var ob = new Admin.types.Integer(123, 'Entity', 'fInteger');
		var res = ob.isModified();
		assertFalse(res);
	},
	
	test_Integer_2 : function(){
		var ob = new Admin.types.Integer(123, 'Entity', 'fInteger');
		ob.internalObj = '123';
		var res = ob.isModified();
		assertFalse(res);
	},
	
	test_Integer_3 : function(){
		var ob = new Admin.types.Integer(123, 'Entity', 'fInteger');
		ob.internalObj = '1234';
		var res = ob.isModified();
		assertTrue(res);
	},
	
	test_Integer_5 : function(){
		var ob = new Admin.types.Integer(123, 'Entity', 'fInteger');
		ob.internalObj = null;
		var res = ob.isModified();
		assertTrue(res);
	},
	
	/* BOOLEAN ------------------------------------------------------------------------------------------------ */ 
	test_Boolean : function(){
		var ob = new Admin.types.Boolean(null, 'Entity', 'fBoolean');
		var res = ob.isModified();
		assertFalse(res);
	},
		 
	test_Boolean_2 : function(){
		var ob = new Admin.types.Boolean(true, 'Entity', 'fBoolean');
		ob.internalObj = false;
		var res = ob.isModified();
		assertTrue(res);
	},
	
	/* ALONE ID ------------------------------------------------------------------------------------------------ */ 
	test_Alone_id : function(){
		var ob = new Admin.types.Alone(3,'Entity', 'fAloneIdentifier');
		var res = ob.isModified();
		assertFalse(res);
	},
	
	test_Alone_id_2 : function(){
		var ob = new Admin.types.Alone(3,'Entity', 'fAloneIdentifier');
		ob.setId(3);
		var res = ob.isModified();
		assertFalse(res);
	},
	
	test_Alone_id_3 : function(){
		var ob = new Admin.types.Alone(3,'Entity', 'fAloneIdentifier');
		ob.setId(4);
		var res = ob.isModified();
		assertTrue(res);
	},
	
	/* ALONE OBJECT ------------------------------------------------------------------------------------------------ */ 
	// пока не реализована работа для ALONE OBJECT
	/*
	test_Alone_object : function(){
		var ob = new Admin.types.Alone({id:3,p1:1,p2:2},'Entity', 'fAloneObject');
		var res = ob.isModified();
		assertFalse(res);
	},
	
	test_Alone_object_2 : function(){
		var ob = new Admin.types.Alone({id:3,p1:1,p2:2},'Entity', 'fAloneObject');
		ob.setId(3);
		var res = ob.isModified();
		assertFalse(res);
	},
	
	test_Alone_object_3 : function(){
		var ob = new Admin.types.Alone({id:3,p1:1,p2:2},'Entity', 'fAloneObject');
		ob.setId(4);
		var res = ob.isModified();
		assertTrue(res);
	},
	
	test_Alone_object_4 : function(){
		var ob = new Admin.types.Alone({id:3,p1:1,p2:2},'Entity', 'fAloneObject');
		ob.internalObj = {id:4,p1:1,p2:2};
		var res = ob.isModified();
		assertTrue(res);
	},
	*/
	
	
	
	
	
	
	
});


}



