package com.gdu.app06.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.gdu.app06.domain.BoardDTO;
import com.gdu.app06.service.BoardService;

// 특정 URI로 요청을 보내면 Controller에서 어떠한 방식으로 처리할지 정의하는 애너테이션.
// 모든 요청에 대한 Mapping에 /board가 prefix(접두사)로 추가된다.
@RequestMapping("/board") 
// DispatcherServlet으로부터 Controller로 스캔되어 Bean 객체에 저장된다.
@Controller
public class BoardController {
	
	// 클래스간의 의존관계를 Spring Container가 자동으로 연결해주는 애너테이션.
	@Autowired
	private BoardService boardService;

	@GetMapping("/list.do")
	public String list(Model model) {  // Model : jsp로 전달(forward)할 데이터(속성, attribute)를 저장한다.
		model.addAttribute("boardList", boardService.getBoardList());
		return "board/list";
	}
	
	@GetMapping("/write.do")
	public String write() {
		return "board/write";
	}
	
	@PostMapping("/add.do")
	public String add(BoardDTO board) {
		boardService.addBoard(board);      // addBoard() 메소드의 호출 결과인 int 값(0 또는 1)은 사용하지 않았다.
		return "redirect:/board/list.do";  // 목록 보기로 redirect(redirect 경로는 항상 mapping으로 작성한다.)
	}
	
	@GetMapping("/detail.do")
	public String detail(@RequestParam(value="board_no", required=false, defaultValue="0") int board_no
			           , Model model) {
		model.addAttribute("b", boardService.getBoardByNo(board_no));
		return "board/detail";
	}
	
	@GetMapping("/remove.do")
	public String remove(@RequestParam(value="board_no", required=false, defaultValue="0") int board_no) {
		boardService.removeBoard(board_no);
		return "redirect:/board/list.do";
	}
	
	@PostMapping("/modify.do")
	public String modify(BoardDTO board) {
		boardService.modifyBoard(board);
		return "redirect:/board/detail.do?board_no=" + board.getBoard_no();
	}
	
}