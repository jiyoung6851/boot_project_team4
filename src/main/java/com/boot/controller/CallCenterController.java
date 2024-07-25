package com.boot.controller;

import org.springframework.stereotype.Controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@Slf4j
public class CallCenterController {
	
	@RequestMapping("/callcenter")
	public String callcenter() {
		log.info("@# callcenter");
		
		return "callcenter/callcenter";
	}
	
}
