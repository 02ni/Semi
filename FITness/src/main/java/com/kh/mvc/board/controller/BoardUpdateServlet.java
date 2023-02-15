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

@WebServlet(name = "boardUpdate", urlPatterns = { "/board/update" })
public class BoardUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public BoardUpdateServlet() {
    }

    @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	// 2. 본인이 쓴 글 맞는지 체크
    	// 1. 로그인 했는지 체크!
    	HttpSession session = request.getSession(false);
	    Member loginMember = (session == null) ? null : (Member) session.getAttribute("loginMember");
	
	    if(loginMember != null) {
	    	// 2.내 게시글 맞는지 체크
	    	Board board = new BoardService().getBoardByNo(Integer.parseInt(request.getParameter("no")));
	    	
	    	// 둘 중에 하나만 참이면 참! (null이 아니면서 로그인 아이디랑, 작성자 아이디가 같지 않으면 잘못된 접근)
	    	if (board != null && loginMember.getId().equals(board.getWriterId())) {
	    		request.setAttribute("board", board);
	    		// 이 구문 넣으면, update.jsp로 이동할것
	    		request.getRequestDispatcher("/views/board/update.jsp").forward(request, response);
	    	} else {
				request.setAttribute("msg", "잘못된 접근입니다.");
				request.setAttribute("location", "/board/list");
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
    	// 로그인 체크 코드
    	HttpSession session = request.getSession(false);
	    Member loginMember = (session == null) ? null : (Member) session.getAttribute("loginMember");
    	
	    if(loginMember != null) {
	    	// 파일이 저장될 경로
	    	String path = getServletContext().getRealPath("/resources/upload/board");
			
			// 파일 최대 사이즈 지정 (10 MB)
			int maxSize = 10485760;
			
			// 파일 인코딩 설정
			String encoding = "UTF-8";
			
			// DefaultFileRenamePolicy : 중복되는 파일 이름 뒤에 1 ~ 9999를 붙인다.
			// MultipartRequest mr = new MultipartRequest(request, path, maxSize, encoding, new DefaultFileRenamePolicy());
			MultipartRequest mr = new MultipartRequest(request, path, maxSize, encoding, new FileRename());
			
			// 파라미터 변조 방지를 위해 체크하는 구문
			if(loginMember.getId().equals(mr.getParameter("writer"))) {
				Board board = new Board();
				
				// no만 정수값으로 받아서 와보자
				board.setNo(Integer.parseInt(mr.getParameter("no")));
				board.setTitle(mr.getParameter("title"));
				board.setContent(mr.getParameter("content"));
				
				int result = new BoardService().save(board);
				
				if (result > 0) {
					request.setAttribute("msg"," 게시글 수정 성공 ");
					// 게시글 수정 후 상세보기로 넘어가기
					request.setAttribute("location","/board/view?no=" + board.getNo());
				} else {
					request.setAttribute("msg"," 게시글 수정 실패 ");
					// 수정 실패 시, 그 게시글 페이지가 다시 보이도록 하기
					request.setAttribute("location","/board/update?no=" + board.getNo());
				}
			} else {
				// 파라미터 접근하려고 하면, 잘못된 접근으로 나오도록
				request.setAttribute("msg", "잘못된 접근입니다.");
				request.setAttribute("location", "/board/list");
			}
	    } else {
			request.setAttribute("msg", "로그인 후 수정해 주세요.");
			request.setAttribute("location", "/");
		}

	    request.getRequestDispatcher("/views/common/msg.jsp").forward(request, response);
    }

}
