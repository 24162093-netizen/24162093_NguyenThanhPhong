package vn.service.impl;

import vn.dao.UserDao;
import vn.dao.impl.UserDaoImpl;
import vn.entity.User;
import vn.service.UserService;

public class UserServiceImpl implements UserService {

    private UserDao userDao =
            new UserDaoImpl();

    @Override
    public User login(String username, String password) {

        User user =
                userDao.findByUsername(username);

        if (user != null &&
            user.getPassWord().equals(password)) {

            return user;
        }

        return null;
    }
}