package com.boot.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.boot.dto.CoinfotbDTO;
import com.boot.dto.CusertbDTO;
import com.boot.dto.ImgtbDTO;
import com.boot.dto.SectortbDTO;
import com.boot.service.CoinfotbService;
import com.boot.service.CusertbService;
import com.boot.service.ImgtbService;
import com.boot.service.SectortbService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class CoinfotbController {

    @Autowired
    private CoinfotbService service;
    
    @Autowired
    private SectortbService sectorservice;
    
    @Autowired
    private ImgtbService imgservice;
    
    @Autowired
    private ServletContext servletContext; //ServletContext 주입
    
    @Autowired
    private CusertbService cuserservice;
    
    @RequestMapping("/coinfo")
    public String coinfo(@RequestParam HashMap<String, String> param, Model model, HttpSession session) {
        log.info("@# coinfo");
        String imgno = null;
        String cuserid = (String) session.getAttribute("id");
        param.put("cuserid", cuserid);
        
        CusertbDTO cuser = cuserservice.CInfoView(param);
        
        CoinfotbDTO dto = service.Coinfotblist(param);
        model.addAttribute("coinfotb", dto);
        
        ArrayList<SectortbDTO> sector = sectorservice.Sectortblist();
        
        // 이미지 정보 가져오기
        param.put("usetb", "coinfotb");
        param.put("gubun", cuserid+"_"+1);
        ImgtbDTO imgDto = imgservice.getFile_coinfo_select(param);
        
        model.addAttribute("imgtb", imgDto);
        model.addAttribute("sector", sector);
        
        if(imgDto != null) imgno = "t";
        else imgno = "n";
        
        model.addAttribute("imgno", imgno);
        model.addAttribute("cuser", cuser);
        
        return "coinfo/coinfo";
    }

    @RequestMapping("/insert")
    public String insert(@RequestParam HashMap<String, String> param, @RequestParam("file") MultipartFile file, RedirectAttributes redirectAttributes, HttpSession session) {
        log.info("@# insert");

        String cuserid = (String) session.getAttribute("id");
        param.put("cuserid", cuserid);
        
        // 파일 업로드
        if (!file.isEmpty()) {
            UUID uuid = UUID.randomUUID(); // 중복 방지 랜덤 난수 생성
            String uploadFileName = file.getOriginalFilename(); // 업로드되는 파일 이름
            String basepath = servletContext.getRealPath("/"); //"D:\\dev\\projectupload"; // 서버 기본 경로 설정
            String path = "coinfo\\" + cuserid; // 파일 경로 설정
            String saveFileName = uuid.toString() + "_" + uploadFileName; // 저장할 파일 이름

            ImgtbDTO imgtb = new ImgtbDTO(); //이미지 정보 테이블
    		//테이블 세팅
            imgtb.setUsetb("coinfotb");	//관련테이블
            imgtb.setGubun(cuserid+"_"+1);	//구분은 세션에서 가져옴
            imgtb.setUuid(uuid.toString());
            imgtb.setUploadpath(path);
            imgtb.setFilename(uploadFileName);
            imgservice.imgupload_resume(imgtb, file, basepath); //imgtb에 데이터 추가 및 해당 파일 저장

            // 파일 경로를 param에 추가
            param.put("filePath", path + "\\" + saveFileName);
        }

        service.insert(param);
        log.info("@# insert param => " + param);
        
        redirectAttributes.addFlashAttribute("message", "기업정보등록이 완료되었습니다.");
        return "redirect:/coinfo";
    }
    
    @RequestMapping("/Coinmodify")
    public String Coinmodify(@RequestParam HashMap<String, String> param, @RequestParam("file") MultipartFile file, RedirectAttributes redirectAttributes, HttpSession session) {
        log.info("@# Coinmodify");

        String cuserid = (String) session.getAttribute("id");
        param.put("cuserid", cuserid);

        service.Coinmodify(param);
        log.info("@# Coinmodify param => " + param);
        
     // 파일 업로드
        if (!file.isEmpty()) {
        	ImgtbDTO imgtb = new ImgtbDTO(); //이미지 정보 테이블
        	imgtb.setUsetb("coinfotb");	//관련테이블
            imgtb.setGubun(cuserid+"_"+1);	//구분은 세션에서 가져옴
            
            imgservice.imgdelete_coifno(imgtb);
        	
        	UUID uuid = UUID.randomUUID(); // 중복 방지 랜덤 난수 생성
            String uploadFileName = file.getOriginalFilename(); // 업로드되는 파일 이름
            String basepath = servletContext.getRealPath("/"); //"D:\\dev\\projectupload"; // 서버 기본 경로 설정
            String path = "coinfo\\" + cuserid; // 파일 경로 설정
            String saveFileName = uuid.toString() + "_" + uploadFileName; // 저장할 파일 이름

            //ImgtbDTO imgtb = new ImgtbDTO(); //이미지 정보 테이블
    		//테이블 세팅
            imgtb.setUsetb("coinfotb");	//관련테이블
            imgtb.setGubun(cuserid+"_"+1);	//구분은 세션에서 가져옴
            imgtb.setUuid(uuid.toString());
            imgtb.setUploadpath(path);
            imgtb.setFilename(uploadFileName);
            imgservice.imgupload_resume(imgtb, file, basepath); //imgtb에 데이터 추가 및 해당 파일 저장

            // 파일 경로를 param에 추가
            param.put("filePath", path + "\\" + saveFileName);
        }
        
        redirectAttributes.addFlashAttribute("message", "기업정보수정이 완료되었습니다.");
        return "redirect:/coinfo";
    }
}