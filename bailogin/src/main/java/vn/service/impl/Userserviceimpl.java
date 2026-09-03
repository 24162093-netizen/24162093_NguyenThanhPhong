package vn.service.impl;

import vn.dao.Userdao;
import vn.dao.impl.Userdaoimpl;
import vn.model.User;
import vn.service.Userservice;

public class Userserviceimpl implements Userservice {

    private Userdao userDao =
            new Userdaoimpl();


    // =========================
    // LOGIN
    // =========================

    @Override
    public User login(
            String username,
            String password) {

        User user =
                userDao.get(username);

        if (user != null &&
            password.equals(user.getPassWord())) {

            return user;
        }

        return null;
    }


    // =========================
    // REGISTER
    // =========================

    @Override
    public boolean register(User user) {

        // Kiểm tra username đã tồn tại
        if (userDao.checkUsername(
                user.getUserName())) {

            return false;
        }

        // Kiểm tra email đã tồn tại
        if (userDao.checkEmail(
                user.getEmail())) {

            return false;
        }

        // Thêm user
        userDao.register(user);

        return true;
    }
}