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
import rw.gov.Model.Transaction;

/**
 *
 * @author andre
 */
public class TransactionDao {
   
    String jdbcUrl = "jdbc:postgresql://localhost:5432/Bank_management_system_db";
    String username = "postgres";
    String password = "123";
    
    public List<Transaction> retrieveTransaction() {
        try {
            Connection con = DriverManager.getConnection(jdbcUrl, username, password);
            String sql = "select transaction_type, amount, transaction_date from transactions";
            PreparedStatement pst = con.prepareStatement(sql);
            List<Transaction> transaction = new ArrayList<>();
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Transaction trans = new Transaction();
                trans.setType(rs.getString("transaction_type"));
                trans.setAmount(rs.getDouble("amount"));
                trans.setTimestamp(rs.getDate("transaction_date"));
                transaction.add(trans);
            }
            con.close();
            return transaction;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
    
    public List<Transaction> transactionAccount(int accountNumber) {
        try {
            Connection con = DriverManager.getConnection(jdbcUrl, username, password);
            String sql = "select transaction_type, amount, transaction_date from transactions where account_number = ?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, accountNumber);
            List<Transaction> transaction = new ArrayList<>();
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Transaction trans = new Transaction();
                trans.setType(rs.getString("transaction_type"));
                trans.setAmount(rs.getDouble("amount"));
                trans.setTimestamp(rs.getDate("transaction_date"));
                transaction.add(trans);
            }
            con.close();
            return transaction;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
}