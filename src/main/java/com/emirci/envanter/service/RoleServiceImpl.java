package com.emirci.envanter.service;

import com.emirci.envanter.dao.GenericDao;
import com.emirci.envanter.dao.RoleDao;
import com.emirci.envanter.dao.UserDao;
import com.emirci.envanter.model.Role;
import com.emirci.envanter.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashSet;

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
        return roleDao.findByRole(role);
    }

}