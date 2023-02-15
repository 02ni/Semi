package com.kh.mvc.board.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kh.mvc.board.model.service.BoardService;
import com.kh.mvc.board.model.vo.Board;
import com.kh.mvc.common.util.FileRename;
import com.kh.mvc.member.model.vo.Member;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

@WebServlet(name = "boardWrite", urlPatterns = { "/board/write" })
public class BoardWriteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public BoardWriteServlet() {
    }

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
	    Member loginMember = (session == null) ? null : (Member) session.getAttribute("loginMember");
	
	    if(loginMember != null) {
	    	request.getRequestDispatcher("/views/board/write.jsp").forward(request, response);
	    } else {
			request.setAttribute("msg", "로그인 후 수정해 주세요.");
			request.setAttribute("location", "/");
			request.getRequestDispatcher("/views/common/msg.jsp").forward(request, response);
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 8. 로그인 체크 로직 (로그인해야만 게시글 작성되도록)
		HttpSession session = request.getSession(false);
	    Member loginMember = (session == null) ? null : (Member) session.getAttribute("loginMember");
	    
	    // 9. 1~7 내용을 if 구문 안에 넣기
	    if (loginMember != null) {
	    	// 1. cos.jr 자르 라이브러리 등록함
			
			// 2. 리얼 패스 (뤱엡/ ~~~~/~~~/~~~ 실제 서버의 물리적인 경로를 가져오고 있다.)
			// 파일이 저장될 경로를 얻어온다.
			String path = getServletContext().getRealPath("/resources/upload/board");
			
			// 3. 파일의 최대 사이즈 지정 (10 MB)
			int maxSize = 10485760;
			
			// 4. 파일 인코딩 설정
			String encoding = "UTF-8";
			
			//5. 자르 파일에 있는 MultipartRequest 클래스로 형식 지정하도록
			// DefaultFileRenamePolicy : 중복되는 파일 이름 뒤에 1 ~ 9999를 붙인다.
			//MultipartRequest mr = new MultipartRequest(request, path, maxSize, encoding, new DefaultFileRenamePolicy());
			MultipartRequest mr = new MultipartRequest(request, path, maxSize, encoding, new FileRename());
			
			Board board = new Board();
			
			//6. 폼 파라미터로 넘어온 값들 (파일에 대한 정보가 아니다.)
			board.setTitle(mr.getParameter("title"));
			board.setWriterNo(loginMember.getNo());
			board.setContent(mr.getParameter("content"));
			
			// 7. 파일에 대한 정보를 가져올 때 (첨부파일 네임 속성값을 주자)
			// 파일에 대한 정보를 가져올 때
			board.setRenamedFileName(mr.getFilesystemName("upfile"));  // 실제로 서버에 저장된 파일 이름
			board.setOriginalFileName(mr.getOriginalFileName("upfile"));
			
			// 10. 영향받은 값이 정수형일 것
			int result = new BoardService().save(board);
			
			// 11. 게시글 등록 여부에 따라 
			if (result > 0) {
				request.setAttribute("msg", "게시글 등록 성공");
				request.setAttribute("location", "/board/list");
			} else {
				request.setAttribute("msg", "게시글 등록 실패");
				request.setAttribute("location", "/board/list");
			}
		} else {
			request.setAttribute("msg", "로그인 후 수정해 주세요.");
			request.setAttribute("location", "/");
		}
	    
	    // 메세지 만들어서 보내기
	    request.getRequestDispatcher("/views/common/msg.jsp").forward(request, response);
	}

}
