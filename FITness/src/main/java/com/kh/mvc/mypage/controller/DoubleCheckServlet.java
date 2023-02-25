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

@WebServlet(name = "doubleCheck", urlPatterns = "/mypage/doubleCheck")
public class DoubleCheckServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public DoubleCheckServlet() {
    }
    
    @Override
   	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	HttpSession session = request.getSession(false);
	    Member loginMember = (session == null) ? null : (Member) session.getAttribute("loginMember");
	    
	    if(loginMember != null) {
			request.getRequestDispatcher("/views/mypage/doubleCheck.jsp").forward(request, response);
	    } else {
			request.setAttribute("msg", "로그인 후 시도해 주세요.");
			request.setAttribute("location", "/");
			request.getRequestDispatcher("/views/common/msg.jsp").forward(request, response);
		}
	}
    	
    @Override
   	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	String userId = request.getParameter("userId");
		String userPwd = request.getParameter("userPwd");
		
   		Member checkMember = new MemberService().login(userId, userPwd);
   		
   		System.out.println(checkMember);
   		
   		if(checkMember != null) {
			
			request.setAttribute("msg", "마이페이지 인증 성공");
   			request.setAttribute("location", "/views/mypage/myPage.jsp");
   		} else {
 
   			request.setAttribute("msg", "아이디나 비밀번호가 일치하지 않습니다.");
   			request.setAttribute("location", "/views/mypage/doubleCheck.jsp");
   			
   		}
   		request.getRequestDispatcher("/views/common/msg.jsp").forward(request, response);

    }
}   