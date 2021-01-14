package movieMVC2.model;

import java.util.ArrayList;

public class MovieService {
	private static MovieService instance = new MovieService();
	
	private MovieService() {
		
	}
	public static MovieService getInstance() {
		return instance;
	}
	
	MovieDAO dao = MovieDAO.getInstance();
	
//	public ArrayList<ScheduleVO> getOneMovie(int movieNo) {
//		return dao.getOneMovie(movieNo);
//	}
//	
//	public ArrayList<MovieVO> getMovieList(int category) {
//		return dao.getMovieList(category);
//	}
	
	public int[] chooseSeat(int schNo) {
		return dao.chooseSeat(schNo);
	}
	
}
