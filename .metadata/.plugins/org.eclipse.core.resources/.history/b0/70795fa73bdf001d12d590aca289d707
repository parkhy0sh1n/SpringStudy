package com.gdu.notice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.gdu.notice.domain.NoticeDTO;
import com.gdu.notice.service.NoticeService;

@Controller
public class NoticeController {

	@Autowired
	private NoticeService noticeService;
	
	/*
		데이터(속성, Attribute) 전달 방법
		1. forward인 경우 Model에 attribute로 저장한다.
		2. redirect인 경우 RedirectAttributes에 flash attribute로 저장한다.
	*/
	
	@GetMapping("/")
	public String welcome() {
		return "index";
	}
	
	@GetMapping("/notice/list.do")
	public String list(Model model) {
		model.addAttribute("noticeList", noticeService.getNoticeList());
		return "notice/list";
	}
	
	@GetMapping("/notice/write.do")
	public String write() {
		return "notice/write";
	}
	
	@PostMapping("/notice/add.do")
	public String add(NoticeDTO notice) {
		noticeService.addNotice(notice);
		return "redirect:/notice/list.do";
	}
	
	@GetMapping("/notice/detail.do")
	public String detail(@RequestParam(value="notice_no", required=false, defaultValue="0") int notice_no
	            	    , Model model) {
		model.addAttribute("n", noticeService.getNotice(notice_no));
		return "notice/detail";
	}
	
	@PostMapping("/notice/modify.do")
	public String remove(NoticeDTO notice) {
		noticeService.modifyNotice(notice);
		return "redirect:/notice/detail.do?notice_no=" + notice.getNotice_no();
	}
	
	@GetMapping("/notice/remove.do")
	public String renove(@RequestParam(value="notice_no", required=false, defaultValue="0") int notice_no) {
		noticeService.removeNotice(notice_no);
		return "redirect:/notice/list.do";
	}
	
}
