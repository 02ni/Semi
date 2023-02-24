package com.kh.mvc.gym.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kh.mvc.gym.model.service.GymService;
import com.kh.mvc.gym.model.vo.Gym;
import com.kh.mvc.gym.model.vo.Review;
import com.kh.mvc.gym.model.vo.Voucher;
import com.kh.mvc.member.model.vo.Member;

@WebServlet(name = "gymDetail", urlPatterns = { "/gym/detail" })
public class GymDetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    public GymDetailServlet() {
    }

    @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	//로그인
    	HttpSession session = request.getSession(false);
		Member loginMember = (session == null) ? null : (Member) session.getAttribute("loginMember");
		
    	// 리뷰수
    	int reviewCount = 0;
    	// 좋아요수
    	int favoriteCount = 0;
    	int sumGrade = 0;
    	double grade = 0;
    	int favorite = 0;
		Gym gym = null;
		
		// 이전페이지에서 전달받은 센터 번호 가져오기
		int no = Integer.parseInt(request.getParameter("gym"));
		
		// 리뷰수 가져오기
		reviewCount = new GymService().getReviewCount(no);
		
		// 좋아요수 가져오기
		favoriteCount = new GymService().getFavoriteCount(no);
		
		// 센터정보 가져오기
		gym = new GymService().getGymByNo(no);
		
		// 리뷰별점 계산
		if(reviewCount != 0) {
		  for  (Review review: gym.getReviews()){
		        sumGrade += review.getGrade();
		    }
		  grade = Math.floor(((double)sumGrade / reviewCount)*10)/10.0;
		
		}else {
			grade=0;
		}

		// 해당 센터의 사용자의 좋아요여부 (센터, 로그인회원No 넘겨줌)
		if(loginMember != null) {
			favorite = new GymService().getIsFavorite(no, loginMember.getNo());
		}
		
		request.setAttribute("reviewCount", reviewCount);
		request.setAttribute("favoriteCount", favoriteCount);
		request.setAttribute("favorite", favorite);
		request.setAttribute("grade", grade);
		request.setAttribute("gym", gym);
		request.getRequestDispatcher("/views/gym/detail.jsp").forward(request, response);
		
	}
    
    @Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
