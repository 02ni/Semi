package com.kh.mvc.gym.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.mvc.common.util.PageInfo;
import com.kh.mvc.gym.model.service.GymService;
import com.kh.mvc.gym.model.vo.Gym;

@WebServlet(name = "gymList", urlPatterns = { "/gym/list" })
public class GymListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public GymListServlet() {
    }
    @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	int page = 0;
    	PageInfo pageInfo = null;
		List<Gym> list = null;
		int totalCount = 0;
		
		try {
		page = Integer.parseInt(request.getParameter("page"));
		} catch (Exception e) {
			page=1;
		}
		// 전체 센터 수 얻어오기
		totalCount = new GymService().getGymCount();
		pageInfo = new PageInfo(page, 10, totalCount, 12);
		
		// 센터 정보 얻어오기
		list = new GymService().getGymList(pageInfo);
		
		for (Gym gym : list) {
			System.out.println(gym);
		}
		
		request.setAttribute("pageInfo", pageInfo);
		request.setAttribute("count", totalCount);
		request.setAttribute("list", list);
		//화면보여주러 가기
		request.getRequestDispatcher("/views/gym/list.jsp").forward(request,response);
	}

    @Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
	}

}
