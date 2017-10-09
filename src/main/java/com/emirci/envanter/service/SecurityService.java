package com.emirci.envanter.service;

/**
 * Created by serdaremirci on 9/19/17.
 */


public interface SecurityService {
    String findLoggedInUsername();

    void autologin(String username, String password);
}
