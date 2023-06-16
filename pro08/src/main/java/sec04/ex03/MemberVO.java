package sec04.ex03;
import java.util.Date;

public class MemberVO {
		
		private String id;
		private String pwd;
		private String name;
		private String email;
		private Date joindate;
		
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
			return joindate;
		}
		public void setJoinDate(Date joindate) {
			this.joindate = joindate;
		}
		
		@Override // alt + shift + s > generate toString
		public String toString() {
			return "MemberVo [id=" + id + ", pwd=" + pwd + ", name=" + name + ", email=" + email + ", joindate=" + joindate
					+ "]";
		}
		
		
		
		
		
	}


