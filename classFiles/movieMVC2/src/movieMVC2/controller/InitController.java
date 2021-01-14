package movieMVC2.controller;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class InitController extends HttpServlet {
	
	String charset = null;
	HashMap<String, Controller> list = null;
	
	@Override
	public void init(ServletConfig sc) throws ServletException {
		// override : 상속받은 자식클래스에서 부모클래스에 있는 메서드를 제정의 할려고 만든것
		// over : 메서드 이름은 똑같고 매개 변수 인자값이 다르게 해주는것
		charset = sc.getInitParameter("charset"); // utf-8
		list = new HashMap<String, Controller>();
		list.put("/index.do", new IndexController());
		list.put("/MovieList.do", new MovieListController());
	}
	
	@Override
	protected void service(HttpServletRequest req, 
			HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding(charset);
//		http://localhost:8070/movieMVC2/index.jsp
//		http;//localhost:포트번호/contextPath/서블렛어노테이션
		String url = req.getRequestURI();
		String contextPath = req.getContextPath();
		String path = url.substring(contextPath.length());
		System.out.println("path=" + path);
		
		Controller controller = list.get(path);
		controller.execute(req, resp);
	}
}
