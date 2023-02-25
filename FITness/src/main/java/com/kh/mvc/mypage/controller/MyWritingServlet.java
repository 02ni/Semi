package com.kh.mvc.mypage.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kh.mvc.board.model.service.BoardService;
import com.kh.mvc.board.model.vo.Board;
import com.kh.mvc.common.util.PageInfo;
import com.kh.mvc.member.model.vo.Member;
import com.kh.mvc.mypage.model.service.MyPageService;

@WebServlet(name = "myWriting", urlPatterns = { "/myWriting" })
public class MyWritingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    public MyWritingServlet() {
    }

    @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	HttpSession session = request.getSession(false);
		Member loginMember = (session == null) ? null : (Member) session.getAttribute("loginMember");
    	
    	int page = 0;
		int listCount = 0;
		PageInfo pageInfo = null;
		List<Board> list = null;
		
		try {
			page = Integer.parseInt(request.getParameter("page"));
		} catch (NumberFormatException e) {
			page = 1;
		}
		if(loginMember != null) {
			Member member = new Member();
			member.setId(loginMember.getId());
			
			listCount = new BoardService().getBoardCount();
			pageInfo = new PageInfo(page, 10, listCount, 10);
			list = new MyPageService().getMyWritingList(pageInfo, member);
			
			request.setAttribute("pageInfo", pageInfo);
			request.setAttribute("list", list);
			request.getRequestDispatcher("/views/mypage/myWriting.jsp").forward(request, response);
		} else {
			request.setAttribute("msg", "로그인 후 시도해 주세요.");
			request.setAttribute("location", "/");
			request.getRequestDispatcher("/views/common/msg.jsp").forward(request, response);
		}

	}

}
