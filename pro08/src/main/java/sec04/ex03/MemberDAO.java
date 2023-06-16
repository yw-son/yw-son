package sec04.ex03;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
//import java.sql.Exception;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

// 로직을 처리하는 DAO 클래스
public class MemberDAO {
	// 필드
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null; // 조회 후 결과를 담을 객체
	private DataSource ds = null; // 책에선 dataFactory라는 변수명
	
	// 생성자
	public MemberDAO() {
		try {
			Context context = new InitialContext();
			ds = (DataSource) context.lookup("java:/comp/env/jdbc/JNDI_MYSQL"); // context.xml에 resourse에 설정해둔 name과 매칭된 객체를 생성하고 반환한다.
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private String USER_LIST = "SELECT * FROM T_MEMBER";
	
	public List<MemberVO> listMembers() { 
		List<MemberVO> userList = new ArrayList<>(); 
		try {
			// JDBC 드라이버 로딩 및 커넥션 연결
			conn = ds.getConnection(); // 미리  context.xml > <Resource>에 설정해두고 DB와 연결해서 객체를 생성하고 커넥션풀에있는 name에 설정해둔
			// 경로로 찾아가서 해당 경로를 담은 객체를 생성해서 ds에 담고 그 커넥션 객체를 반환.
			
			// 2. Statement 생성
			pstmt = conn.prepareStatement(USER_LIST); 
			
			rs = pstmt.executeQuery();
			
			System.out.println("[USER 목록]");
			
			
			while(rs.next()) { 
				MemberVO user = new MemberVO(); 
				user.setId(rs.getString("ID")); 
				user.setPwd(rs.getString("PWD")); 
				user.setName(rs.getString("NAME")); 
				user.setEmail(rs.getString("EMAIL"));
				user.setJoinDate(rs.getTimestamp("JOINDATE")); 
				userList.add(user); 
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		//finally {
//			ds.close(rs, pstmt, conn ); // 만들어둔 JDBCUtil 클래스에 메서드를 만들어 두었다.
//		} 
		return userList; // return 타입을 리스트로 만들었기 때문에 리스트를 반환한다.
		
	}
}
