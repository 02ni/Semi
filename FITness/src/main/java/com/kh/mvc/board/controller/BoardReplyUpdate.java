package com.kh.mvc.board.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kh.mvc.board.model.service.BoardService;
import com.kh.mvc.board.model.vo.Board;
import com.kh.mvc.board.model.vo.Reply;
import com.kh.mvc.member.model.vo.Member;

@WebServlet(name = "replyUpdate", urlPatterns = { "/board/replyupdate" })
public class BoardReplyUpdate extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public BoardReplyUpdate() {
    }

    @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	Reply reply = null;
    	int no = Integer.parseInt(request.getParameter("no"));
    	
    	reply = new BoardService().readReply(no);
		
		request.setAttribute("reply", reply);
		request.getRequestDispatcher("/views/board/reply.jsp").forward(request, response);
    }

    @Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	int result = 0;
    	
    	HttpSession session = request.getSession(false);
		Member loginMember = (session == null) ? null : (Member) session.getAttribute("loginMember");
    	
		int no = Integer.parseInt(request.getParameter("replyNo"));
    	int boardNo = Integer.parseInt(request.getParameter("boardNo"));
		String content = request.getParameter("content");
		
    	Reply reply = new Reply();
		
    	reply.setNo(no);
		reply.setBoardNo(boardNo);
		reply.setContent(content);
		reply.setWriterNo(loginMember.getNo());
		
		result = new BoardService().updateReply(reply);
		
		if(result > 0) {
			request.setAttribute("msg", "댓글 등록 성공");
			//request.setAttribute("script", "self.close()");
			request.setAttribute("location", "/board/view?no=" + boardNo);
			//request.setAttribute("script", "window.opener.location.reload()");
			request.setAttribute("script", "self.close()");
			//response.sendRedirect(request.getContextPath() + "/board/view?no=" + boardNo);
	        
		} else {
			request.setAttribute("msg", "댓글 등록 실패");
			request.setAttribute("location", "/board/view?no=" + boardNo);
		}
			
		request.getRequestDispatcher("/views/common/msg.jsp").forward(request, response);
	}

}