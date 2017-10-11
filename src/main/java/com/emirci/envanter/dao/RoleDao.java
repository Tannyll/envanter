package com.emirci.envanter.dao;

import com.emirci.envanter.model.Role;

/**
 * Created by serdaremirci on 9/19/17.
 */

public interface RoleDao extends GenericDao<Role, Integer> {

    public boolean removeRole(Integer id);

    public boolean isRole(Integer id);

    public Role getRole(Integer id);

    Role findByRole(String role);


}
