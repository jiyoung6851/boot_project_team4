package com.boot.controller;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.boot.service.OfferService;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class OfferContorller {
	
	@Autowired
	private OfferService service;
	
	@RequestMapping("offerPopup")
	public String offerPopup(@RequestParam HashMap<String, String> param, Model model) {
		log.info("offerPopup");
		
		model.addAttribute("data", param);
		return "offer/offerPopup";
	}
}
