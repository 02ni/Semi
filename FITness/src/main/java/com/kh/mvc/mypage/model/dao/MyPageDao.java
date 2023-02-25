package com.kh.mvc.mypage.model.dao;

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
import com.kh.mvc.mypage.model.vo.PurchaseHistory;

public class MyPageDao {
	//나의 게시글
	public List<Board> findMyWriting(Connection connection, PageInfo pageInfo, Member memberI) {
		List<Board> list = new ArrayList<>();
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
					 + 		"FROM BOARD B "
					 + 		"JOIN MEMBER M ON(B.WRITER_NO = M.NO) "
					 + 		"WHERE B.STATUS = 'Y' ORDER BY B.NO DESC"
					 + 	 ")"
					 + ") WHERE ID=? AND RNUM BETWEEN ? and ?";
		
		try {
			pstmt = connection.prepareStatement(query);
			pstmt.setString(1, memberI.getId());
			pstmt.setInt(2, pageInfo.getStartList());
			pstmt.setInt(3, pageInfo.getEndList());
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				Board board = new Board();
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
	
		// 좋아요 찾기 
	public List<Gym> findFavorite(Connection connection, Member member) {
		List<Gym> list = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String query = "SELECT G.NO, "
						+ "G.GYM_NAME, "
						+ "G.ADDRESS, "
						+ "G.CATE "
						+ "FROM GYM G "
						+ "JOIN LIKELIST L ON(G.NO = L.GYM_NO)"
						+ "WHERE L.MEMBER_NO = ?";
		
		try {
			pstmt = connection.prepareStatement(query);
			pstmt.setInt(1, member.getNo());
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				Gym gym= new Gym();
				gym.setNo(rs.getInt("NO"));
				gym.setGymName(rs.getString("GYM_NAME"));
				gym.setAddress(rs.getString("ADDRESS"));
				gym.setCategory(rs.getString("CATE"));
				
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
	
	// 장바구니 찾기 
	public List<Voucher> findBasket(Connection connection, Member member) {
		List<Voucher> list = new ArrayList<>();
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String query = "SELECT B.NO, V.VOUCHER_NO, G.GYM_NAME, V.CATE FROM VOUCHER V JOIN BASKET B ON(V.VOUCHER_NO = B.VOUCHER_NO) JOIN GYM G ON(V.GYM_NO = G.NO) WHERE B.MEMBER_NO = ?";
		
		try {
			pstmt = connection.prepareStatement(query);
			pstmt.setInt(1, member.getNo());
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				Voucher voucher= new Voucher();
				voucher.setBasketNo(rs.getInt("NO"));
				voucher.setVoucherNo(rs.getInt("VOUCHER_NO"));	
				voucher.setGym_name(rs.getString("GYM_NAME"));
				voucher.setCate(rs.getString("CATE"));
				
				list.add(voucher);
			}
			System.out.println(list);
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		return list;
	}
		
	//장바구니삭제
	public int updateMemberStatus(Connection connection, int basketNo) {
		int result = 0;
		PreparedStatement pstmt = null;
		String query = "DELETE FROM BASKET WHERE NO = ?";
		
		try {
			pstmt = connection.prepareStatement(query);
			pstmt.setInt(1, basketNo);
		
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}

		return result;
	}
	
	
	//구매내역조회
	public List<PurchaseHistory> findHistory(Connection connection, Member member) {
		List<PurchaseHistory> list = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String query = "SELECT G.CATE, G.GYM_NAME, P.T_PRICE, P.PAY_METHOD, P.P_DATE FROM P_MANA P JOIN VOUCHER V ON(P.VOUCHER_ID = V.VOUCHER_NO) JOIN GYM G ON(G.NO = P.GYM_ID) WHERE USER_NO=?";
		
		System.out.println(member);
		try {
			pstmt = connection.prepareStatement(query);
			pstmt.setInt(1, member.getNo());

			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				PurchaseHistory history = new PurchaseHistory();
				history.setCate(rs.getString("CATE"));
				history.setGymName(rs.getString("GYM_NAME"));
				history.setTPrice(rs.getInt("T_PRICE"));
				history.setPayMethod(rs.getString("PAY_METHOD"));
				history.setPDate(rs.getDate("P_DATE"));
				
				list.add(history);
				System.out.println("다오" + history);
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
