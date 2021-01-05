package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import movie.MovieDAO;
import movie.MovieVO;

@WebServlet("/TicketInsert.do")
public class TicketInsertController extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doProcess(req, resp);
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doProcess(req, resp);
	}
	
	public void doProcess(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		
		int schNo = Integer.parseInt(req.getParameter("schNo"));
		int seatNo = Integer.parseInt(req.getParameter("seatNo"));
		
		MovieDAO instance = MovieDAO.getInstance();
	    boolean result = instance.insertTicket(req, schNo, seatNo);
	    
	    
	    resp.setContentType("text/html; charset=UTF-8");
	    PrintWriter writer = resp.getWriter();
	    if(result) {
	    	seatNo = seatNo +1;
	    	writer.println("<script>alert('"+ seatNo + "번 좌석 예매 완료했습니다.'); location.href='index.jsp'</script>"); 
	    } else {
	    	writer.println("<script>alert('좌석 예매 실패했습니다.'); location.href='index.jsp'</script>"); 
	    }
	    
	    	 
		writer.close();
	}
}
