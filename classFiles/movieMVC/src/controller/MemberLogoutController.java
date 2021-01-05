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

@WebServlet("/MemberLogout.do")
public class MemberLogoutController extends HttpServlet {
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
		
		MovieDAO instance = MovieDAO.getInstance();
	    instance.logoutMember(req);
	    
	    resp.setContentType("text/html; charset=UTF-8");
	    PrintWriter writer = resp.getWriter();
	     
	    writer.println("<script>alert('로그아웃 했습니다.'); location.href='index.jsp'</script>"); 
	    	 
		writer.close();
	}
}
