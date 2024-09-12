package dao.impl;

import java.sql.Connection;
import java.util.List;

import dao.BaseDao;
import dao.EmployeesDao;
import model.Employees;

public class EmployeesDaoImpl extends BaseDao implements EmployeesDao {

    @Override
    public void insert(Connection conn, Employees emp) {
        String sql = "insert into employees(employeeId,password, employeeName,department, managerId, phone, email,salary,admin)values(?,?,?,?,?,?,?,?,?)";
        update(conn, sql, emp.getEmployeeId(),emp.getPassword(),emp.getEmployeeName(),emp.getDepartment(),emp.getManagerId(),emp.getPhone(),emp.getEmail(),emp.getSalary(),emp.getAdmin());
    }

    @Override
    public void deleteById(Connection conn, Integer id) {
        String sql = "delete from employees where id = ?";
        update(conn, sql, id);
    }

    @Override
    public void deleteByEmployeeId(Connection conn, String employeeId) {
        String sql = "delete from employees where employeeId = ?";
        update(conn, sql, employeeId);
    }

    @Override
    public void updateEmployees(Connection conn,  Employees emp) {
        String sql = "update employees set employeeId=?,password=?, employeeName=?,department=?, managerId=?, phone=?, email=?,salary=?,admin=? where id =?";
        update(conn, sql, emp.getEmployeeId(),emp.getPassword(),emp.getEmployeeName(),emp.getDepartment(),emp.getManagerId(),emp.getPhone(),emp.getEmail(),emp.getSalary(),emp.getAdmin());
    }

    @Override
    public Employees getEmployeesById(Connection conn, Integer id) {
        String sql = "select id,employeeId,password,employeeName,department,managerId,phone,email,salary,admin from employees where id =?";
        Employees emp = getInstance(conn, Employees.class, sql, id);
        return emp;
    }

    @Override
    public Employees getEmployeesByEmployeeName(Connection conn, String employeeName) {
        String sql = "select id,employeeId,password,employeeName,department,managerId,phone,email,salary,admin from employees where employeeName =?";
        Employees emp = getInstance(conn, Employees.class, sql, employeeName);
        return emp;
    }
    public Employees getEmployeesByEmployeeId(Connection conn, String employeeId) {
        String sql = "select id,employeeId,password,employeeName,department,managerId,phone,email,salary,admin from employees where employeeId =?";
        Employees emp = getInstance(conn, Employees.class, sql, employeeId);
        return emp;
    }

    @Override
    public List<Employees> getAllEmployees(Connection conn) {
        String sql = "select id,employeeId,password,employeeName,department,managerId,phone,email,salary,admin from employees";
        List<Employees> employeesList = getForList(conn, Employees.class, sql);
        return employeesList;
    }
    
    public List<Employees> getAllEmployeesDesc(Connection conn) {
        String sql = "select id,employeeId,password,employeeName,department,managerId,phone,email,salary,admin from employees Order by employeeId DESC";
        List<Employees> employeesList = getForList(conn, Employees.class, sql);
        return employeesList;
    }
    
}
