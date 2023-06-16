//package sec02.ex01;
//
//import java.io.IOException;
//import java.io.PrintWriter;
//import java.util.List;
//
//import javax.servlet.RequestDispatcher;
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//
//@WebServlet("/member")
//public class MemberServlet extends HttpServlet {
//	private static final long serialVersionUID = 1L;
//
//
//	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		
//		doHandle(request,response);
//	}
//
//
//	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//
//		doHandle(request,response);
//	}
//	
//	private void doHandle(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException { // 여기서 로직을 정의하고 doget()이나 dopost()에서 호출
//		req.setCharacterEncoding("utf-8");
//		res.setContentType("text/html; charset=utf-8");
//		PrintWriter out = res.getWriter();
//		
//		MemberDAO dao = new MemberDAO();
//		List membersList = dao.listMembers();
//		req.setAttribute("membersList", membersList);
//		
//		RequestDispatcher dispatch = req.getRequestDispatcher("viewMembers");
//		dispatch.forward(req, res);
//	}
//
//}
