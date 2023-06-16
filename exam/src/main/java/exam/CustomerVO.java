package exam;

public class CustomerVO {
	private int id;
    private String name;
    private String addr;
    private String phone;
    
    @Override
	public String toString() {
		return "CustomerVO [id=" + id + ", name=" + name + ", addr=" + addr + ", phone=" + phone + ", email=" + email
				+ "]";
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddr() {
		return addr;
	}
	public void setAddr(String addr) {
		this.addr = addr;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	private String email;
}
