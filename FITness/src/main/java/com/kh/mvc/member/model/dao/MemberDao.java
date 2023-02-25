package com.kh.mvc.member.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import static com.kh.mvc.common.jdbc.JDBCTemplate.close;
import static com.kh.mvc.common.jdbc.JDBCTemplate.getConnection;
import com.kh.mvc.board.model.vo.Board;
import com.kh.mvc.member.model.vo.Member;

public class MemberDao {
	

	// 새로 짠 로직
	public Member findById(Connection connection, String  userId) {
		Member member = null;
		PreparedStatement pstmt = null;
		String query = "SELECT * FROM MEMBER WHERE ID=? AND STATUS='Y'";
		ResultSet rs = null;
		
		try {
			pstmt = connection.prepareStatement(query);
			
			pstmt.setString(1, userId);
			
			rs = pstmt.executeQuery();
		
			while(rs.next()) {
				member = new Member();
				
				member.setNo(rs.getInt("NO"));
				member.setId(rs.getString("ID"));
				member.setPassword(rs.getString("PASSWORD"));
				member.setRole(rs.getString("ROLE"));
				member.setName(rs.getString("NAME"));
				member.setPhone(rs.getString("PHONE"));
				member.setEmail(rs.getString("EMAIL"));
				member.setAddress(rs.getString("ADDRESS"));
				member.setStatus(rs.getString("STATUS"));
				member.setEnrollDate(rs.getDate("ENROLL_DATE"));
				member.setModifyDate(rs.getDate("MODIFY_DATE"));
			
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
				
		return member;
	}

	
	// 새로 짠 로직
	public int insertMember(Connection connection, Member member) {
		int result = 0;
		PreparedStatement pstmt = null;
		String query = "INSERT INTO MEMBER VALUES(SEQ_UNO.NEXTVAL,?,?,DEFAULT,?,?,?,?,DEFAULT,DEFAULT,DEFAULT)";
		
		try {
			// (우리가 확인해서 커밋 or 롤백시킬 수 있도록 false구문으로 둔다.)
			//connection.setAutoCommit(false);
			
			pstmt = connection.prepareStatement(query);
			
			pstmt.setString(1, member.getId());
			pstmt.setString(2, member.getPassword());
			pstmt.setString(3, member.getName());
			pstmt.setString(4, member.getPhone());
			pstmt.setString(5, member.getEmail());
			pstmt.setString(6, member.getAddress());

			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				pstmt.close();
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}finally {
				close(pstmt);
			}
		}
		
		return result;
	}



	


	// 아이디 찾기 ( 폰번호로 ) 
	public String findIdbyPhone(String phone) {
		String id = null;
		Connection connection = getConnection();
		PreparedStatement pstmt = null;
		String query = "SELECT ID FROM MEMBER WHERE PHONE =? ";
		ResultSet rs = null;
		
		try {
			pstmt = connection.prepareStatement(query);
			
			pstmt.setString(1, phone);
			
			rs = pstmt.executeQuery();
		
			while(rs.next()) {
				id = rs.getString("ID");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
				
		return id;
	}

	// 비밀번호 찾기 (id로)
	public String findPwdbyId(String id) {
		String password = null;
		Connection connection = getConnection();
		PreparedStatement pstmt = null;
		String query = "SELECT PASSWORD FROM MEMBER WHERE ID = ? ";
		ResultSet rs = null;
		
		try {
			pstmt = connection.prepareStatement(query);
			
			pstmt.setString(1, id);
			
			rs = pstmt.executeQuery();
		
			while(rs.next()) {
				password = rs.getString("PASSWORD");
			}

			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		
		return password;
	}



	public Board findMemberBoardById(Connection connection, int no) {
		
		Board board = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String query = "SELECT * FROM BOARD B JOIN MEMBER M ON(B.WRITER_NO = M.NO) WHERE M.NO = ? ";

		try {
			pstmt = connection.prepareStatement(query);
				
			pstmt.setInt(1, no);
			
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				board = new Board();
				
				board.setNo(rs.getInt("NO"));
				board.setWriterNo(rs.getInt("WRITER_NO"));
				board.setTitle(rs.getString("TITLE"));
				board.setContent(rs.getString("CONTENT"));
				board.setOriginalFileName(rs.getString("ORIGINAL_FILENAME"));
				board.setRenamedFileName(rs.getString("RENAMED_FILENAME"));
				board.setReadCount(rs.getInt("READCOUNT"));
				board.setStatus(rs.getString("STATUS"));
				board.setCreateDate(rs.getDate("CREATE_DATE"));
				board.setModifyDate(rs.getDate("MODIFY_DATE"));
				board.setWriterId(rs.getString("ID"));
				
				System.out.println(board);
			
				
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


	public int updateMemberStatus(Connection connection, int no, String status) {
		int result = 0;
		PreparedStatement pstmt = null;
		String query = "UPDATE MEMBER SET STATUS=? WHERE NO=?";
		
		try {
			pstmt = connection.prepareStatement(query);
			
			pstmt.setString(1, status);
			pstmt.setInt(2, no);
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}
	
	//회원정보 수정-륜
	public int updateMember(Connection connection, Member member) {
		int result = 0;
		PreparedStatement pstmt = null;
		String query = "UPDATE MEMBER SET PASSWORD=?, NAME=?,PHONE=?,EMAIL=?,ADDRESS=?,MODIFY_DATE=SYSDATE WHERE NO=?";
		
		try {
			pstmt = connection.prepareStatement(query);
			
			pstmt.setString(1, member.getPassword());
			pstmt.setString(2, member.getName());
			pstmt.setString(3, member.getPhone());
			pstmt.setString(4, member.getEmail());
			pstmt.setString(5, member.getAddress());
			pstmt.setInt(6, member.getNo());
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}


	public Member findMemberById(Connection connection, String userId) {
		Member member = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		//String query = "SELECT * FROM MEMBER WHERE ID='admin2' AND STATUS='Y'";
		//String query = "SELECT * FROM MEMBER WHERE ID='" + userId + "' AND STATUS='Y'";
		String query = "SELECT * FROM MEMBER WHERE ID=? AND STATUS='Y'";
		
		try {
			//Class.forName("oracle.jdbc.driver.OracleDriver");
			//connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "WEB", "WEB");
			//statement = connection.createStatement();
			
			pstmt = connection.prepareStatement(query);
			
			pstmt.setString(1, userId);
			
//			resultSet = statement.executeQuery(query);
			rs = pstmt.executeQuery();
			
			
			while(rs.next()) {
				member = new Member();
				
				member.setNo(rs.getInt("NO"));
				member.setId(rs.getString("ID"));
				member.setPassword(rs.getString("PASSWORD"));
				member.setRole(rs.getString("ROLE"));
				member.setName(rs.getString("NAME"));
				member.setPhone(rs.getString("PHONE"));
				member.setEmail(rs.getString("EMAIL"));
				member.setAddress(rs.getString("ADDRESS"));
				member.setStatus(rs.getString("STATUS"));
				member.setEnrollDate(rs.getDate("ENROLL_DATE"));
				member.setModifyDate(rs.getDate("MODIFY_DATE"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		
		return member;
	}
	


	

//	public Member findId(Connection connection, String phone) {
//		Member member = null;
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//		String query = "SELECT ID FROM MEMBER WHERE PHONE = ? ";
//		
//		try {
//			pstmt = connection.prepareStatement(query);
//			rs = pstmt.executeQuery();
//			
//			if (rs.next()) {
//				member = rs.getString(1);
//			} 
//		} catch (SQLException e) {
//			e.printStackTrace();
//		} finally {
//			close(rs);
//			close(pstmt);
//		
//		}
//		
//		return member;	
//		
//	}


//	public int getMemberBoardCount(Connection connection) {
//		int count = 0;
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//		
//		String query = "SELECT COUNT(*) FROM BOARD WHERE WRITER_ID = ?;";
//
//		try {
//			pstmt = connection.prepareStatement(query);
//			
//			pstmt.setString(1, writerId);
//			rs = pstmt.executeQuery();
//			
//			if (rs.next()) {
//				count = rs.getInt(1);
//			} 
//		} catch (SQLException e) {
//			e.printStackTrace();
//		} finally {
//			close(rs);
//			close(pstmt);
//			// 커넥션을 제외한 다른 애들을 클로즈 시켜준다. (역순으로)
//		}
//		// 1. 얻어온 카운트 수를 리턴한다.
//		return count;
//	}
	}