package com.kh.mvc.mypage.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kh.mvc.gym.model.vo.Voucher;
import com.kh.mvc.member.model.vo.Member;
import com.kh.mvc.mypage.model.service.MyPageService;

@WebServlet(name = "basket", urlPatterns = { "/basket" })
public class BasketServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
      
    public BasketServlet() {
    }
    
    @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	HttpSession session = request.getSession(false);
	    Member loginMember = (session == null) ? null : (Member) session.getAttribute("loginMember");
    
	    if(loginMember != null) {
			List<Voucher> list = null;
			Member member = new Member();
			member.setNo(loginMember.getNo());
			
			list = new MyPageService().getBasketList(member);
			
			request.setAttribute("list", list);
			request.getRequestDispatcher("/views/mypage/Basket.jsp").forward(request,response);
		
	    } else {
			request.setAttribute("msg", "로그인 후 시도해 주세요.");
			request.setAttribute("location", "/");
			request.getRequestDispatcher("/views/common/msg.jsp").forward(request, response);
		}
	}
}
