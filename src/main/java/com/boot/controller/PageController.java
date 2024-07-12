package com.boot.controller;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.boot.dto.BoardtbDTO;
import com.boot.dto.CommenttbDTO;
import com.boot.dto.Criteria;
import com.boot.dto.PageDTO;
import com.boot.service.BoardService;
import com.boot.service.CommentService;
import com.boot.service.PageService;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class PageController {
	@Autowired
	private PageService service;
	
	@Autowired
	private BoardService boardservice;
	
	@Autowired
	private CommentService commentService;
	
//	@RequestMapping("/listWithPaging")
	@RequestMapping("/list")
	public String listWithPaging(Criteria cri, Model model) {
		log.info("@# list");
		log.info("@# cri=>"+cri);
		
		ArrayList<BoardtbDTO> list = service.listWithPaging(cri);
//		int total = service.getTotalCount();
		int total = service.getTotalCount(cri);
		log.info("@# total=>"+total);
		
		model.addAttribute("list", list);
//		model.addAttribute("pageMaker", new PageDTO(123, cri));
		model.addAttribute("pageMaker", new PageDTO(total, cri));
		
		return "board/list";
	}
	
	@RequestMapping("/content_view")
	public String content_view(@RequestParam HashMap<String, String> param, Model model) {
		log.info("@# content_view");
		
		boardservice.upHit(param);
		BoardtbDTO dto = boardservice.contentView(param);
		model.addAttribute("content_view", dto);
		
		
//		content_view.jsp 에서 pageMaker 를 가지고 페이징 처리
		model.addAttribute("pageMaker", param);
		
		// 해당 게시글에 작성된 댓글 리스트를 가져옴
		ArrayList<CommenttbDTO> commentList = commentService.findAll(param);
		model.addAttribute("commentList", commentList);
		
		return "board/content_view";
	}
	
	@PostMapping("comment/AjaxShow")
	public ModelAndView AjaxShow(@RequestParam HashMap<String, String> param) {
		log.info("@# Ajaxshow");
		ModelAndView mav = new ModelAndView();
		
		mav.setViewName("board/comment_ajax");
		
		log.info("@# boardno => " + param.get("boardno"));
		
		// 해당 게시글에 작성된 댓글 리스트를 가져옴
		ArrayList<CommenttbDTO> commentList = commentService.findAll(param);
		
		mav.addObject("commentList", commentList);
		
		return mav;
	}
}