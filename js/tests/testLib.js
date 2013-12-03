
with(tool.test){

testProc.add({
		
	name: 'Библиотека',
	desc: 'библиотечные функции',
			
	test_copyObject : function(){
		var val = {q:'w',w:'e'};
		var ob = Admin.lib.copyObject(val);
		assertNotNull(ob);
		
		val.e = {r:'t',f:[1,2,3,4]};
		ob = Admin.lib.copyObject(val);
		assertNotNull(ob);
		assertEqual(ob.e.f[1], 2);
	},

	

});


}



