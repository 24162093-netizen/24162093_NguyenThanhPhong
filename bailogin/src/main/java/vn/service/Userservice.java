package vn.service;

import vn.model.User;

public interface Userservice {

    User login(String username, String password);

    boolean register(User user);

}