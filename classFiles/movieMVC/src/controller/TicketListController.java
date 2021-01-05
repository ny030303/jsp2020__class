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
import javax.servlet.http.HttpSession;

import movie.MemberVO;
import movie.MovieDAO;
import movie.MovieVO;
import movie.TicketVO;

@WebServlet("/TicketList.do")
public class TicketListController extends HttpServlet {
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
		String getType = req.getParameter("type");
		HttpSession httpSession = req.getSession(true);
		MemberVO user = (MemberVO) httpSession.getAttribute("USER");
		
		if(getType.equals("admin")) {
			doAllTicketList(req,resp);
		} else {
			doMemberTicketList(req,resp);
		}
	}
	
	public void doAllTicketList(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		MovieDAO instance = MovieDAO.getInstance();
		ArrayList<TicketVO> list = instance.selectAllTicket();
	    
		req.setAttribute("ticketList", list);
	
	    RequestDispatcher rd = req.getRequestDispatcher("adminPage.jsp");
	    rd.forward(req, resp);
	}
	
	public void doMemberTicketList(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		MovieDAO instance = MovieDAO.getInstance();
		ArrayList<TicketVO> list = instance.selectMemberTicket(req);
		
		req.setAttribute("ticketList", list);
		
	    RequestDispatcher rd = req.getRequestDispatcher("memberTicket.jsp");
	    rd.forward(req, resp);
	}
}
