package com.boot.controller;

import java.util.HashMap;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.boot.dto.Criteria;
import com.boot.dto.PageDTO;
import com.boot.service.CallCenterService;

import lombok.extern.slf4j.Slf4j;


@Controller
@Slf4j
public class CallCenterController {
	
	@Autowired
	private CallCenterService service;
	
	@RequestMapping("/callcenter")
	public String callcenter(@RequestParam HashMap<String, String> param, Model model, HttpSession session, Criteria cri) {
		log.info("@# callcenter");
		log.info("@# param => " + param);
		String authorid = (String) session.getAttribute("id");
		param.put("authorid", authorid);
		
		param.put("pageNum", cri.getPageNum()+"");
		param.put("amount", cri.getAmount()+"");
		
		int total = service.getTotalCount(param);
		
		model.addAttribute("calllist", service.calllist(param));
		model.addAttribute("pageMaker", new PageDTO(total, cri)); //페이징
		
		return "callcenter/callcenter";
	}
	
	@RequestMapping("/callwriteform")
	public String callwriteform() {
		return "callcenter/call_write";
	}
	
	@RequestMapping("/callwriteadd")
	public String callwriteadd(@RequestParam HashMap<String, String> param, HttpSession session) {
		log.info("@# callwriteadd");
		String authorid = (String) session.getAttribute("id");
		param.put("authorid", authorid);
		
		service.callwrite(param);
		
		return "redirect:callcenter";
	}
	
	@RequestMapping("/call_view")
	public String call_view(@RequestParam HashMap<String, String> param, Model model, HttpSession session) {
		log.info("@# call_view");
		log.info("@# param => " + param);
		
		String authorid = (String) session.getAttribute("id");
		param.put("authorid", authorid);
		
		model.addAttribute("callview", service.callselect(param));
		
		
		return "callcenter/call_view";
	}
}
