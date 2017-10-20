package com.emirci.envanter.service;

import com.emirci.envanter.model.AppUser;

/**
 * Created by serdaremirci on 9/19/17.
 */


public interface UserService extends GenericService<AppUser, Integer> {

    AppUser findUserByEmail(String email);

    void saveUser(AppUser user);


}

