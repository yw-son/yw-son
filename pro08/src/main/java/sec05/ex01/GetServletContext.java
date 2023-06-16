package sec05.ex01;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/cget")
public class GetServletContext extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		
		// ServletContext 사용 위해 객체 생성
		ServletContext context = getServletContext(); 
		// ArrayList 타입으로 다운 캐스팅 해줘야한다.
		// set에서 바인딩한 정보를 get으로 가져오겠다.
		List member = (ArrayList) context.getAttribute("member");
		String name = (String) member.get(0); // Object 객체라 다운캐스팅 해줘야함.
		int age = (Integer) member.get(1); // ArrayList라 인덱스 번호를 넣어준다.
		
		out.print(name);
		out.print(age);
	}

}
