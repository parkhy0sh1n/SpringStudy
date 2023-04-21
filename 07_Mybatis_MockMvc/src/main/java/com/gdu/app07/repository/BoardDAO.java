package com.gdu.app07.repository;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.gdu.app07.domain.BoardDTO;

// DTO(Data Access Object) : DB의 데이터에 접근하는 Java Beans(Bean)이다.
// DispatcherServlet으로부터 Repository로 스캔되어 Bean 객체에 저장된다.
@Repository
public class BoardDAO {
	
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	
	private final String NS = "mybatis.mapper.board.";
	
	public List<BoardDTO> selectBoardList() {
		// selectList : 쿼리문이 없으면 List<>로 반환
		// NS(board.xml 경로의 접두사) + ID
		return sqlSessionTemplate.selectList(NS + "selectBoardList");
	}
	
	public BoardDTO selectBoardByNo(int boardNo) {
		// selectOnd : 쿼리문이 없으면 null을 반환
		// NS + ID, 반환할 값
		return sqlSessionTemplate.selectOne(NS + "selectBoardByNo", boardNo);
	}
	
	public int insertBoard(BoardDTO board) {
		return sqlSessionTemplate.insert(NS + "insertBoard", board);
	}
	
	public int updateBoard(BoardDTO board) {
		return sqlSessionTemplate.update(NS + "updateBoard", board);
	}
	
	public int deleteBoard(int boardNo) {
		return sqlSessionTemplate.delete(NS + "deleteBoard", boardNo);
	}
	
}
