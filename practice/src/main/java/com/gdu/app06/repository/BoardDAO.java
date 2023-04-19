package com.gdu.app06.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.gdu.app06.domain.BoardDTO;

// DTO(Data Access Object) : DB의 데이터에 접근하는 Java Beans(Bean)이다.
// DispatcherServlet으로부터 Repository로 스캔되어 Bean 객체에 저장된다.
@Repository
public class BoardDAO {

	// DB와의 연결을 위한 인터페이스.
	private Connection con;
	// 쿼리문 분석하고 실행할 수 있는 인터페이스.
	private PreparedStatement ps;
	// SELECT문의 결과를 행 단위로 불러오는 인터페이스.
	private ResultSet rs;
	// 쿼리문을 담을 객체.
	private String sql;
	
	// BoardDAO 클래스 내부에서만 사용 가능한 "연결" 메소드.
	private Connection getConnection() {
		// // Driver 클래스를 찾지 못할 경우, ClassNotFoundException 예외가 발생하기 때문에 반드시 예외처리 해주어야 한다.
		try {
			// Oracle DB 드라이버 로드(ojdbc8.jar).
			// 로딩되면 자동으로 객체가 생성된 후 DriverManager 클래스에 등록된다.
			Class.forName("oracel.jdbc.OracleDriver");
			// Oravle DB와 연결.
			// DriverManager.getConnection("연결문자열", "DB계정", "DB비밀번호")
			// 연결문자열 : "라이브러리:드라이버종류:드라이버타입:@아이피:포트번호:서비스명"
			return DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "GDJ61", "1111");
		} catch (Exception e) {
			e.printStackTrace();
			return null;                                
		}
	}
	
	// BoardDAO 클래스 내부에서만 사용 가능한 "반납" 메소드.
	private void close() {
		try {
			// DB 서버의 부담을 줄이기 위해 사용한 자원들을 반납한다.
			// 반납 순서는 반드시 선언의 역순으로 해야한다.
			if(rs != null) rs.close();
			if(ps != null) ps.close();
			if(con != null) con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	// 1. 목록
	public List<BoardDTO> selectBoardList() {
		// BoardDTO 클래스를 ArrayList 객체 list로 생성한다.
		List<BoardDTO> list = new ArrayList<BoardDTO>();
		try {
			// 미리 생성해둔 연결 메소드를 통해 간단하게 연결한다.
			con = getConnection();
			// 작성한 쿼리문을 sql 객체에 반환한다.
			sql = "SELECT BOARD_NO, TITLE, CONTENT, WRITER, CREATED_AT, MODIFIED_AT FROM BOARD ORDER BY BOARD_NO DESC";
			// 작성한 쿼리문(sql)을 DB로 전송 후 ps 객체에 반환한다.
			ps = con.prepareStatement(sql);
			// 쿼리문을 실행한 후 rs 객체에 반환한다.
			// SELECT문은 업데이트 사항이 없으므로, 단순히 쿼리문을 실행해주는 메소드 executeQuery()를 사용한다.
			rs = ps.executeQuery();
			// 쿼리문을 공백 단위로 읽어오는 조건문.
			while(rs.next()) {
				// ResultSet 인터페이스의 get 메소드 : SELECT문에서 n번째 항목을 불러온다.
				// 불러온 항목을 board 객체에 반환한다.
				BoardDTO board = new BoardDTO(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6));
				// 인자를 담고 있는 board 객체를 list 객체(ArrayList)에 삽입한다.
				list.add(board);
				}
			} catch(Exception e) {
				e.printStackTrace();
			} finally {
				// 미리 생성해둔 반납 메소드를 통해 간단하게 자원들을 반납한다.
				close();
			}
			// 결과를 list 객체에 반환하여 게시글 목록을 보여준다.
			return list;
		}
		
		// 2. 삽입
		public int insertBoard(BoardDTO board) {
			int result = 0;
			try {
				con = getConnection();
				sql = "INSERT INTO BOARD VALUES(BOARD_SEQ.NEXTVAL, ?, ?, ?, TO_CHAR(SYSDATE, 'YYYY-MM-DD HH24:MI:SS'), TO_CHAR(SYSDATE, 'YYYY-MM-DD HH24:MI:SS'))";
				ps = con.prepareStatement(sql);
				// 첫 번째 물음표(?)에 title 삽입.
				ps.setString(1, board.getTitle());
				// 두 번째 물음표(?)에 content 삽입.
				ps.setString(2, board.getContent());
				// 세 번째 물음표(?)에 writer 삽입.
				ps.setString(3, board.getWriter());
				// INSERT, UPDATE, DELETE문은 업데이트 사항이 있으므로, 데이터를 업데이트 해주는 메소드 executeUpdate()를 사용한다.
				result = ps.executeUpdate();
			} catch(Exception e) {
				e.printStackTrace();
			} finally {
				close();
			}
			return result;
		}
		
		// 3. 상세
			public BoardDTO selectBoardByNo(int board_no) {
				BoardDTO board = null;
				try {
					con = getConnection();
					sql = "SELECT BOARD_NO, TITLE, CONTENT, WRITER, CREATED_AT, MODIFIED_AT FROM BOARD WHERE BOARD_NO = ?";
					ps = con.prepareStatement(sql);
					ps.setInt(1, board_no);
					rs = ps.executeQuery();
					if(rs.next()) {
						board = new BoardDTO(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6));
					}
				} catch(Exception e) {
					e.printStackTrace();
				} finally {
					close();
				}
				return board;
			}
		
		// 4. 삭제
		public int deleteBoard(int board_no) {
			int result = 0;
			try {
				con = getConnection();
				sql = "DELETE FROM BOARD WHERE BOARD_NO = ?";
				ps = con.prepareStatement(sql);
				ps.setInt(1, board_no);
				result = ps.executeUpdate();
			} catch(Exception e) {
				e.printStackTrace();
			} finally {
				close();
			}
			return result;
		}

		// 5. 수정
		public int updateBoard(BoardDTO board) {
			int result = 0;
			try {
				con = getConnection();
				sql = "UPDATE BOARD SET TITLE = ?, CONTENT = ?, MODIFIED_AT = TO_CHAR(SYSDATE, 'YYYY-MM-DD HH24:MI:SS') WHERE BOARD_NO = ?";
				ps = con.prepareStatement(sql);
				ps.setString(1, board.getTitle());
				ps.setString(2, board.getContent());
				ps.setInt(3, board.getBoard_no());
				result = ps.executeUpdate();
			} catch(Exception e) {
				e.printStackTrace();
			} finally {
				close();
			}
			return result;
		}
		
}
