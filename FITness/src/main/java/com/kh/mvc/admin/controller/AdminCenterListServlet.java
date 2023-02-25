package com.kh.mvc.admin.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kh.mvc.admin.model.service.AdminService;
import com.kh.mvc.common.util.PageInfo;
import com.kh.mvc.gym.model.vo.Gym;
import com.kh.mvc.member.model.vo.Member;


@WebServlet(name = "admincenterlist", urlPatterns = { "/admin/centerlist" })
public class AdminCenterListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
    public AdminCenterListServlet() {

    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		Member loginMember = (session == null) ? null : (Member) session.getAttribute("loginMember");	
		
		
		if((!loginMember.getRole().equals("ROLE_ADMIN")) || loginMember == null) {
			request.setAttribute("msg", "올바르지 않은 접근입니다.");
			request.setAttribute("location", "/member/login");
			request.getRequestDispatcher("/views/common/msg.jsp").forward(request, response);
		}
		
		
		
		int page = 0;
		int listCount = 0;
//		int boardCount = 0; 
		PageInfo pageInfo = null;
		List<Gym> list = null;
		
		try {
			page = Integer.parseInt(request.getParameter("page"));
		} catch (NumberFormatException e) {
			page = 1;
		}
		
		listCount = new AdminService().getGymCount();
		

		
		pageInfo = new PageInfo(page, 10, listCount, 10);

		list = new AdminService().getGymList(pageInfo);
		
		
		request.setAttribute("listCount", listCount);
		request.setAttribute("pageInfo", pageInfo);
		request.setAttribute("list", list);
		
		
		request.getRequestDispatcher("/views/admin/centerList.jsp").forward(request, response);
		}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
