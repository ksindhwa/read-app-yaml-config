package com.demo.spring.app.property.rest.controller;

import javax.validation.Valid;
//import javax.validation.constraints.NotEmpty;
//import javax.validation.constraints.Size;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/customized")
public class AppReadController {
	
	@Autowired
	private ConfigClass config;
	
	
	//Below 2 are the application.properties config value auto loaded by Spring boot in the Spring environment
	@Value("${app.logger.status}")
	private String appProperty1;
	
	@Value("${app.logger.description}")
	private String appProperty2;
	
	Logger logger = LoggerFactory.getLogger(AppReadController.class);
	
	@RequestMapping(method = RequestMethod.GET,value="/readyaml/{id}")
	public ResponseEntity<Object> getLoggerMessage(@PathVariable @Valid int id) {

		
		logger.info("this is logger info msg");
		logger.error("This is logger error msg");
		
		logger.info("*********************************************Following is the myapp.properties config values*********************************************");
		
		logger.info(config.getMyAppUrl());
		logger.info(config.getMyAppUsername());
		logger.info(config.getMyAppPassword());
		
		logger.info("*********************************************Following is the sysapp.properties config values*********************************************");
		
		logger.info(config.getSysAppUrl());
		logger.info(config.getSysAppUsername());
		logger.info(config.getSysAppPassword());
	
		
		
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.set("MyResponseHeader", "MyValue");
		
		return new ResponseEntity<Object>(appProperty1 + "  " + appProperty2, responseHeaders, HttpStatus.CREATED);
		
	}

	
	
}
