if(typeof Admin == 'undefined'){Admin = {};}

Admin.RequestManager = function(){
  
  this.inProgress = false;
  this.callbackController = null;
  this.url = null;
  this.params = null;
  
    
  /* GET request ================================================================================= */
  Admin.RequestManager.prototype.doGet = function(url, params , callback_controller){
    if(this.inProgress){
      alert("Request in progress now! Please, waiting for completition of the last one.");
      return;
    }
    this.inProgress = true;
    this.callbackController = callback_controller;
    this.url = url;
    this.params = params;
    $.ajax({ 	context : this,
		    url: this.url + Admin.RequestManager.prepareParamsString(this.params),       
		    cache : false,
		    type : "GET",
		    async : false,
		    dataType : "json",
		    success: this.callback_success,
		    error : this.callback_error});  
  };
  
  /* POST request ================================================================================= */
  Admin.RequestManager.prototype.doPost = function(url, params , callback_controller){
    if(this.inProgress){
      alert("Request in progress now! Please, waiting for completition of the last one.");
      return;
    }
    this.inProgress = true;
    this.callbackController = callback_controller;
    this.url = url;
    this.params = params;
    $.ajax({ 	context : this,
		    url: this.url,       
		    cache : false,
		    type : "POST",
		    async : false,
		    data : params,
		    success: this.callback_success,
		    error : this.callback_error});  
  };
    
  /* callback SUCCESS */
  Admin.RequestManager.prototype.callback_success = function(data, textStatus, xhr){
	if(xhr.status == 200){
      this.callbackController.initLoadedData(data);
    }else{
      alert('Status: ' + xhr.status + "(" + textStatus + ")");
    }
    this.resetAllFields();
  };

  /* callback ERROR */ 
  Admin.RequestManager.prototype.callback_error = function(xhr, ajaxOptions, thrownError){
    alert(xhr.status);
    alert(thrownError);
    this.resetAllFields();
  };								
  
  Admin.RequestManager.prototype.resetAllFields = function(){
    this.inProgress = false;
    this.callbackController = null;
    this.url = null;
    this.params = null;
  };
  
};


Admin.RequestManager.prepareParamsString = function(params){
  var r = '';
  for(var key in params){
    if(r.length == 0){
      r = "?" + key + "=" + params[key];
    }else{
      r += "&" + key + "=" + params[key];
    }
  }
  return r;
};


