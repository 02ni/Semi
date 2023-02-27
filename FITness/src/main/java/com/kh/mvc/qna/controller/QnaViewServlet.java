package com.kh.mvc.qna.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.mvc.admin.model.service.AdminService;
import com.kh.mvc.board.model.service.BoardService;
import com.kh.mvc.qna.model.service.QnaService;
import com.kh.mvc.qna.model.vo.QnaBoard;

@WebServlet(name = "qnaView", urlPatterns = { "/qna/view" })
public class QnaViewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public QnaViewServlet() {
    }

    @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	QnaBoard qnaboard = null;
    	int no = Integer.parseInt(request.getParameter("no"));
    	
    	System.out.println("게시글 번호 : " + no);
    	
    	// 1. 쿠키에 게시글을 조회한 이력이 있는지 확인
    	Cookie[] cookies = request.getCookies();
    	String qnaboardHistory = "";
    	boolean hasRead = false;
    	
    	if(cookies != null) {    		
    		for (Cookie cookie : cookies) {				
				if(cookie.getName().equals("qnaboardHistory")) {
					qnaboardHistory = cookie.getValue();
					
					if(qnaboardHistory.contains("|" + no + "|")) {
						hasRead = true;
						
						break;
					}
				}
			}
    	}
    	
    	// 2. 읽지 않은 게시글이면 쿠키에 기록
    	if(!hasRead) {
    		Cookie cookie = new Cookie("qnaboardHistory", qnaboardHistory + "|" + no + "|");
    		
    		cookie.setMaxAge(-1);
    		response.addCookie(cookie);
    	}
    	
    	qnaboard = new QnaService().getBoardByNo(no, hasRead);
    	qnaboard = new QnaService().getQnaBoardReplyByNo(no, hasRead);

    	System.out.println(qnaboard);
    	
    	request.setAttribute("qnaboard", qnaboard);
    	
    	request.getRequestDispatcher("/views/qna/view.jsp").forward(request, response);
	}
}