package sec01.ex01;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/first")
public class FirstServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter(); // 클라이언트에게 문자 데이터를 보낼때 프린트 객체를 반환해주고 이걸 사용해라.
		// 출력하는 메서드 getWriter는 response타입에 있다 그리고 사용자에게 보내는 데이터 타입을 PrintWriter로 해준다. -> 반환되는 타입이 PrintWriter이기 때문이다. 그래서 받아줄 때 PrintWriter타입으로 받아주는 것.
		
		// sendRedirect 전
		out.print("<h1>First Servlet 입니다.</h1>");
		
		// sendRedirect 후
		response.sendRedirect("second"); // /second 서블릿으로 요청해라 -> 브라우저한테 요청
		out.print("<h1>First Servlet 입니다.</h1>");
		
		
	}

}
