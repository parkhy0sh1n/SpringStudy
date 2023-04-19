package com.gdu.app06.service;

import java.util.List;

import com.gdu.app06.domain.BoardDTO;

public interface BoardService {

	public List<BoardDTO> getBoardList();			// 게시글 목록
	public int addBoard(BoardDTO board);			// 게시글 삽입
	public BoardDTO getBoardByNo(int board_no);		// 상세 보기
	public int removeBoard(int board_no);			// 게시글 삭제
	public int modifyBoard(BoardDTO baord);			// 게시글 수정
	
}
