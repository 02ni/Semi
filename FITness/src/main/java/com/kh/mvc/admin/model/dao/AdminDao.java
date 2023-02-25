package com.kh.mvc.admin.model.dao;

import static com.kh.mvc.common.jdbc.JDBCTemplate.close;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import com.kh.mvc.board.model.vo.Board;
import com.kh.mvc.common.util.PageInfo;
import com.kh.mvc.gym.model.vo.Gym;
import com.kh.mvc.gym.model.vo.Voucher;
import com.kh.mvc.member.model.vo.Member;
import com.kh.mvc.notice.model.vo.Notice;
import com.kh.mvc.qna.model.vo.QnaBoard;
import com.kh.mvc.qna.model.vo.QnaReply;

import lombok.Data;

@Data
public class AdminDao {
	public int result2;

	public int count;
	
	public int getBoardCount(Connection connection) {
		int count = 0;
		
		PreparedStatement pstmt = null;
		
		ResultSet rs = null;
		
		String query = "SELECT COUNT(*) FROM BOARD ";
		
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
		}
		
		return count;
	}

	public Member findMemberByNo(Connection connection, int no) {
		Member member = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String query = "SELECT * FROM MEMBER WHERE NO = ? ";
		
		try {
			pstmt = connection.prepareStatement(query);
			
			pstmt.setInt(1, no);

			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				member = new Member();
				
				member.setNo(rs.getInt("NO"));
				member.setId(rs.getString("ID"));
				member.setName(rs.getString("NAME"));
				member.setPhone(rs.getString("PHONE"));
				member.setEmail(rs.getString("EMAIL"));
				member.setAddress(rs.getString("ADDRESS"));
				member.setStatus(rs.getString("STATUS"));
				member.setEnrollDate(rs.getDate("ENROLL_DATE"));
				
				
			}
			
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		
		}
		
		return member;	
	
	}

	public int getMemberBoardCount(Connection connection, int no) {
		int  count = 0;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		
		String query = "SELECT COUNT(*) FROM BOARD WHERE WRITER_NO = ? ";
		
		try {
			pstmt = connection.prepareStatement(query);
			
			pstmt.setInt(1, no);
			
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				count = rs.getInt(1);
			} 
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		
		return count;
	}

	public List<Board> findAllByNo(Connection connection, PageInfo pageInfo, int no) {
		List<Board> list = new ArrayList<>();
		
		PreparedStatement pstmt = null;

		ResultSet rs = null;
		
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
				 + 		"WHERE B.STATUS = 'Y' AND M.NO = ? ORDER BY B.NO DESC"
				 + 	 ")"
				 + ") WHERE RNUM BETWEEN ? and ?";
		
		try {
			pstmt = connection.prepareStatement(query);

			pstmt.setInt(1, no);
			pstmt.setInt(2, pageInfo.getStartList());
			pstmt.setInt(3, pageInfo.getEndList());
		
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				Board board = new Board();
				
				board.setNo(rs.getInt("NO"));
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

	// 전체 회원수
	public int getMemberCount(Connection connection) {
		int count = 0;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String query = "SELECT COUNT(*) FROM MEMBER ";
		
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
		
		}
		
		return count;	
	
	}

	public List<Member> getMember(Connection connection, PageInfo pageInfo) {
		List<Member> list = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			String query = "SELECT  * "
					+ "FROM ( "
					+ "    SELECT ROWNUM AS RNUM, NO, ID, PASSWORD, ROLE, NAME, PHONE, EMAIL, ADDRESS, STATUS, ENROLL_DATE, MODIFY_DATE "
					+ "    FROM ( "
					+ "        SELECT NO, ID, PASSWORD, ROLE, NAME, PHONE, EMAIL, ADDRESS, STATUS, ENROLL_DATE, MODIFY_DATE "
					+ "        FROM  MEMBER "
					+ "        ORDER BY NO DESC "
					+ "    ) "
					+ ") "
					+ "WHERE RNUM BETWEEN ? and ? ";
				
			pstmt = connection.prepareStatement(query);

			pstmt.setInt(1, pageInfo.getStartList());
			pstmt.setInt(2, pageInfo.getEndList());
			
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				Member member = new Member();
				
				member.setNo(rs.getInt("RNUM"));
				member.setNo(rs.getInt("NO"));
				member.setId(rs.getString("ID"));
				member.setName(rs.getString("NAME"));
				member.setPhone(rs.getString("PHONE"));
				member.setEmail(rs.getString("EMAIL"));
				member.setAddress(rs.getString("ADDRESS"));
				member.setStatus(rs.getString("STATUS"));
				member.setEnrollDate(rs.getDate("ENROLL_DATE"));
				member.setCount(new AdminDao().getcount(connection, member.getNo())); 
				member.setGymName(new AdminDao().getGymName(connection, member.getNo()));
				member.setReplyCount(new AdminDao().getReplyCount(connection, member.getNo()));
				
				list.add(member);
				
				
//				System.out.println(member.getNo());
				
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		
		return list;
	}

	private int getReplyCount(Connection connection, int no) {
		int count = 0;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		
		String query = "SELECT COUNT(*) FROM REPLY R JOIN MEMBER M ON(M.NO = R.WRITER_NO) WHERE M.NO = ? ";
		
		try {
			pstmt = connection.prepareStatement(query);
			
			pstmt.setInt(1, no);
			
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				count = rs.getInt(1);
			} 
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
	
		return count;
	}

	private String getGymName(Connection connection, int no) {
		String name = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		
		String query = "SELECT G.GYM_NAME FROM P_MANA P JOIN MEMBER M ON (P.USER_NO = M.NO) JOIN GYM G ON(G.NO = P.GYM_ID) JOIN VOUCHER V ON(P.VOUCHER_ID = V.VOUCHER_NO) WHERE M.NO = ? ";
		
		try {
			pstmt = connection.prepareStatement(query);
			
			pstmt.setInt(1, no);
			
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				name = rs.getString(1);
			} 
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
			
		if(name == null) {
			name = "x";
		}
		System.out.println(name);
		
		return name;
	}
	
	//게시글 총 수 
	public int getcount(Connection connection, int no) {
		int count = 0;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		
		String query = "SELECT COUNT(*) FROM BOARD B JOIN MEMBER M ON(M.NO = B.WRITER_NO) WHERE M.NO = ? ";
		
		try {
			pstmt = connection.prepareStatement(query);
			
			pstmt.setInt(1, no);
			
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				count = rs.getInt(1);
			} 
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
	
		return count;
	}
		
		


	public int getGymCount(Connection connection) {
		int count = 0;
		
		PreparedStatement pstmt = null;
		
		ResultSet rs = null;
		
		String query = "SELECT COUNT(*) FROM GYM ";
		
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
		}
		
		return count;
	}

	public List<Gym> getGym(Connection connection, PageInfo pageInfo) {
		List<Gym> list = new ArrayList<>();
		
		PreparedStatement pstmt = null;
	
		ResultSet rs = null;
		
		String query = "SELECT  NO, GYM_NAME, ADDRESS, GYM_PHONE, CONTENT, TIME, IMG, THUMB, CATE, STATUS, CEO_PHONE, CEO_EMAIL "
				+ "FROM ( "
				+ "    SELECT ROWNUM AS RNUM, NO, GYM_NAME, ADDRESS, GYM_PHONE, CONTENT, TIME, IMG, THUMB, CATE, STATUS, CEO_PHONE, CEO_EMAIL "
				+ "    FROM ( "
				+ "        SELECT NO, GYM_NAME, ADDRESS, GYM_PHONE, CONTENT, TIME, IMG, THUMB, CATE, STATUS, CEO_PHONE, CEO_EMAIL "
				+ "        FROM  GYM "
				+ "        ORDER BY NO DESC "
				+ "    ) "
				+ ") "
				+ "WHERE RNUM BETWEEN ? and ? ";
					 
		
		try {
			pstmt = connection.prepareStatement(query);
			
			pstmt.setInt(1, pageInfo.getStartList());
			pstmt.setInt(2, pageInfo.getEndList());
			
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				Gym gym = new Gym();
				
				gym.setNo(rs.getInt("NO"));
				gym.setGymName(rs.getString("GYM_NAME"));
				gym.setAddress(rs.getString("ADDRESS"));
				gym.setGymPhone(rs.getString("GYM_PHONE"));
				gym.setContent(rs.getString("CONTENT"));
				gym.setTime(rs.getString("TIME"));
				gym.setImg(rs.getString("IMG"));
				gym.setThumb(rs.getString("THUMB"));
				gym.setCategory(rs.getString("CATE"));
				gym.setStatus(rs.getString("STATUS"));
				gym.setCeoPhone(rs.getString("CEO_PHONE"));
				gym.setCeoEmail(rs.getString("CEO_EMAIL"));
				
				list.add(gym);
			}
			
	
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		
		return list;
	}

	public Gym findGymByNo(Connection connection, int no) {
		Gym gym = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String query = "SELECT * FROM GYM WHERE NO = ? ";
		
		try {
			pstmt = connection.prepareStatement(query);
			
			pstmt.setInt(1, no);

			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				gym = new Gym();
				
				gym.setNo(rs.getInt("NO"));
				gym.setGymName(rs.getString("GYM_NAME"));
				gym.setAddress(rs.getString("ADDRESS"));
				gym.setGymPhone(rs.getString("GYM_PHONE"));
				gym.setContent(rs.getString("CONTENT"));
				gym.setTime(rs.getString("TIME"));
				gym.setImg(rs.getString("IMG"));
				gym.setThumb(rs.getString("THUMB"));
				gym.setCategory(rs.getString("CATE"));
				gym.setStatus(rs.getString("STATUS"));
				gym.setCeoPhone(rs.getString("CEO_PHONE"));
				gym.setCeoEmail(rs.getString("CEO_EMAIL"));
				
			}
			
		
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		
		}
		
		return gym;	
	
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

	public int insertGym(Connection connection, Gym gym) {
		int result = 0;
		PreparedStatement pstmt = null;
		String query = "INSERT INTO GYM VALUES(SEQ_GYM.NEXTVAL,?,?,?,NULL,NULL,DEFAULT,DEFAULT,?,'N',?,?)";
		
		try {
		
			
			pstmt = connection.prepareStatement(query);
			
			pstmt.setString(1, gym.getGymName());
			pstmt.setString(2, gym.getAddress());
			pstmt.setString(3, gym.getGymPhone());
			pstmt.setString(4, gym.getCategory());
			pstmt.setString(5, gym.getCeoPhone());
			pstmt.setString(6, gym.getCeoEmail());

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

	public int insertVoucher(Connection connection, Voucher voucher) {
		int result = 0;
		PreparedStatement pstmt = null;
		String query = "INSERT INTO VOUCHER VALUES(SEQ_VOUCHER.NEXTVAL,?,?,?) ";
		
		try {
			
			pstmt = connection.prepareStatement(query);
			
			pstmt.setInt(1, voucher.getGymNo());
			pstmt.setString(2, voucher.getCate());
			pstmt.setInt(3, voucher.getPrice());

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

	public int updateGymStatus(Connection connection, int no, String string) {
		int result = 0;
		PreparedStatement pstmt = null;
		String query = "UPDATE GYM SET STATUS=? WHERE NO=?";
		
		try {
			pstmt = connection.prepareStatement(query);
			
			pstmt.setString(1, string);
			pstmt.setInt(2, no);
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	public int getQnaBoardCount(Connection connection) {
		int count = 0;
		
		PreparedStatement pstmt = null;
		
		ResultSet rs = null;
		
		String query = "SELECT COUNT(*) FROM QNABOARD ";
		
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
		}
		
		return count;
	}

	public List<QnaBoard> findQnaAll(Connection connection, PageInfo pageInfo) {
		List<QnaBoard> list = new ArrayList<>();
		
		
		PreparedStatement pstmt = null;
	
		ResultSet rs = null;
		
		String query = "SELECT RNUM, NO, WRITER_NO, TITLE, CONTENT, ORIGINAL_FILENAME, RENAMED_FILENAME, READCOUNT, STATUS, CREATE_DATE, MODIFY_DATE, REPLYCOUNT, SECRET_CHECK, NAME"
				+ "					  FROM ( "
				+ "					    SELECT ROWNUM AS RNUM, "
				+ "					          NO, WRITER_NO, TITLE, CONTENT, ORIGINAL_FILENAME, RENAMED_FILENAME, READCOUNT, STATUS, CREATE_DATE, MODIFY_DATE, REPLYCOUNT, SECRET_CHECK, NAME "
				+ "					  	FROM ( "
				+ "					 	    SELECT  Q.NO, Q.WRITER_NO, Q.TITLE, Q.CONTENT, Q.ORIGINAL_FILENAME, Q.RENAMED_FILENAME, Q.READCOUNT, Q.STATUS, Q.CREATE_DATE, Q.MODIFY_DATE, Q.REPLYCOUNT, Q.SECRET_CHECK, M.NAME "
				+ "					  		FROM QNABOARD Q "
				+ "					  		JOIN MEMBER M ON(Q.WRITER_NO = M.NO) "
				+ "                         ORDER BY Q.NO DESC "
				+ "					  	 ) "
				+ "                    ) WHERE RNUM BETWEEN ? and ? ";
		

		try {
			pstmt = connection.prepareStatement(query);

			
			pstmt.setInt(1, pageInfo.getStartList());
			pstmt.setInt(2, pageInfo.getEndList());
		
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				QnaBoard qnaboard = new QnaBoard();
				
				
				qnaboard.setNo(rs.getInt("NO"));
				qnaboard.setWriterNo(rs.getInt("WRITER_NO"));
				qnaboard.setTitle(rs.getString("TITLE"));
				qnaboard.setContent(rs.getString("CONTENT"));
				qnaboard.setOriginalFileName(rs.getString("ORIGINAL_FILENAME"));
				qnaboard.setRenamedFileName(rs.getString("RENAMED_FILENAME"));
				qnaboard.setReadCount(rs.getInt("READCOUNT"));
				qnaboard.setStatus(rs.getString("STATUS"));
				qnaboard.setCreateDate(rs.getDate("CREATE_DATE"));
				qnaboard.setModifyDate(rs.getDate("MODIFY_DATE"));
				qnaboard.setReplyCount(rs.getInt("REPLYCOUNT"));
				qnaboard.setSecretCheck(rs.getString("SECRET_CHECK"));
				qnaboard.setWriterName(rs.getString("NAME"));
				
				
				
				list.add(qnaboard);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		System.out.println(list);
		return list;
	}

	public int getNoticeBoardCount(Connection connection) {
		int count = 0;
		
		PreparedStatement pstmt = null;
		
		ResultSet rs = null;
		
		String query = "SELECT COUNT(*) FROM NOTICE ";
		
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
		}
		
		return count;
	}

	public List<Notice> findNoticeAll(Connection connection, PageInfo pageInfo) {
		List<Notice> list = new ArrayList<>();
		
		
		PreparedStatement pstmt = null;
	
		ResultSet rs = null;
		
		String query = "SELECT RNUM, NO, WRITER_NO, TITLE, CONTENT, ORIGINAL_FILENAME, RENAMED_FILENAME, READCOUNT, STATUS, CREATE_DATE, MODIFY_DATE, REPLYCOUNT, SECRET_CHECK, NAME"
				+ "					  FROM ( "
				+ "					    SELECT ROWNUM AS RNUM, "
				+ "					          NO, WRITER_NO, TITLE, CONTENT, ORIGINAL_FILENAME, RENAMED_FILENAME, READCOUNT, STATUS, CREATE_DATE, MODIFY_DATE, REPLYCOUNT, SECRET_CHECK, NAME "
				+ "					  	FROM ( "
				+ "					 	    SELECT  N.NO, N.WRITER_NO, N.TITLE, N.CONTENT, N.ORIGINAL_FILENAME, N.RENAMED_FILENAME, N.READCOUNT, N.STATUS, N.CREATE_DATE, N.MODIFY_DATE, N.REPLYCOUNT, N.SECRET_CHECK, M.NAME "
				+ "					  		FROM NOTICE N "
				+ "					  		JOIN MEMBER M ON(N.WRITER_NO = M.NO) "
				+ "                         ORDER BY N.NO DESC "
				+ "					  	 ) "
				+ "                    ) WHERE RNUM BETWEEN ? and ? ";
		

		try {
			pstmt = connection.prepareStatement(query);

			
			pstmt.setInt(1, pageInfo.getStartList());
			pstmt.setInt(2, pageInfo.getEndList());
		
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				Notice notice = new Notice();
				
				
				notice.setNo(rs.getInt("NO"));
				notice.setWriterNo(rs.getInt("WRITER_NO"));
				notice.setTitle(rs.getString("TITLE"));
				notice.setContent(rs.getString("CONTENT"));
				notice.setOriginalFileName(rs.getString("ORIGINAL_FILENAME"));
				notice.setRenamedFileName(rs.getString("RENAMED_FILENAME"));
				notice.setReadCount(rs.getInt("READCOUNT"));
				notice.setStatus(rs.getString("STATUS"));
				notice.setCreateDate(rs.getDate("CREATE_DATE"));
				notice.setModifyDate(rs.getDate("MODIFY_DATE"));
				notice.setReplyCount(rs.getInt("REPLYCOUNT"));
				notice.setSecretCheck(rs.getString("SECRET_CHECK"));
				notice.setWriterName(rs.getString("NAME"));
				
				
				
				list.add(notice);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		System.out.println(list);
		return list;
	}

	public int getNotMemberCount(Connection connection) {
		int count = 0;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String query = "SELECT COUNT(*) FROM MEMBER WHERE STATUS = 'N' ";
		
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
		
		}
		
		return count;	
	
	}

	
	public List<Board> findAll(Connection connection, PageInfo pageInfo) {
		List<Board> list = new ArrayList<>();
		
		
		PreparedStatement pstmt = null;
	
		ResultSet rs = null;
		
		String query = "SELECT RNUM, NO, WRITER_NO, TITLE, CONTENT, ORIGINAL_FILENAME, RENAMED_FILENAME, READCOUNT, STATUS, CREATE_DATE, MODIFY_DATE, REPLYCOUNT, SECRET_CHECK, NAME "
				+ "					  FROM ( "
				+ "					    SELECT ROWNUM AS RNUM, "
				+ "					          NO, WRITER_NO, TITLE, CONTENT, ORIGINAL_FILENAME, RENAMED_FILENAME, READCOUNT, STATUS, CREATE_DATE, MODIFY_DATE, REPLYCOUNT, SECRET_CHECK, NAME "
				+ "					  	FROM ( "
				+ "					 	    SELECT  B.NO, B.WRITER_NO, B.TITLE, B.CONTENT, B.ORIGINAL_FILENAME, B.RENAMED_FILENAME, B.READCOUNT, B.STATUS, B.CREATE_DATE, B.MODIFY_DATE, B.REPLYCOUNT, B.SECRET_CHECK, M.NAME "
				+ "					  		FROM BOARD B "
				+ "					  		JOIN MEMBER M ON(B.WRITER_NO = M.NO) "
				+ "                     		  ORDER BY B.NO DESC "
				+ "					  	 ) "
				+ "                    ) WHERE RNUM BETWEEN ? and ? ";
		

		try {
			pstmt = connection.prepareStatement(query);

			
			pstmt.setInt(1, pageInfo.getStartList());
			pstmt.setInt(2, pageInfo.getEndList());
		
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				Board board = new Board();
				
				
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
				board.setReplyCount(rs.getInt("REPLYCOUNT"));
				board.setSecretCheck(rs.getString("SECRET_CHECK"));
				board.setWriterName(rs.getString("NAME"));

				
				
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

	public int reportBoardStatus(Connection connection, int no, String status) {
		int result = 0;
		PreparedStatement pstmt = null;
		String query = "UPDATE BOARD SET STATUS=? WHERE NO=?";
		
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

	public int insertQnaReply(Connection connection, QnaReply qnareply) {
		int result = 0;
		PreparedStatement pstmt = null;
		String query = "INSERT INTO QNAREPLY VALUES(SEQ_REPLY_NO.NEXTVAL, ?, ?, ?, DEFAULT, DEFAULT, DEFAULT) ";
		
		try {
			pstmt = connection.prepareStatement(query);
			
			pstmt.setInt(1, qnareply.getBoardNo());
			pstmt.setInt(2, qnareply.getWriterNo());
			pstmt.setString(3, qnareply.getContent());
			
			
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}
}
//	public List<Test> selectList(Connection connection) {
//		List<Test> list = new ArrayList<>();
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//		
//		try {
//			String query = "SELECT * FROM TEST ";
//				
//			pstmt = connection.prepareStatement(query);
//
//			rs = pstmt.executeQuery();
//			
//			while (rs.next()) {
//				Test test = new Test();
//				
//				test.setSeq(rs.getInt("SEQ"));
//				test.setWriter(rs.getString("WRITER"));
//				test.setTitle(rs.getString("TITLE"));
//				test.setContent(rs.getString("CONTENT"));
//				test.setRegdate(rs.getDate("REGDATE"));
//				
//				list.add(test);
//			}
//			
//		} catch (SQLException e) {
//			e.printStackTrace();
//		} finally {
//			close(rs);
//			close(pstmt);
//		}
//		
//		return list;
//	}
//	
//
//}


