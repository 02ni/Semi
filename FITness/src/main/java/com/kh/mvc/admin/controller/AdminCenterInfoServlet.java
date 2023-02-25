package com.kh.mvc.admin.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kh.mvc.admin.model.service.AdminService;
import com.kh.mvc.gym.model.service.GymService;
import com.kh.mvc.gym.model.vo.Gym;
import com.kh.mvc.member.model.vo.Member;


@WebServlet(name = "AdminCenterInfo", urlPatterns = { "/admin/centerinfo" })
public class AdminCenterInfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public AdminCenterInfoServlet() {
  
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		Member loginMember = (session == null) ? null : (Member) session.getAttribute("loginMember");	
		
		
		if((!loginMember.getRole().equals("ROLE_ADMIN")) || loginMember == null) {
			request.setAttribute("msg", "올바르지 않은 접근입니다.");
			request.setAttribute("location", "/member/login");
			request.getRequestDispatcher("/views/common/msg.jsp").forward(request, response);
		}
		
		
		
		
		
		Gym gym = null;
		int no = Integer.parseInt(request.getParameter("no"));
		
		gym = new AdminService().getGymByNo(no);
			
		request.setAttribute("gym", gym);
		
		request.getRequestDispatcher("/views/admin/centerInfo.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}

}
