package com.kh.mvc.qna.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kh.mvc.qna.model.service.QnaService;
import com.kh.mvc.qna.model.vo.QnaBoard;
import com.kh.mvc.common.util.FileRename;
import com.kh.mvc.member.model.vo.Member;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

@WebServlet(name = "qnaWrite", urlPatterns = { "/qna/write" })
public class QnaWriteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public QnaWriteServlet() {
    }

    @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	HttpSession session = request.getSession(false);
		Member loginMember = (session == null) ? null : (Member) session.getAttribute("loginMember");
		
		if(loginMember != null) {			
			request.getRequestDispatcher("/views/qna/write.jsp").forward(request, response);
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

	    	// DefaultFileRenamePolicy : 중복되는 이름 뒤에 1 ~ 9999 붙인다.
//	    	MultipartRequest mr = new MultipartRequest(request, path, maxSize, encoding, new DefaultFileRenamePolicy());
	    	MultipartRequest mr = new MultipartRequest(request, path, maxSize, encoding, new FileRename());
	    	    	
	    	QnaBoard qnaboard = new QnaBoard();
			
	    	qnaboard.setWriterNo(loginMember.getNo());
			
			qnaboard.setTitle(mr.getParameter("title"));
			qnaboard.setContent(mr.getParameter("content"));
			
			qnaboard.setRenamedFileName(mr.getFilesystemName("upfile"));
			qnaboard.setOriginalFileName(mr.getOriginalFileName("upfile"));
			
			System.out.println("rufufjfj"+qnaboard);
			
			int result = new QnaService().save(qnaboard);
			
			if(result > 0) {
				request.setAttribute("msg", "게시글 등록 성공");
				request.setAttribute("location", "/qna/list");		
			} else {
				request.setAttribute("msg", "게시글 등록 실패");
				request.setAttribute("location", "/qna/list");		
			}
		} else {
			request.setAttribute("msg", "로그인 후 작성해 주세요.");
			request.setAttribute("location", "/");			
		}
		
		request.getRequestDispatcher("/views/common/msg.jsp").forward(request, response);
	}
}