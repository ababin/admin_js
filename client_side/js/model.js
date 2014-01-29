var model = {};


model.URLS = {
	
	BtvChannel : {
		table : '/app-facade/restful/btvChannel/listdata/{page},{pageSize}',  //'data/btvChannels_{page}_{pageSize}.json',
		record : 'data/record/btvChannel.json',
		persist : 'abc/restful/btvChannel',
	},

	MediaAsset : {
		table : 'data/mediaAssets.json',
		record : 'data/record/mediaAsset{id}.json'
	},
	BandwidthRating : {
		table : 'data/bandwidthRating.json',
		record : 'data/record/mediaAsset100.json'
	},

	AgeRating : {
		table : '/app-facade/restful/ageRating/listdata/{page},{pageSize}',
		record : 'data/record/ageRating1.json'

	},

	Genre : {
		table : '/app-facade/restful/genre/btv/listdata/{page},{pageSize}',
		record : 'data/record/genre1.json'

	},
	
	BtvGenre : {
		table : '/app-facade/restful/genre/btv/listdata/{page},{pageSize}',
		record : 'data/record/genre1.json'

	},

	Region : {
		table : '/app-facade/restful/region/listdata/{page},{pageSize}',
		record : 'data/record/region1.json'

	},

};

if (model.CLASSES == undefined) {
	model.CLASSES = {};
};

/* ***************************************************************************************************************************************** */
/* ***************************************************************************************************************************************** */
/* ***************************************************************************************************************************************** */
/* ***************************************************************************************************************************************** */
/* **** BtvChannel ************************************************************************************************************************* */

model.CLASSES.BtvChannel = {};
model.CLASSES.BtvChannel.fields = {
		id : {type : "t:Identifier"	},
		i18nName : {type : "t:I18nString",maxSize : 255,isNullable : false},
		i18nDesc : {type : "t:I18nArea",maxSize : 3800},
		status : {	type : "e:LifeCycleState",	isNullable : false,	defaultValue : "INACTIVE"},
		genres : {
			type : "BtvGenre", /* тип данных, который описвает это поле */
			multitude : "list", /*
								 * множественность. Пока только либо set, либо list, либо
								 * нет ничего. set - определяет, что поле
								 * представлено в виде набора сущностей
								 * указанного типа, list соответсвует массиву (Array)
								 */
			isIdentifier : true, /*
									 * данные представлены в виде списка
									 * идентификаторов
									 */
			pageable : false, /*
								 * нужно ли использовать пейджинацию при
								 * отображении этих данных
								 */
			isComposition : false, /*
									 * создается ли сущность отдельно, либо
									 * подтягивается из справочника
									 */
			isDetached : false, /*
								 * являются ли записи detached. То есть нужно ли
								 * их подгружать или брать данные из самой
								 * записи
								 */
			rel : "manyToMany"
		},
		mediaSources : {
			type : "MulticastChannel,MediaAsset",
			multitude : "set",
			isComposition : true,
			isDetached : true,
			rel : "manyToOne"
		},
		ageRating : {
			type : "AgeRating",
			isIdentifier : true,
			isNullable:false
		},
		regions : {
			type : "Region",
			multitude : "list",
			isIdentifier : true,
			rel : "manyToMany"
		},
		logo : {
			type : "t:ImageUrl"
		},
		sortOrder : {
			type : "t:Integer",
			isNullable : false,
			defaultValue : 0
		},
		barkerPosition : {
			type : "t:Integer"
		},
		doScreenshots : {
			type : "t:Boolean",
			defaultValue : false
		},
		format : {
			type : "t:String",
			isNullable : false
		},
		channelUrl : {
			type : "t:String"
		},
		primaryProtocol : {
			type : "t:String"
		},
		primaryAddress : {
			type : "t:String"
		},
		primaryPortNumber : {
			type : "t:Integer"
		},
};

model.CLASSES.BtvChannel.params = {
		pagedLoading : true
};

model.CLASSES.BtvChannel.table = {
		fields : [
		        "i18nName",
				"id",
				"status",
				{
					name : "multicast",
					val : "Admin.lib.getMulticast(_ob)"
				},
				"doScreenshots",
				{
					name : "primaryAddress",
					val : " Admin.lib.isEmptyString(_ob.primaryProtocol) ? '' : _ob.primaryProtocol + '://' + _ob.primaryAddress + ':' + _ob.primaryPortNumber"
				},
				"sortOrder"],
		columnSizes : {id : '50px', i18nName : '250px', status : '50px', sortOrder : '100px', doScreenshots : '100px'},
	};

model.CLASSES.BtvChannel.record = {
		fields : [ "id", "i18nName", "i18nDesc", "status", "ageRating",
				"sortOrder", "barkerPosition", "doScreenshots", "format",
				"channelUrl", "primaryAddress", "primaryProtocol",
				"primaryPortNumber", "genres", "mediaSources", "regions" ],
	};


model.CLASSES.BtvGenre = {
		fields : { 
			id : {type : "t:Identifier"},
			i18nName : {type : "t:I18nString",maxSize : 255,isNullable : false},
			i18nDesc : {type : "t:I18nArea",maxSize : 3800},
			sortOrder : {type : "t:Integer",	isNullable : false},
			categoryType : {type : "CategoryType"},
		},
		params : {pagedLoading : false},
		table : {
			fields : [ {name : "i18nName",val : "_ob.i18nName.ru_RU"} ]
		},
		record : {
			fields : [ "id", "i18nName", "i18nDesc", "sortOrder", "categoryType" ]
		},
		contextTable : {
			common : {
				fields : [ {name : "i18nName",val : "_ob.i18nName.ru_RU"} ]
			},
			BtvChannel : {
				fields : [ {name : "i18nName",val : "_ob.i18nName.ru_RU"} ]
			},
		},
	};

model.CLASSES.Genre = {
	fields : { 
		id : {type : "t:Identifier"},
		i18nName : {type : "t:I18nString",maxSize : 255,isNullable : false},
		i18nDesc : {type : "t:I18nArea",maxSize : 3800},
		sortOrder : {type : "t:Integer",	isNullable : false},
		categoryType : {type : "CategoryType"},
	},
	params : {pagedLoading : false},
	table : {
		fields : [ {name : "i18nName",val : "_ob.i18nName.ru_RU"} ]
	},
	record : {
		fields : [ "id", "i18nName", "i18nDesc", "sortOrder", "categoryType" ]
	},
	contextTable : {
		common : {
			fields : [ {name : "i18nName",val : "_ob.i18nName.ru_RU"} ]
		},
		BtvChannel : {
			fields : [ {name : "i18nName",val : "_ob.i18nName.ru_RU"} ]
		},
	},
};

model.CLASSES.Region = {
	fields : {
		id : {type : "t:Identifier"},
		name : {type : "t:String",maxSize:255,isNullable : false},
		desc : {type : "t:String",maxSize:255,isNullable : false},
		code : {type : "t:String",maxSize:255,isNullable : false}
	},
	params : {pagedLoading : false},
	table : {
		fields : [ "name", "code" ]
	},
	record : {
		fields : [ "id", "name", "desc" , "code" ]
	},

	contextTable : {
		common : {
			fields : [ "name", "code" ]
		},
	},

};

model.CLASSES.CategoryType = {
	fields : {
		id : {
			type : "identifier"
		},
		name : {
			type : "string",
			"size" : 255,
			"isNullable" : false
		},
		desc : {
			type : "string",
			"size" : 3800
		},
	},
	params : {
		pagedLoading : false
	},
	table : {},
};

model.CLASSES.BandwidthRating = {
	fields : {
		"id" : {
			"type" : "identifier"
		},
		"name" : {
			"type" : "string",
			"size" : 255,
			"isNullable" : false
		},
		"desc" : {
			"type" : "string",
			"size" : 3800
		},
		"code" : {
			"type" : "string",
			"size" : 255,
			"isNullable" : false
		},
		"level" : {
			"type" : "number",
			"isNullable" : false
		},

	},
	table : {
		paged : false
	},
	contextValue : {
		common : {
			val : "_ob.name"
		},
	},
};

model.CLASSES.AgeRating = {
	fields : {
		id : {type : "t:Identifier"},
		i18nName : {type : "t:I18nString",	maxSize : 255, isNullable : false},
		i18nDesc : {type : "t:I18nArea",maxSize : 3800},
		code : {type : "t:String",maxSize : 255, isNullable : false},
		level : {type : "t:Integer",isNullable : false},
	},
	params : {pagedLoading : false},
	table : {},
	contextValue : {
		common : {
			val : "_ob.i18nName['ru_RU']"
		},
	},
};


model.CLASSES.MulticastChannel = {
	fields : {
		id : {type : "t:Identifier"	},
		i18nName : {type : "t:I18nString",	maxSize : 255, isNullable : false},
		i18nDesc : {type : "t:I18nArea", maxSize : 3800 },
		encryption : {type : "e:EncryptionType", isNullable : false, defaultValue : "NONE"},
		copyProtection : {type : "t:Bboolean"},
		encoding : {type : "e:MediaEncodingType",isNullable : false, defaultValue : "MPEG2"	},
		bitrate : {type : "t:Integer"},
		volume : {type : "t:Integer"},
		protocol : {type : "t:String",isNullable : false},
		application : {type : "t:String"},
		bandwidthRating : {type : "BandwidthRating", isIdentifier : true},
		resolution : {type : "e:ResolutionType", isNullable : false, defaultValue : "SD"},
		mcastAddress : {type : "t:String",isNullable : false},
		mcastPortNumber : {type : "t:Integer", isNullable : false}
	},
	params : {	pagedLoading : false},
	table : {},
	contextTable : {
		common : {
			fields : [ 
					"protocol",
					{
						name : "commonSource",
						val : "_ob.protocol + '://' + _ob.mcastAddress + ':' + _ob.mcastPortNumber"
					} ]
		}
	},
};

model.CLASSES.MediaAsset = {
	fields : {
		id : {type : "t:Identifier"	},
		i18nName : {type : "t:I18nString", maxSize : 255,isNullable : false	},
		i18nDesc : {type : "t:I18nArea", maxSize : 3800},
		encryption : {type : "e:EncryptionType",isNullable : false,	defaultValue : "NONE"},
		copyProtection : {type : "t:Boolean"},
		encoding : {type : "e:MediaEncodingType",isNullable : false,defaultValue : "MPEG2"},		
		bitrate : {	type : "t:Integer"},
		volume : {type : "t:Integer"},
		protocol : {type : "t:String",isNullable : false},
		application : {type : "t:String"},
		bandwidthRating : {	type : "BandwidthRating",isIdentifier : true,selectable : true },
		resolution : {type : "e:ResolutionType",isNullable : false,	defaultValue : "SD"	},
		fileName : {type : "t:String",	isNullable : false},
		fileSize : {type : "t:Integer"},
		duration : {type : "t:Integer"}
	},
	params : {pagedLoading : false},
	table : {
		fields : [ "id" ]
	},
	record : {
		fields : [ "id", "protocol", "application", "fileName", "fileSize",
				"copyProtection", "duration", "resolution", "encoding",
				"bandwidthRating" ]
	},
	contextTable : {
		common : {
			fields : [ 
					"protocol",
					{
						name : "commonSource",
						val : "_ob.protocol + '://' + _ob.application + '/' + _ob.fileName"
					} ]
		}
	},

};

model.ENUMS = {
	LifeCycleState : [ "ACTIVE", "INACTIVE" ],
	EncryptionType : [ "NONE", "VERIMATRIX", "WIDEVINE", "SECUREMEDIA", "VIACCESS" ],
	MediaEncodingType : [ "MP3", "MPEG2", "MPEG4", "MPEG4_AC3" ],
	ResolutionType : [ "SD", "HD", "FULL_HD" ],
};

model.PERMS = {
	"BtvChannel" : "CRUD",
	"Videoserver" : "CRUD",
	"MediaAsset" : "CRUD",
	"BandwidthRating" : "CRUD",
	"AgeRating" : "CRUD",
	"Genre" : "CRUD",
	"BtvGenre" : "CRUD",
	"Region" : "CRUD",
};
