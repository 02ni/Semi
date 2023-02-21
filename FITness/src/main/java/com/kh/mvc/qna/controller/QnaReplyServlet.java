package com.kh.mvc.qna.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kh.mvc.qna.model.service.QnaService;
import com.kh.mvc.qna.model.vo.QnaBoard;
import com.kh.mvc.qna.model.vo.QnaReply;
import com.kh.mvc.common.util.FileRename;
import com.kh.mvc.member.model.vo.Member;
import com.oreilly.servlet.MultipartRequest;

@WebServlet(name = "qnaReply", urlPatterns = { "/qna/reply" })
public class QnaReplyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    public QnaReplyServlet() {
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int result = 0;

    	HttpSession session = request.getSession(false);
		Member loginMember = (session == null) ? null : (Member) session.getAttribute("loginMember");		
		
		int boardNo = Integer.parseInt(request.getParameter("boardNo"));
		String content = request.getParameter("content");
		
		if(loginMember != null) {
			QnaReply reply = new QnaReply();
			
			reply.setBoardNo(boardNo);
			reply.setWriterNo(loginMember.getNo());
			reply.setContent(content);
			
			result = new QnaService().saveReply(reply);
			
			if(result > 0) {
				request.setAttribute("msg", "댓글 등록 성공");
				request.setAttribute("location", "/qna/view?no=" + boardNo);
			} else {
				request.setAttribute("msg", "댓글 등록 실패");
				request.setAttribute("location", "/qna/view?no=" + boardNo);
			}
			
		} else {
			request.setAttribute("msg", "로그인 후 작성해 주세요.");
			request.setAttribute("location", "/member/login");			
			
		}

		request.getRequestDispatcher("/views/common/msg.jsp").forward(request, response);
	}

}