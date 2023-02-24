package com.kh.mvc.gym.model.dao;

import static com.kh.mvc.common.jdbc.JDBCTemplate.close;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.kh.mvc.common.util.PageInfo;
import com.kh.mvc.gym.model.vo.Gym;
import com.kh.mvc.gym.model.vo.Review;
import com.kh.mvc.gym.model.vo.Voucher;
import com.kh.mvc.member.model.vo.Member;

public class GymDao {

	// 센터 총 개수 (센터리스트페이지)
	public int getGymCount(Connection connection) {
		int count = 0;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String query = "SELECT COUNT(*) FROM GYM WHERE STATUS='Y'";
		
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
	
	// 센터 리스트(no, 이름, 주소) 가져오기 
	public List<Gym> findAll(Connection connection, PageInfo pageInfo ) {
		List<Gym> list = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String query = "SELECT RNUM, NO, GYM_NAME, ADDRESS, CATE, PRICE, STATUS "
				+ "FROM ( "
				+ "SELECT ROWNUM AS RNUM, NO, GYM_NAME, ADDRESS, CATE, PRICE, STATUS "
				+ "FROM ( "
				+ "SELECT  G.NO, G.GYM_NAME, G.ADDRESS, G.CATE, V.PRICE, G.STATUS "
				+ "FROM VOUCHER V JOIN GYM G ON(V.VOUCHER_NO = G.NO) "
				+ "WHERE G.STATUS = 'Y'  ORDER BY G.NO ASC "
				+ ") "
				+ ") "
				+ "WHERE RNUM BETWEEN ? and ?";
		
		 try {
			pstmt = connection.prepareStatement(query);
			pstmt.setInt(1, pageInfo.getStartList());
			pstmt.setInt(2, pageInfo.getEndList());
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				Gym gym= new Gym();
				
				gym.setNo(rs.getInt("NO"));
				gym.setGymName(rs.getString("GYM_NAME"));
				gym.setAddress(rs.getString("ADDRESS"));
				gym.setCategory(rs.getString("CATE"));
				// 이용권 정보 불러오기
				gym.setPrice(rs.getInt("PRICE"));
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

	

	// 센터정보가져오기 - 한개만 (상세페이지용)
	public Gym findGymByNo(Connection connection, int no) {
		Gym gym = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String query = "SELECT * FROM GYM WHERE STATUS = 'Y' AND NO=?";
		
		try {
			pstmt = connection.prepareStatement(query);
			
			pstmt.setInt(1, no);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
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

				// 이용권 정보 불러오기
				gym.setVouchers(this.getVouchersByNo(connection, no));
				// 리뷰 정보 
				gym.setReviews(this.getReviewsByNo(connection, no));
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		
		return gym;
	}

	// 이용권 정보 불러오기 (모든정보, 상세페이지)
	// no는 Gym의 no에 해당
	private List<Voucher> getVouchersByNo(Connection connection, int no) {
		List<Voucher> vouchers = new ArrayList<>();
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String query = "SELECT VOUCHER_NO, "
							 + "GYM_NO, "
							 + "CATE, "
							 + "PRICE "
							 + "FROM VOUCHER "
							 + "WHERE GYM_NO=?";
		
		try {
			pstmt = connection.prepareStatement(query);
			
			pstmt.setInt(1, no);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				Voucher voucher = new Voucher();
				voucher.setVoucherNo(rs.getInt("VOUCHER_NO"));
				voucher.setGymNo(rs.getInt("GYM_NO"));
				voucher.setCate(rs.getString("CATE"));
				voucher.setPrice(rs.getInt("PRICE"));
				vouchers.add(voucher);
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		
		return vouchers;
	}
	
	// 리뷰 정보 불러오기 (상세페이지)
		private List<Review> getReviewsByNo(Connection connection, int no) {
			List<Review> reviews = new ArrayList<>();
			
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			String query = "SELECT R.NO, "
								+ "R.CONTENT, "
								+ "R.CREATE_DATE, "
								+ "R.MODIFY_DATE, "
								+ "R.GRADE, "
								+ "R.GYM_NO, "
								+ "M.ID "
						+ "FROM REVIEW R "
						+ "JOIN MEMBER M ON(R.WRITER_NO = M.NO) "
						+ "WHERE R.STATUS='Y' AND R.GYM_NO=? "
						+ "ORDER BY R.NO DESC";
			
			try {
				pstmt = connection.prepareStatement(query);
				
				pstmt.setInt(1, no);
				
				rs = pstmt.executeQuery();
				
				while(rs.next()) {
					Review  review = new Review();
					
					review.setNo(rs.getInt("NO"));
					review.setContent(rs.getString("CONTENT"));
					review.setCreateDate(rs.getDate("CREATE_DATE"));
					review.setModifyDate(rs.getDate("MODIFY_DATE"));
					review.setGrade(rs.getInt("GRADE"));
					review.setGymNo(rs.getInt("GYM_NO"));
					review.setWriterId(rs.getString("ID"));
					
					reviews.add(review);
					
				}
				
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				close(rs);
				close(pstmt);
			}
			
			return reviews;
		}
		// 리뷰쓰기 (상세페이지)
		public int insertReply(Connection connection, Review review) {
			int result = 0;
			PreparedStatement pstmt = null;
			String query = "INSERT INTO REVIEW (NO, CONTENT, CREATE_DATE, MODIFY_DATE, STATUS, GRADE, GYM_NO, WRITER_NO) "
					+ " VALUES(SEQ_REVIEW.NEXTVAL, ?, DEFAULT, DEFAULT, DEFAULT, ?, ?, ?)";
			
				
				try {
					pstmt = connection.prepareStatement(query);
					
					pstmt.setString(1, review.getContent());
					pstmt.setInt(2, review.getGrade());
					pstmt.setInt(3, review.getGymNo());
					pstmt.setInt(4, review.getWriterNo());
					
					result = pstmt.executeUpdate();
					
				} catch (SQLException e) {
					e.printStackTrace();
				} finally {
					close(pstmt);
				}
			return result;
		}

		// 리뷰수 불러오기 (상세페이지)
		public int getReviewCount(Connection connection, int no) {
			int count = 0;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			String query = "SELECT COUNT(*) FROM REVIEW WHERE STATUS='Y' AND GYM_NO =?";
			
			try {
				pstmt = connection.prepareStatement(query);
				
				pstmt.setInt(1, no);
				
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
		
		
		// 좋아요 개수 불러오기 (상세페이지)
		public int getFavoriteCount(Connection connection, int no) {
			int count = 0;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			String query = "SELECT COUNT(*) FROM LIKELIST WHERE GYM_NO =?";
			
			try {
				pstmt = connection.prepareStatement(query);
				
				pstmt.setInt(1, no);
				
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
		
		
		
		
		
		// 좋아요 찾기 (마이페이지)
		   public List<Gym> findFavorite(Connection connection, Member member) {
		      List<Gym> list = new ArrayList<>();
		      PreparedStatement pstmt = null;
		      ResultSet rs = null;
		      String query = "SELECT G.NO, "
		                  + "G.GYM_NAME, "
		                  + "G.ADDRESS, "
		                  + "G.CATE "
		                  + "FROM GYM G "
		                  + "JOIN LIKELIST L ON(G.NO = L.GYM_NO) "
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
		            
	            //이용권 정보 불러오기 
	            // gym.setVouchers(this.getVouchersByNo(connection, gym.getNo()));
		             
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
		   // 사용자의 특정센터의 좋아요 여부 (좋아요수카운트)
		public int getIsFavorite(Connection connection, int gymNo, int memberNo) {
			int count = 0;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			String query = "SELECT COUNT(*) FROM LIKELIST WHERE GYM_NO=? AND MEMBER_NO=?";
			
			try {
				pstmt = connection.prepareStatement(query);
				
				pstmt.setInt(1, gymNo);
				pstmt.setInt(2, memberNo);
				
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

		public int saveFavorite(Connection connection, int gymNo, int memberNo) {
			int result = 0;
			PreparedStatement pstmt = null;
			String query = "INSERT INTO LIKELIST (NO, GYM_NO, MEMBER_NO) "
					+ "VALUES(SEQ_LIKELIST.NEXTVAL, ?, ?)";
			
				
				try {
					pstmt = connection.prepareStatement(query);
					
					pstmt.setInt(1, gymNo);
					pstmt.setInt(2, memberNo);
					
					result = pstmt.executeUpdate();
					
				} catch (SQLException e) {
					e.printStackTrace();
				} finally {
					close(pstmt);
				}
			return result;
		}
		// 좋아요해제
		public int deleteFavorite(Connection connection, int gymNo, int memberNo) {
			int result = 0;
			PreparedStatement pstmt = null;
			String query = "DELETE FROM LIKELIST "
					+ "WHERE GYM_NO=? AND MEMBER_NO=?";
				try {
					pstmt = connection.prepareStatement(query);
					
					pstmt.setInt(1, gymNo);
					pstmt.setInt(2, memberNo);
					
					result = pstmt.executeUpdate();
					
				} catch (SQLException e) {
					e.printStackTrace();
				} finally {
					close(pstmt);
				}
			return result;
		}
		//리뷰수정창에 리뷰불러오기
		public Review readReview(Connection connection, int no) {
			Review review = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			
			String query = "SELECT NO, CONTENT, GRADE, GYM_NO "
					+ "FROM REVIEW "
					+ "WHERE NO=?";
			
			try {
				pstmt = connection.prepareStatement(query);
				pstmt.setInt(1, no);
				rs = pstmt.executeQuery();
				
				if(rs.next()) {
					review = new Review();
					
					review.setNo(rs.getInt("NO"));
					review.setContent(rs.getString("CONTENT"));
					review.setGrade(rs.getInt("GRADE"));
					review.setGymNo(rs.getInt("GYM_NO"));
				}
				
				
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				close(rs);
				close(pstmt);
			}
			return review;
		}
		//리뷰수정하기
		public int updateReview(Connection connection, Review review) {
			int result = 0; 
			PreparedStatement pstmt = null;
			String query = "UPDATE REVIEW SET CONTENT=?, MODIFY_DATE=SYSDATE, GRADE=? WHERE NO =?";
			
			try {
				pstmt = connection.prepareStatement(query);
				
				pstmt.setString(1, review.getContent());
				pstmt.setInt(2, review.getGrade());
				pstmt.setInt(3, review.getNo());
				
				result = pstmt.executeUpdate();
				
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				close(pstmt);
			}
			return result;
			
		}

		public int deleteReview(Connection connection, Review review) {
			int result = 0; 
			PreparedStatement pstmt = null;
			String query = "UPDATE REVIEW SET STATUS='N' WHERE NO =?";
			
			try {
				pstmt = connection.prepareStatement(query);
				
				pstmt.setInt(1, review.getNo());
				
				result = pstmt.executeUpdate();
				
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				close(pstmt);
			}
			return result;
		}

		public int saveCart(Connection connection, int voucher, int memberNo) {
			int result = 0;
			PreparedStatement pstmt = null;
			String query = "INSERT INTO BASKET (NO, VOUCHER_NO, MEMBER_NO)VALUES(SEQ_BASKET.NEXTVAL, ?, ?)";
			
				
				try {
					pstmt = connection.prepareStatement(query);
					
					pstmt.setInt(1, voucher);
					pstmt.setInt(2, memberNo);
					
					result = pstmt.executeUpdate();
					
				} catch (SQLException e) {
					e.printStackTrace();
				} finally {
					close(pstmt);
				}
			return result;
		}
		// 1. 카테고리만 검색하기 (지역: 전체)
		public List<Gym> findSearchList(Connection connection, String[] cates) {
			List<Gym> list = new ArrayList<>();
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			String query = null;
			String query1 = "SELECT G.NO, G.GYM_NAME, G.ADDRESS, G.CATE, V.PRICE "
					+ "FROM VOUCHER V "
					+ "JOIN GYM G ON(V.VOUCHER_NO = G.NO) "
					+ "WHERE G.STATUS = 'Y' AND (G.CATE LIKE ?";
			String query2 = "";
			String query3 = ")";
			
			for (int i = 1; i <= cates.length-2; i++) {
				query2 += " OR G.CATE LIKE ?";
			}
			
			query = query1 + query2 + query3;
			
			try {
				pstmt = connection.prepareStatement(query);
				
				
				for (int i = 1; i <= cates.length-1; i++) {
					pstmt.setString(i, "%"+cates[i]+"%"); 
				}
				
				rs = pstmt.executeQuery();
				
				while(rs.next()) {
					Gym gym= new Gym();
					
					gym.setNo(rs.getInt("NO"));
					gym.setGymName(rs.getString("GYM_NAME"));
					gym.setAddress(rs.getString("ADDRESS"));
					gym.setCategory(rs.getString("CATE"));
					// 이용권 정보 불러오기
					gym.setPrice(rs.getInt("PRICE"));
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
		// 2. 지역만 검색하기 (카테고리: 전체)
		public List<Gym> findSearchList(Connection connection, String bigRegion, String region) {
			List<Gym> list = new ArrayList<>();
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			String query = "SELECT G.NO, G.GYM_NAME, G.ADDRESS, G.CATE, V.PRICE "
					+ "FROM VOUCHER V "
					+ "JOIN GYM G ON(V.VOUCHER_NO = G.NO) "
					+ "WHERE G.STATUS = 'Y' AND (G.ADDRESS LIKE ? AND G.ADDRESS LIKE ?)";
			
			try {
				pstmt = connection.prepareStatement(query);
				
			
				pstmt.setString(1, bigRegion+"%"); 
				pstmt.setString(2, "%"+region+"%"); 
				
				rs = pstmt.executeQuery();
				
				while(rs.next()) {
					Gym gym= new Gym();
					
					gym.setNo(rs.getInt("NO"));
					gym.setGymName(rs.getString("GYM_NAME"));
					gym.setAddress(rs.getString("ADDRESS"));
					gym.setCategory(rs.getString("CATE"));
					// 이용권 정보 불러오기
					gym.setPrice(rs.getInt("PRICE"));
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

		// 3. 지역, 카테고리 검색하기
		public List<Gym> findSearchList(Connection connection, String[] cates, String bigRegion, String region) {
			List<Gym> list = new ArrayList<>();
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			String query = null;
			String query1 = "SELECT NO, GYM_NAME, ADDRESS, CATE, PRICE "
					+ "FROM(SELECT G.NO, G.GYM_NAME, G.ADDRESS, G.CATE,  G.STATUS, V.PRICE "
					+ "FROM VOUCHER V "
					+ "JOIN GYM G ON(V.VOUCHER_NO = G.NO) "
					+ "WHERE G.STATUS = 'Y' AND (G.ADDRESS LIKE ? AND G.ADDRESS LIKE ?)) "
					+ "WHERE STATUS = 'Y' AND (CATE LIKE ?";
			String query2 = "";
			String query3 = ")";
			
			for (int i = 1; i <= cates.length-2; i++) {
				query2 += " OR CATE LIKE ?";
			}
			
			query = query1 + query2 + query3;
			
			try {
				pstmt = connection.prepareStatement(query);
				
				
				pstmt.setString(1, bigRegion+"%"); 
				pstmt.setString(2, "%"+region+"%");
				for (int i = 1; i <= cates.length-1; i++) {
					pstmt.setString(i+2, "%"+cates[i]+"%"); 
				}
				
				rs = pstmt.executeQuery();
				
				while(rs.next()) {
					Gym gym= new Gym();
					
					gym.setNo(rs.getInt("NO"));
					gym.setGymName(rs.getString("GYM_NAME"));
					gym.setAddress(rs.getString("ADDRESS"));
					gym.setCategory(rs.getString("CATE"));
					// 이용권 정보 불러오기
					gym.setPrice(rs.getInt("PRICE"));
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
		
		// 2-2. 지역만 검색한 결과 수 
		public int getSearchCount(Connection connection,String bigRegion, String region) {
			int count = 0;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			String query = "SELECT COUNT(*) "
					+ "FROM VOUCHER V "
					+ "JOIN GYM G ON(V.VOUCHER_NO = G.NO) "
					+ "WHERE G.STATUS = 'Y' AND (G.ADDRESS LIKE ? AND G.ADDRESS LIKE ?)";
			
			try {
				
				pstmt = connection.prepareStatement(query);
				
				pstmt.setString(1, bigRegion+"%"); 
				pstmt.setString(2, "%"+region+"%"); 
				
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
		
//		1-1. 카테고리만 검색한 결과수
		public int getSearchCount(Connection connection, String[] cates) {
			int count = 0;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			String query = null;
			String query1 = "SELECT COUNT(*) "
					+ "FROM VOUCHER V "
					+ "JOIN GYM G ON(V.VOUCHER_NO = G.NO) "
					+ "WHERE G.STATUS = 'Y' AND (G.CATE LIKE ?";
			String query2 = "";
			String query3 = ")";
			
			for (int i = 1; i <= cates.length-2; i++) {
				query2 += " OR G.CATE LIKE ?";
			}
			
			query = query1 + query2 + query3;
			
			try {
				pstmt = connection.prepareStatement(query);
				
				
				for (int i = 1; i <= cates.length-1; i++) {
					pstmt.setString(i, "%"+cates[i]+"%"); 
				}
				
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

		// 지역 카테고리 모두 검색
		public int getSearchCount(Connection connection, String[] cates, String bigRegion, String region) {
			int count = 0;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			String query = null;
			String query1 = "SELECT COUNT(*) "
					+ "FROM(SELECT G.NO, G.GYM_NAME, G.ADDRESS, G.CATE,  G.STATUS, V.PRICE "
					+ "FROM VOUCHER V "
					+ "JOIN GYM G ON(V.VOUCHER_NO = G.NO) "
					+ "WHERE G.STATUS = 'Y' AND (G.ADDRESS LIKE ? AND G.ADDRESS LIKE ?)) "
					+ "WHERE STATUS = 'Y' AND (CATE LIKE ?";
			String query2 = "";
			String query3 = ")";
			
			for (int i = 1; i <= cates.length-2; i++) {
				query2 += " OR CATE LIKE ?";
			}
			
			query = query1 + query2 + query3;
			
			try {
				pstmt = connection.prepareStatement(query);
				
				
				pstmt.setString(1, bigRegion+"%"); 
				pstmt.setString(2, "%"+region+"%");
				for (int i = 1; i <= cates.length-1; i++) {
					pstmt.setString(i+2, "%"+cates[i]+"%"); 
				}
				
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


		

}
