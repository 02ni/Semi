package com.kh.mvc.notice.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.mvc.notice.model.service.NoticeService;
import com.kh.mvc.notice.model.vo.Notice;

@WebServlet(name = "noticeView", urlPatterns = { "/notice/view" })
public class NoticeViewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public NoticeViewServlet() {
    }

    @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	Notice notice = null;
    	int no = Integer.parseInt(request.getParameter("no"));
    	
    	System.out.println("ddddd : " + no);
    	
    	Cookie[] cookies = request.getCookies();
    	String boardHistory = "";
    	boolean hasRead = false;
    	
    	if(cookies != null) {    		
    		for (Cookie cookie : cookies) {				
				if(cookie.getName().equals("boardHistory")) {
					boardHistory = cookie.getValue();
					
					if(boardHistory.contains("|" + no + "|")) {
						hasRead = true;
						
						break;
					}
				}
			}
    	}
    	
    	if(!hasRead) {
    		Cookie cookie = new Cookie("boardHistory", boardHistory + "|" + no + "|");
    		
    		cookie.setMaxAge(-1);
    		response.addCookie(cookie);
    	}
    	
    	notice = new NoticeService().getBoardByNo(no, hasRead);

    	System.out.println(notice);
    	
    	request.setAttribute("notice", notice);
    	request.getRequestDispatcher("/views/notice/view.jsp").forward(request, response);
	}
}