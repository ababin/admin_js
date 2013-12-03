//==================================================================================================================
//==================================================================================================================

if(typeof tool == 'undefined'){tool = {};}
if(typeof tool.test == 'undefined'){tool.test = {};}

tool.test.TestProcessor = function(){
	this.testObjects = new Array();
};
tool.test.TestProcessor.prototype.add = function(_ob){
	this.testObjects[this.testObjects.length] = _ob;
};

tool.test.TestProcessor.prototype.run = function(){
	
	var allView = '';
	
	// test suits
	for(var obI in this.testObjects){
		
		var ob = this.testObjects[obI];
						
		var testSuiteView =  ''; 
		var isTestSuiteOK = true;
		var index = 1;
		for(i in ob){
			// 1. INDEX
			var curTest = '<td>' + index + '</td>';
			var isCurTestOK = true;
			if(typeof ob[i] == "function"){
				// 2. TEST NAME
				curTest +=  '<td>' + i + '()</td> ';
				index ++;
				try{
					ob[i].call();
					// if error
					curTest += '<td colspan="3"></td>';
				}catch(e){
					isTestSuiteOK = false;
					isCurTestOK = false;
					// 3. ERROR TYPE
					curTest += '<td>' + e.type + '</td>';
					// 4. MESSAGE
					curTest += '<td>' + this.prepareMesForException(e) + '</td>';
					// 5. LINE NUMBER if exists
					curTest += e.firstName != undefined && e.lineNumber != undefined ? '<td>(' + e.fileName + ' : ' + e.lineNumber + ')</td>' : '<td></td>';
				}
				testSuiteView += '<tr class="' + (isCurTestOK ? 'success' : 'error') + '">' + curTest + '</tr>';  
			}
		}
		allView += '<table class="test-result' + (isTestSuiteOK ? '' : ' test-suite-error') + '">' + 
				'<tr><th colspan="5">' + ob.name + ' (' + ob.desc + ')' + '</th></tr>' +	  testSuiteView + '</table>'; 
	}
	
	var div = $('#result'); 
	div.html(allView);
};

tool.test.TestProcessor.prototype.prepareMesForException = function(e){
	switch(e.type){
	case tool.test.assert.IS_EQUAL :	if(e.val1 == undefined){return 'VALUE1: <i>undefined</i>';};
										if(e.val2 == undefined){return 'VALUE2: <i>undefined</i>';};
										return 'VALUE1 (length=' + e.val1.toString().length + '): <pre>' + 
										e.val1.toString().replace(/</g,"&lt;").replace(/>/g,"&gt;") + 
										'</pre>VALUE2 (length=' + e.val2.toString().length + '): <pre>' + e.val2.toString().replace(/</g,"&lt;").replace(/>/g,"&gt;") + '</pre>';
	default : return '';
	}
		
};


tool.test.assertNull = function(_val, mes){
	if(_val != null){
		throw {val:_val, mes:mes,type:'NULL'};
	}
};

tool.test.assertNotNull = function(_val, mes){
	if(_val == null){
		throw {val:_val,mes:mes,type:'NOT_NULL'};
	}
};

tool.test.assertEqual = function(_val1, _val2 , mes){
	if(_val1 != _val2){
		throw {val1:_val1,val2:_val2,mes:mes,type:tool.test.assert.IS_EQUAL};
	}
};

tool.test.assertIdentical = function(_val1, _val2 , mes){
	if(_val1 !== _val2){
		throw {val1:_val1,val2:_val2,mes:mes,type:'IS_IDENTICAL'};
	}
};

tool.test.assertTrue = function(_val, mes){
	if(_val !== true){
		throw {val:_val, mes:mes,type:'IS_TRUE'};
	}
};

tool.test.assertNotTrue = function(_val, mes){
	if(_val !== true){
	}else{
		throw {val:_val, mes:mes,type:'IS_NOT_TRUE'};
	}
};

tool.test.assertFalse = function(_val, mes){
	if(_val !== false){
		throw {val:_val, mes:mes,type:'IS_FALSE'};
	}
};

tool.test.assertNotFalse = function(_val, mes){
	if(_val !== false){
	}else{
		throw {val:_val, mes:mes,type:'IS_NOT_FALSE'};
	}
};

tool.test.assert = {
	IS_EQUAL : 'IS_EQUAL',
	IS_TRUE : 'IS_TRUE',
	IS_NOT_TRUE : 'IS_NOT_TRUE',
	IS_FALSE : 'IS_FALSE',
	IS_NOT_FALSE : 'IS_NOT_FALSE',
	IS_IDENTICAL : 'IS_IDENTICAL'
};

testProc = new tool.test.TestProcessor();





