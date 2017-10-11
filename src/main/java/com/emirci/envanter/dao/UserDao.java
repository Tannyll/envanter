package com.emirci.envanter.dao;


import com.emirci.envanter.model.AppUser;

/**
 * Created by serdaremirci on 9/19/17.
 */

public interface UserDao extends GenericDao<AppUser, Integer> {

    public boolean removeUser(Integer id);

    public boolean isUser(Integer id);

    public AppUser getUser(Integer id);

    AppUser findByEmail(String email);


}
