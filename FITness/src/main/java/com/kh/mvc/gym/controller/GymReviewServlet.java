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


@WebServlet(name = "gymReview", urlPatterns = { "/gym/review" })
public class GymReviewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public GymReviewServlet() {
    }

    //리뷰 수정
    @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
int result = 0;
    	
    	//로그인체크
    	HttpSession session = request.getSession(false);
		Member loginMember = (session == null) ? null : (Member) session.getAttribute("loginMember");
		
//		// gymNo
		if(loginMember != null) {
			//리뷰 번호 입력
			int no = Integer.parseInt(request.getParameter("no"));
			String content = request.getParameter("content");
			int grade = Integer.parseInt(request.getParameter("grade"));
		
			Review review = new Review();
			
			review.setGymNo(no);
			review.setWriterNo(loginMember.getNo());
			review.setContent(content);
			review.setGrade(grade);
			
			result = new GymService().saveReview(review);
			
			if(result > 0) {
				request.setAttribute("msg", "리뷰 수정 성공!");
				request.setAttribute("location", "/gym/detail?gym=" + no);
			} else {
				request.setAttribute("msg", "리뷰 수정 실패!");
				request.setAttribute("location", "/gym/detail?gym=" + no);
			}
		
		
		} else {
			request.setAttribute("msg", "로그인 후 작성해 주세요.");	
			request.setAttribute("location", "/member/login");			
		}
		request.getRequestDispatcher("/views/common/msg.jsp").forward(request, response);
    	
	}

    //리뷰 등록
    @Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	int result = 0;
    	
    	//로그인체크
    	HttpSession session = request.getSession(false);
		Member loginMember = (session == null) ? null : (Member) session.getAttribute("loginMember");
		
//		// gymNo
		if(loginMember != null) {
			//센터번호입력
			int no = Integer.parseInt(request.getParameter("no"));
			String content = request.getParameter("content");
			int grade = Integer.parseInt(request.getParameter("grade"));
		
			Review review = new Review();
			
			review.setGymNo(no);
			review.setWriterNo(loginMember.getNo());
			review.setContent(content);
			review.setGrade(grade);
			
			result = new GymService().saveReview(review);
			
			if(result > 0) {
				request.setAttribute("msg", "리뷰 등록 성공!");
				request.setAttribute("location", "/gym/detail?gym=" + no);
			} else {
				request.setAttribute("msg", "리뷰 등록 실패!");
				request.setAttribute("location", "/gym/detail?gym=" + no);
			}
		
		
		} else {
			request.setAttribute("msg", "로그인 후 작성해 주세요.");	
			request.setAttribute("location", "/member/login");			
		}
		
		request.getRequestDispatcher("/views/common/msg.jsp").forward(request, response);
	}

}
