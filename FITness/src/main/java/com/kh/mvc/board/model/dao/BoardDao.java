package com.kh.mvc.board.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.kh.mvc.board.model.vo.Board;
import com.kh.mvc.common.util.PageInfo;

import static com.kh.mvc.common.jdbc.JDBCTemplate.*;

public class BoardDao {

	public int getBoardCount(Connection connection) {
		// 1. 정수값 리턴하도록 변수 만들기
		int count = 0;
		
		// 3. 오브젝트의 참조변수 만들기
		PreparedStatement pstmt = null;
		
		// 3. 리절트셋 변수 선언
		ResultSet rs = null;
		
		// 2. 실행 시킬 쿼리문 넣었어 (총 게시글 갯수 쿼리 문)
		String query = "SELECT COUNT(*) FROM BOARD WHERE STATUS='Y'";
		// select 구문 리턴하면 뭘 리턴? resultset한다. - 그래서 위에 리절트 셋 변수도 선언
		
		
		// 4. 커넥션으로부터 매개변수인 query 를 가져온다. + 예외처리 구문 실행
		try {
			pstmt = connection.prepareStatement(query);
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				count = rs.getInt(1);
			} 
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
			// 커넥션을 제외한 다른 애들을 클로즈 시켜준다. (역순으로)
		}
		// 1. 얻어온 카운트 수를 리턴한다.
		return count;
	}

	public List<Board> findAll(Connection connection, PageInfo pageInfo) {
		// 1. 셀렉해서 조회된 데이터가 없으면, 빈 리스트를 준다. 있으면, arraylist 에 담아서 준다.
		List<Board> list = new ArrayList<>();
		
		// 3. 커넥션 값을 가져올 변수 선언
		PreparedStatement pstmt = null;
	
		// 5. 쿼리문을 실행시킬 ResultSet 참조변수 위에 선언
		ResultSet rs = null;
		
		// 2. 쿼리문 입력
		String query = "SELECT RNUM, NO, TITLE, ID, CREATE_DATE, ORIGINAL_FILENAME, READCOUNT, STATUS "
					 + "FROM ("
					 +    "SELECT ROWNUM AS RNUM, "
					 +           "NO, "
					 + 			"TITLE, "
					 + 			"ID, "
					 + 			"CREATE_DATE, "
					 + 			"ORIGINAL_FILENAME, "
					 +  			"READCOUNT, "
					 +     		"STATUS "
					 + 	 "FROM ("
					 + 	    "SELECT B.NO, "
					 + 			   "B.TITLE, "
					 +  			   "M.ID, "
					 + 			   "B.CREATE_DATE, "
					 + 			   "B.ORIGINAL_FILENAME, "
					 + 			   "B.READCOUNT, "
					 + 	   		   "B.STATUS "
					 + 		"FROM BOARD B "
					 + 		"JOIN MEMBER M ON(B.WRITER_NO = M.NO) "
					 + 		"WHERE B.STATUS = 'Y' ORDER BY B.NO DESC"
					 + 	 ")"
					 + ") WHERE RNUM BETWEEN ? and ?";
		
		// 3. 변수 실행
		// 4. 예외처리
		// 5. 쿼리문을 실행시킬 ResultSet 참조변수
		try {
			pstmt = connection.prepareStatement(query);

			pstmt.setInt(1, pageInfo.getStartList());
			pstmt.setInt(2, pageInfo.getEndList());
		
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				Board board = new Board();
				
				board.setNo(rs.getInt("No"));
				board.setRowNum(rs.getInt("RNUM"));
				board.setWriterId(rs.getString("ID"));
				board.setTitle(rs.getString("TITLE"));
				board.setCreateDate(rs.getDate("CREATE_DATE"));
				board.setOriginalFileName(rs.getString("ORIGINAL_FILENAME"));
				board.setReadCount(rs.getInt("READCOUNT"));
				board.setStatus(rs.getString("STATUS"));
				
				list.add(board);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		
		return list;
	}

	public Board findBoardByNo(Connection connection, int no) {
		// 조회하는거 해보자
		//1. 참조변수 리턴할 수 있도록 선언하자
		Board board = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		// 2. 쿼리문 입력
		String query = 	"SELECT  B.NO, "
							  + "B.TITLE, "
							  + "M.ID, "
							  + "B.READCOUNT, "
							  + "B.ORIGINAL_FILENAME, "
							  + "B.RENAMED_FILENAME, "
							  + "B.CONTENT, "
							  + "B.CREATE_DATE, "
							  + "B.MODIFY_DATE "
						+ "FROM BOARD B "
						+ "JOIN MEMBER M ON(B.WRITER_NO = M.NO) "
						+ "WHERE B.STATUS = 'Y' AND B.NO=?";
		
		// 3. 커넥션 오브젝트로 부터 PreparedStatement를 얻어와보자
		try {
			pstmt = connection.prepareStatement(query);
		
			// 4. ?값 정리해 주기
			pstmt.setInt(1, no);
			
			rs = pstmt.executeQuery();
			
			// 5. if 혹은 while 문을 통해
			if (rs.next()) {
				board = new Board();
				
				board.setNo(rs.getInt("NO"));
				board.setTitle(rs.getString("TITLE"));
				board.setWriterId(rs.getString("ID"));
				board.setReadCount(rs.getInt("READCOUNT"));
				board.setOriginalFileName(rs.getString("ORIGINAL_FILENAME"));
				board.setRenamedFileName(rs.getString("RENAMED_FILENAME"));
				board.setContent(rs.getString("CONTENT"));
				board.setCreateDate(rs.getDate("CREATE_DATE"));
				board.setModifyDate(rs.getDate("MODIFY_DATE"));
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			// 6. 리소스 받을 수 있도록 클로즈!
		} finally {
			close(rs);
			close(pstmt);
		}
		
		return board;
	}

	public int insertBoard(Connection connection, Board board) {
		int result = 0;
		PreparedStatement pstmt = null;
		
		String query = "INSERT INTO BOARD VALUES(SEQ_BOARD_NO.NEXTVAL,?,?,?,?,?,DEFAULT,DEFAULT,DEFAULT,DEFAULT)";
		
		try {
			pstmt = connection.prepareStatement(query);

			// 쿼리문 실행 전 물음표 부분 값 채워 넣기
			pstmt.setInt(1, board.getWriterNo());
			pstmt.setString(2, board.getTitle());
			pstmt.setString(3, board.getContent());
			pstmt.setString(4, board.getOriginalFileName());
			pstmt.setString(5, board.getRenamedFileName());
		
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	public int updateBoard(Connection connection, Board board) {
		int result = 0;
		PreparedStatement pstmt = null;
		// 파일 업데이트 시, 파일 구문도 추가해서 수정하는 것으로 
//		String query = "UPDATE BOARD SET TITLE=?,CONTENT=?,ORIGINAL_FILENAME=?,RENAMED_FILENAME=?,MODIFY_DATE=SYSDATE WHERE NO=?";
		String query = "UPDATE BOARD SET TITLE=?,CONTENT=?,MODIFY_DATE=SYSDATE WHERE NO=?";
		
		try {
			pstmt = connection.prepareStatement(query);

			pstmt.setString(1, board.getTitle());
			pstmt.setString(2, board.getContent());
			pstmt.setInt(3, board.getNo());
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}
	
	

}
