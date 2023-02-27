package com.kh.mvc.notice.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.kh.mvc.common.util.PageInfo;
import com.kh.mvc.notice.model.vo.Notice;

import static com.kh.mvc.common.jdbc.JDBCTemplate.close;

public class NoticeDao {

	public int getBoardCount(Connection connection) {
		int count = 0;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String query = "SELECT COUNT(*) FROM NOTICE WHERE STATUS='Y'";
		
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

	public List<Notice> findAll(Connection connection, PageInfo pageInfo, int fix) {
		List<Notice> list = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String query = "SELECT RNUM, NO, TITLE, ID, CREATE_DATE, ORIGINAL_FILENAME, READCOUNT, STATUS, REPLYCOUNT, SECRET_CHECK, FIX "
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
					 +     		 "SECRET_CHECK, "
					 +     		 "FIX "
					 + 	 "FROM ("
					 + 	    "SELECT B.NO, "
					 + 			   "B.TITLE, "
					 +  		   "M.ID, "
					 + 			   "B.CREATE_DATE, "
					 + 			   "B.ORIGINAL_FILENAME, "
					 + 			   "B.READCOUNT, "
					 + 	   		   "B.STATUS, "
					 +     		   "B.REPLYCOUNT, "
					 +     		   "B.SECRET_CHECK, "
					 +     		   "B.FIX "
					 + 		"FROM NOTICE B "
					 + 		"JOIN MEMBER M ON(B.WRITER_NO = M.NO) "
					 + 		"WHERE B.STATUS = 'Y' ORDER BY b.FIX desc, B.NO desc "
					 + 	 ")"
					 + ") WHERE RNUM BETWEEN ? and ?";
		
		try {
			pstmt = connection.prepareStatement(query);
			
			pstmt.setInt(1, pageInfo.getStartList());
			pstmt.setInt(2, pageInfo.getEndList());
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				Notice notice = new Notice();
				
				notice.setNo(rs.getInt("NO"));
				notice.setRowNum(rs.getInt("RNUM"));
				notice.setWriterId(rs.getString("ID"));
				notice.setTitle(rs.getString("TITLE"));
				notice.setCreateDate(rs.getDate("CREATE_DATE"));
				notice.setOriginalFileName(rs.getString("ORIGINAL_FILENAME"));
				notice.setReadCount(rs.getInt("READCOUNT"));
				notice.setStatus(rs.getString("STATUS"));
				notice.setReplyCount(rs.getInt("REPLYCOUNT"));
				notice.setSecretCheck(rs.getString("SECRET_CHECK"));
				notice.setFix(rs.getString("FIX"));
				
				list.add(notice);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		
		return list;
	}

	public Notice findBoardByNo(Connection connection, int no) {
		Notice notice = null;
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
					  + "FROM NOTICE B "
					  + "JOIN MEMBER M ON(B.WRITER_NO = M.NO) "
					  + "WHERE B.STATUS = 'Y' AND B.NO=?";
		
		try {
			pstmt = connection.prepareStatement(query);
			
			pstmt.setInt(1, no);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				notice = new Notice();
				
				notice.setNo(rs.getInt("NO"));
				notice.setTitle(rs.getString("TITLE"));
				notice.setWriterId(rs.getString("ID"));
				notice.setReadCount(rs.getInt("READCOUNT"));
				notice.setOriginalFileName(rs.getString("ORIGINAL_FILENAME"));
				notice.setRenamedFileName(rs.getString("RENAMED_FILENAME"));
				notice.setContent(rs.getString("CONTENT"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		
		return notice;
	}

	public int insertBoard(Connection connection, Notice notice) {
		int result = 0;
		PreparedStatement pstmt = null;
		String query = "INSERT INTO NOTICE VALUES(SEQ_NOTICE_NO.NEXTVAL,?,?,?,?,?,DEFAULT,DEFAULT,DEFAULT,DEFAULT,DEFAULT,DEFAULT,DEFAULT)";
		
		try {
			pstmt = connection.prepareStatement(query);
			
			pstmt.setInt(1, notice.getWriterNo());
			pstmt.setString(2, notice.getTitle());
			pstmt.setString(3, notice.getContent());
			pstmt.setString(4, notice.getOriginalFileName());
			pstmt.setString(5, notice.getRenamedFileName());

			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	public int updateBoard(Connection connection, Notice notice) {
		int result = 0; 
		PreparedStatement pstmt = null;
		String query = "UPDATE NOTICE SET TITLE=?,CONTENT=?,ORIGINAL_FILENAME=?,RENAMED_FILENAME=?,MODIFY_DATE=SYSDATE WHERE NO=?";
		
		try {
			pstmt = connection.prepareStatement(query);
			
			pstmt.setString(1, notice.getTitle());
			pstmt.setString(2, notice.getContent());
			pstmt.setString(3, notice.getOriginalFileName());
			pstmt.setString(4, notice.getRenamedFileName());
			pstmt.setInt(5, notice.getNo());
			
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
		String query = "UPDATE NOTICE SET STATUS=? WHERE NO=?";
		
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
	

	public int updateReadCount(Connection connection, Notice notice) {
		int result = 0;
		PreparedStatement pstmt = null;
		String query = "UPDATE NOTICE SET READCOUNT=? WHERE NO=?";		
		
		try {
			pstmt = connection.prepareStatement(query);
			
			notice.setReadCount(notice.getReadCount() + 1);
			
			pstmt.setInt(1, notice.getReadCount());
			pstmt.setInt(2, notice.getNo());

			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	public int updateReplyCount(Connection connection, Notice notice) {
		int result = 0;
		PreparedStatement pstmt = null;
		
		String query = "SELECT COUNT(NO) "
					 + "FROM REPLY "
					 + "WHERE REPLY.NOTICE_NO =(SELECT NO FROM NOTICE WHERE NO=?)";			 
		
		try {
			pstmt = connection.prepareStatement(query);
			notice.setReplyCount(notice.getReplyCount());
			
			pstmt.setInt(1, notice.getReplyCount());

			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}

}