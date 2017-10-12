package com.emirci.envanter.dao;

import com.emirci.envanter.model.Department;

public interface DepartmentDao extends GenericDao<Department, Long> {

    public boolean removeDepartment(Long id);

    public boolean isDepartment(Long id);

    public Department getDepartment(Long id);


}
