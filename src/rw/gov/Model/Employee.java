
package rw.gov.Model;

/**
 *
 * @author andre
 */
public class Employee {
  
    private int employee_id;
    private String first_name;
    private String last_name;
    private String position;
    private String email;
    private String phone_number;

    public Employee() {
    }

    public Employee(int employee_id, String first_name, String last_name, String position, String email, String phone_number) {
        this.employee_id = employee_id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.position = position;
        this.email = email;
        this.phone_number = phone_number;
    }

    public int getEmployee_id() {
        return employee_id;
    }

    public void setEmployee_id(int employee_id) {
        this.employee_id = employee_id;
    }

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

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }
    
    
    
}
