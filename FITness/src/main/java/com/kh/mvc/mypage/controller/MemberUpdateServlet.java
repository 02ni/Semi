package com.kh.mvc.mypage.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kh.mvc.member.model.service.MemberService;
import com.kh.mvc.member.model.vo.Member;


@WebServlet(name = "memberUpdate", urlPatterns = "/member/update" )
public class MemberUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public MemberUpdateServlet() {
    }
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		Member loginMember = (session == null) ? null : (Member)session.getAttribute("loginMember"); 
		
		Member member = null;
		int result = 0;
		
		if(loginMember != null) {
			member = new Member();
			member.setNo(loginMember.getNo());
			member.setPassword(request.getParameter("userPwd"));
			member.setName(request.getParameter("userName"));
			member.setPhone(request.getParameter("phone"));
			member.setEmail(request.getParameter("email"));
			member.setAddress(request.getParameter("address"));
			
			result = new MemberService().updatemember(member);
			
			if(result > 0 ) {
				session.setAttribute("loginMember", new MemberService().findMemberById(loginMember.getId()));
				request.setAttribute("msg", "회원 정보 수정 완료 ");
	   			request.setAttribute("location", "/views/mypage/myPage.jsp");
			} else {
				request.setAttribute("msg", "회원 정보 수정 실패 ");
				request.setAttribute("location", "/views/mypage/myPage.jsp");
			}
		} else {
			request.setAttribute("msg", "로그인 후 시도해 주세요.");
			request.setAttribute("location", "/");
		}
		request.getRequestDispatcher("/views/common/msg.jsp").forward(request, response);
	}
}
