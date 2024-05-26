/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rw.gov.Model;


import java.util.Date;

/**
 *
 * @author andre
 */
public class Account {
    
//    private int account_id;
    private String first_name;
    private String last_name;
    private String gender;
    private String address;
    private String account_type;
    private String telno;
    private int account_nbr;
    private String pin;    
    private Date open_date;
    private Double amount;
    private String email;


    public Account() {
    }

    public Account(String first_name, String last_name, String gender, String address,
          String account_type, String telno, int account_nbr, String pin, Date open_date, Double amount, String email) {
//        this.account_id = account_id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.gender = gender;
        this.address = address;
        this.account_type = account_type;
        this.telno = telno;
        this.account_nbr = account_nbr;
        this.pin = pin;
        this.open_date = open_date;
        this.amount = amount;
        this.email = email;
        
    }

//    public int getAccount_id() {
//        return account_id;
//    }
//
//    public void setAccount_id(int account_id) {
//        this.account_id = account_id;
//    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAccount_type() {
        return account_type;
    }

    public void setAccount_type(String account_type) {
        this.account_type = account_type;
    }

    public String getTelNo() {
        return telno;
    }

    public void setTelNo(String telno) {
        this.telno = telno;
    }

    public int getAccount_nbr() {
        return account_nbr;
    }

    public void setAccount_nbr(int account_nbr) {
        this.account_nbr = account_nbr;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    public Date getOpen_date() {
        return open_date;
    }

    public void setOpen_date(Date open_date) {
        this.open_date = open_date;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    

    
    
    
    
}
