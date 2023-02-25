package com.kh.mvc.qna.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.kh.mvc.qna.model.vo.QnaBoard;
import com.kh.mvc.qna.model.vo.QnaReply;
import com.kh.mvc.board.model.vo.Board;
import com.kh.mvc.common.util.PageInfo;

import static com.kh.mvc.common.jdbc.JDBCTemplate.close;

public class QnaDao {

	public int getBoardCount(Connection connection) {
		int count = 0;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String query = "SELECT COUNT(*) FROM QNABOARD WHERE STATUS='Y'";
		
		try {
			pstmt = connection.prepareStatement(query);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
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

	public List<QnaBoard> findAll(Connection connection, PageInfo pageInfo) {
		List<QnaBoard> list = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String query = "SELECT RNUM, NO, TITLE, ID, CREATE_DATE, ORIGINAL_FILENAME, READCOUNT, STATUS, REPLYCOUNT, SECRET_CHECK "
					 + "FROM ("
					 +    "SELECT ROWNUM AS RNUM, "
					 +           "NO, "
					 + 			 "TITLE, "
					 + 			 "ID, "
					 + 			 "CREATE_DATE, "
					 + 			 "ORIGINAL_FILENAME, "
					 +  		 "READCOUNT, "
					 +     		 "STATUS, "
					 +     		 "REPLYCOUNT, "
					 +     		 "SECRET_CHECK "
					 + 	 "FROM ("
					 + 	    "SELECT B.NO, "
					 + 			   "B.TITLE, "
					 +  		   "M.ID, "
					 + 			   "B.CREATE_DATE, "
					 + 			   "B.ORIGINAL_FILENAME, "
					 + 			   "B.READCOUNT, "
					 + 	   		   "B.STATUS, "
					 +     		   "B.REPLYCOUNT, "
					 +     		   "B.SECRET_CHECK "
					 + 		"FROM QNABOARD B "
					 + 		"JOIN MEMBER M ON(B.WRITER_NO = M.NO) "
					 + 		"WHERE B.STATUS = 'Y' ORDER BY B.NO DESC"
					 + 	 ")"
					 + ") WHERE RNUM BETWEEN ? and ?";
		
		try {
			pstmt = connection.prepareStatement(query);
			
			pstmt.setInt(1, pageInfo.getStartList());
			pstmt.setInt(2, pageInfo.getEndList());
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				QnaBoard qnaboard = new QnaBoard();
				
				qnaboard.setNo(rs.getInt("NO"));
				qnaboard.setRowNum(rs.getInt("RNUM"));
				qnaboard.setWriterId(rs.getString("ID"));
				qnaboard.setTitle(rs.getString("TITLE"));
				qnaboard.setCreateDate(rs.getDate("CREATE_DATE"));
				qnaboard.setOriginalFileName(rs.getString("ORIGINAL_FILENAME"));
				qnaboard.setReadCount(rs.getInt("READCOUNT"));
				qnaboard.setStatus(rs.getString("STATUS"));
				qnaboard.setReplyCount(rs.getInt("REPLYCOUNT"));
				qnaboard.setSecretCheck(rs.getString("SECRET_CHECK"));
				
				list.add(qnaboard);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		
		return list;
	}

	public QnaBoard findBoardByNo(Connection connection, int no) {
		QnaBoard qnaboard = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String query = "SELECT B.NO, "
							+ "B.TITLE, "
							+ "M.ID, "
							+ "B.READCOUNT, "
							+ "B.ORIGINAL_FILENAME, "
							+ "B.RENAMED_FILENAME, "
							+ "B.CONTENT, "
							+ "B.CREATE_DATE, "
							+ "B.MODIFY_DATE "
					  + "FROM QNABOARD B "
					  + "JOIN MEMBER M ON(B.WRITER_NO = M.NO) "
					  + "WHERE B.STATUS = 'Y' AND B.NO=?";
		
		
		try {
			pstmt = connection.prepareStatement(query);
			
			pstmt.setInt(1, no);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				qnaboard = new QnaBoard();
				
				qnaboard.setNo(rs.getInt("NO"));
				qnaboard.setTitle(rs.getString("TITLE"));
				qnaboard.setWriterId(rs.getString("ID"));
				qnaboard.setReadCount(rs.getInt("READCOUNT"));
				qnaboard.setOriginalFileName(rs.getString("ORIGINAL_FILENAME"));
				qnaboard.setRenamedFileName(rs.getString("RENAMED_FILENAME"));
				qnaboard.setContent(rs.getString("CONTENT"));
				// 댓글 조회
				qnaboard.setReplies(this.getRepliesByNo(connection, no));				
				qnaboard.setCreateDate(rs.getDate("CREATE_DATE"));
				qnaboard.setModifyDate(rs.getDate("MODIFY_DATE"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		
		return qnaboard;
	}

	public int insertBoard(Connection connection, QnaBoard qnaboard) {
		int result = 0;
		PreparedStatement pstmt = null;
		String query = "INSERT INTO QNABOARD VALUES(SEQ_QNABOARD_NO.NEXTVAL,?,?,?,?,?,DEFAULT,DEFAULT,DEFAULT,DEFAULT,DEFAULT,DEFAULT)";
		
		try {
			pstmt = connection.prepareStatement(query);
			
			pstmt.setInt(1, qnaboard.getWriterNo());
			pstmt.setString(2, qnaboard.getTitle());
			pstmt.setString(3, qnaboard.getContent());
			pstmt.setString(4, qnaboard.getOriginalFileName());
			pstmt.setString(5, qnaboard.getRenamedFileName());

			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	public int updateBoard(Connection connection, QnaBoard qnaboard) {
		int result = 0; 
		PreparedStatement pstmt = null;
		String query = "UPDATE QNABOARD SET TITLE=?,CONTENT=?,ORIGINAL_FILENAME=?,RENAMED_FILENAME=?,MODIFY_DATE=SYSDATE WHERE NO=?";
		
		try {
			pstmt = connection.prepareStatement(query);
			
			pstmt.setString(1, qnaboard.getTitle());
			pstmt.setString(2, qnaboard.getContent());
			pstmt.setString(3, qnaboard.getOriginalFileName());
			pstmt.setString(4, qnaboard.getRenamedFileName());
			pstmt.setInt(5, qnaboard.getNo());
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	public int updateStatus(Connection connection, int no, String status) {
		int result = 0;
		PreparedStatement pstmt = null;
		String query = "UPDATE QNABOARD SET STATUS=? WHERE NO=?";
		
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
	
	public List<QnaReply> getRepliesByNo(Connection connection, int no) {
		List<QnaReply> replies = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String query = "SELECT * FROM QNAREPLY Q JOIN MEMBER M ON(Q.WRITER_NO = M.NO) WHERE Q.BOARD_NO= ? ";
					
		
		try {
			pstmt = connection.prepareStatement(query);
			
			pstmt.setInt(1, no);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				QnaReply qnareply = new QnaReply();
				
				qnareply.setNo(rs.getInt("NO"));
				qnareply.setBoardNo(rs.getInt("BOARD_NO"));
				qnareply.setContent(rs.getString("CONTENT"));
				qnareply.setWriterId(rs.getString("ID"));
				qnareply.setCreateDate(rs.getDate("CREATE_DATE"));
				qnareply.setModifyDate(rs.getDate("MODIFY_DATE"));
				
				replies.add(qnareply);
				
			
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		
		return replies;
	}

	public int insertReply(Connection connection, QnaReply reply) {
		int result = 0;
		PreparedStatement pstmt = null;
		String query = "INSERT INTO REPLY VALUES(SEQ_REPLY_NO.NEXTVAL, ?, ?, ?, DEFAULT, DEFAULT, DEFAULT)";
		
		try {
			pstmt = connection.prepareStatement(query);
			
			pstmt.setInt(1, reply.getBoardNo());
			pstmt.setInt(2, reply.getWriterNo());
			pstmt.setString(3, reply.getContent());
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	public int updateReadCount(Connection connection, QnaBoard qnaboard) {
		int result = 0;
		PreparedStatement pstmt = null;
		String query = "UPDATE QNABOARD SET READCOUNT=? WHERE NO=?";		
		
		try {
			pstmt = connection.prepareStatement(query);
			
			qnaboard.setReadCount(qnaboard.getReadCount() + 1);
			
			pstmt.setInt(1, qnaboard.getReadCount());
			pstmt.setInt(2, qnaboard.getNo());

			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	public int updateReplyCount(Connection connection, QnaBoard qnaboard) {
		int result = 0;
		PreparedStatement pstmt = null;
		
		String query = "SELECT COUNT(NO) "
					 + "FROM REPLY "
					 + "WHERE REPLY.QNABOARD_NO =(SELECT NO FROM QNABOARD WHERE NO=?)";			 
		
		try {
			pstmt = connection.prepareStatement(query);
			qnaboard.setReplyCount(qnaboard.getReplyCount());
			
			pstmt.setInt(1, qnaboard.getReplyCount());

			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	public List<QnaBoard> findAllByNo(Connection connection, PageInfo pageInfo, int no) {
		List<QnaBoard> list = new ArrayList<>();
	      
	      PreparedStatement pstmt = null;

	      ResultSet rs = null;
	     
	      
	      String query = "SELECT RNUM, NO, TITLE, ID, CREATE_DATE, ORIGINAL_FILENAME, READCOUNT, STATUS, REPLYCOUNT, SECRET_CHECK  "
	             + "FROM ("
	             +    "SELECT ROWNUM AS RNUM, "
	             +          "NO, "
	             +          "TITLE, "
	             +          "ID, "
	             +          "CREATE_DATE, "
	             +          "ORIGINAL_FILENAME, "
	             +           "READCOUNT, "
	             +           "STATUS, "
				 +     		 "REPLYCOUNT, "
				 +     		 "SECRET_CHECK "
	             +     "FROM ("
	             +        "SELECT B.NO, "
	             +             "B.TITLE, "
	             +             "M.ID, "
	             +             "B.CREATE_DATE, "
	             +             "B.ORIGINAL_FILENAME, "
	             +             "B.READCOUNT, "
	             +             "B.STATUS, "
				 +     		   "B.REPLYCOUNT, "
				 +     		   "B.SECRET_CHECK "
	             +       "FROM QNABOARD B "
	             +       "JOIN MEMBER M ON(B.WRITER_NO = M.NO) "
	             +       "WHERE B.STATUS = 'Y' AND M.NO = ? ORDER BY B.NO DESC"
	             +     ")"
	             + ") WHERE RNUM BETWEEN ? and ?";
	      
	      try {
	         pstmt = connection.prepareStatement(query);

	         pstmt.setInt(1, no);
	         pstmt.setInt(2, pageInfo.getStartList());
	         pstmt.setInt(3, pageInfo.getEndList());
	      
	         rs = pstmt.executeQuery();
	         
	         while (rs.next()) {
	        	 QnaBoard board = new QnaBoard();
	            
	            board.setNo(rs.getInt("NO"));
	            board.setRowNum(rs.getInt("RNUM"));
	            board.setWriterId(rs.getString("ID"));
	            board.setTitle(rs.getString("TITLE"));
	            board.setCreateDate(rs.getDate("CREATE_DATE"));
	            board.setOriginalFileName(rs.getString("ORIGINAL_FILENAME"));
	            board.setReadCount(rs.getInt("READCOUNT"));
	            board.setStatus(rs.getString("STATUS"));
				board.setReplyCount(rs.getInt("REPLYCOUNT"));
				board.setSecretCheck(rs.getString("SECRET_CHECK"));
	            
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

	public QnaBoard findQnaBoardByNo(Connection connection, int no) {
		QnaBoard qnaboard = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String query = "SELECT B.NO, "
							+ "B.TITLE, "
							+ "M.ID, "
							+ "B.READCOUNT, "
							+ "B.ORIGINAL_FILENAME, "
							+ "B.RENAMED_FILENAME, "
							+ "B.CONTENT, "
							+ "B.CREATE_DATE, "
							+ "B.MODIFY_DATE "
					  + "FROM QNABOARD B "
					  + "JOIN MEMBER M ON(B.WRITER_NO = M.NO) "
					  + "WHERE B.STATUS = 'Y' AND B.NO=?";
		
		
		try {
			pstmt = connection.prepareStatement(query);
			
			pstmt.setInt(1, no);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				qnaboard = new QnaBoard();
				
				qnaboard.setNo(rs.getInt("NO"));
				qnaboard.setTitle(rs.getString("TITLE"));
				qnaboard.setWriterId(rs.getString("ID"));
				qnaboard.setReadCount(rs.getInt("READCOUNT"));
				qnaboard.setOriginalFileName(rs.getString("ORIGINAL_FILENAME"));
				qnaboard.setRenamedFileName(rs.getString("RENAMED_FILENAME"));
				qnaboard.setContent(rs.getString("CONTENT"));
				// 댓글 조회
				qnaboard.setReplies(this.getRepliesByNo(connection, no));				
				qnaboard.setCreateDate(rs.getDate("CREATE_DATE"));
				qnaboard.setModifyDate(rs.getDate("MODIFY_DATE"));
			}
			System.out.println("1번쨰 " + qnaboard);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		
		return qnaboard;
	}

	public int updateQnaReplyCount(Connection connection, QnaBoard qnaboard) {
		int result = 0;
		PreparedStatement pstmt = null;
		
		String query = "SELECT COUNT(NO) "
					 + "FROM REPLY "
					 + "WHERE REPLY.QNABOARD_NO =(SELECT NO FROM QNABOARD WHERE NO=?)";			 
		
		try {
			pstmt = connection.prepareStatement(query);
			qnaboard.setReplyCount(qnaboard.getReplyCount());
			
			pstmt.setInt(1, qnaboard.getReplyCount());

			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}

}