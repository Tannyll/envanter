package com.emirci.envanter.service;

import com.emirci.envanter.model.Role;

/**
 * Created by serdaremirci on 9/19/17.
 */

public interface RoleService extends GenericService<Role, Integer> {

    public Role findRoleByRole(String role);

}

