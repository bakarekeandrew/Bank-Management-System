
package rw.gov.Dao;

import java.util.*;
import java.sql.*;
import rw.gov.Model.Account;
import rw.gov.Model.Customer;
import rw.gov.Dao.CustomersDao;
import rw.gov.Model.Transaction;
/**
 *
 * @author andre
 */
public class AccountDao {
    
    String jdbcUrl = "jdbc:postgresql://localhost:5432/Bank_management_system_db";
    String username = "postgres";
    String password = "123";
    
    public Integer recordAccount(Account objAccount){
        Integer rowsAffected = null;
      try{
        Connection con = DriverManager.getConnection(jdbcUrl, username, password);
        //step2: prepare statement
        String sql = "insert into accounts(first_name, last_name, gender, address, account_type, telno, account_nbr, pin, open_date,amount,email) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        
        PreparedStatement pst = con.prepareStatement(sql);
//        pst.setInt(1, objAccount.getAccount_id());
        pst.setString(1, objAccount.getFirst_name());
        pst.setString(2, objAccount.getLast_name());
        pst.setString(3, objAccount.getGender());
        pst.setString(4, objAccount.getAddress());
        pst.setString(5, objAccount.getAccount_type());      
        pst.setString(6, objAccount.getTelNo());
        pst.setInt(7, objAccount.getAccount_nbr());
        pst.setString(8, objAccount.getPin());              
        java.util.Date utilDate = objAccount.getOpen_date();
        java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
        pst.setDate(9, sqlDate);
        pst.setDouble(10, objAccount.getAmount());
        pst.setString(11, objAccount.getEmail());

        
        rowsAffected = pst.executeUpdate();
        if(rowsAffected > 0){
            System.out.println("ACCOUNT DATA SAVED");
            
            
            String sqlCustomer = "insert into customers(f_name, l_name, phone_number, address, gender) values (?, ?, ?, ?, ?)";

            // Prepare statement for customer insertion
            PreparedStatement pstCustomer = con.prepareStatement(sqlCustomer);
            pstCustomer.setString(1, objAccount.getFirst_name());
            pstCustomer.setString(2, objAccount.getLast_name());
            pstCustomer.setString(3, objAccount.getTelNo());
            pstCustomer.setString(4, objAccount.getAddress());
            pstCustomer.setString(5, objAccount.getGender());

            // Execute customer insertion
            int rowsAffectedCustomer = pstCustomer.executeUpdate();

            if (rowsAffectedCustomer > 0) {
                System.out.println("Customer Data Saved");
            } else {
                System.out.println("Customer Data Not Saved");
            }

        }
        else{
            System.out.println("Account data not saved");
            }
        
        con.close();
       
       }catch(Exception ex){
            ex.printStackTrace();
                    }
        return rowsAffected;
      }
    
       public void depositAmount(Account account){
         
                  
           try (Connection con = DriverManager.getConnection(jdbcUrl, username, password)) {
            String sql = "UPDATE accounts SET amount = ? WHERE account_nbr = ?";
              try (PreparedStatement pst = con.prepareStatement(sql)) {
                pst.setDouble(1, account.getAmount());
                pst.setInt(2, account.getAccount_nbr());

                int rowsAffected = pst.executeUpdate();
                if (rowsAffected > 0) {
                    System.out.println("Deposit Successful");
                } else {
                    System.out.println("No rows updated. Deposit failed.");
                 }
              }
        } catch (SQLException e) {
            System.out.println("Error depositing amount: " + e.getMessage());
        }
           
       }
       public void depositUserAmount(Account account){
         
                  
           try (Connection con = DriverManager.getConnection(jdbcUrl, username, password)) {
            String sql = "UPDATE accounts SET amount = amount + ? WHERE account_nbr = ?";
              try (PreparedStatement pst = con.prepareStatement(sql)) {
                pst.setDouble(1, account.getAmount());
                pst.setInt(2, account.getAccount_nbr());

                int rowsAffected = pst.executeUpdate();
                if (rowsAffected > 0) {
                    System.out.println("Deposit Successful");
                } else {
                    System.out.println("No rows updated. Deposit failed.");
                 }
              }
        } catch (SQLException e) {
            System.out.println("Error depositing amount: " + e.getMessage());
        }
           
       }
       
      public void updateAccount(Account account, boolean isDeposit) {
        try (Connection con = DriverManager.getConnection(jdbcUrl, username, password)) {
            String sql;
            if (isDeposit) {
                sql = "UPDATE accounts SET amount = amount + ? WHERE account_nbr = ?";
            } else {
                sql = "UPDATE accounts SET amount = amount - ? WHERE account_nbr = ?";
            }
            try (PreparedStatement pst = con.prepareStatement(sql)) {
                pst.setDouble(1, account.getAmount());
                pst.setInt(2, account.getAccount_nbr());

                int rowsAffected = pst.executeUpdate();
                if (rowsAffected > 0) {
                    System.out.println("Transaction Successful");
                } else {
                    System.out.println("No rows updated. Transaction failed.");
                }
            }
        } catch (SQLException e) {
            System.out.println("Error during transaction: " + e.getMessage());
        }
    }


        public void WithdrawUserAmount(Account account){
            try (Connection con = DriverManager.getConnection(jdbcUrl, username, password)) {
                String selectSql = "SELECT amount FROM accounts WHERE account_nbr = ?";
                try (PreparedStatement selectStmt = con.prepareStatement(selectSql)) {
                    selectStmt.setInt(1, account.getAccount_nbr());
                    try (ResultSet rs = selectStmt.executeQuery()) {
                        if (rs.next()) {
                            double currentAmount = rs.getDouble("amount");
                            double withdrawAmount = account.getAmount();
                            if (currentAmount >= withdrawAmount) {
                                String updateSql = "UPDATE accounts SET amount = amount - ? WHERE account_nbr = ?";
                                try (PreparedStatement updateStmt = con.prepareStatement(updateSql)) {
                                    updateStmt.setDouble(1, withdrawAmount);
                                    updateStmt.setInt(2, account.getAccount_nbr());
                                    int rowsAffected = updateStmt.executeUpdate();
                                    if (rowsAffected > 0) {
                                        System.out.println("Withdraw Successful");
                                    } else {
                                        System.out.println("No rows updated. Withdraw failed.");
                                    }
                                }
                            } else {
                                System.out.println("Insufficient funds. Withdrawal not allowed.");
                            }
                        } else {
                            System.out.println("Account not found.");
                        }
                    }
                }
            } catch (SQLException e) {
                System.out.println("Error withdrawing amount: " + e.getMessage());
            }
        }


        public Account SearchForDeposit(int accountNumber) {
            Account account = null;
            try {
                Connection con = DriverManager.getConnection(jdbcUrl, username, password);
                String sql = "select first_name, account_nbr from accounts where account_nbr = ?";

                PreparedStatement pst = con.prepareStatement(sql);
                pst.setInt(1, accountNumber);

                ResultSet rs = pst.executeQuery();
                if (rs.next()) {
                    account = new Account();
                    account.setAccount_nbr(rs.getInt("account_nbr"));
                    account.setFirst_name(rs.getString("first_name"));
                }

                con.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            return account;
        }
        
        public Account SearchForTransfer(int accountNumber){
        
            Account account = null;
            try {
                Connection con = DriverManager.getConnection(jdbcUrl, username, password);
                String sql = "select account_nbr from accounts where account_nbr = ?";

                PreparedStatement pst = con.prepareStatement(sql);
                pst.setInt(1, accountNumber);

                ResultSet rs = pst.executeQuery();
                if (rs.next()) {
                    account = new Account();
                    account.setAccount_nbr(rs.getInt("account_nbr"));
//                    account.setFirst_name(rs.getString("first_name"));
                }

                con.close();
            }catch (Exception ex) {
                ex.printStackTrace();
            }
            return account;
            
            
        }
    
    public void updateBalance(Account account, boolean isDeposit) {
        try (Connection con = DriverManager.getConnection(jdbcUrl, username, password)) {
            String sql;
            if (isDeposit) {
                sql = "UPDATE accounts SET amount = amount + ? WHERE account_nbr = ?";
            } else {
                sql = "UPDATE accounts SET amount = amount - ? WHERE account_nbr = ?";
            }
            try (PreparedStatement pst = con.prepareStatement(sql)) {
                pst.setDouble(1, account.getAmount());
                pst.setInt(2, account.getAccount_nbr());

                int rowsAffected = pst.executeUpdate();
                if (rowsAffected > 0) {
                    System.out.println("Balance updated successfully");
                } else {
                    System.out.println("No rows updated. Balance update failed.");
                }
            }
        } catch (SQLException e) {
            System.out.println("Error updating balance: " + e.getMessage());
        }
}

     public void EditProfile(Account objAccount){
    
        try{       
          Connection con = DriverManager.getConnection(jdbcUrl, username, password);
          //step2: prepare statement
         String sql = "UPDATE accounts SET gender=?, address=?, telno=? WHERE account_nbr=?";
        
           PreparedStatement pst = con.prepareStatement(sql);
           pst.setString(1, objAccount.getGender());
           pst.setString(2, objAccount.getAddress());
           pst.setString(3, objAccount.getTelNo());
           pst.setInt(4, objAccount.getAccount_nbr());

          int rowAffected = pst.executeUpdate();
          if(rowAffected > 0){
             System.out.println("Data Data Successfully!!");
              }
          else{
             System.out.println("Data not Edited");
              }
        
           con.close();
       
         }catch(Exception ex){
            ex.printStackTrace();
                    
      }
    }   
       
    
   public Account retrieveAccount(int accountNumber) {
    Account account = null;
    try {
        Connection con = DriverManager.getConnection(jdbcUrl, username, password);
        String sql = "select * from accounts where account_nbr = ?";
        
        PreparedStatement pst = con.prepareStatement(sql);
        pst.setInt(1, accountNumber);
        
        ResultSet rs = pst.executeQuery();
        if (rs.next()) {
            account = new Account();
            account.setAccount_nbr(rs.getInt("account_nbr"));
            account.setFirst_name(rs.getString("first_name"));
            account.setLast_name(rs.getString("last_name"));
            account.setGender(rs.getString("gender"));
            account.setAddress(rs.getString("address"));
            account.setAccount_type(rs.getString("account_type"));
            account.setTelNo(rs.getString("telno"));
            account.setPin(rs.getString("pin"));
            account.setOpen_date(rs.getDate("open_date"));
            account.setAmount(rs.getDouble("amount"));
        }

        con.close();
    } catch (Exception ex) {
        ex.printStackTrace();
    }
    return account;
}



    public Account retrieveAmount(int accountNumber) {
        Account account = null;
        try {
            Connection con = DriverManager.getConnection(jdbcUrl, username, password);
            String sql = "select first_name, amount from accounts where account_nbr = ?";

            PreparedStatement pst = con.prepareStatement(sql);
//            pst.setInt(1, first_name);
//            pst.setInt(2, amount);
            pst.setInt(1, accountNumber);

            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                account = new Account();
                account.setAccount_nbr(rs.getInt("account_nbr"));
                account.setFirst_name(rs.getString("first_name"));
                account.setAmount(rs.getDouble("amount"));
            }

            con.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return account;
    }
      public void saveTransaction(Transaction transaction, int accountNumber) throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = DriverManager.getConnection(jdbcUrl, username, password);

            String sql = "INSERT INTO transactions (account_number, transaction_type, amount, transaction_date) VALUES (?, ?, ?, ?)";
            stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            stmt.setInt(1, accountNumber);
            stmt.setString(2, transaction.getType());
            stmt.setDouble(3, transaction.getAmount());
            stmt.setTimestamp(4, new java.sql.Timestamp(transaction.getTimestamp().getTime()));

            int affectedRows = stmt.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Creating transaction failed, no rows affected.");
            }

            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    transaction.setTransactionId(generatedKeys.getInt(1));
                } else {
                    throw new SQLException("Creating transaction failed, no ID obtained.");
                }
            }
        } catch (SQLException e) {
            System.err.println("SQL Exception during transaction insertion: " + e.getMessage());
            e.printStackTrace();
            throw e; 
        }catch (Exception e) {
            System.err.println("Exception during transaction insertion: " + e.getMessage());
            e.printStackTrace();
            throw e; 

        } finally {
            if (stmt != null) {
                stmt.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
    }
      
   public Integer deleteAccount(Account objAccount) {
    Integer rowsAffected = null;
    try {
        Connection con = DriverManager.getConnection(jdbcUrl, username, password);
        String sql = "DELETE FROM accounts WHERE account_nbr = ?";
        PreparedStatement pst = con.prepareStatement(sql);
        pst.setInt(1, objAccount.getAccount_nbr());
        rowsAffected = pst.executeUpdate();
        if (rowsAffected > 0) {
            System.out.println("Account deleted successfully!");
        } else {
            System.out.println("Account not deleted");
        }
        con.close();
    } catch (Exception ex) {
        // Handle the exception (e.g., log it or throw a custom exception)
        ex.printStackTrace();
    }
    return rowsAffected;
}

public Integer deleteCustomer(Account searchUser) {
    Integer rowsAffected = null;
    try {
        Connection con = DriverManager.getConnection(jdbcUrl, username, password);
        String sql = "DELETE FROM customers WHERE f_name = ? AND l_name = ? AND phone_number = ? AND address = ? AND gender = ?";
        PreparedStatement pst = con.prepareStatement(sql);
        pst.setString(1, searchUser.getFirst_name());
        pst.setString(2, searchUser.getLast_name());
        pst.setString(3, searchUser.getTelNo());
        pst.setString(4, searchUser.getAddress());
        pst.setString(5, searchUser.getGender());
        rowsAffected = pst.executeUpdate();
        if (rowsAffected > 0) {
            System.out.println("Customer deleted successfully");
        } else {
            System.out.println("Customer not deleted");
        }
        con.close();
    } catch (Exception ex) {
        // Handle the exception (e.g., log it or throw a custom exception)
        ex.printStackTrace();
    }
    return rowsAffected;
}

   public String updatePassword(Account objAccount){
        try{ 

             Connection con = DriverManager.getConnection(jdbcUrl, username, password);
             String sql = "update accounts set account_nbr = ? where first_name = ? ";
             PreparedStatement pst = con.prepareStatement(sql);

             pst.setInt(1, objAccount.getAccount_nbr());
             pst.setString(2, objAccount.getFirst_name());


             int rowaffected = pst.executeUpdate();
 
             con.close();
            if (rowaffected > 0){
            return "data updated successfully";
              }else{
              return "data not updated";
              
                   }
        }catch(Exception e){
          e.printStackTrace();
        }
        return "";
   }
}
    

