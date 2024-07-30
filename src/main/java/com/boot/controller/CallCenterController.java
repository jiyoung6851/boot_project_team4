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
import net.nurigo.sdk.NurigoApp;
import net.nurigo.sdk.message.model.Message;
import net.nurigo.sdk.message.request.SingleMessageSendingRequest;
import net.nurigo.sdk.message.response.SingleMessageSentResponse;
import net.nurigo.sdk.message.service.DefaultMessageService;

import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@Slf4j
public class CallCenterController {
	
	@Autowired
	private CallCenterService service;
	
	/* sms 전송을 위한 세팅 */
	final DefaultMessageService messageService;

    public CallCenterController() {
        // 반드시 계정 내 등록된 유효한 API 키, API Secret Key를 입력해주셔야 합니다!
        this.messageService = NurigoApp.INSTANCE.initialize("NCSEPSQXUWDO2WGS", "BJUJJHURG1BIKIUKNKJLH1XIIQWPIYSL", "https://api.coolsms.co.kr");
    }
    /* sms 전송을 위한 세팅 */
    
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
	
	@RequestMapping("/callupdate")
	public String callUpdate(@RequestParam HashMap<String, String> param, Model model) {
		log.info("@# callcenter");
		log.info("@# param => " + param);
		
		
		
		return "redirect:call_view";
	}
	/*관리자 계정*/
	@RequestMapping("/admincall")
	public String admincall(@RequestParam HashMap<String, String> param, Model model, Criteria cri) {
		log.info("@# admincall");
		
		if(param.get("callyn") == null) param.put("callyn", "n");
		
		param.put("pageNum", cri.getPageNum()+"");
		param.put("amount", cri.getAmount()+"");
		
		int total = service.getTotalCountAll(param);
		
		model.addAttribute("calllist", service.CallAllList(param));
		model.addAttribute("pageMaker", new PageDTO(total, cri)); //페이징
		model.addAttribute("callyn", param.get("callyn"));
		
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
		model.addAttribute("callyn", param.get("callyn"));
		
		return "/admin/admincall";
	}
	
	@RequestMapping("/admin_call_detail")
	public String admin_call_detail(@RequestParam HashMap<String, String> param, Model model) {
		log.info("@# admin_call_detail");
		log.info("#@ param => "+param);
		
		model.addAttribute("calldetail", service.Calldetail(param));
		model.addAttribute("pageMaker", param);
		
		return "/admin/admincall_view";
	}
	
	@RequestMapping("/admin_reply")
	public String admin_reply(@RequestParam HashMap<String, String> param) {
		log.info("@# admin_reply");
		log.info("@# param => " + param);
		
		service.callUpdate(param); //문의 답변 글 업데이트
		
		/* 문자 전송을 위한 세팅 금액 문제로 현재는 주석 처리 */
		//Message message = new Message();
        // 발신번호 및 수신번호는 반드시 01012345678 형태로 입력되어야 합니다.
        //message.setFrom("01049190758");
        //message.setTo("01033733551");
        //message.setText("접수하신 문의글에 답변이 등록되었습니다. 로그인 후 확인 가능합니다.");

        //SingleMessageSentResponse response = this.messageService.sendOne(new SingleMessageSendingRequest(message));
        /* 문자 전송을 위한 세팅 */
        
		String sending = "?callno="+param.get("callno")+"&authorid="+param.get("authorid")+"&pageNum="+param.get("pageNum")+"&amount="+param.get("amount")+"&type="+param.get("type")+"&keyword="+param.get("keyword");
		return "redirect:admin_call_detail"+sending;
	}
	
}