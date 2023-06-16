package sec04.ex01;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/hsr2")
public class HttpServletRequest_2 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		
		// 출력
		PrintWriter out = response.getWriter();
		String address = (String) request.getAttribute("address"); // -> 여기에 해당하는 키가 있다면, 그 키에 해당하는 값을 가져오고 없다면 null 값을 가져온다.
		// -> setAttribute() 에서 Object(최상위)타입으로 파라미터를 저장했기 때문에 (String)타입으로 다운캐스팅 해서 받아와야 한다.
		out.print("<h1>주소 : " + address + "</h1");
	}

}
