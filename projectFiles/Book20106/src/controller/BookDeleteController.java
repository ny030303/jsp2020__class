package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import book.BookDAO;
import book.BookVO;

import java.sql.*;

// * 기존 파일 경로" http://localhost:8090/프로젝트명/...(파일경로) " 를
// URL 매핑을 통해 http://localhost:8090/프로젝트명/URL명 으로 간단하게 변경 해줌.
// ==> 보안에 노출되어 있는 것을 막을 수 있음.

//즉 @WebServlet("/URL")의 URL 주소로 접속하면 톰켓 서버의 컨테이너가 매핑된 서블릿을 찾아 실행해줌.
@WebServlet("/BookDelete.do")
public class BookDeleteController extends HttpServlet {
	
//	클라이언트가 request한 어떤 정보를 가져오는 방식 중 GET방식으로 정보를 전달했을 시에 이를 처리하는 메소드
//	GET방식: URL 값으로 정보가 전송됨
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doProcess(req, resp);
	}
//	클라이언트가 request한 어떤 정보를 가져오는 방식 중 POST방식으로 정보를 전달했을 시에 이를 처리하는 메소드
//	POST방식: header를 이용해 정보가 전송됨
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doProcess(req, resp);
	}
	
//	Get이나 Post로 오는 것 둘다 처리할 것은 같으므로 한 곳에서 작업하기 위해 
//	doProcess(req, resp) 에 request 값과 response 값을 파라미터 값으로 보내줌.
	
	// 실제 BookDeleteController에서 Book을 delete하기 위한 작업을 하는 메소드
	public void doProcess(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		Request utf-8 인코딩 작업
		req.setCharacterEncoding("UTF-8");
		
		// request 파라미터 가져와서 타입에 맞게 저장
		int bcode = Integer.parseInt(req.getParameter("bcode"));
		// 인스턴스 가져옴.
		BookDAO instance = BookDAO.getInstance();
//		instance 의 deleteBook(bcode) 메소드를 이용해서 실제 DB에 delete 기능 구현.
		int cnt = instance.deleteBook(bcode);
		
//		한글 문자가 깨지므로 setContentType을 사용하여 charset을 UTF-8으로 바꿔줌.
		resp.setContentType("text/html; charset=UTF-8");
//		출력스트림을 얻어옴.
		PrintWriter writer = resp.getWriter();
//		cnt에 따라 다른 alert 창 띄우고 BookList.do 로 이동
		if(cnt > 0) {writer.println("<script>alert('도서 삭제 완료'); location.href='BookList.do'</script>");}
		else {writer.println("<script>alert('도서 삭제 실패'); location.href='BookList.do'</script>");}
//		PrintWriter 객체 writer close 하기
		writer.close();
	}
}
