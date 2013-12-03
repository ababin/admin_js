package ru.cti.tve.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import ru.cti.tve.rest.service.EntityService;


@Controller
@RequestMapping(value="/region")
public class RegionController extends BaseController{

	@Autowired
	@Qualifier("regionService")
	EntityService service;
	
	
	protected EntityService getService(){
		return service;
	}
	
}

