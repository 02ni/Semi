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

@WebServlet(name = "favoriteDelete", urlPatterns = { "/favorite/delete" })
public class FavoriteDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public FavoriteDeleteServlet() {
    }

    @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

    @Override
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
		
			result = new GymService().deleteFavorite(gymNo,memberNo);
			
			if(result > 0) {
				request.setAttribute("msg", "좋아요가 해제되셨습니다!");
				request.setAttribute("location", "/gym/detail?gym=" + gymNo);
			} else {
				request.setAttribute("msg", "실패했습니다!");
				request.setAttribute("location", "/gym/detail?gym=" + gymNo);
			}
		
		} else {
			request.setAttribute("msg", "로그인 후 작성해 주세요.");	
			request.setAttribute("location", "/member/login");			
		}
		request.getRequestDispatcher("/views/common/msg.jsp").forward(request, response);
	}
		

}
