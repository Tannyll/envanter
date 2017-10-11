package com.emirci.envanter.service;

import com.emirci.envanter.model.AppUser;
import com.emirci.envanter.model.Inventory;
import org.springframework.stereotype.Service;

/**
 * Created by serdaremirci on 9/19/17.
 */


public interface UserService extends GenericService<AppUser, Integer> {

    public AppUser findUserByEmail(String email);
    public void saveUser(AppUser user);


}

