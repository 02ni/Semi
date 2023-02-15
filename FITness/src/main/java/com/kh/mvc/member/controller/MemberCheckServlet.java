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

@WebServlet("/member/idCheck")
public class MemberCheckServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	public MemberCheckServlet() {
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Map<String, Boolean> map = new HashMap<>();
		String userId = request.getParameter("userId");
		
		// 아이디가 중복되면 dubplicate 라는 속성에 true
		// duplicate라는 키값에 ~~~value 값을 맵오브젝트에 put 하겠다.
		map.put("duplicate", new MemberService().isDuplicateId(userId));
		
		System.out.println(new Gson().toJson(map));
		
		response.setContentType("application/json;charset=UTF-8");

		// 출력 스트림으로 내보낸다. (클라이언트와 연결된)
		new Gson().toJson(map, response.getWriter());
		
//		new Gson().toJson()
	
	}
}
