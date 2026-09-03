package vn.dao;

import vn.model.User;

public interface Userdao {

    User get(String username);

    void register(User user);

    boolean checkUsername(String username);

    boolean checkEmail(String email);
}