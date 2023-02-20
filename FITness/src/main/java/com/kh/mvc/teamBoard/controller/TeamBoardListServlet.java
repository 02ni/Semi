package com.kh.mvc.teamBoard.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.mvc.common.util.PageInfo;
import com.kh.mvc.teamBoard.model.service.TeamBoardService;
import com.kh.mvc.teamBoard.model.vo.TeamBoard;

@WebServlet(name = "teamBoardList", urlPatterns = { "/teamboard/list" })
public class TeamBoardListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public TeamBoardListServlet() {
    }

    @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	int page = 0;
		int listCount = 0;
		PageInfo pageInfo = null;
		List<TeamBoard> list = null;
		
		try {
			page = Integer.parseInt(request.getParameter("page"));
		} catch (NumberFormatException e) {
			page = 1;
		}
		
		listCount = new TeamBoardService().getBoardCount();
		pageInfo = new PageInfo(page, 10, listCount, 10);
		
		list = new TeamBoardService().getBoardList(pageInfo);
		
		System.out.println(list);
		
		request.setAttribute("pageInfo", pageInfo);
		request.setAttribute("list", list);
		request.getRequestDispatcher("/views/teamboard/list.jsp").forward(request, response);
    }

}
