package exam;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
   
        String url = "jdbc:mysql://localhost:3306/CUST_Member"; // 데이터베이스 URL
        String username = "root"; 
        String password = "1234"; 

        try (Connection connection = DriverManager.getConnection(url, username, password)) {
            
            CustomerDAO customerDAO = new CustomerDAO(connection);

            
            CustomerVO newCustomer = new CustomerVO();
            newCustomer.setName("김도겸");
            newCustomer.setAddr("대구 남구");
            newCustomer.setPhone("010-5638-8865");
            newCustomer.setEmail("dcd6548@naver.com");

            
            int generatedId = customerDAO.createCustomer(newCustomer);
            if (generatedId > 0) {
                System.out.println("고객 등록 성공! 생성된 ID: " + generatedId);
            } else {
                System.out.println("고객 등록 실패!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
