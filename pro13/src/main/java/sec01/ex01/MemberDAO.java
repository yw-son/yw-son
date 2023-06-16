package sec01.ex01;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

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
			ds = (DataSource) context.lookup("java:/comp/env/jdbc/JNDI_MYSQL"); // context.xml에 resourse에 설정해둔 name과 매칭된
																				// 객체를 생성하고 반환한다.

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 회원 등록 (C)
	// MemberBean 객체에 저장된 회원 정보가 전달된다.
	public void addMember(MemberBean memberBean) {
		// db 커넥션 연결
		try {
			conn = ds.getConnection();
			
			// memberBean 속성에 저장된 값 가져오기 - getxxx() 메서드 사용
			String id = memberBean.getId();
			String pwd = memberBean.getPwd();
			String name = memberBean.getName();
			String email = memberBean.getEmail();
			
			
			
			// 쿼리문 작성
			String sql = "insert into t_member";
			sql += "(id, pwd, name, email)";
			sql += "values(?, ?, ?, ?)";
			
			
			// slq 전송 위한 객체 셍성
			pstmt = conn.prepareStatement(sql);
			
			// sql 전송 전 ? 값 성정
			// 준비된 쿼리의 매개변수를 설정(?)
			//  1 : 첫번째 매개변수에 설정
			pstmt.setString(1, id);
			pstmt.setString(2, pwd);
			pstmt.setString(3, name);
			pstmt.setString(4, email);
			
			// 쿼리 실행
			int count = pstmt.executeUpdate();
			System.out.println(count + "건 데이터 처리 성공");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			close();
		}

	}

	// 전체 회원 조회 (R)
	public List<MemberBean> listMembers() {

		List<MemberBean> list = new ArrayList<>();

		try {
			// DB 커넥션 연결
			conn = ds.getConnection();
			
			// 최근 가입일 순으로 회원 조회
			String sql = "select * from t_member order by joinDate desc";
			
			// 쿼리 전송 위한 객체 생성
			pstmt = conn.prepareStatement(sql);
			
			// 쿼리 전송 후 결과 받을 객체(rs)에 저장 - db에 있는 회원 테이블 정보
			rs = pstmt.executeQuery();
			
			while(rs.next()) { // 결과를 셍성할 때 처음과 마지막은 default값으로 만들기 때문에 rs.next()를 써줘야 데이터 조회가 가능하다(while의 조건이 true이니까 실행)
				// 조회한 회원 정보를 MemberBean 객체의 속성에 저장
				// set - 값 저장 / get - 값 읽는다
				MemberBean vo = new MemberBean();
				vo.setId(rs.getString("id"));
				vo.setPwd(rs.getString("pwd"));
				vo.setName(rs.getString("name"));
				vo.setEmail(rs.getString("email"));
				vo.setJoinDate(rs.getDate("joinDate"));
				
				list.add(vo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				 rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			close();
		}
		return list; // 입력받을 객체를 생성했으니 리턴으로 값들을 리턴해준다.
	}

	private void close() {
		try {
			// rs.close();
			pstmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}