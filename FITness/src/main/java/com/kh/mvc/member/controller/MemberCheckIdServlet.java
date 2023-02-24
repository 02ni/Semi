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


@WebServlet(name = "MemberCheckId", urlPatterns = { "/member/checkid" })
public class MemberCheckIdServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public MemberCheckIdServlet() {
    }


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Map<String, Boolean> map = new HashMap<>();
		String userId = request.getParameter("userId");
		
		System.out.println("유저아이디는 " + userId);
		//value 부분이 오브젝트 값
		map.put("isChecked", new MemberService().isCheckedId(userId));
		
		System.out.println(map);
		
		System.out.println();
		
		// 서버에서 응답하는 데이터가 뭔지 알려주는 작업
		response.setContentType("application/json;charset=UTF-8");
	
		
		// tojson에서 오브젝트로 문자열만들고 출력스트림으로 보내준다.(클라이언트와 연결된)
		new Gson().toJson(map, response.getWriter());

		
		
	}

}
