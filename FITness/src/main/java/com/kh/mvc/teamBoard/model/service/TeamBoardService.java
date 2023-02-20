package com.kh.mvc.teamBoard.model.service;

import static com.kh.mvc.common.jdbc.JDBCTemplate.close;
import static com.kh.mvc.common.jdbc.JDBCTemplate.commit;
import static com.kh.mvc.common.jdbc.JDBCTemplate.rollback;
import static com.kh.mvc.common.jdbc.JDBCTemplate.getConnection;

import java.sql.Connection;
import java.util.List;

import com.kh.mvc.board.model.vo.Reply;
import com.kh.mvc.teamBoard.model.dao.TeamBoardDao;
import com.kh.mvc.teamBoard.model.vo.TeamBoard;
import com.kh.mvc.common.jdbc.JDBCTemplate;
import com.kh.mvc.common.util.PageInfo;

public class TeamBoardService {

	public int getBoardCount() {
		int count = 0;
		Connection connection = getConnection();
		
		count = new TeamBoardDao().getBoardCount(connection);
		
		close(connection);
		
		return count;
	}

	public List<TeamBoard> getBoardList(PageInfo pageInfo) {
		List<TeamBoard> list = null;
		Connection connection = getConnection();
		
		list = new TeamBoardDao().findAll(connection, pageInfo);
		
		close(connection);
		
		return list;
	}

	public TeamBoard getBoardByNo(int no, boolean hasRead) {
		TeamBoard board = null;
		Connection connection = getConnection();
		
		board = new TeamBoardDao().findBoardByNo(connection, no);
		
		// 게시글 조회 수 증가 로직
		if(board != null && !hasRead) {
			int result = new TeamBoardDao().updateReadCount(connection, board);
			
			if(result > 0) {
				commit(connection);
			} else {
				rollback(connection);
			}
		}
				
		close(connection);
		
		return board;
	}

	public int save(TeamBoard board) {
		int result = 0;
		Connection connection = getConnection();
		
		if(board.getNo() > 0) {
			// update 작업
			result = new TeamBoardDao().updateBoard(connection, board);
		} else {
			// insert 작업
			result = new TeamBoardDao().insertBoard(connection, board);
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
		
		result = new TeamBoardDao().updateStatus(connection, no, "N");
		
		if(result > 0) {
			commit(connection);
		} else {
			rollback(connection);
		}
		
		close(connection);
		
		return result;
	}

	public int saveReply(Reply reply) {
		int result = 0;
		Connection connection = getConnection();
		
		result = new TeamBoardDao().insertReply(connection, reply);
		
		if(result > 0) {
			commit(connection);
		} else {
			rollback(connection);
		}
		
		close(connection);
		
		return result;
	}
}