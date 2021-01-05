package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import movie.MovieDAO;
import movie.MovieVO;

@WebServlet("/MovieList.do")
public class MovieListController extends HttpServlet {
// 방명록 목록을 보여주는 서블릿
	// get, post 로도 서비스 해줘야 함
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
		
		int category = Integer.parseInt(req.getParameter("category"));
		MovieDAO instance = MovieDAO.getInstance();
	    ArrayList<MovieVO> list = instance.selectMovieList(category);
	    // 공유 - 세션 
	    req.setAttribute("list", list);
	    RequestDispatcher rd = req.getRequestDispatcher("selectMovie.jsp");
	    rd.forward(req, resp);
	}
	
}
