package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.BoardDAO;
import board.BoardVO;
@WebServlet("/BoardUpdate.do")
public class BoardUpdateController extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doProcess(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doProcess(req, resp);
	}
	
	public void doProcess(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 한글 인코딩 처리
		req.setCharacterEncoding("UTF-8");
		// 파라미터 가져오기
		int boardNum = Integer.parseInt(req.getParameter("boardNum"));
		String writeDate = req.getParameter("writeDate");
		String name = req.getParameter("name");
		String pass = req.getParameter("pass");
		String title = req.getParameter("title");
		String content = req.getParameter("content");
		// vo객체 생성 -> 담기 -> DAO에서 메서드 처리
		BoardDAO instance = BoardDAO.getInstance();
//		int boardNum = instance.getBoardNum();
//		String writeDate = instance.getDate();
		BoardVO vo = new BoardVO(boardNum, name, pass, title, content, writeDate);
		instance.updateBoard(vo);
		// 화면에 출력 -> jsp의 역할 (view역할 -> view 페이지로)
		RequestDispatcher rd = req.getRequestDispatcher("selectBoard.jsp");
		rd.forward(req, resp);
	}
}
