package com.kh.mvc.notice.model.service;

import static com.kh.mvc.common.jdbc.JDBCTemplate.close;
import static com.kh.mvc.common.jdbc.JDBCTemplate.commit;
import static com.kh.mvc.common.jdbc.JDBCTemplate.rollback;
import static com.kh.mvc.common.jdbc.JDBCTemplate.getConnection;

import java.sql.Connection;
import java.util.List;

import com.kh.mvc.common.jdbc.JDBCTemplate;
import com.kh.mvc.common.util.PageInfo;
import com.kh.mvc.notice.model.dao.NoticeDao;
import com.kh.mvc.notice.model.vo.Notice;

public class NoticeService {

	public int getBoardCount() {
		int count = 0;
		Connection connection = getConnection();
		
		count = new NoticeDao().getBoardCount(connection);
		
		close(connection);
		
		return count;
	}

	public List<Notice> getBoardList(PageInfo pageInfo, int fix) {
		List<Notice> list = null;
		Connection connection = getConnection();
		
		list = new NoticeDao().findAll(connection, pageInfo, fix);
		
		close(connection);
		
		return list;
	}

	public Notice getBoardByNo(int no, boolean hasRead) {
		Notice NoticeBoard = null;
		Connection connection = getConnection();
		
		NoticeBoard = new NoticeDao().findBoardByNo(connection, no);
		
		// 게시글 조회 수 증가 로직
		if(NoticeBoard != null && !hasRead) {
			int result = new NoticeDao().updateReadCount(connection, NoticeBoard);
			
			if(result > 0) {
				commit(connection);
			} else {
				rollback(connection);
			}
		}
				
		close(connection);
		
		return NoticeBoard;
	}

	public int save(Notice NoticeBoard) {
		int result = 0;
		Connection connection = getConnection();
		
		if(NoticeBoard.getNo() > 0) {
			// update 작업
			result = new NoticeDao().updateBoard(connection, NoticeBoard);
		} else {
			// insert 작업
			result = new NoticeDao().insertBoard(connection, NoticeBoard);
		}		
		
		if(result > 0) {
			commit(connection);
		} else {
			rollback(connection);
		}
		
		close(connection);
		
		return result;
	}

	public int delete(int no) {
		int result = 0;
		Connection connection = getConnection();
		
		result = new NoticeDao().updateStatus(connection, no, "N");
		
		if(result > 0) {
			commit(connection);
		} else {
			rollback(connection);
		}
		
		close(connection);
		
		return result;
	}

	public Notice getBoardReplyByNo(int no, boolean hasRead) {
		Notice NoticeBoard = null;
		Connection connection = getConnection();
		
		NoticeBoard = new NoticeDao().findBoardByNo(connection, no);
		
		// 게시글 조회 수 증가 로직
		if(NoticeBoard != null && !hasRead) {
			int result = new NoticeDao().updateReplyCount(connection, NoticeBoard);
			
			if(result > 0) {
				commit(connection);
			} else {
				rollback(connection);
			}
		}
				
		close(connection);
		
		return NoticeBoard;
	}
	


}