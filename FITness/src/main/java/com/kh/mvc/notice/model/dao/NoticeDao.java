package com.kh.mvc.notice.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.kh.mvc.common.util.PageInfo;
import com.kh.mvc.notice.model.vo.NoticeBoard;

import static com.kh.mvc.common.jdbc.JDBCTemplate.close;

public class NoticeDao {

	public int getBoardCount(Connection connection) {
		int count = 0;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String query = "SELECT COUNT(*) FROM NoticeBoard WHERE STATUS='Y'";
		
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

	public List<NoticeBoard> findAll(Connection connection, PageInfo pageInfo) {
		List<NoticeBoard> list = new ArrayList<>();
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
					 + 		"FROM NoticeBoard B "
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
				NoticeBoard NoticeBoard = new NoticeBoard();
				
				NoticeBoard.setNo(rs.getInt("NO"));
				NoticeBoard.setRowNum(rs.getInt("RNUM"));
				NoticeBoard.setWriterId(rs.getString("ID"));
				NoticeBoard.setTitle(rs.getString("TITLE"));
				NoticeBoard.setCreateDate(rs.getDate("CREATE_DATE"));
				NoticeBoard.setOriginalFileName(rs.getString("ORIGINAL_FILENAME"));
				NoticeBoard.setReadCount(rs.getInt("READCOUNT"));
				NoticeBoard.setStatus(rs.getString("STATUS"));
				NoticeBoard.setReplyCount(rs.getInt("REPLYCOUNT"));
				NoticeBoard.setSecretCheck(rs.getString("SECRET_CHECK"));
				
				list.add(NoticeBoard);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		
		return list;
	}

	public NoticeBoard findBoardByNo(Connection connection, int no) {
		NoticeBoard NoticeBoard = null;
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
					  + "FROM NoticeBoard B "
					  + "JOIN MEMBER M ON(B.WRITER_NO = M.NO) "
					  + "WHERE B.STATUS = 'Y' AND B.NO=?";
		
		
		try {
			pstmt = connection.prepareStatement(query);
			
			pstmt.setInt(1, no);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				NoticeBoard = new NoticeBoard();
				
				NoticeBoard.setNo(rs.getInt("NO"));
				NoticeBoard.setTitle(rs.getString("TITLE"));
				NoticeBoard.setWriterId(rs.getString("ID"));
				NoticeBoard.setReadCount(rs.getInt("READCOUNT"));
				NoticeBoard.setOriginalFileName(rs.getString("ORIGINAL_FILENAME"));
				NoticeBoard.setRenamedFileName(rs.getString("RENAMED_FILENAME"));
				NoticeBoard.setContent(rs.getString("CONTENT"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		
		return NoticeBoard;
	}

	public int insertBoard(Connection connection, NoticeBoard NoticeBoard) {
		int result = 0;
		PreparedStatement pstmt = null;
		String query = "INSERT INTO NoticeBoard VALUES(SEQ_NoticeBoard_NO.NEXTVAL,?,?,?,?,?,DEFAULT,DEFAULT,DEFAULT,DEFAULT,DEFAULT,DEFAULT)";
		
		try {
			pstmt = connection.prepareStatement(query);
			
			pstmt.setInt(1, NoticeBoard.getWriterNo());
			pstmt.setString(2, NoticeBoard.getTitle());
			pstmt.setString(3, NoticeBoard.getContent());
			pstmt.setString(4, NoticeBoard.getOriginalFileName());
			pstmt.setString(5, NoticeBoard.getRenamedFileName());

			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	public int updateBoard(Connection connection, NoticeBoard NoticeBoard) {
		int result = 0; 
		PreparedStatement pstmt = null;
		String query = "UPDATE NoticeBoard SET TITLE=?,CONTENT=?,ORIGINAL_FILENAME=?,RENAMED_FILENAME=?,MODIFY_DATE=SYSDATE WHERE NO=?";
		
		try {
			pstmt = connection.prepareStatement(query);
			
			pstmt.setString(1, NoticeBoard.getTitle());
			pstmt.setString(2, NoticeBoard.getContent());
			pstmt.setString(3, NoticeBoard.getOriginalFileName());
			pstmt.setString(4, NoticeBoard.getRenamedFileName());
			pstmt.setInt(5, NoticeBoard.getNo());
			
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
		String query = "UPDATE NoticeBoard SET STATUS=? WHERE NO=?";
		
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
	

	public int updateReadCount(Connection connection, NoticeBoard NoticeBoard) {
		int result = 0;
		PreparedStatement pstmt = null;
		String query = "UPDATE NoticeBoard SET READCOUNT=? WHERE NO=?";		
		
		try {
			pstmt = connection.prepareStatement(query);
			
			NoticeBoard.setReadCount(NoticeBoard.getReadCount() + 1);
			
			pstmt.setInt(1, NoticeBoard.getReadCount());
			pstmt.setInt(2, NoticeBoard.getNo());

			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	public int updateReplyCount(Connection connection, NoticeBoard NoticeBoard) {
		int result = 0;
		PreparedStatement pstmt = null;
		
		String query = "SELECT COUNT(NO) "
					 + "FROM REPLY "
					 + "WHERE REPLY.NoticeBoard_NO =(SELECT NO FROM NoticeBoard WHERE NO=?)";			 
		
		try {
			pstmt = connection.prepareStatement(query);
			NoticeBoard.setReplyCount(NoticeBoard.getReplyCount());
			
			pstmt.setInt(1, NoticeBoard.getReplyCount());

			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	public List<NoticeBoard> findAllByNo(Connection connection, PageInfo pageInfo, int no) {
		List<NoticeBoard> list = new ArrayList<>();
	      
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
	             +       "FROM NoticeBoard B "
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
	        	 NoticeBoard board = new NoticeBoard();
	            
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
	

}