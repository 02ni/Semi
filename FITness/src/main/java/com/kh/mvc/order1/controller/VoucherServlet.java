package com.kh.mvc.order1.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kh.mvc.gym.model.service.GymService;
import com.kh.mvc.gym.model.vo.Gym;
import com.kh.mvc.member.model.vo.Member;
import com.kh.mvc.order1.model.service.OptionService;
import com.kh.mvc.order1.model.vo.Option;
import com.kh.mvc.order1.model.vo.Voucher;

@WebServlet(name = "voucherServlet", urlPatterns = { "/order1/voucher" })
public class VoucherServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public VoucherServlet() {
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		Option option = null;
		Voucher voucher = null;
		Gym gym = null;
		
		HttpSession session = request.getSession(false);
		Member loginMember = (session == null) ? null : (Member)session.getAttribute("loginMember"); 
		
		if(loginMember != null) {
		
			int no = Integer.parseInt(request.getParameter("gymNo"));
			
			gym = new GymService().getGymByNo(no);
			
			request.setAttribute("gym", gym);
			request.setAttribute("gymNo", no);
			
			
		int cno = Integer.parseInt(request.getParameter("option"));
		
		option = new OptionService().getOptionByNo(cno);
		
		int vno = Integer.parseInt(request.getParameter("voucher"));
		
		voucher = new OptionService().getVouchersByNo(vno);
		
	
		request.setAttribute("voucher", voucher);		
		
		request.setAttribute("option", option);
		request.getRequestDispatcher("/views/order/order2.jsp").forward(request, response);
		
		}
	
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
