package ru.cti.tve.rest.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import ru.cti.tve.rest.service.EntityService;

@Controller
@RequestMapping(value = "/btvChannel")
public class BtvChannelController extends BaseController {

	@Autowired
	@Qualifier("btvChannelService")
	private EntityService service;

	protected EntityService getService() {
		return service;
	}

	@RequestMapping(value = "btvChannel", method = RequestMethod.POST)
	@ResponseBody
	public String create(Map<String,Object> map) {
		return null;
		//String newId = service.create(null);
		//return newId;
	}

}
