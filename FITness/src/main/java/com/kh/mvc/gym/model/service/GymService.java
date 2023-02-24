package com.kh.mvc.gym.model.service;

import static com.kh.mvc.common.jdbc.JDBCTemplate.close;
import static com.kh.mvc.common.jdbc.JDBCTemplate.commit;
import static com.kh.mvc.common.jdbc.JDBCTemplate.getConnection;
import static com.kh.mvc.common.jdbc.JDBCTemplate.rollback;

import java.sql.Connection;
import java.util.List;

import com.kh.mvc.common.util.PageInfo;
import com.kh.mvc.gym.model.dao.GymDao;
import com.kh.mvc.gym.model.vo.Gym;
import com.kh.mvc.gym.model.vo.Review;
import com.kh.mvc.member.model.vo.Member;

public class GymService {
	// 센터 총 개수
	public int getGymCount() {
		int count = 0;
		Connection connection = getConnection();
		
		count = new GymDao().getGymCount(connection);
		
		close(connection);
		
		
		return count;
	}
	// 센터 리스트(no, 이름, 주소) 
	public List<Gym> getGymList(PageInfo pageInfo) {
		List<Gym> list = null;
		Connection connection =  getConnection();
		
		list = new GymDao().findAll(connection, pageInfo);
		
		close(connection);
		return list;
	}

	
	// 센터 정보 가져오기(상세페이지용)
	public Gym getGymByNo(int no) {
		Gym gym = null;
		Connection connection =  getConnection();
		
		gym = new GymDao().findGymByNo(connection, no);
		
		close(connection);
		return gym;
	}
	
	// 리뷰 남기기
	public int saveReview(Review review) {
		int result = 0;
		Connection connection =  getConnection();
		
		result = new GymDao().insertReply(connection, review);
		
		if(result > 0) {
			commit(connection);
		}else {
			rollback(connection);
		}
		
		close(connection);
		
		return result;
	}
	
	// 리뷰 총 개수
	public int getReviewCount(int no) {
		int count = 0;
		Connection connection = getConnection();
		
		count = new GymDao().getReviewCount(connection, no);
		
		close(connection);
		
		
		return count;
	}
	
	// 센터좋아요 개수
	public int getFavoriteCount(int no) {
		int count = 0;
		Connection connection = getConnection();
		
		count = new GymDao().getFavoriteCount(connection, no);
		
		close(connection);
		
		return count;
	}
	
	// 좋아요 찾기
	 public List<Gym> getFavoriteGymList(Member member) {
	      List<Gym> list = null;
	      Connection connection = getConnection();
	      
	      list = new GymDao().findFavorite(connection, member);
	      
	      close(connection);
	      return list;
	   }
	 
	 // 사용자의 특정센터의 좋아요 여부 (좋아요수 카운트)
	public int getIsFavorite(int gymNo, int memberNo) {
		int count = 0;
		Connection connection = getConnection();
		
		count = new GymDao().getIsFavorite(connection, gymNo, memberNo);
		
		close(connection);
		
		return count;
	}
	// 좋아요 저장
	public int saveFavorite(int gymNo, int memberNo) {
		int result = 0;
		Connection connection =  getConnection();
		
		result = new GymDao().saveFavorite(connection, gymNo, memberNo);
		
		if(result > 0) {
			commit(connection);
		}else {
			rollback(connection);
		}
		
		close(connection);
		
		return result;
	}
	// 좋아요삭제
	public int deleteFavorite(int gymNo, int memberNo) {
		int result = 0;
		Connection connection =  getConnection();
		
		result = new GymDao().deleteFavorite(connection, gymNo, memberNo);
		
		if(result > 0) {
			commit(connection);
		}else {
			rollback(connection);
		}
		
		close(connection);
		
		return result;
		
	}
	// 리뷰수정시 원래 리뷰 불러오기
	public Review readReview(int no) {
		Review review = null;
		Connection connection = getConnection();
		
		review = new GymDao().readReview(connection, no);
		
		close(connection);
		
		return review;
	}
	// 리뷰 수정하기 
	public int updateReview(Review review) {
		int result = 0;
		Connection connection = getConnection();
		
		result = new GymDao().updateReview(connection, review);
		
		if(result > 0) {
			commit(connection);
		} else {
			rollback(connection);
		}
		
		close(connection);
		
		return result;
	}
	// 리뷰 삭제하기
	public int deleteReview(Review review) {
		int result = 0;
		Connection connection = getConnection();
		
		result = new GymDao().deleteReview(connection, review);
		
		if(result > 0) {
			commit(connection);
		} else {
			rollback(connection);
		}
		
		close(connection);
		
		return result;
	}

	public int saveCart(int voucher, int memberNo) {
		int result = 0;
		Connection connection =  getConnection();
		
		result = new GymDao().saveCart(connection, voucher, memberNo);
		
		if(result > 0) {
			commit(connection);
		}else {
			rollback(connection);
		}
		
		close(connection);
		
		return result;
	}
	public List<Gym> getSearchList(String[] cates) {
			List<Gym> list = null;
			Connection connection =  getConnection();
			
			list = new GymDao().findSearchList(connection, cates);
			
			close(connection);
			return list;
	}
	public List<Gym> getSearchList(String bigRegion, String region) {
			List<Gym> list = null;
			Connection connection =  getConnection();
			
			list = new GymDao().findSearchList(connection, bigRegion, region);
			
			close(connection);
			return list;
	}
	public List<Gym> getSearchList(String[] cates, String bigRegion, String region) {
			List<Gym> list = null;
			Connection connection =  getConnection();
			
			list = new GymDao().findSearchList(connection, cates, bigRegion, region);
			
			close(connection);
			return list;
	}
	// 1. (검색)지역만 검색시 전체 목록수
	public int getSearchCount(String bigRegion, String region) {
		int count = 0;
		Connection connection = getConnection();
		
		count = new GymDao().getSearchCount(connection, bigRegion, region);
		
		close(connection);
		
		
		return count;
	}
	// 2. (카테고리)만 검색시 전체목록 수 
	public int getSearchCount(String[] cates) {
		int count = 0;
		Connection connection = getConnection();
		
		count = new GymDao().getSearchCount(connection, cates);
		
		close(connection);
		
		
		return count;
	}
	public int getSearchCount(String[] cates, String bigRegion, String region) {
		int count = 0;
		Connection connection = getConnection();
		
		count = new GymDao().getSearchCount(connection, cates, bigRegion, region);
		
		close(connection);
		
		
		return count;
	}

}
