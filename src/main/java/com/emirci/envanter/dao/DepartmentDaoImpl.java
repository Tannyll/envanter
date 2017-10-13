package com.emirci.envanter.dao;

import com.emirci.envanter.model.Department;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;

@Repository
public class DepartmentDaoImpl extends GenericDaoImpl<Department, Long> implements DepartmentDao {


    @Override
    public boolean removeDepartment(Long id) {
        Query query = currentSession().createQuery(
                "select from Department u where departmentId:id", Department.class);
        query.setParameter("departmentId", id);
        return query.executeUpdate() > 0;
    }

    @Override
    public boolean isDepartment(Long id) {

        Query query = currentSession().createQuery(
                "select 'A' from Department u where departmentId=:id", Department.class);
        query.setParameter("departmentId", id);

        return query.getResultList().size() > 0;
    }

    @Override
    public Department getDepartment(Long id) {
        Query query = currentSession().createQuery(
                "select from Department" + "where departmentId=:id", Department.class);
        query.setParameter("departmentId", id);

        return (Department) query.getSingleResult();

    }
}
