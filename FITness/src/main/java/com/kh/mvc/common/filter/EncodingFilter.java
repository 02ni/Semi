package com.kh.mvc.common.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

/*
 * 서블릿 필터
 *  - request, response가 서블릿이나 JSP 등에 도달하기 전에 필요한 전/후 처리 작업을 실행한다.
 *  - filterChain을 통해서 여러 개의 필터를 연속적으로 사용이 가능하다. 
 *  
 *  서블릿 필터 등록 및 매핑
 *  - WEB-INF/web.xml 파일에 필터를 등록해서 사용한다.
 *  - @WebFilter 어노테이션으로 필터를 등록해서 사용한다.
 */

//모든 요청에 대해 필터를 적용시키겠다.
@WebFilter(filterName = "encoding", urlPatterns = "/*")
public class EncodingFilter implements Filter{	
	
	public EncodingFilter() {
	}
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		System.out.println("인코딩 필터가 생성되어 초기화 진행");
	}


	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// 서블릿을 호출하기 전에 앞단에서 먼저!
		System.out.println("서블릿 동작 전 코드 실행");
		
		String requestMethod = ((HttpServletRequest)request).getMethod();
		
		if (requestMethod.equals("POST")) {
			request.setCharacterEncoding("UTF-8");
			
			System.out.println(request.getCharacterEncoding() + " 인코딩 완료");
		}
		
		// 다음 필터를 호출하거나, 마지막 필터면 Servlet, JSP 를 호출한다.
		// 클라이언트 -> 서블릿 필터 -> 서버가 서블릿을 찾아서 요청 
		chain.doFilter(request, response);
		
//		JSP에는 상단에 기본세팅이 있다. 그래서 아래 구문은 필요없지만, 아래 구문도 참고로!
//		response.setContentType("text/html;charset=UTF-8");
		
		System.out.println("서블릿 동작 후 코드 실행");
	}

	
	@Override
	public void destroy() {
		Filter.super.destroy();
	}
}
