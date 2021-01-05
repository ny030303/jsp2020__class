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

@WebServlet("/MemberLogin.do")
public class MemberLoginController extends HttpServlet {
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
		
		String id = req.getParameter("id");
		String pw = req.getParameter("pw");
		
		MovieDAO instance = MovieDAO.getInstance();
	    boolean result = instance.loginMember(req, id, pw);
	    
	    resp.setContentType("text/html; charset=UTF-8");
	    PrintWriter writer = resp.getWriter();
	    if(result) {
	    	if(id.equals("admin") && pw.equals("admin")) {
	    		writer.println("<script>alert('어서오세요. 관리자님'); location.href='TicketList.do?type=admin'</script>"); 
	    	} else {
	    		writer.println("<script>alert('어서오세요. "+ id +"님'); location.href='index.jsp'</script>");
	    	}
	    } else {writer.println("<script>alert('로그인 실패'); location.href='index.jsp'</script>");}
//		PrintWriter 객체 writer close 하기
		writer.close();
	}
}
