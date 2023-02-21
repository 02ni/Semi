package com.kh.mvc.qna.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.mvc.qna.model.service.QnaService;
import com.kh.mvc.qna.model.vo.QnaBoard;
import com.kh.mvc.common.util.PageInfo;


@WebServlet(name = "qnaList", urlPatterns = { "/qna/list" })
public class QnaListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public QnaListServlet() {
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int no = Integer.parseInt(request.getParameter("no"));
		
		int page = 0;
		int listCount = 0;
		PageInfo pageInfo = null;
		List<QnaBoard> list = null;
		
		System.out.println(no);
	
		try {
			page = Integer.parseInt(request.getParameter("page"));
		} catch (NumberFormatException e) {
			page = 1;
		}
		
		listCount = new QnaService().getBoardCount();
		pageInfo = new PageInfo(page, 10, listCount, 10);
		
		list = new QnaService().getBoardList(pageInfo, no);
		
		System.out.println(list);
		
		request.setAttribute("pageInfo", pageInfo);
		request.setAttribute("list", list);
		request.getRequestDispatcher("/views/qna/list.jsp").forward(request, response);
	}

}
