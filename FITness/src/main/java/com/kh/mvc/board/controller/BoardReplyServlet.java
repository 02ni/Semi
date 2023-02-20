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
import com.kh.mvc.common.util.FileRename;
import com.kh.mvc.member.model.vo.Member;
import com.oreilly.servlet.MultipartRequest;

@WebServlet(name = "boardReply", urlPatterns = { "/board/reply" })
public class BoardReplyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    public BoardReplyServlet() {
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int result = 0;

    	HttpSession session = request.getSession(false);
		Member loginMember = (session == null) ? null : (Member) session.getAttribute("loginMember");		
		
		int boardNo = Integer.parseInt(request.getParameter("boardNo"));
		String content = request.getParameter("content");
		
		if(loginMember != null) {
			Reply reply = new Reply();
			
			reply.setBoardNo(boardNo);
			reply.setWriterNo(loginMember.getNo());
			reply.setContent(content);
			
			result = new BoardService().saveReply(reply);
			
			if(result > 0) {
				request.setAttribute("msg", "댓글 등록 성공");
				request.setAttribute("location", "/board/view?no=" + boardNo);
			} else {
				request.setAttribute("msg", "댓글 등록 실패");
				request.setAttribute("location", "/board/view?no=" + boardNo);
			}
			
		} else {
			request.setAttribute("msg", "로그인 후 작성해 주세요.");
			request.setAttribute("location", "/member/login");			
			
		}

		request.getRequestDispatcher("/views/common/msg.jsp").forward(request, response);
	}

}