package com.kh.mvc.member.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.mvc.member.model.service.MemberService;
import com.kh.mvc.member.model.vo.Member;


@WebServlet(name = "memberjoin", urlPatterns = { "/member/join" })
public class MemberJoinServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
    public MemberJoinServlet() {
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/views/member/join.jsp").forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		
		int result = 0;
		Member member = new Member();		
		
		member.setId(request.getParameter("userId"));
		member.setPassword(request.getParameter("userPwd"));
		member.setName(request.getParameter("userName"));
		member.setPhone(request.getParameter("phone"));
		member.setEmail(request.getParameter("email"));
		String adress = request.getParameter("address");
		String adress2 = request.getParameter("addressdetail");
		
		member.setAddress(adress + " " + adress2);
		
		
		
		
		
		System.out.println(member);
		result = new MemberService().save(member);
		
		if(result > 0) {
			request.setAttribute("msg", "회원 가입 성공");
			request.setAttribute("location", "/");
		}else {
			request.setAttribute("msg", "회원 가입 실패");
			request.setAttribute("location", "/member/join");
		}
		
		request.getRequestDispatcher("/views/common/msg.jsp").forward(request, response);
		
	}

}
