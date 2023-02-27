package com.kh.mvc.gym.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kh.mvc.gym.model.service.GymService;
import com.kh.mvc.member.model.vo.Member;

@WebServlet(name = "bucketInsert", urlPatterns = { "/bucket/insert" })
public class BucketInsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public BucketInsertServlet() {
    }
    @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	int result = 0;
    	int voucher = 0;
    	int memberNo = 0;
    	int gymNo = 0;
    	//로그인체크
    	HttpSession session = request.getSession(false);
		Member loginMember = (session == null) ? null : (Member) session.getAttribute("loginMember");
		
		// gymNo
		if(loginMember != null) {
			
			
			memberNo = loginMember.getNo();
			gymNo = Integer.parseInt(request.getParameter("gymno"));
				voucher = Integer.parseInt(request.getParameter("voucher"));
				result = new GymService().saveCart(voucher, memberNo);
				
			if(result > 0) {
				request.setAttribute("msg", "장바구니에 담겼습니다.");
				request.setAttribute("location", "/gym/detail?gym=" + gymNo);
			} else {
				request.setAttribute("msg", "다시 담아주세요.");
				request.setAttribute("location", "/gym/detail?gym=" + gymNo);
			}
		
		
		} else {
			request.setAttribute("msg", "로그인 후 작성해 주세요.");	
			request.setAttribute("location", "/member/login");			
		}
		request.getRequestDispatcher("/views/common/msg.jsp").forward(request, response);
		
		
	
	}
    @Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
    }
}
