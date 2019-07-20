package com.ddl.mybatis.dao;


import com.ddl.mybatis.bean.Employee;

import java.util.List;

public interface EmployeeMapper {

    Employee getEmpById(Integer id);

    List<Employee> getEmps();


}
