package com.kh.mvc.board.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.mvc.board.model.service.BoardService;
import com.kh.mvc.board.model.vo.Board;

@WebServlet(name = "boardView", urlPatterns = { "/board/view" })
public class BoardViewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public BoardViewServlet() {
    }

    @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	Board board = null;
    	int no = Integer.parseInt(request.getParameter("no"));
    	
    	System.out.println("게시글 번호 : " + no);
    	
    	// 1. 쿠키에 게시글을 조회한 이력이 있는지 확인
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
    	
    	// 2. 읽지 않은 게시글이면 쿠키에 기록
    	if(!hasRead) {
    		Cookie cookie = new Cookie("boardHistory", boardHistory + "|" + no + "|");
    		
    		cookie.setMaxAge(-1);
    		response.addCookie(cookie);
    	}
    	
    	board = new BoardService().getBoardByNo(no, hasRead);
    	
    	board = new BoardService().getBoardReplyByNo(no, hasRead);

    	System.out.println(board);
    	
    	request.setAttribute("board", board);
    	request.getRequestDispatcher("/views/board/view.jsp").forward(request, response);
	}
}