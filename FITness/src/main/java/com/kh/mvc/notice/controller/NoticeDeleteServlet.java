package com.kh.mvc.notice.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kh.mvc.notice.model.service.NoticeService;
import com.kh.mvc.notice.model.vo.Notice;
import com.kh.mvc.member.model.vo.Member;

@WebServlet(name = "noticeDelete", urlPatterns = { "/notice/delete" })
public class NoticeDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public NoticeDeleteServlet() {
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int result = 0;
		int no = Integer.parseInt(request.getParameter("no"));
		result =  new NoticeService().delete(no);

    	HttpSession session = request.getSession(false);
		Member loginMember = (session == null) ? null : (Member) session.getAttribute("loginMember");
		
		if(loginMember != null) {
			Notice notice = new NoticeService().getBoardByNo(Integer.parseInt(request.getParameter("no")), true);
			
			if(result > 0) {
				request.setAttribute("msg", "게시글 삭제 성공");
				request.setAttribute("location", "/notice/list");
			} else {
				request.setAttribute("msg", "게시글 삭제 실패");
				request.setAttribute("location", "/notice/view?no=" + no);
			}
			
		} else {
			request.setAttribute("msg", "로그인 후 수정해 주세요.");
			request.setAttribute("location", "/");			
			request.getRequestDispatcher("/views/common/msg.jsp").forward(request, response);
		}
		result =  new NoticeService().delete(no);
		
		request.getRequestDispatcher("/views/common/msg.jsp").forward(request, response);
	}

}