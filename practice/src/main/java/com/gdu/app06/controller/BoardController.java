package com.gdu.app04.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.gdu.app04.domain.BoardDTO;
import com.gdu.app04.service.BoardService;

// 특정 URI로 요청을 보내면 Controller에서 어떠한 방식으로 처리할지 정의하는 애너테이션.
// 모든 요청에 대한 Mapping에 /board가 prefix(접두사)로 추가된다.
@RequestMapping("/board")
// DispatcherServlet으로부터 Controller로 스캔되어 Bean 객체에 저장된다.
@Controller
public class BoardController {
	
	// 클래스간의 의존관계를 Spring Container가 자동으로 연결해주는 애너테이션.
	@Autowired
	private BoardService boardService;

	// Get, Post 방식으로 요청하는 애너테이션.
	@GetMapping("/list.do")
	// Model : Controller에서 생성된 데이터를 담아 View로 전달할 때 사용하는 객체이다.
	public String list(Model model) { 
		// value 객체를 name 이름으로 추가함.(View 코드에서는 name으로 지정한 이름을 통해서 value를 사용한다.)
		model.addAttribute("boardList", boardService.getBoardList());
		return "board/list";
	}
	
	@GetMapping("/write.do")
	public String write() {
		return "board/write";
	}
	
	@PostMapping("/add.do")
	public String add(BoardDTO board) {
		// addBoard() 메소드의 호출 결과인 int 값(0 또는 1)은 사용하지 않았다.
		boardService.addBoard(board);   
		// 목록 보기로 redirect(redirect 경로는 항상 mapping으로 작성한다.)
		return "redirect:/board/list.do"; 
	}
	
	@GetMapping("/detail.do")
	/*	
		@RequestParam : 요청 파라미터 애너테이션.
		value 		     : Key 값(board_no) 뒤에 오는 변수 board_no에 해당 값을 바인딩한다.
		required=false   : Key 값이 존재하지 않다고 해서 BadRequest(HTTP 400 오류)가 발생하지 않는다. (디폴트는 true)
		defaultValue="0" : 만약 Key 값이 존재하지 않다면, board_no 변수에 default로 0이 들어가게 된다.
	 */
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