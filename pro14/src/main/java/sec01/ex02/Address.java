package sec01.ex02;

public class Address {
	private String city; // 거주 도시
	private String zipcode; // 우편 번호
	
	
	public String getCity() {
		return city;
	}


	public void setCity(String city) {
		this.city = city;
	}


	public String getZipcode() {
		return zipcode;
	}


	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}


	public Address(String city, String zipcode) {
		super();
	}


	public Address() {
		super();
	}
	
	
}
