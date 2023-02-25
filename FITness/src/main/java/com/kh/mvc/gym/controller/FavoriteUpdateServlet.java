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

@WebServlet(name = "favoriteUpdate", urlPatterns = { "/favorite/update" })
public class FavoriteUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public FavoriteUpdateServlet() {
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}
	// 좋아요 누르면 좋아요 삽입 
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int result = 0;
    	
    	//로그인체크
    	HttpSession session = request.getSession(false);
		Member loginMember = (session == null) ? null : (Member) session.getAttribute("loginMember");
		
		// gymNo
		if(loginMember != null) {
			//센터번호입력
			int gymNo = Integer.parseInt(request.getParameter("gymNo"));
			int memberNo = loginMember.getNo();
		
			result = new GymService().saveFavorite(gymNo,memberNo);
			
			if(result > 0) {
				request.setAttribute("msg", "좋아요!");
				request.setAttribute("location", "/gym/detail?gym=" + gymNo);
			} else {
				request.setAttribute("msg", "좋아요 실패!");
				request.setAttribute("location", "/gym/detail?gym=" + gymNo);
			}
		
		} else {
			request.setAttribute("msg", "로그인 후 작성해 주세요.");	
			request.setAttribute("location", "/member/login");			
		}
		request.getRequestDispatcher("/views/common/msg.jsp").forward(request, response);
	}

}
