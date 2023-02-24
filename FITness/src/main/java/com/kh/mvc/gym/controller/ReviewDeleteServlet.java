package com.kh.mvc.gym.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kh.mvc.gym.model.service.GymService;
import com.kh.mvc.gym.model.vo.Review;
import com.kh.mvc.member.model.vo.Member;

@WebServlet(name = "reviewDelete", urlPatterns = { "/review/delete" })
public class ReviewDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	
    public ReviewDeleteServlet() {
    }
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}
	//리뷰삭제
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int result = 0;
    	HttpSession session = request.getSession(false);
		Member loginMember = (session == null) ? null : (Member) session.getAttribute("loginMember");
    	
		if(loginMember != null) {
			int no = Integer.parseInt(request.getParameter("no"));
			int gymNo = Integer.parseInt(request.getParameter("gno"));
	    	Review review = new Review();
	    
	    	review.setNo(no);
			
	    	
			result = new GymService().deleteReview(review);
			
			if(result > 0) {
				request.setAttribute("msg", "리뷰가 삭제되었습니다.");
				request.setAttribute("location", "/gym/detail?gym=" + gymNo);
			} else {
				request.setAttribute("msg", "다시 시도해 주세요");
				request.setAttribute("location", "/gym/detail?gym=" + gymNo);
			}

		} else {
			request.setAttribute("msg", "로그인 후 작성해 주세요.");	
			request.setAttribute("location", "/member/login");			
		}
		request.getRequestDispatcher("/views/common/msg.jsp").forward(request, response);
	}

}
