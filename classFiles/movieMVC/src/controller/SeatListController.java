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

@WebServlet("/SeatList.do")
public class SeatListController extends HttpServlet {
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
		MovieDAO instance = MovieDAO.getInstance();
		int[] seatInfo = instance.chooseSeat(schNo);
	    
	    req.setAttribute("seatInfo", seatInfo);
	    req.setAttribute("schNo", schNo);
	    RequestDispatcher rd = req.getRequestDispatcher("chooseSeat.jsp");
	    rd.forward(req, resp);
	}
	
//	public void buyTicket()
}
