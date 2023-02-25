package com.kh.mvc.admin.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kh.mvc.admin.model.service.AdminService;
import com.kh.mvc.gym.model.vo.Gym;
import com.kh.mvc.member.model.service.MemberService;
import com.kh.mvc.member.model.vo.Member;


@WebServlet(name = "admincenterjoin", urlPatterns = { "/admin/centerjoin" })
public class AdminCenterJoinServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
 
    public AdminCenterJoinServlet() {

    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/views/admin/centerJoin.jsp").forward(request, response);

	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		Member loginMember = (session == null) ? null : (Member) session.getAttribute("loginMember");	
		
		
		if((!loginMember.getRole().equals("ROLE_ADMIN")) || loginMember == null) {
			request.setAttribute("msg", "올바르지 않은 접근입니다.");
			request.setAttribute("location", "/member/login");
			request.getRequestDispatcher("/views/common/msg.jsp").forward(request, response);
		}
		
		
		
		
		int maxSize = 10485760;
		String path = getServletContext().getRealPath("/resources/upload/center");
			
		Gym gym = new Gym();
		gym.setGymName(request.getParameter("centerId"));
		gym.setCategory(String.join(",", request.getParameterValues("cate")));
		gym.setCeoPhone(request.getParameter("ceoPhone"));
		gym.setCeoEmail(request.getParameter("ceoEmail"));
		gym.setGymPhone(request.getParameter("gymPhone"));
		String adress = request.getParameter("address");
		String adress2 = request.getParameter("addressdetail");
		
		gym.setAddress(adress + " " + adress2);
		
		System.out.println(gym);
		
		int result = new AdminService().save(gym);
		
		System.out.println(result);
		
		if(result > 0) {
			request.setAttribute("msg", "센터 등록 성공");
			request.setAttribute("location", "/admin/centerlist");
		}else {
			request.setAttribute("msg", "센터  실패");
			request.setAttribute("location", "/admin/centerjoin");
		}
		
		request.getRequestDispatcher("/views/common/msg.jsp").forward(request, response);
		
	}
		
		
		
	}


