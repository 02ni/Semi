package com.kh.mvc.common.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

import com.kh.mvc.common.wrapper.EncryptPasswordWrapper;

//@WebFilter(filterName = "encrypt", urlPatterns = {"/login", "/member/enroll"})
@WebFilter(filterName = "encrypt", servletNames = {"memberlogin", "memberjoin", "memberUpdate", "doubleCheck"})
public class EncryptFilter implements Filter {

    public EncryptFilter() {
    }

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// 기본적으로 사용자가 보낸 요청, 응답 객체를 임의로 수정할 수 없지만 요청, 응답을 감싸는 래퍼 클래스를 만들면 수정할 수 있다.
		EncryptPasswordWrapper wrapper = new EncryptPasswordWrapper((HttpServletRequest)request);
		
		// request 대신에 생성한 wrapper를 넘겨준다.
		chain.doFilter(wrapper, response);
	}
}