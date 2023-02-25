package com.kh.mvc.order1.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kh.mvc.member.model.vo.Member;
import com.kh.mvc.order1.model.service.OptionService;
import com.kh.mvc.order1.model.vo.Pmana;

@WebServlet(name = "saveServlet", urlPatterns = { "/order1/save" })
public class SaveServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public SaveServlet() {
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		Pmana pmana = null;
		int result = 0;
		
		
		
		HttpSession session = request.getSession(false);
		Member loginMember = (session == null) ? null : (Member)session.getAttribute("loginMember"); 
		
		int gno = Integer.parseInt(request.getParameter("gymNo"));
		int uno = loginMember.getNo();
		int ono = Integer.parseInt(request.getParameter("optNo"));
		int vno = Integer.parseInt(request.getParameter("voucherNo"));
		String sDate = request.getParameter("sDate");
		String eDate = request.getParameter("eDate");
		String pDate = request.getParameter("pDate");
		String status = request.getParameter("status");
		int tprice = Integer.parseInt(request.getParameter("tPrice"));
		String paymethod = request.getParameter("payMethod");
		
		if(loginMember != null) {
			pmana = new Pmana();
			pmana.setGymNo(gno);
			pmana.setUserNo(uno);
			pmana.setOptNo(ono);
			pmana.setVoucherNo(vno);
			pmana.setSDate(sDate);
			pmana.setEDate(eDate);
			pmana.setPDate(pDate);
			pmana.setStatus(status);
			pmana.setTPrice(tprice);
			pmana.setPayMethod(paymethod);
			
			System.out.println(pmana);
			result = new OptionService().save(pmana);
			
			request.getRequestDispatcher("/views/order/complete1.jsp").forward(request, response);
//			request.getRequestDispatcher("/views/order/order2.jsp").forward(request, response);
		
		}
	}

}
