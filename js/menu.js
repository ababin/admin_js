

var MAIN_MENU = 
[
  {
    "name" : "BTV",
    "code" : "btv",
    "menu" : [
		  {
		    "name" : "BTV-каналы",
		    "type" : "BtvChannel",
		    "table":{
			"permissions" : "CRUD",
			"fields" : ["id", "name", "desc"],
			"fieldNames" : {
			  "id" : "ID",
			  "name" : "Имя",
			  "desc" : "Описание"
			}
		    },
		    "record" : {
			"permissions" : "CRUD",
			"fields" : ["id", "name", "desc"],
			"fieldNames" : {
			  "id" : "ID",
			  "name" : "Имя",
			  "desc" : "Описание"
			}
		    }
		  },
		  {
		    "name" : "подменю 2",
		    "type" : "qqq",
		    "permissions" : "CRUD"
		  },
		  {
		    "name" : "подменю 3",
		    "code" : "qqq2",
		    "permissions" : "CRUD"
		  },
	    ]
  },
  {
    "name" : "Ресурсы",
    "menu" : [
		  {
		    "name" : "Видеосерверы",
		    "type" : "Bideoserver",
		    "table":{
			"permissions" : "CRUD",
			"fields" : ["id", "name", "desc", "host","port","status"],
			"fieldNames" : {
			  "id" : "ID",
			  "name" : "Имя",
			  "desc" : "Описание",
			  "host" : "Адрес",
			  "port" : "Порт",
			  "status" : "Статус",
			}
		    },
		    "record" : {
			"permissions" : "CRUD",
			"fields" : ["id", "name", "desc"],
			"fieldNames" : {
			  "id" : "ID",
			  "name" : "Имя",
			  "desc" : "Описание"
			}
		    }
		  },
		  {
		    "name" : "подменю 2",
		    "type" : "qqq",
		    "permissions" : "CRUD"
		  },
		  {
		    "name" : "подменю 3",
		    "code" : "qqq2",
		    "permissions" : "CRUD"
		  },
	    ]
  },
  {
    "name" : "пункт меню 3",
    "code" : "qqq8",
    "permissions" : "CRUD"
  }
  
];