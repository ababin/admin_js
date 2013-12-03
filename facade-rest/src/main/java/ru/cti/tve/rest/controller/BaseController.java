package ru.cti.tve.rest.controller;

import java.util.Map;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import ru.cti.tve.rest.api.ResultCollection;
import ru.cti.tve.rest.service.EntityService;



public abstract class BaseController {
		
	
	@RequestMapping(value = "/listdata/{pageNum},{pageSize}", method = RequestMethod.GET)
	@ResponseBody
	public ResultCollection listData(@PathVariable int pageNum, @PathVariable int pageSize) {
		return getService().readCollection(pageNum,pageSize);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	public Map<String,Object> getItem(@PathVariable String pageNum, @PathVariable int pageSize) {
		return null;//
	}
	
	
	protected abstract EntityService getService();
	
}
