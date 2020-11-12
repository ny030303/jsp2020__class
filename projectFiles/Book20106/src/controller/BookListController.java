package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import book.BookDAO;
import book.BookVO;

//* 기존 파일 경로" http://localhost:8090/프로젝트명/...(파일경로) " 를
//URL 매핑을 통해 http://localhost:8090/프로젝트명/URL명 으로 간단하게 변경 해줌.
//==> 보안에 노출되어 있는 것을 막을 수 있음.

//즉 @WebServlet("/URL")의 URL 주소로 접속하면 톰켓 서버의 컨테이너가 매핑된 서블릿을 찾아 실행해줌.
@WebServlet("/BookList.do")
public class BookListController extends HttpServlet {
//	클라이언트가 request한 어떤 정보를 가져오는 방식 중 POST방식으로 정보를 전달했을 시에 이를 처리하는 메소드
//	POST방식: header를 이용해 정보가 전송됨
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doProcess(req, resp);
	}
//	클라이언트가 request한 어떤 정보를 가져오는 방식 중 GET방식으로 정보를 전달했을 시에 이를 처리하는 메소드
//	GET방식: URL 값으로 정보가 전송됨
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doProcess(req, resp);
	}
	
//	Get이나 Post로 오는 것 둘다 처리할 것은 같으므로 한 곳에서 작업하기 위해 
//	doProcess(req, resp) 에 request 값과 response 값을 파라미터 값으로 보내줌.
	
	// 실제 BookListController에서 전체 Book을 가져오기 위한 작업을 하는 메소드
	public void doProcess(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		Request utf-8 인코딩 작업
		req.setCharacterEncoding("UTF-8");
		
		// 인스턴스 가져옴.
		BookDAO instance = BookDAO.getInstance();
//		list에 book 정보 옮겨주기
	    ArrayList<BookVO> list = instance.selectAllBooks();
//	    request 의 attribute에 list set하기
	    req.setAttribute("list", list);
//	    getRequestDispatcher()가 실행되는 순간 서버단에서 모든 지정된 페이지를 읽어들여서 리다이렉트 작업을 함.
//	    request, response 를 리다이렉트 시키는 페이지에 전달해서 사용할 수 있음.
	    RequestDispatcher rd = req.getRequestDispatcher("selectBook.jsp");
	    rd.forward(req, resp);
	}
	
}