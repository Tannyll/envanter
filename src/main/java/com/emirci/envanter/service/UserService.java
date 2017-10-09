package com.emirci.envanter.service;

import com.emirci.envanter.model.AppUser;

/**
 * Created by serdaremirci on 9/19/17.
 */

public interface UserService {

    public AppUser findUserByEmail(String email);
    public void saveUser(AppUser user);


}

