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

@WebServlet("/cset")
public class SetServletContext extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		
		// ServletContext 사용 위해 객체 생성
		// HttpServlet을 상속받는데 이 HttpServlet은 GenericServlet을 상속받고 있다. 
		// 그래서 상속받고 있어서 객체 생성없이(new X) 오버라이딩 해서 쓰는 것임.
		ServletContext context = getServletContext(); 
		
		List member = new ArrayList(); // 인덱스번호를 사용한다는 특징이있다.
		member.add("이순신");
		member.add(30);
		context.setAttribute("member", member);
		out.print("<h1>이순신과 30 설정</h1>");
		
		
		
		
	}

}













