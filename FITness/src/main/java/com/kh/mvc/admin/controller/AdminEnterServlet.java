


package com.kh.mvc.admin.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kh.mvc.admin.model.dao.AdminDao;
import com.kh.mvc.admin.model.service.AdminService;
import com.kh.mvc.board.model.service.BoardService;
import com.kh.mvc.board.model.vo.Board;
import com.kh.mvc.common.util.PageInfo;
import com.kh.mvc.member.model.dao.MemberDao;
import com.kh.mvc.member.model.service.MemberService;
import com.kh.mvc.member.model.vo.Member;


@WebServlet(name = "adminenterservlet", urlPatterns = { "/admin/memberlist" })
public class AdminEnterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
    public AdminEnterServlet() {

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
		int listCount2 = 0;
		PageInfo pageInfo = null;
		List<Member> list = null;
		
		try {
			page = Integer.parseInt(request.getParameter("page"));
		} catch (NumberFormatException e) {
			page = 1;
		}
		
		
		// 전체 회원수 구하기
		listCount = new AdminService().getMemberCount();
			
		listCount2 = new AdminService().getNotMemberCount();
		
		pageInfo = new PageInfo(page, 10, listCount, 10);
		
		// 회원 목록 구하기
		list = new AdminService().getMemberList(pageInfo);
		

		
		request.setAttribute("listCount2", listCount2);
		request.setAttribute("listCount", listCount);
		request.setAttribute("pageInfo", pageInfo);
		request.setAttribute("list", list);
		request.getRequestDispatcher("/views/admin/memberList.jsp").forward(request, response);
		
		
	}

}
