package sec03.ex01;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/forwardone")
public class FirstForwardServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=utf-8");
		
		PrintWriter out = response.getWriter();
		out.println("<h1>FirstForwardServlet입니다.</h1>");
		
		// dispatcher 사용하기 위한 RequestDispatcher 객체 생성
		// 매개변수로 패스를 받고 있다. -> 이 패스에 관한 정보를 담고있는 객체를 생성한다.
		RequestDispatcher dispatch = request.getRequestDispatcher("forwardtwo"); 
//		dispatch.forward(request, response); // request, response객체를 받고있다.
		dispatch.include(request, response);
		// request -> 브라우저가 firstservlet에게 보낸 요청이 담겨져있다.
		// reponse ->  second 서블릿이 보낸 응답이 담겨져있다.
		out.println("<h1>FirstForwardServlet입니다.</h1>");
	}

}
