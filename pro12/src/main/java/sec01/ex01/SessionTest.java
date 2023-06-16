package sec01.ex01;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/sess")
public class SessionTest extends HttpServlet {
   private static final long serialVersionUID = 1L;

   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      response.setContentType("text/html; charset=utf-8");
      PrintWriter pw = response.getWriter();
      
      HttpSession session = request.getSession(); // 서블릿은 객체 생성해서 써야함. <-> JSP
      session.setAttribute("name", "이순신");
      
      pw.print("<h1>세션에 이름을 바인딩합니다.</h1>");
      pw.print("<a href='/pro12/test01/session.jsp'>첫번째 페이지로 이동하기</a>");
   }

}