package dao;

import java.sql.*;
import java.util.*;

import model.Customer;
import util.DbConnector;

public class CustomerDao {

    public void saveCustomer(Customer c) {

        String sql = "INSERT INTO customer(name, email, phone, address) VALUES (?, ?, ?, ?)";
        String email = c.getEmail();
     if (email == null || email.trim().isEmpty()) {
         throw new IllegalArgumentException("Email cannot be empty");
     }
     
     String name = c.getName();
     if (name == null || name.trim().isEmpty() ){
         throw new IllegalArgumentException("Name cannot be empty");
     }
     if (!name.matches("[A-Za-z ]+")) {
    	    throw new IllegalArgumentException("Name must contain only letters");
    	}
     
     String phone=c.getPhone();
     if(phone==null || phone.trim().isEmpty()) throw new IllegalArgumentException("Contact Number cannot be empty");
     if(!phone.matches("\\d{10}")) throw new IllegalArgumentException("Invalid Entry");
     
     String addr = c.getAddress();
     if (addr == null || addr.trim().isEmpty()) {
         throw new IllegalArgumentException("Address cannot be empty");
     }
     
        try (Connection con = DbConnector.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, c.getName());
            ps.setString(2, c.getEmail());
            ps.setString(3,c.getPhone());
            ps.setString(4, c.getAddress());
            ps.executeUpdate();

        } catch (Exception e) { 
            e.printStackTrace();
        }
    }
    public Customer getCustomerById(int id) {

        Customer c = null;
        String sql = "SELECT * FROM customer WHERE id = ?";

        try (Connection con = DbConnector.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                c = new Customer();
                c.setId(rs.getInt("id"));
                c.setName(rs.getString("name"));
                c.setEmail(rs.getString("email"));
                c.setPhone(rs.getString("phone"));
                c.setAddress(rs.getString("address"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return c;
    }
    public Customer updateCustomer(Customer c)
    {
    	String sql = "UPDATE Customer SET name=?, email=?, phone=?, address=? WHERE id = ?";
    	
    	try (Connection con = DbConnector.getConnection();
                PreparedStatement ps = con.prepareStatement(sql)) {

               ps.setString(1,c.getName());
               ps.setString(2,c.getEmail());
               ps.setString(3,c.getPhone());
               ps.setString(4,c.getAddress());
               ps.setInt(5, c.getId()); 
               ps.executeUpdate();
               

           } catch (Exception e) {
               e.printStackTrace();
           }
    	return c;
    }

    public void deleteCustomer(int id)
    {
    	String sql="DELETE FROM Customer WHERE id = ?";
    	
    	try(Connection con=DbConnector.getConnection();
    			PreparedStatement ps = con.prepareStatement(sql)){
    		ps.setInt(1, id);
            ps.executeUpdate();
    	}
    	catch(Exception e)
    	{
    		e.printStackTrace();
    	}
    }
    public List<Customer> getAllCustomers() {

        List<Customer> list = new ArrayList<>();
        String sql = "SELECT * FROM Customer";

        try (Connection con = DbConnector.getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
               Customer c = new Customer();
                c.setId(rs.getInt("id"));
                c.setName(rs.getString("name"));
                c.setEmail(rs.getString("email"));
                c.setPhone(rs.getString("phone"));
                c.setAddress(rs.getString("address"));
                list.add(c);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}
