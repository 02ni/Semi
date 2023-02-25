package com.kh.mvc.admin.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kh.mvc.admin.model.service.AdminService;
import com.kh.mvc.member.model.vo.Member;
import com.kh.mvc.qna.model.vo.QnaReply;


@WebServlet(name = "AdminQnaReply", urlPatterns = { "/admin/qnareply" })
public class AdminQnaReplyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
 
    public AdminQnaReplyServlet() {

    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int result = 0;
		
		
		HttpSession session = request.getSession(false);
		Member loginMember = (session == null) ? null : (Member) session.getAttribute("loginMember");		
		
		
		int qnaboardNo = Integer.parseInt(request.getParameter("qnaboardNo"));
		
		System.out.println(qnaboardNo);
		
		String content = request.getParameter("content");
		
		if(loginMember != null) {
		QnaReply qnareply = new QnaReply();
		
		qnareply.setBoardNo(qnaboardNo);
		qnareply.setWriterNo(loginMember.getNo());
		qnareply.setContent(content);
		
		result = new AdminService().saveqnaReply(qnareply);
		
		
		System.out.println(result);
		
		
		if(result > 0) {
			request.setAttribute("msg", "댓글 등록 성공");
			request.setAttribute("location", "/qna/view?no=" + qnaboardNo);
			
		} else {
			request.setAttribute("msg", "댓글 등록 실패");
			request.setAttribute("location", "/qna/view?no=" + qnaboardNo);
		} 
		
		
		}else {
			request.setAttribute("msg", "로그인 후 작성해 주세요.");
			request.setAttribute("location", "/member/login");			
		
	
	}
//		response.sendRedirect(request.getContextPath() + "/qna/view?no=" + qnaboardNo);
		request.getRequestDispatcher("/views/common/msg.jsp").forward(request, response);
	}
		
}



