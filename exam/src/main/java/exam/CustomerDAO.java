package exam;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CustomerDAO {
    private Connection connection;

    public CustomerDAO(Connection connection) {
        this.connection = connection;
    }

    public List<CustomerVO> getAllCustomers() {
        List<CustomerVO> customers = new ArrayList<>();
        String selectQuery = "SELECT * FROM customer";
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(selectQuery)) {
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String addr = resultSet.getString("addr");
                String phone = resultSet.getString("phone");
                String email = resultSet.getString("email");
                CustomerVO customer = new CustomerVO();
                customer.setId(id);
                customer.setName(name);
                customer.setAddr(addr);
                customer.setPhone(phone);
                customer.setEmail(email);
                customers.add(customer);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customers;
    }

    public int createCustomer(CustomerVO customer) {
        int generatedId = 0;
        String insertQuery = "INSERT INTO customer (name, addr, phone, email) VALUES (?, ?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(insertQuery, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, customer.getName());
            preparedStatement.setString(2, customer.getAddr());
            preparedStatement.setString(3, customer.getPhone());
            preparedStatement.setString(4, customer.getEmail());
            preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                generatedId = resultSet.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return generatedId;
    }

    public boolean updateCustomer(CustomerVO customer) {
        String updateQuery = "UPDATE customer SET name=?, addr=?, phone=?, email=? WHERE id=?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(updateQuery)) {
            preparedStatement.setString(1, customer.getName());
            preparedStatement.setString(2, customer.getAddr());
            preparedStatement.setString(3, customer.getPhone());
            preparedStatement.setString(4, customer.getEmail());
            preparedStatement.setInt(5, customer.getId());
            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean deleteCustomer(int customerId) {
        String deleteQuery = "DELETE FROM customer WHERE id=?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(deleteQuery)) {
            preparedStatement.setInt(1, customerId);
            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public CustomerVO getCustomerById(int customerId) {
        String selectQuery = "SELECT * FROM customer WHERE id=?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(selectQuery)) {
            preparedStatement.setInt(1, customerId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String addr = resultSet.getString("addr");
                String phone = resultSet.getString("phone");
                String email = resultSet.getString("email");
                CustomerVO customer = new CustomerVO();
                customer.setId(id);
                customer.setName(name);
                customer.setAddr(addr);
                customer.setPhone(phone);
                customer.setEmail(email);
                return customer;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}