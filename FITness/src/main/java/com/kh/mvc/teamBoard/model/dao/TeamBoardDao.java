package com.kh.mvc.teamBoard.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.kh.mvc.board.model.vo.Reply;
import com.kh.mvc.teamBoard.model.vo.TeamBoard;

import com.kh.mvc.common.util.PageInfo;

import static com.kh.mvc.common.jdbc.JDBCTemplate.close;

public class TeamBoardDao {

	public int getBoardCount(Connection connection) {
		int count = 0;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String query = "SELECT COUNT(*) FROM TEAMBOARD WHERE STATUS='Y'";
		
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

	public List<TeamBoard> findAll(Connection connection, PageInfo pageInfo) {
		List<TeamBoard> list = new ArrayList<>();
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
					 + 		"FROM TEAMBOARD B "
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
				TeamBoard teamboard = new TeamBoard();
				
				teamboard.setNo(rs.getInt("NO"));
				teamboard.setRowNum(rs.getInt("RNUM"));
				teamboard.setWriterId(rs.getString("ID"));
				teamboard.setTitle(rs.getString("TITLE"));
				teamboard.setCreateDate(rs.getDate("CREATE_DATE"));
				teamboard.setOriginalFileName(rs.getString("ORIGINAL_FILENAME"));
				teamboard.setReadCount(rs.getInt("READCOUNT"));
				teamboard.setStatus(rs.getString("STATUS"));
				teamboard.setReplyCount(rs.getInt("REPLYCOUNT"));
				teamboard.setSecretCheck(rs.getString("SECRET_CHECK"));
				
				list.add(teamboard);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		
		return list;
	}

	public TeamBoard findBoardByNo(Connection connection, int no) {
		TeamBoard teamboard = null;
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
					  + "FROM TEAMBOARD B "
					  + "JOIN MEMBER M ON(B.WRITER_NO = M.NO) "
					  + "WHERE B.STATUS = 'Y' AND B.NO=?";
		
		
		try {
			pstmt = connection.prepareStatement(query);
			
			pstmt.setInt(1, no);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				teamboard = new TeamBoard();
				
				teamboard.setNo(rs.getInt("NO"));
				teamboard.setTitle(rs.getString("TITLE"));
				teamboard.setWriterId(rs.getString("ID"));
				teamboard.setReadCount(rs.getInt("READCOUNT"));
				teamboard.setOriginalFileName(rs.getString("ORIGINAL_FILENAME"));
				teamboard.setRenamedFileName(rs.getString("RENAMED_FILENAME"));
				teamboard.setContent(rs.getString("CONTENT"));
				// 댓글 조회
				teamboard.setReplies(this.getRepliesByNo(connection, no));				
				teamboard.setCreateDate(rs.getDate("CREATE_DATE"));
				teamboard.setModifyDate(rs.getDate("MODIFY_DATE"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		
		return teamboard;
	}

	public int insertBoard(Connection connection, TeamBoard teamboard) {
		int result = 0;
		PreparedStatement pstmt = null;
		String query = "INSERT INTO TEAMBOARD VALUES(SEQ_BOARD_NO.NEXTVAL,?,?,?,?,?,DEFAULT,DEFAULT,DEFAULT,DEFAULT)";
		
		try {
			pstmt = connection.prepareStatement(query);
			
			pstmt.setInt(1, teamboard.getWriterNo());
			pstmt.setString(2, teamboard.getTitle());
			pstmt.setString(3, teamboard.getContent());
			pstmt.setString(4, teamboard.getOriginalFileName());
			pstmt.setString(5, teamboard.getRenamedFileName());
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	public int updateBoard(Connection connection, TeamBoard teamboard) {
		int result = 0; 
		PreparedStatement pstmt = null;
		String query = "UPDATE TEAMBOARD SET TITLE=?,CONTENT=?,ORIGINAL_FILENAME=?,RENAMED_FILENAME=?,MODIFY_DATE=SYSDATE WHERE NO=?";
		
		try {
			pstmt = connection.prepareStatement(query);
			
			pstmt.setString(1, teamboard.getTitle());
			pstmt.setString(2, teamboard.getContent());
			pstmt.setString(3, teamboard.getOriginalFileName());
			pstmt.setString(4, teamboard.getRenamedFileName());
			pstmt.setInt(5, teamboard.getNo());
			
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
		String query = "UPDATE TEAMBOARD SET STATUS=? WHERE NO=?";
		
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
	
	public List<Reply> getRepliesByNo(Connection connection, int no) {
		List<Reply> replies = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String query = 
				"SELECT R.NO, "
					 + "R.BOARD_NO, "
					 + "R.CONTENT, "
					 + "M.ID, "
					 + "R.CREATE_DATE, "
					 + "R.MODIFY_DATE "
			  + "FROM REPLY R "
			  + "JOIN MEMBER M ON(R.WRITER_NO = M.NO) "
			  + "WHERE R.STATUS='Y' AND BOARD_NO=? "
			  + "ORDER BY R.NO DESC";		
		
		try {
			pstmt = connection.prepareStatement(query);
			
			pstmt.setInt(1, no);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				Reply reply = new Reply();
				
				reply.setNo(rs.getInt("NO"));
				reply.setBoardNo(rs.getInt("BOARD_NO"));
				reply.setContent(rs.getString("CONTENT"));
				reply.setWriterId(rs.getString("ID"));
				reply.setCreateDate(rs.getDate("CREATE_DATE"));
				reply.setModifyDate(rs.getDate("MODIFY_DATE"));
				
				replies.add(reply);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		
		return replies;
	}

	public int insertReply(Connection connection, Reply reply) {
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

	public int updateReadCount(Connection connection, TeamBoard teamboard) {
		int result = 0;
		PreparedStatement pstmt = null;
		String query = "UPDATE TEAMBOARD SET READCOUNT=? WHERE NO=?";		
		
		try {
			pstmt = connection.prepareStatement(query);
			
			teamboard.setReadCount(teamboard.getReadCount() + 1);
			
			pstmt.setInt(1, teamboard.getReadCount());
			pstmt.setInt(2, teamboard.getNo());

			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}
	
	
	

}