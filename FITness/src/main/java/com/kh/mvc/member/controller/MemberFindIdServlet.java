package com.kh.mvc.member.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.kh.mvc.member.model.dao.MemberDao;
import com.kh.mvc.member.model.service.MemberService;
import com.kh.mvc.member.model.vo.Member;


@WebServlet(name = "memberFindId", urlPatterns = { "/member/findid" })
public class MemberFindIdServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
    public MemberFindIdServlet() {

    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	
		request.getRequestDispatcher("/views/member/findId.jsp").forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Map<String, Object> map = new HashMap<>();
		String phone = request.getParameter("phone");
		
		String id = new MemberService().findIdbyPhone(phone);
		
		if(id == null) {
			map.put("resultCode", 0);
			map.put("id", "");
				
		} else {
			map.put("resultCode", 1);
			map.put("id", id);
			
		}
		
     System.out.println(map);
		response.setContentType("application/json;charset=UTF-8");
		
		new Gson().toJson(map, response.getWriter());		
	} 


}


