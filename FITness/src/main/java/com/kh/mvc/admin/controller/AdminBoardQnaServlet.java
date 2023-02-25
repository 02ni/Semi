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
import com.kh.mvc.board.model.service.BoardService;
import com.kh.mvc.board.model.vo.Board;
import com.kh.mvc.common.util.PageInfo;
import com.kh.mvc.member.model.vo.Member;
import com.kh.mvc.qna.model.vo.QnaBoard;


@WebServlet(name = "AdminBoardQna", urlPatterns = { "/admin/boardqna" })
public class AdminBoardQnaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public AdminBoardQnaServlet() {

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
		int boardCount = 0; 
		PageInfo pageInfo = null;
		List<QnaBoard> list = null;
		
		try {
			page = Integer.parseInt(request.getParameter("page"));
		} catch (NumberFormatException e) {
			page = 1;
		}
		
		
		// 전체 게시글 수 구하기
		listCount = new AdminService().getQnaBoardCount();
		
		System.out.println(listCount);
		
		pageInfo = new PageInfo(page, 10, listCount, 10);
		
		
		
		// 게시글 목록 구하기
		list = new AdminService().getQnaBoardList(pageInfo);
		
		request.setAttribute("list", list);
		request.setAttribute("listCount", listCount);
		request.setAttribute("pageInfo", pageInfo);
		
		request.getRequestDispatcher("/views/admin/boardQna.jsp").forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

}
