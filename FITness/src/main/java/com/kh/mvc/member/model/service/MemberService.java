package com.kh.mvc.member.model.service;

import static com.kh.mvc.common.jdbc.JDBCTemplate.close;
import static com.kh.mvc.common.jdbc.JDBCTemplate.commit;
import static com.kh.mvc.common.jdbc.JDBCTemplate.getConnection;
import static com.kh.mvc.common.jdbc.JDBCTemplate.rollback;

import java.sql.Connection;
import java.sql.PreparedStatement;

import com.kh.mvc.member.model.dao.MemberDao;
import com.kh.mvc.member.model.vo.Member;

public class MemberService {

	// save에 존재하면 납두고, 새로 저장하면 insert 하는 형태로
	public int save(Member member) {
		int result = 0;
		Connection connection = getConnection();
		
		// 멤버의 no가 0보다 크면, update (update 서블릿 참고)
		if(member.getNo() > 0) {
			// update 작업
			result = new MemberDao().updateMember(connection, member);
		} else {
			// insert 작업
			result = new MemberDao().insertMember(connection, member);
		}
		
		
		//서비스에서 커넥션 만들고 커밋 or 롤백 (커넥션 객체로 커밋 롤백 제어)
		if(result > 0) {
			commit(connection);
		} else {
			rollback(connection);
		}
		
		close(connection);
		
		// 정수 값 리턴한다.
		return result;
	}

//	public boolean isDuplicateId(String userId) {
//		Connection connection = getConnection();
//		
//		// 하나의 행을 멤버 오브젝트로 만들어서 반환해 준다.
//		Member member = new MemberDao().findMemberById(connection, userId);
//		
//		// 리턴하기 전에 클로즈
//		close(connection);
//		
//		// 멤버가 null이 아니면 트루!
//		return member !=null;
//	}
	
//	public boolean isDuplicateId(String userId) {
//	
//		return this.findMemberById(userId) != null;
//	}
	

	// id 값 받아서 dao에서 조회한 다음에 리턴하도록 만든다.
//	public Member findMemberById(String userId) {
//		Connection connection = getConnection();
//		
//		Member member = new MemberDao().findMemberById(connection, userId);
//		
//		close(connection);
//		
//		return member;
//	}

	// UpdatePasswordServlet()에서 만들었다.
	public int updatePassword(int no, String userPwd) {
		int result = 0;
		
		// 겟 커넥션 메소드(DB를)로 커넥션을 가져온다.
		Connection connection = getConnection();
		result = new MemberDao().updateMemberPassword(connection, no, userPwd);
		
		if(result > 0) {
			commit(connection);
		} else {
			rollback(connection);
		}
		
		close(connection);
		
		return result;
	}

	// DeleteServlet()에서 만들었다.
	public int delete(int no) {
		int result = 0;
		Connection connection = getConnection();

		result = new MemberDao().updateMemberStatus(connection, no, "N");
		
		if(result > 0) {
			commit(connection);
		} else {
			rollback(connection);
		}
		
		close(connection);
		
		return result;
	}

	
	
	// 새로짠 로직 
	public Member login(String userId, String userPwd) {
		Connection connection = getConnection();
		Member member = new MemberDao().findById(connection, userId);
	
		if(member == null || !member.getPassword().equals(userPwd)) {
			return null;
		} 
		
		return member;
	}

}
