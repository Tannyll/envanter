package com.emirci.envanter.service;

import com.emirci.envanter.dao.GenericDao;
import com.emirci.envanter.dao.RoleDao;
import com.emirci.envanter.dao.UserDao;
import com.emirci.envanter.model.AppUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service("userService")
public class UserServiceImpl extends GenericServiceImpl<AppUser, Integer> implements UserService {

    //@Autowired
    private RoleDao roleDao;

    //@Autowired
    private UserDao userDao;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public UserServiceImpl(@Qualifier("userDaoImpl") GenericDao<AppUser, Integer> genericDao) {

        super(genericDao);
        this.userDao = (UserDao) genericDao;
    }

    @Override
    public AppUser findUserByEmail(String email) {
        return userDao.findByEmail(email);
    }

    @Override
    public void saveUser(AppUser user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setActive(1);
/*        Role userRole = roleDao.findByRole("USER");
        user.setRoles(new HashSet<Role>(Arrays.asList(userRole)));*/
        userDao.saveOrUpdate(user);
    }


}