package com.kh.mvc.member.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.mvc.member.model.vo.Member;
import com.kh.mvc.member.model.service.MemberService;

@WebServlet(name ="enroll", urlPatterns = {"/member/enroll"})
public class MemberEnrollServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public MemberEnrollServlet() {
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 회원가입 페이지로 포워드하는 기능
		request.getRequestDispatcher("/views/member/enroll.jsp").forward(request, response);
		// 가급적 jsp 페이지보단 서블릿 태워서 보내주기 
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//request.setCharacterEncoding("UTF-8");
		
		Member member = new Member();
		
		member.setId(request.getParameter("userId"));
		member.setPassword(request.getParameter("userPwd"));
		member.setName(request.getParameter("userName"));
		member.setPhone(request.getParameter("phone"));
		member.setEmail(request.getParameter("email"));
		member.setAddress(request.getParameter("address"));
//		member.setHobby(String.join(", ", request.getParameterValues("hobby"))) ;

		
		String hobby = request.getParameterValues("hobby") !=null ?
				String.join(", ", request.getParameterValues("hobby")) : null;
		
		member.setHobby(hobby);
		
		System.out.println(member);
		
		int result = new MemberService().save(member);

		System.out.println(result);
		
		if(result > 0) {
			// 회원가입 완료
			request.setAttribute("msg", "회원 가입 성공");
			request.setAttribute("location", "/");
		}else {
			// 서블릿을 통해 페이지가 열리도록 세팅
			request.setAttribute("msg", "회원 가입 실패");
			request.setAttribute("location", "/member/enroll");
		}
		
		request.getRequestDispatcher("/views/common/msg.jsp").forward(request, response);
		
		
		
		
	}
}
