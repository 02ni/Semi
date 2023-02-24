package com.kh.mvc.board.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kh.mvc.board.model.service.BoardService;
import com.kh.mvc.board.model.vo.Board;
import com.kh.mvc.board.model.vo.Reply;
import com.kh.mvc.common.util.FileRename;
import com.kh.mvc.common.util.PageInfo;
import com.kh.mvc.member.model.vo.Member;
import com.oreilly.servlet.MultipartRequest;

@WebServlet(name = "boardSearch", urlPatterns = { "/board/search" })
public class BoardSearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public BoardSearchServlet() {
    }

    @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }
    
    @Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int page = 0;
		int seachCount = 0;
		PageInfo pageInfo = null;
    	
    	String searchField = null;
    	String searchText = null;

    	HttpSession session = request.getSession(false);
		Member loginMember = (session == null) ? null : (Member) session.getAttribute("loginMember");		
		
		searchField = request.getParameter("searchField");
		searchText = request.getParameter("searchText");
		
		System.out.println(searchField);
		System.out.println(searchText);
		
		List<Board> list = null;
			
		list = new BoardService().search(pageInfo, searchField, searchText);
		System.out.println("검색 구현 창" + list);
			
		try {
			page = Integer.parseInt(request.getParameter("page"));
		} catch (NumberFormatException e) {
			page = 1;
		}
			
//			if(list!=null) {
//				request.setAttribute("msg", "두둔");	
//				request.setAttribute("location", "/board/list");
//				request.getRequestDispatcher("/views/common/msg.jsp").forward(request, response);
//			}
			
			seachCount = new BoardService().getBoardsearchCount();
			pageInfo = new PageInfo(page, 10, seachCount, 10);
			list = new BoardService().getBoardList(pageInfo);	
			
		request.setAttribute("pageInfo", pageInfo);
		request.setAttribute("list", list);
		request.setAttribute("location", "/board/list");
		request.getRequestDispatcher("/views/common/msg.jsp").forward(request, response);
    }	
		
}
