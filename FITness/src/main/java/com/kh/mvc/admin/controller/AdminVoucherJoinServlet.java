package com.kh.mvc.admin.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.swing.text.html.Option;

import com.kh.mvc.admin.model.dao.AdminDao;
import com.kh.mvc.admin.model.service.AdminService;
import com.kh.mvc.gym.model.vo.Gym;
import com.kh.mvc.gym.model.vo.Voucher;
import com.kh.mvc.member.model.vo.Member;


@WebServlet(name = "AdminVoucherJoin", urlPatterns = { "/admin/voucher" })
public class AdminVoucherJoinServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
    public AdminVoucherJoinServlet() {
 
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		Member loginMember = (session == null) ? null : (Member) session.getAttribute("loginMember");	
		
		
		if((!loginMember.getRole().equals("ROLE_ADMIN")) || loginMember == null) {
			request.setAttribute("msg", "올바르지 않은 접근입니다.");
			request.setAttribute("location", "/member/login");
			request.getRequestDispatcher("/views/common/msg.jsp").forward(request, response);
		}
		
		
		
		
		
		Gym gym = null;
		int no = Integer.parseInt(request.getParameter("no"));
		
		System.out.println(no);
	
		
		gym = new AdminService().getGymInfoByNo(no);
		
		request.setAttribute("gym", gym);
		
	
		
		request.getRequestDispatcher("/views/admin/voucher.jsp").forward(request, response);
		

	}
	
	


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int no = Integer.parseInt(request.getParameter("no"));
		int result2 = 0;
//			int voucher = Integer.parseInt(request.getParameter("voucher"));
		
		Voucher voucher = new Voucher();
		
		voucher.setGymNo(Integer.parseInt(request.getParameter("no")));
		voucher.setCate(request.getParameter("category"));
		voucher.setPrice(Integer.parseInt(request.getParameter("voucher")));
		
	
		
		System.out.println(voucher);
		
		int result = new AdminService().save1(voucher);
		

		
		
		if(result > 0) {
			request.setAttribute("msg", "이용권 등록 성공");
		    result2 = new AdminService().updateGymStatus(no, "Y");	
			request.setAttribute("location", "/admin/centerlist");
		}else {
			request.setAttribute("msg", "이용권 등록 실패");
			request.setAttribute("location", "/admin/centerjoin");
		}
		
		request.getRequestDispatcher("/views/common/msg.jsp").forward(request, response);
		
		
		
		
		
	}

}
