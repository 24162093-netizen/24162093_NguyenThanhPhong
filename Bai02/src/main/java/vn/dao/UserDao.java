package vn.dao;

import vn.entity.User;

public interface UserDao {

    User findByUsername(String username);

}