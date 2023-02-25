package com.kh.mvc.admin.model.service;

import static com.kh.mvc.common.jdbc.JDBCTemplate.close;
import static com.kh.mvc.common.jdbc.JDBCTemplate.commit;
import static com.kh.mvc.common.jdbc.JDBCTemplate.getConnection;
import static com.kh.mvc.common.jdbc.JDBCTemplate.rollback;

import java.sql.Connection;
import java.util.List;

import com.kh.mvc.admin.model.dao.AdminDao;
import com.kh.mvc.board.model.dao.BoardDao;
import com.kh.mvc.board.model.vo.Board;
import com.kh.mvc.common.util.PageInfo;
import com.kh.mvc.gym.model.vo.Gym;
import com.kh.mvc.gym.model.vo.Voucher;
import com.kh.mvc.member.model.dao.MemberDao;
import com.kh.mvc.member.model.vo.Member;
import com.kh.mvc.notice.model.vo.Notice;
import com.kh.mvc.qna.model.vo.QnaBoard;
import com.kh.mvc.qna.model.vo.QnaReply;


public class AdminService {

	public int getBoardCount() {
		int count = 0;
		Connection connection = getConnection();

		count = new AdminDao().getBoardCount(connection);
		
		close(connection);
		
		return count;
	}

	public Member getMemberByNo(int no) {
		Member member = null;
				
		Connection connection = getConnection();
				
		member = new AdminDao().findMemberByNo(connection, no);
				
		close(connection);
				
		return member;
	}

	public int getMemberBoardCount(int no) {
		int count = 0;
		Connection connection = getConnection();

		count = new AdminDao().getMemberBoardCount(connection , no);
		
		close(connection);
		
		return count;
	}

	public List<Board> getBoardList(PageInfo pageInfo, int no) {
		List<Board> list = null;
		Connection connection = getConnection();
		
		list = new AdminDao().findAllByNo(connection, pageInfo, no);
		
		close(connection);
		
		return list;
	}

	public int getMemberCount() {
		int count = 0;
		Connection connection = getConnection();

		count = new AdminDao().getMemberCount(connection);
		
		close(connection);
		
		return count;
	}

	public List<Member> getMemberList(PageInfo pageInfo) {
		List<Member> list = null;
		Connection connection = getConnection();
		
		list = new AdminDao().getMember(connection, pageInfo);
		
		System.out.println(list);
		
		

		close(connection);

		return list;
	}

	public int getGymCount() {
		int count = 0;
		Connection connection = getConnection();

		count = new AdminDao().getGymCount(connection);
		
		close(connection);
		
		return count;
	}

	public List<Gym> getGymList(PageInfo pageInfo) {
		List<Gym> list = null;
		Connection connection = getConnection();
		
		list = new AdminDao().getGym(connection, pageInfo);
		
		close(connection);
		
		return list;
	}

	public Gym getGymByNo(int no) {
		Gym gym = null;
		
		
		Connection connection = getConnection();
		
		
		gym = new AdminDao().findGymByNo(connection, no);
		
		close(connection);
		
		return gym;	
	}

	public int delete(int no, String status) {
		int result = 0;
		Connection connection = getConnection();
		if(status.equals("Y")) {
			result = new AdminDao().updateMemberStatus(connection, no, "N");			
		} else {
			result = new AdminDao().updateMemberStatus(connection, no, "Y");	
		}
		
		if(result > 0) {
			commit(connection);
		} else {
			rollback(connection);
		}
		
		close(connection);
		
		return result;
	}

	public int save(Gym gym) {
		int result = 0;
		Connection connection = getConnection();
		
	
		result = new AdminDao().insertGym(connection, gym);
		
		return result;
		
	}

	public Gym getGymInfoByNo(int no) {
		Gym gym = null;
		
		Connection connection = getConnection();
				
		gym = new AdminDao().findGymByNo(connection, no);
				
		close(connection);
				
		return gym;
	}

	public int save1(Voucher voucher) {
		int result = 0;
		Connection connection = getConnection();
		
	
		result = new AdminDao().insertVoucher(connection, voucher);
		
	
		
		return result;
		
	}

	public int updateGymStatus(int no, String string) {
		int result = 0;
		Connection connection = getConnection();
		
		result = new AdminDao().updateGymStatus(connection, no, "Y");
		
		if(result > 0) {
			commit(connection);
		} else {
			rollback(connection);
		}
		
		close(connection);
		
	return result;
	}

	public int getQnaBoardCount() {
		int count = 0;
		Connection connection = getConnection();

		count = new AdminDao().getQnaBoardCount(connection);
		
		close(connection);
		
		return count;
	}

	public List<QnaBoard> getQnaBoardList(PageInfo pageInfo) {
		List<QnaBoard> list = null;
		Connection connection = getConnection();
		
		list = new AdminDao().findQnaAll(connection, pageInfo);
		
		close(connection);
		
		return list;
	}

	public int getNoticeBoardCount() {
		int count = 0;
		Connection connection = getConnection();

		count = new AdminDao().getNoticeBoardCount(connection);
		
		close(connection);
		
		return count;
	}

	public List<Notice> getNoticeList(PageInfo pageInfo) {
		List<Notice> list = null;
		Connection connection = getConnection();
		
		list = new AdminDao().findNoticeAll(connection, pageInfo);
		
		close(connection);
		
		return list;
	}

	public int getNotMemberCount() {
		int count = 0;
		Connection connection = getConnection();

		count = new AdminDao().getNotMemberCount(connection);
		
		close(connection);
		
		return count;
	}

	

	public List<Board> getBoardList(PageInfo pageInfo) {
		List<Board> list = null;
		Connection connection = getConnection();
		
		list = new AdminDao().findAll(connection, pageInfo);
		
		close(connection);
		
		return list;
	}

	public QnaBoard saveqnaReply(int no, boolean hasRead) {
		
		
		
		return null;
	}

	public int report(int no, String status) {
		int result = 0;
		Connection connection = getConnection();
		if(status.equals("Y")) {
			result = new AdminDao().reportBoardStatus(connection, no, "N");			
		} else {
			result = new AdminDao().reportBoardStatus(connection, no, "Y");	
		}
		
		if(result > 0) {
			commit(connection);
		} else {
			rollback(connection);
		}
		
		close(connection);
		
		return result;
	}

	public int saveqnaReply(QnaReply qnareply) {
		int result = 0;
		Connection connection = getConnection();
		
		result = new AdminDao().insertQnaReply(connection, qnareply);
		
		if(result > 0) {
			commit(connection);
		} else {
			rollback(connection);
		}
		
		close(connection);
		
		return result;
	}
}
