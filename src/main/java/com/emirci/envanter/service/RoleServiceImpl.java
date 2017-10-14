package com.emirci.envanter.service;

import com.emirci.envanter.dao.GenericDao;
import com.emirci.envanter.dao.RoleDao;
import com.emirci.envanter.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service("roleService")
public class RoleServiceImpl extends GenericServiceImpl<Role, Integer> implements RoleService {


    private RoleDao roleDao;


    @Autowired
    public RoleServiceImpl(@Qualifier("roleDaoImpl") GenericDao<Role, Integer> genericDao) {

        super(genericDao);
        this.roleDao = (RoleDao) genericDao;
    }

    @Override
    public Role findRoleByRole(String role) {
        return (Role) roleDao.get(String.format("SELECT e FROM Role e WHERE e.role = '%s' ", role)).get(0);


    }


}