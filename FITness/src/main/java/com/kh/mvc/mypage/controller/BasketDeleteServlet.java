package com.kh.mvc.mypage.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.kh.mvc.mypage.model.service.MyPageService;

@WebServlet(name = "basketDelete", urlPatterns = { "/basketDelete" })
public class BasketDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
      
    public BasketDeleteServlet() {
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int result = 0;
		int basketNo = Integer.parseInt(request.getParameter("basketNo"));
		
		result = new MyPageService().delete(basketNo);
		
		if(result > 0) {
//			request.setAttribute("msg", "장바구니에서 삭제되었습니다.");
//			request.setAttribute("location", "/views/mypage/Basket.jsp");
			response.sendRedirect(request.getContextPath() + "/basket");
		}else {
			request.setAttribute("msg", "품목 삭제에 실패했습니다.");
			request.setAttribute("location", "/views/mypage/Basket.jsp");
			request.getRequestDispatcher("/views/common/msg.jsp").forward(request, response);
		}
	}
}
