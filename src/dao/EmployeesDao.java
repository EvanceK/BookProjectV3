package dao;

import java.sql.Connection;
import java.util.List;

import model.Employees;

public interface EmployeesDao {
    //新增 Create
    //Employees，添加到數據庫中
    public abstract void insert(Connection conn, Employees emp);


    //刪除 Delete
    //將指定id的紀錄，從數據庫中刪除
    public abstract void deleteById(Connection conn, Integer id);
    //將指定employeeId的紀錄，從數據庫中刪除
    public abstract void deleteByEmployeeId(Connection conn, String employeeId);

    //更新 Update
    //針對Employees對象，修改數據表中的紀錄
    public abstract void updateEmployees(Connection conn,  Employees emp);//更新一般資訊 帳號 密碼 使用者名稱

    //查詢select  Read
    //查詢指定id，數據表中的紀錄
    public abstract Employees getEmployeesById(Connection conn, Integer id);
    public abstract Employees getEmployeesByEmployeeName(Connection conn, String employeeName);
    public abstract Employees getEmployeesByEmployeeId(Connection conn, String employeeName);

    //查詢表中的所有紀錄
    public abstract List<Employees> getAllEmployees(Connection conn);
    public abstract List<Employees> getAllEmployeesDesc(Connection conn);

}