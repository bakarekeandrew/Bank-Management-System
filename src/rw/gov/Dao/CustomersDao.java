/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rw.gov.Dao;

import java.util.*;
import java.sql.*;
import rw.gov.Model.Account;
import rw.gov.Model.Customer;

/**
 *
 * @author andre
 */
public class CustomersDao {
    
    String jdbcUrl = "jdbc:postgresql://localhost:5432/Bank_management_system_db";
    String username = "postgres";
    String password = "123";
    
    
    public void createAcount(Customer objCustomer){
      
       try{
        Connection con = DriverManager.getConnection(jdbcUrl, username, password);
        //step2: prepare statement
        String sql = "insert into customers(f_name, l_name, phone_number, address, gender) values (?, ?, ?, ?, ?)";
        
        PreparedStatement pst = con.prepareStatement(sql);
//        pst.setInt(1, objCustomer.getCustomer_id());
        pst.setString(1, objCustomer.getF_name());
        pst.setString(2, objCustomer.getL_name());
        pst.setString(3, objCustomer.getPhone_number());
        pst.setString(4, objCustomer.getAddress());
        pst.setString(5, objCustomer.getAddress());


        
        int rowAffected = pst.executeUpdate();
        if(rowAffected > 0){
            System.out.println("DATA SAVED");
            }
        else{
            System.out.println("data not saved");
            }
        
        con.close();
       
       }catch(Exception ex){
            ex.printStackTrace();
                    }
      }
    
    public void UpdateCustomer(Customer objCustomer){
    
         try{
        Connection con = DriverManager.getConnection(jdbcUrl, username, password);
        //step2: prepare statement
        String sql = "update table customers set f_name = ? l_name ?, phone_number, address where f_name = ?" ;
        
        PreparedStatement pst = con.prepareStatement(sql);
//        pst.setInt(1, objCustomer.getCustomer_id());
        pst.setString(1, objCustomer.getF_name());
        pst.setString(2, objCustomer.getL_name());
//        pst.setString(4, objCustomer.getEmail());
        pst.setString(3, objCustomer.getPhone_number());
        pst.setString(4, objCustomer.getAddress());
        pst.setString(5, objCustomer.getGender());

        
        int rowAffected = pst.executeUpdate();
        if(rowAffected > 0){
            System.out.println("Data Updated Successfully!!");
            }
        else{
            System.out.println("Data not Updated");
            }
        
        con.close();
       
       }catch(Exception ex){
            ex.printStackTrace();
                    
      }
    }
    
    public List<Customer> retrieveCustomers(){
        
        try{
        Connection con = DriverManager.getConnection(jdbcUrl, username, password);
        String sql = "select * from customers";
        PreparedStatement pst = con.prepareStatement(sql);
        List<Customer> customer = new ArrayList<>();
        ResultSet rs = pst.executeQuery();
        while(rs.next()){
//            Account account = new Account();
            Customer cust = new Customer();
            cust.setF_name(rs.getString(1));
            cust.setL_name(rs.getString(2));
            cust.setPhone_number(rs.getString(3));
            cust.setAddress(rs.getString(4));
            cust.setGender(rs.getString(5));
            
            customer.add(cust);
        }
        con.close();
        return customer;
        }catch(Exception ex){
            ex.printStackTrace();
            return null;
        }

    }
        
        
       
    public void deleteCustomer(Customer objCustomer){
    
    
        try{
          
         Connection con  = DriverManager.getConnection(jdbcUrl, username, password);
         String sql = "delete from cutomers where f_name =  ? ";
         PreparedStatement pst = con.prepareStatement(sql);
      
         pst.setString(1, objCustomer.getF_name());
      
      
         int rowAffected = pst.executeUpdate();
      
         if(rowAffected > 0){
            System.out.println("Customer deleted successful!");
         }
         else{
            System.out.println("Customer not deleted");
         }
      }
      
      catch(Exception ex){
        ex.printStackTrace();
      }  
    }  
}
