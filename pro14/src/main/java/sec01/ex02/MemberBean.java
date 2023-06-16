package sec01.ex02;

import java.util.Date;

public class MemberBean {
	// java Bean class
	// 1. 속성 필드가 모두 private
	// 2. setter / getter()
	// 3. 인자(매개변수)가 없는 기본생성자 필요 - useBean 액션 태그 사용 위해서
	
	private String id;
	private String pwd;
	private String name;
	private String email;
	private Date joinDate;
	private Address addr;
	// 주소 정보를 저장하는 address 클래스 타입 속성 선언
	// hash-a 관계 : 한 객체가 다른 객체를 포함하거나 참조하는 관계
	// 객체 가느이 구성을 표현 혹은 객체가 다른 객체를 사용하는 경우
	
	
	
	public MemberBean() {
		super();
	}
	
	
	public MemberBean(String id, String pwd, String name, String email) {
		super();
		this.id = id;
		this.pwd = pwd;
		this.name = name;
		this.email = email;
	}


	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Date getJoinDate() {
		return joinDate;
	}
	public void setJoinDate(Date joinDate) {
		this.joinDate = joinDate;
	}
	public Address getAddr() {
		return addr;
	}
	public void setAddr(Address addr) {
		this.addr = addr;
	}


	@Override
	public String toString() {
		return "MemberBean [id=" + id + ", pwd=" + pwd + ", name=" + name + ", email=" + email + ", joinDate="
				+ joinDate + ", addr=" + addr + "]";
	}
	
	
	
	
}
