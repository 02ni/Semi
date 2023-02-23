package com.kh.mvc.notice.controller;

import java.io.File;
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

@WebServlet(name = "noticeUpdate", urlPatterns = { "/notice/update" })
public class NoticeUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public NoticeUpdateServlet() {
    }

    @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	HttpSession session = request.getSession(false);
		Member loginMember = (session == null) ? null : (Member) session.getAttribute("loginMember");
		
		if(loginMember != null) {
			Notice notice = new NoticeService().getBoardByNo(Integer.parseInt(request.getParameter("no")), true);
			
			if(notice != null && loginMember.getId().equals(notice.getWriterId())) {
				request.setAttribute("board", notice);
				request.getRequestDispatcher("/views/notice/update.jsp").forward(request, response);
			} else {
				request.setAttribute("msg", "잘못된 접근입니다.");
				request.setAttribute("location", "/notice/list");			
				request.getRequestDispatcher("/views/common/msg.jsp").forward(request, response);
			}
			
		} else {
			request.setAttribute("msg", "로그인 후 수정해 주세요.");
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

	    	// DefaultFileRenamePolicy : 중복되는 이름 뒤에 1 ~ 9999 붙인다.
//	    	MultipartRequest mr = new MultipartRequest(request, path, maxSize, encoding, new DefaultFileRenamePolicy());
	    	MultipartRequest mr = new MultipartRequest(request, path, maxSize, encoding, new FileRename());
	    	
			Notice noitce = new NoticeService().getBoardByNo(Integer.parseInt(mr.getParameter("no")), true);
			
			if(noitce != null && loginMember.getId().equals(noitce.getWriterId())) {
	    		noitce.setTitle(mr.getParameter("title"));
	    		noitce.setContent(mr.getParameter("content"));
	    		
	    		String originalFileName = mr.getOriginalFileName("upfile");
	    		String filesystemName = mr.getFilesystemName("upfile");
	    		
	    		if(originalFileName != null && filesystemName != null) {
	    			// 기존에 업로드된 파일 삭제
	    			File file = new File(path + "/" + noitce.getRenamedFileName());
	    			
	    			if(file.exists()) {
	    				file.delete();
	    			}
	    			
	    			noitce.setOriginalFileName(originalFileName);
	    			noitce.setRenamedFileName(filesystemName);
	    		}
	    		
	    		int result = new NoticeService().save(noitce);
	    		
	    		if(result > 0) {
	    			request.setAttribute("msg", "게시글 수정 성공");
	    			request.setAttribute("location", "/notice/view?no=" + noitce.getNo());
	    		} else {
	    			request.setAttribute("msg", "게시글 수정 실패");
	    			request.setAttribute("location", "/notice/update?no=" + noitce.getNo());	    			
	    		}
	    	} else {
				request.setAttribute("msg", "잘못된 접근입니다.");
				request.setAttribute("location", "/notice/list");			
			}	    	
		} else {
			request.setAttribute("msg", "로그인 후 수정해 주세요.");
			request.setAttribute("location", "/");			
		}
		
		request.getRequestDispatcher("/views/common/msg.jsp").forward(request, response);
	}
}