package vn.service;

import vn.entity.User;

public interface UserService {

    User login(String username, String password);

}