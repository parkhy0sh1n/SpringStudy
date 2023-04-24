package com.gdu.app08.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.gdu.app08.domain.BoardDTO;

public interface BoardService {
	
	// 게시글 목록
	public List<BoardDTO> getBoardList();
	// 상세 보기
	public BoardDTO getBoardByNo(HttpServletRequest request);
	// 게시글 삽입
	public int addBoard(HttpServletRequest request);
	// 게시글 수정
	public int modifyBoard(HttpServletRequest request);
	// 게시글 삭제
	public int removeBoard(HttpServletRequest request);
	// 트랜잭션
	public void testTx();
}