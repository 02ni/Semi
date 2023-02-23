package com.kh.mvc.notice.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kh.mvc.notice.model.service.NoticeService;
import com.kh.mvc.notice.model.vo.Notice;
import com.kh.mvc.member.model.vo.Member;

import com.kh.mvc.common.util.FileRename;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

@WebServlet(name = "noticeWrite", urlPatterns = { "/notice/write" })
public class NoticeWriteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public NoticeWriteServlet() {
    }

    @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	HttpSession session = request.getSession(false);
		Member loginMember = (session == null) ? null : (Member) session.getAttribute("loginMember");
		
		if(loginMember != null) {			
			request.getRequestDispatcher("/views/notice/write.jsp").forward(request, response);
		} else {
			request.setAttribute("msg", "로그인 후 작성해 주세요.");
			request.setAttribute("location", "/");			
			request.getRequestDispatcher("/views/common/msg.jsp").forward(request, response);
		}
	}

    @Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	HttpSession session = request.getSession(false);
		Member loginMember = (session == null) ? null : (Member) session.getAttribute("loginMember");
		
		if(loginMember != null) {
	    	// 파일이 저장될 경로
	    	String path = getServletContext().getRealPath("/resources/upload/board");
	    	
	    	// 파일의 최대 사이즈 지정 (10MB)
	    	int maxSize = 10485760;
	    	
	    	// 파일 인코딩 설정
	    	String encoding = "UTF-8";

	    	MultipartRequest mr = new MultipartRequest(request, path, maxSize, encoding, new FileRename());
	    	    	
	    	Notice notice = new Notice();
			
	    	notice.setWriterNo(loginMember.getNo());
			// 폼 파라미터로 넘어온 값들 (파일에 대한 정보 X)
			notice.setTitle(mr.getParameter("title"));
			notice.setContent(mr.getParameter("content"));
			// 파일에 대한 정보를 가져올 때
			notice.setRenamedFileName(mr.getFilesystemName("upfile"));
			notice.setOriginalFileName(mr.getOriginalFileName("upfile"));
			
			int result = new NoticeService().save(notice);
			
			if(result > 0) {
				request.setAttribute("msg", "게시글 등록 성공");
				request.setAttribute("location", "/notice/list");		
			} else {
				request.setAttribute("msg", "게시글 등록 실패");
				request.setAttribute("location", "/notice/list");		
			}
		} else {
			request.setAttribute("msg", "로그인 후 작성해 주세요.");
			request.setAttribute("location", "/");			
		}
		
		request.getRequestDispatcher("/views/common/msg.jsp").forward(request, response);
	}
}