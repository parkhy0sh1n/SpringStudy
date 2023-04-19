package com.gdu.app06.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gdu.app06.domain.BoardDTO;
import com.gdu.app06.repository.BoardDAO;

// DispatcherServlet으로부터 Service로 스캔되어 Bean 객체에 저장된다.
@Service
public class BoardServiceImpl implements BoardService {
	
	// 클래스간의 의존관계를 Spring Container가 자동으로 연결해주는 애너테이션.
	@Autowired
	private BoardDAO boardDAO;
	
	// 요청한 서비스에 따라 BoardDAO 메소드들의 매개변수로 전달한다.
	@Override
	public List<BoardDTO> getBoardList() {
		return boardDAO.selectBoardList();
	}

	@Override
	public int addBoard(BoardDTO board) {
		return boardDAO.insertBoard(board);

	}
	@Override
	public BoardDTO getBoardByNo(int board_no) {
		return boardDAO.selectBoardByNo(board_no);
	}

	@Override
	public int removeBoard(int board_no) {
		return boardDAO.deleteBoard(board_no);
	}

	@Override
	public int modifyBoard(BoardDTO board) {
		return boardDAO.updateBoard(board);
	}

}
 