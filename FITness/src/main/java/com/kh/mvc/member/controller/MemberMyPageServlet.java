package com.kh.mvc.member.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kh.mvc.member.model.vo.Member;

@WebServlet("/member/myPage")
public class MemberMyPageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public MemberMyPageServlet() {
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 로그인 정보는 세션 영역에 담아놔야 한다!!
		HttpSession session = request.getSession(false);
		
		// 로그인 멤버 속성이 있으면 가져온다.
		// 오브젝트로 리턴하는데, 멤버로 받으려고 하기 때문에 멤버로 형변환해야 한다! 
//		Member loginMember = session == null ? null : session.getAttribute("loginMember"); // 형변환 전
		Member loginMember = (session == null) ? null : (Member)session.getAttribute("loginMember"); 
//		Member loginMember = (Member)session.getAttribute("loginMember"); // 안넣어도 되는 것 생략 
		
		if(loginMember != null) {
			// 아래 루트로 포워드 되게 한다.
			request.getRequestDispatcher("/views/member/myPage.jsp").forward(request, response);
		}else {
			// 메세지는 아래와 같이 팝업 나오게 하고, 위치는 홈으로 가도록
			request.setAttribute("msg", "로그인 후 수정해 주세요.");
			request.setAttribute("location", "/");
			
			request.getRequestDispatcher("/views/common/msg.jsp").forward(request, response);
		}
	
	}

}
