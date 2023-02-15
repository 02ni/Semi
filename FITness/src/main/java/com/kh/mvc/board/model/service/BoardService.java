package com.kh.mvc.board.model.service;

// ctrl shift o
import static com.kh.mvc.common.jdbc.JDBCTemplate.close;
import static com.kh.mvc.common.jdbc.JDBCTemplate.commit;
import static com.kh.mvc.common.jdbc.JDBCTemplate.rollback;
import static com.kh.mvc.common.jdbc.JDBCTemplate.getConnection;

import java.sql.Connection;
import java.util.List;

import com.kh.mvc.board.model.dao.BoardDao;
import com.kh.mvc.board.model.vo.Board;
import com.kh.mvc.common.jdbc.JDBCTemplate;
import com.kh.mvc.common.util.PageInfo;

public class BoardService {

	// 데이터를 접근해서 가져올 오브젝트 만들어
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

	
	public Board getBoardByNo(int no) {
		// 1. board가 리턴될 수 있게 변수 선언
		Board board = null;
		
		// 2. 데이터를 조회하는데 getConnection()으로 얻어온다.
		Connection connection = getConnection();
		
		// 3. 커넥션하고, 매개값으로 받아온 no를 보낸다. -> 메소드 없는거 생성
		board = new BoardDao().findBoardByNo(connection, no);
		
		close(connection);
		
		return board;
	}

	
	public int save(Board board) {
		// 보드 게시글을 등록하는 서비스 로직임
		int result = 0;
		Connection connection = getConnection();
		
		if (board.getNo() > 0) {
			// update 작업
			result = new BoardDao().updateBoard(connection, board);
		} else {
			// insert 작업
			//JDBC로 행의 값을 담아주자 (맨 위 임포트에서도 값을 넣기)
			result = new BoardDao().insertBoard(connection, board);
		}
		
		if (result > 0) {
			commit(connection);
		}else {
			rollback(connection);
		}
		
		close(connection);
		
		return result;
	}

}
