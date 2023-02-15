package com.kh.mvc.member.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kh.mvc.member.model.service.MemberService;
import com.kh.mvc.member.model.vo.Member;

@WebServlet(name = "update", urlPatterns = { "/member/update" })
public class MemberUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public MemberUpdateServlet() {
    }
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Member member = null;
		int result = 0;
		
		// 1. 로그인 된 사용자인지 체크
		HttpSession session = request.getSession(false);
		Member loginMember = (session == null) ? null : (Member)session.getAttribute("loginMember"); 
		
		if(loginMember != null) {
		//2. 사용자가 수정한 내용을 가지고 Member 객체를 생성
			member = new Member();
			
			// 회원 정보를 확인하는 member (getNo 식별자 PK 로)
			member.setNo(loginMember.getNo());
			member.setName(request.getParameter("userName"));
			member.setPhone(request.getParameter("phone"));
			member.setEmail(request.getParameter("email"));
			member.setAddress(request.getParameter("address"));
			
			String hobby = request.getParameter("hobby") != null ?
					String.join(", ", request.getParameterValues("hobby")) : null;
			
			member.setHobby(hobby);
			
		// 3. 회원 정보 수정
			// 방금 만든 member로 매개값 줘야 한다.
			result = new MemberService().save(member);
			
			if(result > 0 ) {
				// 정보 수정 성공
				// 세션을 갱신한다.
				// 별도의 아이디 값만 넘기는 메소드를 만들어 보자
				
				session.setAttribute("loginMember", new MemberService().findMemberById(loginMember.getId()));
				
				request.setAttribute("msg", "회원 정보 수정 완료 ");
				request.setAttribute("location", "/member/myPage");
			} else {
				// 정보 수정 실패
				request.setAttribute("msg", "회원 정보 수정 실패 ");
				request.setAttribute("location", "/member/myPage");
			}
		} else {
			request.setAttribute("msg", "로그인 후 수정해 주세요.");
			request.setAttribute("location", "/");
			
		}
		// 로그인 유무에 따라, 해당 부분이 수행 될 수 있게맨 마지막에 배치
		request.getRequestDispatcher("/views/common/msg.jsp").forward(request, response);
	}

}
