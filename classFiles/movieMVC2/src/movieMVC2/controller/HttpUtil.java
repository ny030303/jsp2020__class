package movieMVC2.controller;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class HttpUtil {
	
	public static void forword(HttpServletRequest req, HttpServletResponse res, String path) {
//	"index.jsp"
		try {
			RequestDispatcher dis = req.getRequestDispatcher(path);
			dis.forward(req, res);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
 // RequestDispatcher dis = request.dis
	// dis.foword(req,res)
}
