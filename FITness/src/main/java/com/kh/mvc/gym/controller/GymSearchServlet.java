package com.kh.mvc.gym.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.mvc.gym.model.service.GymService;
import com.kh.mvc.gym.model.vo.Gym;

@WebServlet(name = "gymSearch", urlPatterns = { "/gym/search" })
public class GymSearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public GymSearchServlet() {
    }

    @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	int totalCount = 0;
    	List<Gym> list = null;
    	
    	//cate에는 디폴트 값으로 1개가 있다.
    	String[] cates = request.getParameterValues("cate");
    	String bigRegion = request.getParameter("bigregion");
    	String region = request.getParameter("region");
    	
    	//지역, 카테고리 모두 전체를 선택 > 다시 전체목록 불러오기
    	if(bigRegion.equals("전체")&&(cates.length==1)) { 
    		request.setAttribute("msg", "전체 센터를 선택하셨습니다!");	
			request.setAttribute("location", "/gym/list");
			request.getRequestDispatcher("/views/common/msg.jsp").forward(request, response);
    	}else {
    	
    		// 1. 지역을 선택하고 카테고리 선택을 안했을때//혹은 전체만인경우 지역만 검색
	    	if((!(bigRegion.equals("전체")))&&(cates.length==1)) {
	    		
	    			list = new GymService().getSearchList(bigRegion,region);
	    			totalCount = new GymService().getSearchCount(bigRegion,region);
		    		request.setAttribute("count", totalCount);
		    		request.setAttribute("list", list);	
					request.setAttribute("cate", "");	
					request.setAttribute("bigRegion", bigRegion);				
					request.setAttribute("region", region);	
		    		request.getRequestDispatcher("/views/gym/searchResult.jsp").forward(request,response);	}
		    		
		    // 2.지역은 전체로 선택하고 카테고리만 선택했을 때 카테고리만 검색
		    if(((bigRegion.equals("전체"))&&(cates.length>1))){
		    		list = new GymService().getSearchList(cates);
	    			totalCount = new GymService().getSearchCount(cates);
		    		request.setAttribute("count", totalCount);
		    		request.setAttribute("list", list);
					request.setAttribute("cate", cates);	
					request.setAttribute("bigRegion", "");				
					request.setAttribute("region", "");	
		    		//화면보여주러 가기
		    		request.getRequestDispatcher("/views/gym/searchResult.jsp").forward(request,response);
		    	}
	    	
	    	// 3.지역과 전체 선택해서 지역과 카테고리 검색
		    if((!(bigRegion.equals("전체"))&&(cates.length>1))){
		    		list = new GymService().getSearchList(cates, bigRegion, region);
	    			totalCount = new GymService().getSearchCount(cates, bigRegion, region);
		    		request.setAttribute("count", totalCount);
		    		request.setAttribute("list", list);
					request.setAttribute("cate", cates);	
					request.setAttribute("bigRegion", bigRegion);				
					request.setAttribute("region", region);	
		    		//화면보여주러 가기
		    		request.getRequestDispatcher("/views/gym/searchResult.jsp").forward(request,response);
		    }
    	}
    }
    	
    
    //값 받음
    @Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
    	
    	
    	
    	
    	
    	
	}

}
