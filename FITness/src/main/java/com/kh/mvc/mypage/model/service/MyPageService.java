package com.kh.mvc.mypage.model.service;

import static com.kh.mvc.common.jdbc.JDBCTemplate.close;
import static com.kh.mvc.common.jdbc.JDBCTemplate.commit;
import static com.kh.mvc.common.jdbc.JDBCTemplate.getConnection;
import static com.kh.mvc.common.jdbc.JDBCTemplate.rollback;

import java.sql.Connection;
import java.util.List;
import com.kh.mvc.board.model.vo.Board;
import com.kh.mvc.common.util.PageInfo;
import com.kh.mvc.gym.model.vo.Gym;
import com.kh.mvc.gym.model.vo.Voucher;
import com.kh.mvc.member.model.vo.Member;
import com.kh.mvc.mypage.model.dao.MyPageDao;
import com.kh.mvc.mypage.model.vo.PurchaseHistory;

public class MyPageService {
	
	//나의 게시글 보기
	public List<Board> getMyWritingList(PageInfo pageInfo, Member member) {
		List<Board> list = null;
		Connection connection = getConnection();
		list = new MyPageDao().findMyWriting(connection, pageInfo, member);
		
		close(connection);
		
		return list;
	}
	
	//좋아요찾기
		public List<Gym> getFavoriteGymList(Member member) {
			List<Gym> list = null;
			Connection connection = getConnection();
			list = new MyPageDao().findFavorite(connection, member);
			
			close(connection);
			
			return list;
		}
		
		
		//장바구니찾기
		public List<Voucher> getBasketList(Member member) {
			List<Voucher> list = null;
			Connection connection = getConnection();
			list = new MyPageDao().findBasket(connection, member);
			
			close(connection);
			
			return list;
		}
		
		//장바구니삭제
		public int delete(int basketNo) {
			int result = 0;
			Connection connection = getConnection();
			result = new MyPageDao().updateMemberStatus(connection, basketNo);
			
			if(result > 0) {
				commit(connection);
			} else {
				rollback(connection);
			}
			
			close(connection);
			
			return result;
		}
		
		//구매내역 조회
		public List<PurchaseHistory> getPurchaseHistory(Member member) {
			List<PurchaseHistory> list = null;
			Connection connection = getConnection();
			list = new MyPageDao().findHistory(connection, member);
			
			close(connection);
			
			return list;
		}
}
