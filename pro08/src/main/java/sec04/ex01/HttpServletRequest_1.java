package sec04.ex01;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/hsr1")
public class HttpServletRequest_1 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		request.setAttribute("address", "서울시 성북구"); // 키=값 쌍을 만들어서 묶어서 request 객체에 저장.(바인딩) -> 재요청 보낼때 같이 보냄
		response.sendRedirect("hsr2"); 
		
	}

}
