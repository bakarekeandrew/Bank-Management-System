/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rw.gov.Dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import rw.gov.Model.Admins;

/**
 *
 * @author andre
 */
public class AdminsDao {
    
    String jdbcUrl = "jdbc:postgresql://localhost:5432/Bank_management_system_db";
    String username = "postgres";
    String password = "123";
    
    
     public Integer recordAdmins(Admins objAdmin){
        Integer rowsAffected = null;
      try{
        Connection con = DriverManager.getConnection(jdbcUrl, username, password);
        //step2: prepare statement
        String sql = "insert into users(username, password, role) values (?, ?, ?)";
        
        PreparedStatement pst = con.prepareStatement(sql);
//        pst.setInt(1, objAccount.getAccount_id());
        pst.setString(1, objAdmin.getUsername());
        pst.setString(2, objAdmin.getPassword());
        pst.setString(3, objAdmin.getRole());
  
        rowsAffected = pst.executeUpdate();
        if(rowsAffected > 0){
            System.out.println("Admin DATA SAVED");
  
        }else{
            System.out.println("Account data not saved");
            }
        
        con.close();
       
       }catch(Exception ex){
            ex.printStackTrace();
                    }
        return rowsAffected;
      }
     
     
     
      public List<Admins> retrieveCustomers(){
        
        try{
        Connection con = DriverManager.getConnection(jdbcUrl, username, password);
        String sql = "select * from users";
        PreparedStatement pst = con.prepareStatement(sql);
        List<Admins> admin= new ArrayList<>();
        ResultSet rs = pst.executeQuery();
        while(rs.next()){
//            Account account = new Account();
            Admins adminuser = new Admins();
            adminuser.setUsername(rs.getString(1));
            adminuser.setPassword(rs.getString(2));
            adminuser.setRole(rs.getString(3));
            
            
            admin.add(adminuser);
        }
        con.close();
        return admin;
        }catch(Exception ex){
            ex.printStackTrace();
            return null;
        }

      }   
    
}
