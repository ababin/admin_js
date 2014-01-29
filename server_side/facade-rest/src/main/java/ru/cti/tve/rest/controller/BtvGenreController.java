package ru.cti.tve.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import ru.cti.tve.rest.service.EntityService;


@Controller
@RequestMapping(value="/genre/btv")
public class BtvGenreController extends BaseController{

	@Autowired
	@Qualifier("btvGenreService")
	EntityService service;
	
	protected EntityService getService(){
		return service;
	}
	
}
