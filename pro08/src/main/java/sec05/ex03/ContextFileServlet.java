package sec05.ex03;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/cfile")
public class ContextFileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		
		PrintWriter out = response.getWriter();
		
		ServletContext context = getServletContext();
		
		// 파일을 읽기 위한 InputStream 생성
		InputStream is = context.getResourceAsStream("/WEB-INF/bin/init.txt"); // 해당 경로에 있는 파일과 연결하는 InputStream 객체생성후 반환
		BufferedReader buffer = new BufferedReader(new InputStreamReader(is)); // byte단위로 생성해서 문자단위로 읽는다. 위에 생성해둔 객체를 BufferedReader에게 전달하고 있다.
		// -> 고로 바이트단위를 문자단위로 읽어내겠다는 것. // BufferedReader는 문자열로 한 줄씩 읽는 기능도 있다. 문자열의 파일의 끝은 null이다. (String 타입이라)
		
		String menu = null;
		String menu_member = null;
		String menu_order = null;
		String menu_goods = null;
		
		while((menu=buffer.readLine()) != null) { // -> 파일을 문자열단위로 읽다가 구분자를 만나면 출력을 하고 또 읽다가 null을 만나면(데이터가 없으면) 종료.
			// StringTokenizer - 문자열을 지정한 구분자로 나누어주는 클래스
			// 나누어진 문자열을 토큰이라고 부른다.
			StringTokenizer tokens = new StringTokenizer(menu, ","); 
			menu_member = tokens.nextToken(); 
			menu_order = tokens.nextToken();
			menu_goods = tokens.nextToken();
		}
		
		out.print(menu_member + "<br>");
		out.print(menu_order + "<br>");
		out.print(menu_goods  + "<br>");
	}

}
