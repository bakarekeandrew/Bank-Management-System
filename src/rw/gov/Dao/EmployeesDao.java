/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rw.gov.Dao;


import rw.gov.Model.Employee;
import java.sql.*;
import java.util.*;
import rw.gov.Model.Customer;


/**
 *
 * @author andre
 */
public class EmployeesDao {
    
    String jdbcUrl = "jdbc:postgresql://localhost:5432/Bank_management_system_db";
    String username = "postgres";
    String password = "123";
    
    public void addEmployee(Employee objEmployee){
    
           
       try{
        Connection con = DriverManager.getConnection(jdbcUrl, username, password);
        //step2: prepare statement
        String sql = "insert into employees(employee_id, first_name, last_name, position, email, phone_number) values (?, ?, ?, ?, ?, ?)";
        
        PreparedStatement pst = con.prepareStatement(sql);
        pst.setInt(1, objEmployee.getEmployee_id());
        pst.setString(2, objEmployee.getFirst_name());
        pst.setString(3, objEmployee.getLast_name());
        pst.setString(4, objEmployee.getPosition());
        pst.setString(5, objEmployee.getEmail());
        pst.setString(6, objEmployee.getPhone_number());


        
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
    
    public void UpdateEmployee(Employee objEmployee){
   
        try{
          Connection con = DriverManager.getConnection(jdbcUrl, username, password);
         
        //step2: prepare statement
          String sql = "update table employees set first_name =?, last_name =?, position=?, email=?, phone_number=? where employee_id = ?" ;
        
          PreparedStatement pst = con.prepareStatement(sql);
          
          pst.setString(1, objEmployee.getFirst_name());
          pst.setString(2, objEmployee.getLast_name());
          pst.setString(3, objEmployee.getPosition());
          pst.setString(4, objEmployee.getEmail());
          pst.setString(5, objEmployee.getPhone_number());
          pst.setInt(6, objEmployee.getEmployee_id());


        
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
    
    public void retrieveEmployee(Employee objEmployee){
        
        try{
        Connection con = DriverManager.getConnection(jdbcUrl, username, password);
        //step2: prepare statement
        String sql = "select * from employees";
        
        PreparedStatement pst = con.prepareStatement(sql);
        ResultSet rs = pst.executeQuery();
        int counter = 0;
        while(rs.next()){
        counter ++;
//             counter +=1;
//             counter = counter +1;
        System.out.println("Customers "+counter);
        System.out.println("-----------");
        System.out.println("Employee ID   : "+rs.getInt("employee_id"));
        System.out.println("Employee First Name: "+rs.getString("first_name"));
        System.out.println("Employee Last Name: "+rs.getString("last_name"));
        System.out.println("Employee position: "+rs.getString("position"));
        System.out.println("Employee Email: "+rs.getString("email"));
        System.out.println("Employee Phone number: "+rs.getString("phone_number"));
        
        


        System.out.println("------------\n");
                            
             }
            //close connection
         con.close();
         if(counter == 0){
             System.out.println("No Employee saved");
         }
         else{
             System.out.println("!!!!!!end!!!!!!!!");
         }
          
      
        }
        catch(Exception ex){
          ex.printStackTrace();
        }
    
       
    }
    
    public void deleteEmployee(Employee objEmployee){
    
    
        try{
          
         Connection con  = DriverManager.getConnection(jdbcUrl, username, password);
         String sql = "delete from employees where employee_id =  ? ";
         PreparedStatement pst = con.prepareStatement(sql);
      
         pst.setInt(1, objEmployee.getEmployee_id());
      
      
         int rowAffected = pst.executeUpdate();
      
         if(rowAffected > 0){
            System.out.println("Employee deleted successful!");
         }
         else{
            System.out.println("Employee not deleted");
         }
      }
      
      catch(Exception ex){
        ex.printStackTrace();
      }  
    } 
    
     
}
