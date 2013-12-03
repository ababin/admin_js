
with(tool.test){

testProc.add({
		
	name: 'Validate',
	desc: 'осуществляет валидацию данных для BaseType объектов',
			
	/* INTEGER ------------------------------------------------------------------------------------------------ */ 
	test_validate_IntegerNullable : function(){
		var ob = new Admin.types.Integer('', 'EntityValidate', 'fIntegerNullable');
		var res = ob.validate();
		assertTrue(res);
		ob = new Admin.types.Integer('w', 'EntityValidate', 'fIntegerNullable');
		res = ob.validate();
		assertNotTrue(res);
		assertEqual(res.error, Admin.render.errors.NOT_INTEGER);
	},
	
	test_validate_IntegerMax : function(){
		var ob = new Admin.types.Integer(150, 'EntityValidate', 'fIntegerMax');
		var res = ob.validate();
		assertEqual(res.error, Admin.render.errors.TOO_BIG);
	},
	
	test_validate_IntegerMin : function(){
		var ob = new Admin.types.Integer(1, 'EntityValidate', 'fIntegerMin');
		var res = ob.validate();
		assertEqual(res.error, Admin.render.errors.TOO_SMALL);
	},
	
	test_validate_IntegerFloat : function(){
		var ob = new Admin.types.Integer(23.4, 'EntityValidate', 'fInteger');
		var res = ob.validate();
		assertEqual(res.error, Admin.render.errors.NOT_INTEGER);
	},
		
});


}



