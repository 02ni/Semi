package com.kh.mvc.notice.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.mvc.notice.model.service.NoticeService;
import com.kh.mvc.notice.model.vo.Notice;
import com.kh.mvc.common.util.PageInfo;

@WebServlet(name = "noticeList", urlPatterns = { "/notice/list" })
public class NoticeListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public NoticeListServlet() {
    }
    
    @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	int page = 0;
		int listCount = 0;
		PageInfo pageInfo = null;
		int fix = 0;
		List<Notice> list = null;
		
		
		try {
			page = Integer.parseInt(request.getParameter("page"));
		} catch (NumberFormatException e) {
			page = 1;
		}
		
		listCount = new NoticeService().getBoardCount();
		pageInfo = new PageInfo(page, 10, listCount, 10);
		list = new NoticeService().getBoardList(pageInfo, fix);
		
		System.out.println(list);
		
		request.setAttribute("pageInfo", pageInfo);
		request.setAttribute("list", list);
		request.getRequestDispatcher("/views/notice/list.jsp").forward(request, response);
	}

}
