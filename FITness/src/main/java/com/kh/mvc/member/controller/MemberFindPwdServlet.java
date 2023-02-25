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
import com.kh.mvc.member.model.service.MemberService;


@WebServlet(name = "MemberFindPwd", urlPatterns = { "/member/findpwd" })
public class MemberFindPwdServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
 
    public MemberFindPwdServlet() {

    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/views/member/findPwd.jsp").forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Map<String, Object> map = new HashMap<>();
		String id = request.getParameter("id");
		
		
		String password1 = new MemberService().findPwdbyId(id);
	      
	      
	      
        String password = password1.substring(0,2) + "*****";
     		
	      
      
     
		
		
			map.put("resultCode", 1);
			map.put("password", password);
			
     System.out.println(map);
		response.setContentType("application/json;charset=UTF-8");
		
		new Gson().toJson(map, response.getWriter());		
		
		
	}

}
