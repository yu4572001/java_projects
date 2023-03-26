package com.yystudy.mhl.service;

import com.yystudy.mhl.dao.EmployeeDAO;
import com.yystudy.mhl.domain.Employee;

//该类完成对employee表的各种操作(通过创建调用EmployeeDAO对象实现)
public class EmployeeService {
    private EmployeeDAO employeeDAO = new EmployeeDAO();

    //该方法用于检查登录账户与密码是否与在数据库employee表存在，如果存在则返回该记录对象
    public Employee checkEmployee(String emp_id, String emp_pass){
        Employee employee = employeeDAO.querySingle("select * from employee where empid=? and pwd=md5(?)",
                Employee.class, emp_id, emp_pass);
        return employee;
    }
}
