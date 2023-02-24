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

@WebServlet(name = "reviewUpdate", urlPatterns = { "/review/update" })
public class ReviewUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ReviewUpdateServlet() {
    }
    // 리뷰 텍스트창에 불러오기
    @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	Review review = null;
    	// review no 불러온다.
    	int no = Integer.parseInt(request.getParameter("no"));
    	
    	review = new GymService().readReview(no);
    	
    	request.setAttribute("review", review);
    	request.getRequestDispatcher("/views/gym/reviewUpdate.jsp").forward(request, response);
    	
    	
	}
    // 팝업에서 수정하기 버튼 누르면
    @Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	int result = 0;
    	HttpSession session = request.getSession(false);
		Member loginMember = (session == null) ? null : (Member) session.getAttribute("loginMember");
    	
		int no = Integer.parseInt(request.getParameter("reviewNo"));
    	int gymNo = Integer.parseInt(request.getParameter("gymNo"));
		String content = request.getParameter("content");
    	int grade = Integer.parseInt(request.getParameter("grade"));
    	Review review = new Review();
		
    	review.setNo(no);
    	review.setGymNo(gymNo);
    	review.setContent(content);
    	review.setGrade(grade);
    	review.setWriterNo(loginMember.getNo());
		
    	
		result = new GymService().updateReview(review);
		
		if(result > 0) {
			request.setAttribute("msg", "리뷰가 수정되었습니다.");
			request.setAttribute("script", "window.opener.location.reload();");
	        request.setAttribute("script", "self.close()");
		} else {
			request.setAttribute("msg", "다시 시도해 주세요");
			request.setAttribute("location", "/review/update");
		}
			
		request.getRequestDispatcher("/views/common/msg.jsp").forward(request, response);
	}

}
