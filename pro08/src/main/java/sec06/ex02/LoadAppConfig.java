package sec06.ex02;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "loadAppConfig", urlPatterns= {"/loadConfig"}, loadOnStartup = 1)
public class LoadAppConfig extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private ServletContext context;
	
	public void init(ServletConfig config) throws ServletException {
		System.out.println("loadAppConfig의 init 메서드 호출");
		context = config.getServletContext();
		String menu_member = context.getInitParameter("menu_member");
		String menu_order = context.getInitParameter("menu_order");
		String menu_goods = context.getInitParameter("menu_goods");
		
		context.setAttribute(menu_member, menu_member);
		context.setAttribute(menu_goods, menu_goods);
		context.setAttribute(menu_order, menu_order);
	}
	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		
		String menu_member = context.getInitParameter("menu_member");
		String menu_order = context.getInitParameter("menu_order");
		String menu_goods = context.getInitParameter("menu_goods");
		
		out.print("<h2>menu_member : " + menu_member + "</h2><br>");
		out.print("<h2>menu_order : " + menu_order + "</h2><br>");
		out.print("<h2>menu_goods : " + menu_goods + "</h2><br>");
		
		
		
	}


}
