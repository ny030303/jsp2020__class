package movieMVC2.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class IndexController implements Controller {
	public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException ,IOException {
		HttpUtil.forword(req, resp, "index.jsp");
	};
	
	public void printHome() {
		System.out.println("index 화면");
	}
}
