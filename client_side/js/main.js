var settings = {};
settings.def = {};
settings.locale = 'ru_RU';
settings.def.locale='ru_RU';

var requestManager = new Admin.RequestManager();
var viewManager = new Admin.ViewController();
var controllerManager = new Admin.ControllerManager();


var jms = new Admin.Jms();

/* ___________________________________________________________________________ */



function loadMenu(){
  $('#MAIN_MENU').html("");
  for(var i= 0; i < MAIN_MENU.length; i++){
    $('#MAIN_MENU').append(prepareMenuTree(1 , MAIN_MENU[i])); 
  }
};

function prepareMenuItem(level, ar){
  var pixels = level * 20;
  var div = '';
  if(!ar.menu){
      try{
	// set tableParams
	eval(ar.type + "TableController.initParams(ar.table)");
	// set recordParams
	eval(ar.type + "RecordController.initParams(ar.record)");
      }catch(e){}
      div = '<div class="dv" style="padding-left: ' + pixels + 'px;"><a href="javascript:viewManager.showMainDatas(\'' + ar.type + '\')">' + ar.name + '</a></div>';
  }else{
      div = '<div class="dv" style="padding-left: ' + pixels + 'px;">' + ar.name + '</div>';
  }
  return div;
};

function prepareMenuTree(level, ar){
  var res = prepareMenuItem(level , ar);  
  if(ar.menu){
    for(var key in ar.menu){
      res += prepareMenuTree(level + 1, ar.menu[key]);
    }
  }
  return res;
}


function func2(){
	var f = new BB();
	alert(f.getE());
}


BB = function(){
	var e = '11';
	this.getIE = function(){return e;};
	
	function privateMethod(){return e;};
	
};
BB.prototype.getE = function(){
	return privateMethod();
};
