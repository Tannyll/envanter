package com.emirci.envanter.service;

import com.emirci.envanter.model.Department;

public interface DepartmentService extends GenericService<Department, Long> {

    public boolean removeDepartment(Long id);

    public boolean isDepartment(Long id);

    public Department getDepartment(Long id);

}
