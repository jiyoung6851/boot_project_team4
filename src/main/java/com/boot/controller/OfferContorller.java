package com.boot.controller;

import java.util.Date;
import java.util.HashMap;

import javax.servlet.http.HttpSession;

import org.apache.ibatis.javassist.expr.NewArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.boot.dto.Criteria;
import com.boot.dto.PageDTO;
import com.boot.service.OfferService;

import lombok.extern.slf4j.Slf4j;
import okhttp3.Response;

import org.springframework.web.bind.annotation.RequestMethod;


@Controller
@Slf4j
public class OfferContorller {
	
	@Autowired
	private OfferService service;
	
	@RequestMapping("offerview")
	public String offerview(@RequestParam HashMap<String, String> param, Model model, HttpSession session, Criteria cri) {
		log.info("@# offerview");
		param.put("puserid", (String)session.getAttribute("id"));
		
		// 페이지 정보 설정
		param.put("pageNum", cri.getPageNum() + "");
		param.put("amount", cri.getAmount() + "");
		
		// 총 데이터 수
		int total = service.getTotalCount(param);
		
		model.addAttribute("offerlist", service.offerlist(param));
		model.addAttribute("pageMaker", new PageDTO(total, cri));
		return "offer/offerview";
	}
	
	@RequestMapping("offerPopup")
	public String offerPopup(@RequestParam HashMap<String, String> param, Model model) {
		log.info("offerPopup");
		
		Date today = new Date();
		
		model.addAttribute("data", param);
		model.addAttribute("date", today);
		return "offer/offerPopup";
	}
	
	@RequestMapping("offerViewPopup")
	public String offerViewPopup(@RequestParam HashMap<String, String> param, Model model) {
		log.info("offerViewPopup");
		
		model.addAttribute("data", service.offerValue(param));
		return "offer/offerViewPopup";
	}
	
	@RequestMapping("offersave")
	public ResponseEntity<String> offersave(@RequestParam HashMap<String, String> param, Model model, HttpSession session) {
		log.info("@# offersave");
		
		param.put("cuserid", (String)session.getAttribute("id"));
		service.offersave(param);
		
		return ResponseEntity.ok("success");
	}
	
	@RequestMapping("offeryn")
	public ResponseEntity<Object> offeryn(@RequestParam HashMap<String, String> param) {
		log.info("@# offeryn");
		log.info("@# param => " + param);
		String message = "";
		String gubun = param.get("gubun");
		if(param.get("gubun").equals("Y")) {
			service.offerreply(param);
			message = "해당 제안을 수락했습니다.";
		} else {
			service.offerreply(param);
			message = "해당 제안을 거절했습니다.";
		}
		
		return ResponseEntity.ok().body("{\"message\": \""+message+"\", \"gubun\": \""+gubun+"\"}");
	}
}