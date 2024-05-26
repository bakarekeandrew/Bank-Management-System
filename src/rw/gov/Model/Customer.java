/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rw.gov.Model;


/**
 *
 * @author andre
 */
public class Customer {
//    private int customer_id;
    private String f_name;
    private String l_name;
//    private String email;
    private String phone_number;
    private String address;
    private String gender;

    public Customer() {
    }

    public Customer(String f_name, String l_name, String phone_number, String address, String gender) {
//        this.customer_id = customer_id;
        this.f_name = f_name;
        this.l_name = l_name;
//        this.email = email;
        this.phone_number = phone_number;
        this.address = address;
        this.gender = gender;
    }

  

    public String getF_name() {
        return f_name;
    }

    public void setF_name(String f_name) {
        this.f_name = f_name;
    }

    public String getL_name() {
        return l_name;
    }

    public void setL_name(String l_name) {
        this.l_name = l_name;
    }


    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
    
    

}
