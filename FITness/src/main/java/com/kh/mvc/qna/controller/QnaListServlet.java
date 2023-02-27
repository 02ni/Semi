package com.kh.mvc.qna.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kh.mvc.qna.model.service.QnaService;
import com.kh.mvc.qna.model.vo.QnaBoard;
import com.kh.mvc.common.util.PageInfo;
import com.kh.mvc.member.model.vo.Member;


@WebServlet(name = "qnaList", urlPatterns = { "/qna/list" })
public class QnaListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public QnaListServlet() {
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int page = 0;
		int listCount = 0;
		PageInfo pageInfo = null;
		List<QnaBoard> list = null;
		
		int no = 0;
		
		
		System.out.println(no);
		
		HttpSession session = request.getSession(false);
		Member loginMember = (session == null) ? null : (Member) session.getAttribute("loginMember");
	
		if(loginMember != null) {
			try {
				no=Integer.parseInt(request.getParameter("no"));
				page = Integer.parseInt(request.getParameter("page"));
			} catch (NumberFormatException e) {
				page = 1;
			}
			
			listCount = new QnaService().getBoardCount();
			pageInfo = new PageInfo(page, 10, listCount, 10);
			list = new QnaService().getBoardList(pageInfo, no);
			
			System.out.println("용" + pageInfo);
			
			request.setAttribute("pageInfo", pageInfo);
			request.setAttribute("no", no);
			request.setAttribute("list", list);
			request.getRequestDispatcher("/views/qna/list.jsp").forward(request, response);
		} else {
			request.setAttribute("msg", "로그인 후 작성해 주세요.");
			request.setAttribute("location", "/member/login");			
			request.getRequestDispatcher("/views/common/msg.jsp").forward(request, response);
		}
	}

}
