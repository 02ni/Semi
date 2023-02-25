package com.kh.mvc.qna.model.service;

import static com.kh.mvc.common.jdbc.JDBCTemplate.close;
import static com.kh.mvc.common.jdbc.JDBCTemplate.commit;
import static com.kh.mvc.common.jdbc.JDBCTemplate.rollback;
import static com.kh.mvc.common.jdbc.JDBCTemplate.getConnection;

import java.sql.Connection;
import java.util.List;

import com.kh.mvc.qna.model.dao.QnaDao;
import com.kh.mvc.qna.model.vo.QnaBoard;
import com.kh.mvc.qna.model.vo.QnaReply;
import com.kh.mvc.board.model.dao.BoardDao;
import com.kh.mvc.board.model.vo.Board;
import com.kh.mvc.common.jdbc.JDBCTemplate;
import com.kh.mvc.common.util.PageInfo;

public class QnaService {

	public int getBoardCount() {
		int count = 0;
		Connection connection = getConnection();
		
		count = new QnaDao().getBoardCount(connection);
		
		close(connection);
		
		return count;
	}

	public List<QnaBoard> getBoardList(PageInfo pageInfo) {
		List<QnaBoard> list = null;
		Connection connection = getConnection();
		
		list = new QnaDao().findAll(connection, pageInfo);
		
		close(connection);
		
		return list;
	}

	public QnaBoard getBoardByNo(int no, boolean hasRead) {
		QnaBoard qnaboard = null;
		Connection connection = getConnection();
		
		qnaboard = new QnaDao().findBoardByNo(connection, no);
		
		// 게시글 조회 수 증가 로직
		if(qnaboard != null && !hasRead) {
			int result = new QnaDao().updateReadCount(connection, qnaboard);
			
			if(result > 0) {
				commit(connection);
			} else {
				rollback(connection);
			}
		}
				
		close(connection);
		
		return qnaboard;
	}

	public int save(QnaBoard qnaboard) {
		int result = 0;
		Connection connection = getConnection();
		
		if(qnaboard.getNo() > 0) {
			// update 작업
			result = new QnaDao().updateBoard(connection, qnaboard);
		} else {
			// insert 작업
			result = new QnaDao().insertBoard(connection, qnaboard);
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
		
		result = new QnaDao().updateStatus(connection, no, "N");
		
		if(result > 0) {
			commit(connection);
		} else {
			rollback(connection);
		}
		
		close(connection);
		
		return result;
	}

	public int saveReply(QnaReply reply) {
		int result = 0;
		Connection connection = getConnection();
		
		result = new QnaDao().insertReply(connection, reply);
		
		if(result > 0) {
			commit(connection);
		} else {
			rollback(connection);
		}
		
		close(connection);
		
		return result;
	}

	public QnaBoard getBoardReplyByNo(int no, boolean hasRead) {
		QnaBoard qnaboard = null;
		Connection connection = getConnection();
		
		qnaboard = new QnaDao().findBoardByNo(connection, no);
		
		// 게시글 조회 수 증가 로직
		if(qnaboard != null && !hasRead) {
			int result = new QnaDao().updateReplyCount(connection, qnaboard);
			
			if(result > 0) {
				commit(connection);
			} else {
				rollback(connection);
			}
		}
				
		close(connection);
		
		return qnaboard;
	}

	public List<QnaBoard> getBoardList(PageInfo pageInfo, int no) {
		List<QnaBoard> list = null;
	      Connection connection = getConnection();
	      
	      list = new QnaDao().findAllByNo(connection, pageInfo, no);
	      
	      close(connection);
	      
	      return list;
		
	}

	public QnaBoard getQnaBoardReplyByNo(int no, boolean hasRead) {
		QnaBoard qnaboard = null;
		Connection connection = getConnection();
		
		qnaboard = new QnaDao().findQnaBoardByNo(connection, no);
		
		if(qnaboard != null && !hasRead) {
			int result = new QnaDao().updateQnaReplyCount(connection, qnaboard);
			
			if(result > 0) {
				commit(connection);
			} else {
				rollback(connection);
			}
		}
				
		close(connection);
		
		return qnaboard;
	}

}