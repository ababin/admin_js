
with(tool.test){

testProc.add({
		
	name: 'Типы данных',
	desc: 'Типы, задействованные в процессе: Identifier, Number, String, Boolean, ImageUrl, I18nString, I18nArea',
			
	test_string : function(){
		var val = 'val';
		var ob = new Admin.types.String(val,'BtvChannel', 'format');
		assertEqual(val,ob.getString());
	},

	test_i18nString : function(){
		settings = {locale:'ru_RU'};
		var val = {ru_RU : 'ru_ru',en_EN : 'en_en'};
		var ob = new Admin.types.I18nString(val,'Entity', 'fI18nString');
		assertEqual('ru_ru',ob.getString());
	},
	
	test_i18nString_getView : function(){
		settings = {locale:'ru_RU'};
		var val = {ru_RU : 'Название передачи',en_EN : 'English name'};
		var ob = new Admin.types.I18nString(val,'Entity', 'fI18nString');
		
		var view = ob.getView('R', 'ru_RU');
		assertEqual(view, val.ru_RU);
		
		view = ob.getView('R', 'en_EN');
		assertEqual(view, val.en_EN);
				
	},

	testFactory_string : function(){
		var ob = Admin.types.Factory.createType('vall','Entity','fString');
		assertTrue(ob instanceof Admin.types.String);
		assertEqual('vall', ob.getString());
	},
	
	testFactory_number : function(){
		var ob = Admin.types.Factory.createType(123,'Entity','fNumber');
		assertTrue(ob instanceof Admin.types.Number);
		assertEqual(123, ob.getInternalValue());
		assertIdentical(123, ob.getInternalValue());
	},

	testFactory_boolean : function(){
		var ob = Admin.types.Factory.createType(false,'Entity','fBoolean');
		assertTrue(ob instanceof Admin.types.Boolean);
		assertFalse(ob.getInternalValue());
		
		ob = Admin.types.Factory.createType(false,'Entity','fBoolean');
		assertTrue(ob instanceof Admin.types.Boolean);
		assertEqual('false', ob.getString());
		
		var ob = Admin.types.Factory.createType(true,'Entity','fBoolean');
		assertTrue(ob instanceof Admin.types.Boolean);
		assertTrue(ob.getInternalValue());
		assertEqual('true', ob.getString());
	},
	
	testFactory_foreignAloneType : function(){
		var ob = Admin.types.Factory.createType(false,'Entity','fForeignAloneType');
		assertTrue(ob instanceof Admin.types.ForeignAloneType);
		assertEqual('Entity.fForeignAloneType:AgeRating' , ob.getString());
	},

});


}



