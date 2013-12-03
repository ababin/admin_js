var model = {};

model.URLS = {
	'BtvChannel' : {
		'table' : 'data/table/btvChannels_{page}_{pageSize}.json',
		'record' : 'data/record/btvChannel.json'
	},

	'MediaAsset' : {
		'table' : 'data/table/mediaAssets.json',
		'record' : 'data/record/mediaAsset{id}.json'
	},
	'BandwidthRating' : {
		'table' : 'data/table/bandwidthRating.json',
		'record' : 'data/record/mediaAsset100.json'
	},

	'AgeRating' : {
		'table' : 'data/table/ageRatings.json',
		'record' : 'data/record/ageRating1.json'

	},

	'Genre' : {
		'table' : 'data/table/genres.json',
		'record' : 'data/record/genre1.json'

	},

	'Region' : {
		'table' : 'data/table/regions.json',
		'record' : 'data/record/region1.json'
	},
	
	TestControllerEntity : {
		persist : 'url/persist'
	},

};

model.CLASSES = {

	Entity : {
		fields : {
			id : {type : "t:Identifier"	},
			fString : {	type : "t:String",size : 255,nullable : false},
			fI18nString : {	type : "t:I18nString",	size : 3800	},
			fI18nArea : {type : "t:I18nArea",size : 3800},
			fInteger : {type : "t:Integer"},
			fIntegerRequired : {type : "t:Integer",	isNullable : false},
			fBoolean : {type : "t:Boolean"},
			fImageUrl : {type : "t:ImageUrl"},
			fAloneObject : {type : "AgeRating"	},
			fAloneIdentifier : {type : "Genre",isIdentifier : true},
			fEnum : {type : "e:EncryptionType"},
			fEnumRequired : {type : "e:EncryptionType",	isNullable : false},
			fSet : {type : "Genre",	multitude : 'set',	isIdentifier : true	},
		},
		params : {pagedLoading : false},
		table : {
			fields : [
					"id",
					"i18nName",
					"i18nDesc",
					"status",
					{
						"name" : "primaryAddress",
						"val" : "_ob.primaryProtocol + '://' + _ob.primaryAddress + ':' + _ob.primaryPortNumber"
					} ],
		},
		record : {
			fields : [ "id", "i18nName", "i18nDesc", "status", "ageRating",
					"logo", "sortOrder", "barkerPosition", "doScreenshots",
					"format", "channelUrl", "primaryAddress",
					"primaryProtocol", "primaryPortNumber", "genres",
					"regions", "mediaSources" ],
		},
	},

	TestControllerEntity : {
		fields : {
			id : {type : "t:Identifier"	},
			fString : {	type : "t:String",size : 255,nullable : false},
			fI18nString : {	type : "t:I18nString",	size : 3800	},
			fI18nArea : {type : "t:I18nArea",size : 3800},
			fInteger : {type : "t:Integer"},
			fIntegerRequired : {type : "t:Integer",	isNullable : false},
			fBoolean : {type : "t:Boolean"},
			fImageUrl : {type : "t:ImageUrl"},
			fAloneObject : {type : "AgeRating"	},
			fAloneIdentifier : {type : "Genre",isIdentifier : true},
			fEnum : {type : "e:EncryptionType"},
			fEnumRequired : {type : "e:EncryptionType",	isNullable : false},
			fSet : {type : "Genre",	multitude : 'set',	isIdentifier : true	},
		},
		params : {pagedLoading : false},
		table : {
			fields : [
					"id",
					"i18nName",
					"i18nDesc",
					"status",
					{
						"name" : "primaryAddress",
						"val" : "_ob.primaryProtocol + '://' + _ob.primaryAddress + ':' + _ob.primaryPortNumber"
					} ],
		},
		record : {
			fields : [ "id", "i18nName", "i18nDesc", "status", "ageRating",
					"logo", "sortOrder", "barkerPosition", "doScreenshots",
					"format", "channelUrl", "primaryAddress",
					"primaryProtocol", "primaryPortNumber", "genres",
					"regions", "mediaSources" ],
		},
	},
	
	
	"BtvChannel" : {
		"fields" : {
			"id" : {
				"type" : "identifier"
			},
			"i18nName" : {
				"type" : "i18nString",
				"size" : 255,
				"nullable" : false
			},
			"i18nDesc" : {
				"type" : "i18nArea",
				"size" : 3800
			},
			"status" : {
				"type" : "LifeCycleState",
				"nullable" : false,
				"def" : "INACTIVE"
			},
			"genres" : {
				"type" : "Genre",
				"multitude" : "set"
			},
			"mediaSources" : {
				"type" : "MulticastChannel,MediaAsset",
				"multitude" : "set"
			},
			"ageRating" : {
				"type" : "AgeRating",
				"selectable" : true
			},
			"regions" : {
				"type" : "Region",
				"multitude" : "set",
				"selectable" : true
			},
			"logo" : {
				"type" : "image"
			},
			"sortOrder" : {
				"type" : "number",
				"nullable" : false
			},
			"barkerPosition" : {
				"type" : "number"
			},
			"doScreenshots" : {
				"type" : "boolean"
			},
			"format" : {
				"type" : "type:String"
			},
			"channelUrl" : {
				"type" : "string"
			},
			"primaryProtocol" : {
				"type" : "string"
			},
			"primaryAddress" : {
				"type" : "string"
			},
			"primaryPortNumber" : {
				"type" : "number"
			},
		},
		"table" : {
			"fields" : [
					"id",
					"i18nName",
					"i18nDesc",
					"status",
					{
						"name" : "primaryAddress",
						"val" : "_ob.primaryProtocol + '://' + _ob.primaryAddress + ':' + _ob.primaryPortNumber"
					} ],
		},
		"record" : {
			"fields" : [ "id", "i18nName", "i18nDesc", "status", "ageRating",
					"logo", "sortOrder", "barkerPosition", "doScreenshots",
					"format", "channelUrl", "primaryAddress",
					"primaryProtocol", "primaryPortNumber", "genres",
					"regions", "mediaSources" ],
		},
		"perms" : "CRUD"
	},

	Genre : {
		fields : {
			id : {
				type : "t:Identifier"
			},
			i18nName : {
				type : "t:I18nString",
				size : 255,
				nullable : false
			},
			i18nDesc : {
				type : "t:I18nArea",
				size : 3800
			},
			sortOrder : {
				type : "t:Number",
				nullable : false
			},
			categoryType : {
				type : "CategoryType"
			},
		},
		params : {
			pagedLoading : false
		},
		table : {
			fields : [ {
				name : "i18nName",
				val : "_ob.i18nName.ru_RU"
			} ]
		},
		record : {
			fields : [ "id", "i18nName", "i18nDesc", "sortOrder",
					"categoryType" ]
		},
		contextTable : {
			common : {
				fields : [ {
					name : "i18nName",
					val : "_ob.i18nName.ru_RU"
				} ]
			},
			BtvChannel : {
				fields : [ {
					name : "i18nName",
					val : "_ob.i18nName.ru_RU"
				} ]
			},
		},
		contextValue : {
			common : {
				val : "_ob.i18nName['ru_RU']"
			},
		},

	},

	"CategoryType" : {
		"fields" : {
			"id" : {
				"type" : "identifier"
			},
			"name" : {
				"type" : "string",
				"size" : 255,
				"nullable" : false
			},
			"desc" : {
				"type" : "string",
				"size" : 3800
			},
		}
	},

	"BandwidthRating" : {
		"fields" : {
			"id" : {
				"type" : "identifier"
			},
			"name" : {
				"type" : "string",
				"size" : 255,
				"nullable" : false
			},
			"desc" : {
				"type" : "string",
				"size" : 3800
			},
			"code" : {
				"type" : "string",
				"size" : 255,
				"nullable" : false
			},
			"level" : {
				"type" : "number",
				"nullable" : false
			},

		},
		"contextRecord" : {
			"common" : {
				"name" : "bandwidthRating",
				"val" : "_ob.name"
			},

		},
	},

	"AgeRating" : {
		"fields" : {
			"id" : {
				"type" : "identifier"
			},
			"i18nName" : {
				"type" : "i18nString",
				"size" : 255,
				"nullable" : false
			},
			"i18nDesc" : {
				"type" : "i18nArea",
				"size" : 3800
			},
			"code" : {
				"type" : "string",
				"size" : 255,
				"nullable" : false
			},
			"level" : {
				"type" : "number",
				"nullable" : false
			},

		},
		"contextRecord" : {
			"common" : {
				"name" : "name",
				"val" : "_ob.i18nName['ru_RU']"
			},

		},
	},

	"MulticastChannel" : {
		"fields" : {
			"id" : {
				"type" : "identifier"
			},
			"i18nName" : {
				"type" : "i18nString",
				"size" : 255,
				"nullable" : false
			},
			"i18nDesc" : {
				"type" : "i18nArea",
				"size" : 3800
			},
			"encryption" : {
				"type" : "EncryptionType",
				"nullable" : false,
				"default" : "NONE"
			},
			"copyProtection" : {
				"type" : "boolean"
			},
			"encoding" : {
				"type" : "MediaEncodingType",
				"nullable" : false,
				"default" : "MPEG2"
			},
			"bitrate" : {
				"type" : "number"
			},
			"volume" : {
				"type" : "number"
			},
			"protocol" : {
				"type" : "string",
				"nullable" : false
			},
			"application" : {
				"type" : "string"
			},
			"bandwidthRating" : {
				"type" : "BandwidthRating",
				"selectable" : true
			},
			"resolution" : {
				"type" : "ResolutionType",
				"nullable" : false,
				"default" : "SD"
			},
			"mcastAddress" : {
				"type" : "string",
				"nullable" : false
			},
			"mcastPortNumber" : {
				"type" : "number",
				"nullable" : false
			}
		},
	},

	"MediaAsset" : {
		"fields" : {
			"id" : {
				"type" : "identifier"
			},
			"i18nName" : {
				"type" : "i18nString",
				"size" : 255,
				"nullable" : false
			},
			"i18nDesc" : {
				"type" : "i18nArea",
				"size" : 3800
			},
			"encryption" : {
				"type" : "EncryptionType",
				"nullable" : false,
				"default" : "NONE"
			},
			"copyProtection" : {
				"type" : "boolean"
			},
			"encoding" : {
				"type" : "MediaEncodingType",
				"nullable" : false,
				"default" : "MPEG2"
			},
			"bitrate" : {
				"type" : "number"
			},
			"volume" : {
				"type" : "number"
			},
			"protocol" : {
				"type" : "string",
				"nullable" : false
			},
			"application" : {
				"type" : "string"
			},
			"bandwidthRating" : {
				"type" : "BandwidthRating",
				"selectable" : true
			},
			"resolution" : {
				"type" : "ResolutionType",
				"nullable" : false,
				"default" : "SD"
			},
			"fileName" : {
				"type" : "string",
				"nullable" : false
			},
			"fileSize" : {
				"type" : "number"
			},
			"duration" : {
				"type" : "number"
			}
		},
		"table" : {
			"fields" : [ "id" ]
		},
		"record" : {
			"fields" : [ "id", "protocol", "application", "fileName",
					"fileSize", "copyProtection", "duration", "resolution",
					"encoding", "bandwidthRating" ]
		},
		"contextTable" : {
			"common" : {
				"fields" : [
						"protocol",
						{
							"name" : "commonSource",
							"val" : "_ob.protocol + '://' + _ob.application + '/' + _ob.fileName"
						} ]
			},
			"BtvChannel" : {
				"fields" : [
						"protocol",
						{
							"name" : "commonSource",
							"val" : "_ob.protocol + '://' + _ob.application + '/' + _ob.fileName"
						} ]
			},
		},

	},

};


model.CLASSES.EntityValidate = {
	fields : {
		fIntegerNullable : {type : "t:Integer",	nullable : true},
		fIntegerNotNullable : {type : "t:Integer",	nullable : false},
		fIntegerMax : {type : "t:Integer",	max : 99},
		fIntegerMin : {type : "t:Integer",	min : 3},
	},
};



model.ENUMS = {
	"LifeCycleState" : [ "ACTIVE", "INACTIVE" ],
	"EncryptionType" : [ "NONE", "VERIMATRIX", "WIDEVINE", "SECUREMEDIA",
			"VIACCESS" ],
	"MediaEncodingType" : [ "MP3", "MPEG2", "MPEG4", "MPEG4_AC3" ],
	"ResolutionType" : [ "SD", "HD", "FULL_HD" ],

};

model.PERMS = {
	"BtvChannel" : "CRUD",
	"Videoserver" : "CRUD",
	"MediaAsset" : "CRUD",
	"BandwidthRating" : "CRUD",
	"AgeRating" : "CRUD",
	"Genre" : "CRUD",
	"Region" : "CRUD",
};
