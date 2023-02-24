package com.kh.mvc.board.model.service;

import static com.kh.mvc.common.jdbc.JDBCTemplate.close;
import static com.kh.mvc.common.jdbc.JDBCTemplate.commit;
import static com.kh.mvc.common.jdbc.JDBCTemplate.rollback;
import static com.kh.mvc.common.jdbc.JDBCTemplate.getConnection;

import java.sql.Connection;
import java.util.List;

import com.kh.mvc.board.model.dao.BoardDao;
import com.kh.mvc.board.model.vo.Board;
import com.kh.mvc.board.model.vo.Reply;
import com.kh.mvc.common.jdbc.JDBCTemplate;
import com.kh.mvc.common.util.PageInfo;

public class BoardService {

	public int getBoardCount() {
		int count = 0;
		Connection connection = getConnection();
		
		count = new BoardDao().getBoardCount(connection);
		
		close(connection);
		
		return count;
	}

	public List<Board> getBoardList(PageInfo pageInfo) {
		List<Board> list = null;
		Connection connection = getConnection();
		
		list = new BoardDao().findAll(connection, pageInfo);
		
		close(connection);
		
		return list;
	}

	public Board getBoardByNo(int no, boolean hasRead) {
		Board board = null;
		Connection connection = getConnection();
		
		board = new BoardDao().findBoardByNo(connection, no);
		
		// 게시글 조회 수 증가 로직
		if(board != null && !hasRead) {
			int result = new BoardDao().updateReadCount(connection, board);
			
			if(result > 0) {
				commit(connection);
			} else {
				rollback(connection);
			}
		}
				
		close(connection);
		
		return board;
	}

	public int save(Board board) {
		int result = 0;
		Connection connection = getConnection();
		
		if(board.getNo() > 0) {
			// update 작업
			result = new BoardDao().updateBoard(connection, board);
		} else {
			// insert 작업
			result = new BoardDao().insertBoard(connection, board);
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
		
		result = new BoardDao().updateStatus(connection, no, "N");
		
		if(result > 0) {
			commit(connection);
		} else {
			rollback(connection);
		}
		
		close(connection);
		
		return result;
	}
	

	

	public Board getBoardReplyByNo(int no, boolean hasRead) {
		Board board = null;
		Connection connection = getConnection();
		
		board = new BoardDao().findBoardByNo(connection, no);
		
		if(board != null && !hasRead) {
			int result = new BoardDao().updateReplyCount(connection, board);
			
			if(result > 0) {
				commit(connection);
			} else {
				rollback(connection);
			}
		}
				
		close(connection);
		
		return board;
	}

	public Reply readReply(int no) {
		Reply reply = null;
		Connection connection = getConnection();
		
		reply = new BoardDao().readReplyBoard(connection, no);
		
		close(connection);
		
		return reply;
	}
	
	public int saveReply(Reply reply) {
		int result = 0;
		Connection connection = getConnection();
		
		result = new BoardDao().insertReply(connection, reply);
		
		if(result > 0) {
			commit(connection);
		} else {
			rollback(connection);
		}
		
		close(connection);
		
		return result;
	}

	public int updateReply(Reply reply) {
		int result = 0;
		Connection connection = getConnection();
		
		result = new BoardDao().updateReply(connection, reply);
		
		if(result > 0) {
			commit(connection);
		} else {
			rollback(connection);
		}
		
		close(connection);
		
		return result;
		
	}
	
	public int deleteReply(Reply reply) {
		int result = 0;
		Connection connection = getConnection();
		
		result = new BoardDao().deleteReply(connection, reply);
		
		if(result > 0) {
			commit(connection);
		} else {
			rollback(connection);
		}
		close(connection);
		
		return result;
	}

	
	
	public List<Board> search(PageInfo pageInfo, String searchField, String searchText) {
		List<Board> list = null;
		Connection connection = getConnection();
		
		list = new BoardDao().getSearch(connection, pageInfo, searchField, searchText);
		
		close(connection);
		
		return list;
		
	}

	public int getBoardsearchCount() {
		int seachCount = 0;
		Connection connection = getConnection();
		
		seachCount = new BoardDao().getBoardSearchCount(connection);
		
		close(connection);
		
		return seachCount;
	}


}