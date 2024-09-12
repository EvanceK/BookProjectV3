package service.impl;

import java.sql.Connection;
import java.util.List;

import dao.impl.EmployeesDaoImpl;
import model.Employees;
import model.Member;
import service.EmployeesService;

public class EmployeesServiceImpl implements EmployeesService {
    EmployeesDaoImpl edi = new EmployeesDaoImpl();

    @Override
    public void addEmployee(Connection conn, Employees emp) {
        edi.insert(conn,emp);
    }

    @Override
    public void deleteEmployeeById(Connection conn, Integer id) {
        edi.deleteById(conn,id);
    }

    @Override
    public void deleteEmployeeByEmployeeId(Connection conn, String employeeId) {
        edi.deleteByEmployeeId(conn,employeeId);

    }

    @Override
    public void updateEmployees(Connection conn, Employees emp) {
        edi.updateEmployees(conn,emp);
    }

    @Override
    public Employees getEmployeesById(Connection conn, Integer id) {
        Employees employeesById = edi.getEmployeesById(conn, id);
        return employeesById;
    }

    @Override
    public Employees getEmployeesByEmployeeId(Connection conn, String employeeId) {
        Employees employeesByUserID = edi.getEmployeesByEmployeeId(conn, employeeId);
        return employeesByUserID;
    }
    
    @Override
    public Employees getEmployeesByEmployeeName(Connection conn, String employeeName) {
        Employees employeesByUserID = edi.getEmployeesByEmployeeId(conn, employeeName);
        return employeesByUserID;
    }
  

  public  Employees comfirmAdmin(Connection conn, String employee, String password, Integer number) {
	  
	  Employees emp = this.login(conn,employee,password);
	  if (emp != null && number.equals(emp.getAdmin())) {
		  return emp;
	  } else {
		  return null;
	  }
    }

    @Override
    public List<Employees> getAllEmployees(Connection conn) {
        List<Employees> allEmployees = edi.getAllEmployees(conn);
        return allEmployees;
    }

	@Override
	public Employees login(Connection conn, String employee, String password) {
		
        Employees emp1 = edi.getEmployeesByEmployeeId(conn, employee);
        
        Employees emp2 = edi.getEmployeesByEmployeeName(conn, employee);
        
        Employees emp = null;
        if (emp1 != null) {
        	 emp = emp1;
        } else if (emp2 != null) {
        	 emp = emp2;
        }
        
        if (emp != null) {
            if (password.equals(emp.getPassword())) {
                return emp;
            } else {
                System.out.println("密碼錯誤");
            }
        } else {
            // 不存在
            System.out.println("帳號或工號錯誤");
        }
        return null;
    }	
	

    public String getNextEmployeeId(Connection conn) {
    	
    	List<Employees> allEmployeesDesc = edi.getAllEmployeesDesc(conn);
    	String employeeId = allEmployeesDesc.get(0).getEmployeeId();
    	Integer valueOf = Integer.valueOf(employeeId);
    	Integer integer = valueOf+1;
    	String NextEmployeeId = String.valueOf(integer);
		return NextEmployeeId;
	}
}
