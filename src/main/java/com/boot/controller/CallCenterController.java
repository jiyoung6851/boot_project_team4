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
import org.springframework.web.bind.annotation.RequestMethod;



@Controller
@Slf4j
public class CallCenterController {
	
	@Autowired
	private CallCenterService service;
	
	@RequestMapping("/callcenter")
	public String callcenter(@RequestParam HashMap<String, String> param, Model model, HttpSession session, Criteria cri) {
		log.info("@# callcenter");
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
		String authorid = (String) session.getAttribute("id");
		param.put("authorid", authorid);
		
		model.addAttribute("callview", service.callselect(param));
		
		return "callcenter/call_view";
	}
	
	@RequestMapping("/callcenter_search")
	public String callcenter_search(@RequestParam HashMap<String, String> param, Model model, HttpSession session, Criteria cri) {
		log.info("@# callcenter");
		String authorid = (String) session.getAttribute("id");
		param.put("authorid", authorid);
		cri.setPageNum(1);
		
		param.put("pageNum", cri.getPageNum()+"");
		param.put("amount", cri.getAmount()+"");
		
		int total = service.getTotalCount(param);
		
		model.addAttribute("calllist", service.calllist(param));
		model.addAttribute("pageMaker", new PageDTO(total, cri)); //페이징
		
		return "callcenter/callcenter";
	}
	
	/*관리자 계정*/
	@RequestMapping("/admincall")
	public String admincall(@RequestParam HashMap<String, String> param, Model model, Criteria cri) {
		log.info("@# admincall");
		
		param.put("pageNum", cri.getPageNum()+"");
		param.put("amount", cri.getAmount()+"");
		
		int total = service.getTotalCountAll(param);
		
		model.addAttribute("calllist", service.CallAllList(param));
		model.addAttribute("pageMaker", new PageDTO(total, cri)); //페이징
		
		return "/admin/admincall";
	}
	
	@RequestMapping("/admin_call_search")
	public String admin_call_search(@RequestParam HashMap<String, String> param, Model model, Criteria cri) {
		log.info("@# admin_call_search");
		
		cri.setPageNum(1);
		
		param.put("pageNum", cri.getPageNum()+"");
		param.put("amount", cri.getAmount()+"");
		
		int total = service.getTotalCountAll(param);
		
		model.addAttribute("calllist", service.CallAllList(param));
		model.addAttribute("pageMaker", new PageDTO(total, cri)); //페이징
		
		return "/admin/admincall";
	}
	
	@RequestMapping("/admin_call_detail")
	public String admin_call_detail(@RequestParam HashMap<String, String> param, Model model) {
		log.info("@# admin_call_detail");
		model.addAttribute("calldetail", service.Calldetail(param));
		
		return "/admin/admincall_view";
	}
	
	@RequestMapping("/admin_reply")
	public String admin_reply(@RequestParam HashMap<String, String> param) {
		log.info("@# admin_reply");
		return "redirect:admin_call_detail";
	}
	
}