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
import movie.ScheduleVO;

@WebServlet("/MovieSchedule.do")
public class MovieScheduleController extends HttpServlet {
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
		
		int movieNo = Integer.parseInt(req.getParameter("movieNo"));
		
		
		MovieDAO instance = MovieDAO.getInstance();
		
		ArrayList<ScheduleVO> scheduleList = instance.selectAMovieSchedule(movieNo);
		if(scheduleList.size() > 0) {
			req.setAttribute("scheduleList", scheduleList);
			//for(ScheduleVO sch : scheduleList) {
			//		System.out.println(sch.toString());
			//}
			RequestDispatcher rd = req.getRequestDispatcher("selectMovieSchedule.jsp");
		    rd.forward(req, resp);
		} else {
			resp.setContentType("text/html; charset=UTF-8");
		    PrintWriter writer = resp.getWriter();
		    writer.println("<script>alert('해당 영화는 상영 시간이 없습니다. 다른 영화를 선택해주시기 바랍니다.'); history.go(-1);</script>");
		}
		
	}
}
