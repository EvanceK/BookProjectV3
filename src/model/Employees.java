package model;

import java.io.Serializable;

public class Employees implements Serializable {
    private Integer id;
    private String employeeId;

    private String password;
    private String employeeName;
    private String department;
    private String managerId;
    private String phone;
    private String email;
    private Integer salary;

    private Integer admin;


    public Employees() {
    }

    public Employees(Integer id, String employeeId, String password, String employeeName, String department, String managerId, String phone, String email, Integer salary, Integer admin) {
        this.id = id;
        this.employeeId = employeeId;
        this.password = password;
        this.employeeName = employeeName;
        this.department = department;
        this.managerId = managerId;
        this.phone = phone;
        this.email = email;
        this.salary = salary;
        this.admin = admin;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getManagerId() {
        return managerId;
    }

    public void setManagerId(String managerId) {
        this.managerId = managerId;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getSalary() {
        return salary;
    }

    public void setSalary(Integer salary) {
        this.salary = salary;
    }

    public Integer getAdmin() {
        return admin;
    }

    public void setAdmin(Integer admin) {
        this.admin = admin;
    }
}