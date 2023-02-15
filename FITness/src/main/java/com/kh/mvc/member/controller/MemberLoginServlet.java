package com.kh.mvc.member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kh.mvc.member.model.service.MemberService;
import com.kh.mvc.member.model.vo.Member;

@WebServlet(name = "login", urlPatterns = "/login")
public class MemberLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public MemberLoginServlet() {
    }

    @Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	HttpSession session = null;
		String userId = request.getParameter("userId");
		String userPwd = request.getParameter("userPwd");
		String saveId = request.getParameter("saveId");
		
		System.out.println(userId + ", " + userPwd + ", " + saveId);
//		System.out.println(EncryptUtil.oneWayEnc(userPwd, "SHA-256"));
		
		if(saveId != null) {
			// 전달된 아이디를 쿠키에 저장
			// 1. 쿠키 생성
			Cookie cookie = new Cookie("saveId", userId);
			
			// 2. response 객체에 쿠키 추가
//			cookie.setMaxAge(-1); // 세션 쿠키(브라우저가 종료될 때까지 유지된다.) 
			cookie.setMaxAge(259200); // 3일 동안 유지
			response.addCookie(cookie);
		} else {
			// 기존 쿠키 값을 삭제
			Cookie cookie = new Cookie("saveId", "");
			
			cookie.setMaxAge(0);
			response.addCookie(cookie);
		}
		
		Member loginMember = new MemberService().login(userId, userPwd);
		
		System.out.println(loginMember);
		
		if(loginMember != null) {
			// loginMember 세션에 저장
			session = request.getSession();
			
			session.setAttribute("loginMember", loginMember);
			
			// 로그인이 완료되면 홈 화면으로 이동시킨다.
			response.sendRedirect(request.getContextPath() + "/");
		} else {
			// 로그인 실패에 대한 메시지를 띄워주고 홈 화면으로 이동
			
			// 1. 공용으로 사용하는 에러 메시지 출력 페이지에
			//    전달할 메시지와 메시지 출력 후 이동할 페이지를 request 객체에 저장한다.
			request.setAttribute("msg", "아이디나 비밀번호가 일치하지 않습니다.");
			request.setAttribute("location", "/");
			
			// 2. request 객체의 데이터를 유지해서 에러 메시지를 출력 페이지에 전달하기 위해 forward를 실행한다.
			request.getRequestDispatcher("/views/common/msg.jsp").forward(request, response);
		}
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	}
}