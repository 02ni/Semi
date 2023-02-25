package com.kh.mvc.order1.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.mvc.gym.model.service.GymService;
import com.kh.mvc.gym.model.vo.Gym;
import com.kh.mvc.order1.model.service.OptionService;
import com.kh.mvc.order1.model.vo.Option;
import com.kh.mvc.gym.model.vo.Voucher;

@WebServlet(name = "optionServlet", urlPatterns = { "/order/order1" })
public class OptionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public OptionServlet() {
    }

    @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Gym gym = null;
		int no = Integer.parseInt(request.getParameter("gymno"));
		
		gym = new GymService().getGymByNo(no);
		
//		System.out.println(no);
		request.setAttribute("gym", gym);
		request.setAttribute("gymNo", no);
		
		request.getRequestDispatcher("/views/order/order1.jsp").forward(request, response);
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		
		
	}

}
