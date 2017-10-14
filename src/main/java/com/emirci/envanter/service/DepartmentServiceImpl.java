package com.emirci.envanter.service;

import com.emirci.envanter.dao.DepartmentDao;
import com.emirci.envanter.dao.GenericDao;
import com.emirci.envanter.model.Department;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service("departmentService")
public class DepartmentServiceImpl extends GenericServiceImpl<Department, Long> implements DepartmentService {


    private DepartmentDao departmentDao;

    @Autowired
    public DepartmentServiceImpl(@Qualifier("departmentDaoImpl") GenericDao<Department, Long> genericDao) {

        super(genericDao);
        this.departmentDao = (DepartmentDao) genericDao;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public boolean removeDepartment(Long id) {
        return departmentDao.removeDepartment(id);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public boolean isDepartment(Long id) {
        return departmentDao.isDepartment(id);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public Department getDepartment(Long id) {
        return departmentDao.getDepartment(id);
    }


}
