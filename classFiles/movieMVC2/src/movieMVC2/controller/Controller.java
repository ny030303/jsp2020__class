package movieMVC2.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface Controller {
	// 추상메서드 -> {} 없는것, 상수밖에 올수 없다.
	public void execute (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
}
