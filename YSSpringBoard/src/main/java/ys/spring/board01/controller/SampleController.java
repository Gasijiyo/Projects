package ys.spring.board01.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

// POJO (Plain Old Java Object)
@Controller
public class SampleController {
	private static final Logger logger = LoggerFactory.getLogger(SampleController.class);
	
	@RequestMapping(value = "sample1", method = RequestMethod.GET)
	public String testSample1() {
		logger.info("testSample1() 호출");
		
		return "sample1";
	}
	
	@RequestMapping(value = "sample2", method = RequestMethod.GET)
	public void testSample2() {
		logger.info("testSample2() 호출");
	}
	
	
}
