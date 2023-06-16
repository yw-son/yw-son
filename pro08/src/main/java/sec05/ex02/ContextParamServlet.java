package sec05.ex02;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/initMenu")
public class ContextParamServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		
		// web.xml에 설정한 context-param 사용하기
		ServletContext context = getServletContext(); //ServletContext의 객체를 반환해준다.
		String menu_member = context.getInitParameter("menu_member"); // name에 해당되는 매개변수의 초기화 값을 반환한다.
		// -> 컨테이너가 애플리케이션 서블릿 컨텍스트라는 공간을 만드는데 이때, web.xml에 정의된 내용들을 초기값으로 세팅한다.
		String menu_order = context.getInitParameter("menu_order");
		String menu_goods = context.getInitParameter("menu_goods");
		
		PrintWriter out = response.getWriter();
		out.print("<table border=1 cellspacing=0><tr>메뉴 이름</tr>");
		out.print("<tr><td>" + menu_member + "</td></tr>");
		out.print("<tr><td>" + menu_order + "</td></tr>");
		out.print("<tr><td>" + menu_goods + "</td></tr>");
		out.print("</tr></table>");
		
	}

}
