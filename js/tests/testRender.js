
with(tool.test){

testProc.add({
		
	name: 'Render',
	desc: 'осуществляет рендеринг данных для BaseType объектов',
	
	/* INTEGER ------------------------------------------------------------------------------------------------ */ 
	test_rendering_Integer_R : function(){
		var ob = new Admin.types.Integer(1354,'Entity', 'fInteger');
		var res = Admin.render.doRendering(ob, 'R');
		assertEqual(res , '<div class="clearfix"><label class="for-form">Entity.fInteger.ru_RU</label><span>1354</span></div>');
	},
			
	test_rendering_Integer_C : function(){
		var ob = new Admin.types.Integer(1354,'Entity', 'fInteger');
		var res = Admin.render.doRendering(ob, 'C');
		assertEqual(res , '<div class="clearfix"><label class="for-form" for="Entity_fInteger">Entity.fInteger.ru_RU</label><input type="text" id="Entity_fInteger" value="1354"></div>');
	},
	
	test_rendering_Integer_U : function(){
		var ob = new Admin.types.Integer(1354,'Entity', 'fInteger');
		var res = Admin.render.doRendering(ob, 'U');
		assertEqual(res , '<div class="clearfix"><label class="for-form" for="Entity_fInteger">Entity.fInteger.ru_RU</label><input type="text" id="Entity_fInteger" value="1354"></div>');
	},
	
	test_rendering_Integer_U_requered : function(){
		var ob = new Admin.types.Integer(1354,'Entity', 'fIntegerRequired');
		var res = Admin.render.doRendering(ob, 'U');
		assertEqual(res, '<div class="clearfix"><label class="for-form" for="Entity_fIntegerRequired">Entity.fIntegerRequired.ru_RU<span class="required">*</span></label><input type="text" id="Entity_fIntegerRequired" value="1354"></div>');
	},
	
	
	/* STRING ------------------------------------------------------------------------------------------------ */ 
	test_rendering_String_R : function(){
		var ob = new Admin.types.String('init value','BtvChannel', 'format');
		var res = Admin.render.doRendering(ob, 'R');
		assertEqual(res , '<div class="clearfix"><label class="for-form">Формат</label><span>init value</span></div>');
	},
	
	test_rendering_String_R_undefined : function(){
		var ob = new Admin.types.String(null,'BtvChannel', 'format');
		var res = Admin.render.doRendering(ob, 'R');
		assertEqual(res , '<div class="clearfix"><label class="for-form">Формат</label><span class="undefined">не определено</span></div>');
	},
	
	test_rendering_String_C : function(){
		var ob = new Admin.types.String('init value','BtvChannel', 'format');
		var res = Admin.render.doRendering(ob, 'C');
		assertEqual(res , '<div class="clearfix"><label class="for-form" for="BtvChannel_format">Формат</label><input type="text" id="BtvChannel_format" value="init value"></div>');
	},
	
	test_rendering_String_U : function(){
		var ob = new Admin.types.String('init value','BtvChannel', 'format');
		var res = Admin.render.doRendering(ob, 'U');
		assertEqual(res , '<div class="clearfix"><label class="for-form" for="BtvChannel_format">Формат</label><input type="text" id="BtvChannel_format" value="init value"></div>');
	},
	
	/* I18NSTRING ------------------------------------------------------------------------------------------------ */
	test_rendering_I18nString_R : function(){
		var ob = new Admin.types.I18nString({ru_RU : 'инициализация переменной', en_EN: 'init value'} ,'BtvChannel', 'i18nName');
		var res = Admin.render.doRendering(ob, 'R', 'ru_RU');
		assertTrue(res.length == 105);
		res = Admin.render.doRendering(ob, 'R', 'en_EN');
		assertTrue(res.length == 91);
	},
	
	test_rendering_I18nString_C : function(){
		var ob = new Admin.types.I18nString({ru_RU : 'инициализация переменной', en_EN: 'init value'} ,'BtvChannel', 'i18nName');
		var res = Admin.render.doRendering(ob, 'C', 'ru_RU');
		assertEqual(res, '<div class="clearfix"><label class="for-form" for="BtvChannel_i18nName_ru_RU">Название</label><input type="text" id="BtvChannel_i18nName_ru_RU" value="инициализация переменной"></div>');
		res = Admin.render.doRendering(ob, 'C', 'en_EN');
		assertEqual(res, '<div class="clearfix"><label class="for-form" for="BtvChannel_i18nName_en_EN">Название</label><input type="text" id="BtvChannel_i18nName_en_EN" value="init value"></div>');
	},
	
	test_rendering_I18nString_U : function(){
		var ob = new Admin.types.I18nString({ru_RU : 'инициализация переменной', en_EN: 'init value'} ,'BtvChannel', 'i18nName');
		var res = Admin.render.doRendering(ob, 'U', 'ru_RU');
		assertEqual(res, '<div class="clearfix"><label class="for-form" for="BtvChannel_i18nName_ru_RU">Название</label><input type="text" id="BtvChannel_i18nName_ru_RU" value="инициализация переменной"></div>');
		res = Admin.render.doRendering(ob, 'U', 'en_EN');
		assertEqual(res, '<div class="clearfix"><label class="for-form" for="BtvChannel_i18nName_en_EN">Название</label><input type="text" id="BtvChannel_i18nName_en_EN" value="init value"></div>');
	},
	
	/* I18NAREA ------------------------------------------------------------------------------------------------ */
	test_rendering_I18nArea_R : function(){
		var ob = new Admin.types.I18nArea({ru_RU : 'инициализация переменной', en_EN: 'init value'} ,'BtvChannel', 'i18nName');
		var res = Admin.render.doRendering(ob, 'R', 'ru_RU');
		assertTrue(res.length == 105);
		res = Admin.render.doRendering(ob, 'R', 'en_EN');
		assertTrue(res.length == 91);
	},
	
	test_rendering_I18nArea_C : function(){
		var ob = new Admin.types.I18nArea({ru_RU : 'инициализация переменной', en_EN: 'init value'} ,'BtvChannel', 'i18nName');
		var res = Admin.render.doRendering(ob, 'C', 'ru_RU');
		assertEqual(res, '<div class="clearfix"><label class="for-form" for="BtvChannel_i18nName_ru_RU">Название</label><textarea id="BtvChannel_i18nName_ru_RU">инициализация переменной</textarea></div>');
		res = Admin.render.doRendering(ob, 'C', 'en_EN');
		assertEqual(res, '<div class="clearfix"><label class="for-form" for="BtvChannel_i18nName_en_EN">Название</label><textarea id="BtvChannel_i18nName_en_EN">init value</textarea></div>');
	},
	
	test_rendering_I18nArea_U : function(){
		var ob = new Admin.types.I18nArea({ru_RU : 'инициализация переменной', en_EN: 'init value'} ,'BtvChannel', 'i18nName');
		var res = Admin.render.doRendering(ob, 'U', 'ru_RU');
		assertEqual(res, '<div class="clearfix"><label class="for-form" for="BtvChannel_i18nName_ru_RU">Название</label><textarea id="BtvChannel_i18nName_ru_RU">инициализация переменной</textarea></div>');
		res = Admin.render.doRendering(ob, 'U', 'en_EN');
		assertEqual(res, '<div class="clearfix"><label class="for-form" for="BtvChannel_i18nName_en_EN">Название</label><textarea id="BtvChannel_i18nName_en_EN">init value</textarea></div>');
	},
	
			
	/* IDENTIFIER ------------------------------------------------------------------------------------------------ */ 
	test_rendering_Identifier_R : function(){
		var ob = new Admin.types.Identifier(1354,'Entity', 'id');
		var res = Admin.render.doRendering(ob, 'R');
		assertEqual(res , '<div class="clearfix"><label class="for-form">Entity.id.ru_RU</label><span class="disabled">1354</span></div>');
	},
			
	test_rendering_Identifier_C : function(){
		var ob = new Admin.types.Identifier(1354,'Entity', 'id');
		var res = Admin.render.doRendering(ob, 'C');
		assertEqual(res , '<div class="clearfix"><label class="for-form" for="Entity_id">Entity.id.ru_RU</label><input type="text" id="Entity_id" value="1354"></div>');
	},
	
	test_rendering_Identifier_U : function(){
		var ob = new Admin.types.Identifier(1354,'Entity', 'id');
		var res = Admin.render.doRendering(ob, 'U');
		assertEqual(res , '<div class="clearfix"><label class="for-form" for="Entity_id">Entity.id.ru_RU</label><input type="text" id="Entity_id" value="1354"></div>');
	},
	
	
	
	/* BOOLEAN ------------------------------------------------------------------------------------------------ */ 
	test_rendering_Boolean_R : function(){
		var ob = new Admin.types.Boolean(true,'Entity', 'fBoolean','en_EN');
		var res = Admin.render.doRendering(ob, 'R');
		assertTrue(res.length == 97);
	},
			
	test_rendering_Boolean_C : function(){
		var ob = new Admin.types.Boolean(true,'Entity', 'fBoolean');
		var res = Admin.render.doRendering(ob, 'C');
		assertTrue(res.length == 155);
	},
	
	test_rendering_Boolean_U : function(){
		var ob = new Admin.types.Boolean(true,'Entity', 'fBoolean');
		var res = Admin.render.doRendering(ob, 'U');
		assertTrue(res.length == 155);
	},
	
	test_rendering_Boolean_U_disabled : function(){
		var ob = new Admin.types.Boolean(false,'Entity', 'fBoolean');
		var res = Admin.render.doRendering(ob, 'U');
		assertTrue(res.length == 147);
	},
	
	/* ENUM ------------------------------------------------------------------------------------------------ */ 
	test_rendering_Enum_R : function(){
		var ob = new Admin.types.Enum("NONE",'Entity', 'fEnum');
		var res = Admin.render.doRendering(ob, 'R');
		assertEqual(res, '<div class="clearfix"><label class="for-form">Entity.fEnum.ru_RU</label><span>EncryptionType.NONE.ru_RU</span></div>');
	},
			
	test_rendering_Enum_C : function(){
		var ob = new Admin.types.Enum(null,'Entity', 'fEnum');
		var res = Admin.render.doRendering(ob, 'C');
		assertEqual(res, '<div class="clearfix"><label class="for-form" for="Entity_fEnum">Entity.fEnum.ru_RU</label><select class="combobox" id="Entity_fEnum"><option value=""></option><option value="NONE">EncryptionType.NONE.ru_RU</option><option value="VERIMATRIX">EncryptionType.VERIMATRIX.ru_RU</option><option value="WIDEVINE">EncryptionType.WIDEVINE.ru_RU</option><option value="SECUREMEDIA">EncryptionType.SECUREMEDIA.ru_RU</option><option value="VIACCESS">EncryptionType.VIACCESS.ru_RU</option></select></div>');
	},
	
	test_rendering_Enum_U : function(){
		var ob = new Admin.types.Enum("NONE",'Entity', 'fEnum');
		var res = Admin.render.doRendering(ob, 'U');
		assertEqual(res, '<div class="clearfix"><label class="for-form" for="Entity_fEnum">Entity.fEnum.ru_RU</label><select class="combobox" id="Entity_fEnum"><option value=""></option><option value="NONE" selected>EncryptionType.NONE.ru_RU</option><option value="VERIMATRIX">EncryptionType.VERIMATRIX.ru_RU</option><option value="WIDEVINE">EncryptionType.WIDEVINE.ru_RU</option><option value="SECUREMEDIA">EncryptionType.SECUREMEDIA.ru_RU</option><option value="VIACCESS">EncryptionType.VIACCESS.ru_RU</option></select></div>' );
	},
	
	test_rendering_Enum_U_required : function(){
		var ob = new Admin.types.Enum("NONE",'Entity', 'fEnumRequired');
		var res = Admin.render.doRendering(ob, 'U');
		assertEqual(res, '<div class="clearfix"><label class="for-form" for="Entity_fEnumRequired">Entity.fEnumRequired.ru_RU<span class="required">*</span></label><select class="combobox" id="Entity_fEnumRequired"><option value="NONE" selected>EncryptionType.NONE.ru_RU</option><option value="VERIMATRIX">EncryptionType.VERIMATRIX.ru_RU</option><option value="WIDEVINE">EncryptionType.WIDEVINE.ru_RU</option><option value="SECUREMEDIA">EncryptionType.SECUREMEDIA.ru_RU</option><option value="VIACCESS">EncryptionType.VIACCESS.ru_RU</option></select></div>' );
	},
	
	
	/* ALONE ------------------------------------------------------------------------------------------------ */ 
	test_rendering_Alone_R_idUndefined : function(){
		var ob = new Admin.types.Alone(333,'Entity', 'fAloneIdentifier');
		var res = Admin.render.doRendering(ob, 'R');
		assertEqual(res , '<div class="clearfix"><label class="for-form">Entity.fAloneIdentifier.ru_RU</label><span class="undefined">не определено</span></div>');
	},
	
	test_rendering_Alone_R_id : function(){
		var ob = new Admin.types.Alone(3,'Entity', 'fAloneIdentifier');
		var res = Admin.render.doRendering(ob, 'R');
		assertEqual(res , '<div class="clearfix"><label class="for-form">Entity.fAloneIdentifier.ru_RU</label><span>История</span></div>');
	},
	
	test_rendering_Alone_C_id : function(){
		var ob = new Admin.types.Alone(3,'Entity', 'fAloneIdentifier');
		var res = Admin.render.doRendering(ob, 'C');
		assertEqual(res , '<div class="clearfix"><label class="for-form" for="Entity_fAloneIdentifier">Entity.fAloneIdentifier.ru_RU</label><select class="combobox" id="Entity_fAloneIdentifier"><option value=""></option><option value="1" >Новости</option><option value="2" >Развлечения</option><option value="3" selected>История</option></select></div>');
	},
	
	test_rendering_Alone_U_id : function(){
		var ob = new Admin.types.Alone(3,'Entity', 'fAloneIdentifier');
		var res = Admin.render.doRendering(ob, 'U');
		assertEqual(res , '<div class="clearfix"><label class="for-form" for="Entity_fAloneIdentifier">Entity.fAloneIdentifier.ru_RU</label><select class="combobox" id="Entity_fAloneIdentifier"><option value=""></option><option value="1" >Новости</option><option value="2" >Развлечения</option><option value="3" selected>История</option></select></div>');
	},
	
	/* SET ------------------------------------------------------------------------------------------------ */ 
	test_rendering_Set_R : function(){
		var ob = new Admin.types.Set([2, 3],'Entity', 'fSet');
		var res = Admin.render.doRendering(ob, 'R');
		assertEqual(res , '<table class="commonTable"><thead><tr><td colspan="1">Entity.fSet.ru_RU</td></tr></thead><tr><th>#</th><th>Название</th></tr><tr class="data-mode-R" onclick="viewManager.showRecord(\'Genre\', 2,\'R\')"><td>1</td><td>Развлечения</td></tr><tr class="data-mode-R" onclick="viewManager.showRecord(\'Genre\', 3,\'R\')"><td>2</td><td>История</td></tr></table>');
	},
	
	
	test_rendering_Set_C : function(){
		var ob = new Admin.types.Set([2, 3],'Entity', 'fSet');
		var res = Admin.render.doRendering(ob, 'C');
		assertEqual(res , '<table class="commonTable"><thead><tr><td colspan="2">Entity.fSet.ru_RU</td><td class="for-icon"><i title="Редактировать" class="glyphicon glyphicon-edit" onclick="viewManager.modifyCollectionFor(\'Entity\', \'fSet\')"></i></td></tr></thead><tr><th>#</th><th>Название</th><th></th></tr><tr class="data-mode-C" onclick="viewManager.showRecord(\'Genre\', 2,\'R\')"><td>1</td><td>Развлечения</td></tr><tr class="data-mode-C" onclick="viewManager.showRecord(\'Genre\', 3,\'R\')"><td>2</td><td>История</td></tr></table>');
	},
	
	test_rendering_Set_U : function(){
		var ob = new Admin.types.Set([2, 3],'Entity', 'fSet');
		var res = Admin.render.doRendering(ob, 'U');
		assertTrue(res.length == 502);
	},
	
});


}



