package vn.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import vn.connection.DBconnection;
import vn.dao.Userdao;
import vn.model.User;

public class Userdaoimpl implements Userdao {

    @Override
    public User get(String username) {

        String sql =
                "SELECT * FROM users WHERE username = ?";

        try (
            Connection conn =
                    new DBconnection().getConnection();

            PreparedStatement ps =
                    conn.prepareStatement(sql)
        ) {

            ps.setString(1, username);

            ResultSet rs =
                    ps.executeQuery();

            if (rs.next()) {

                User user = new User();

                user.setId(rs.getInt("id"));

                user.setEmail(
                    rs.getString("email")
                );

                user.setUserName(
                    rs.getString("username")
                );

                user.setFullName(
                    rs.getString("fullname")
                );

                user.setPassWord(
                    rs.getString("password")
                );

                user.setAvatar(
                    rs.getString("avatar")
                );

                user.setRoleid(
                    rs.getInt("roleid")
                );

                user.setPhone(
                    rs.getString("phone")
                );

                user.setCreatedDate(
                    rs.getDate("createdDate")
                );

                return user;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }


    // =========================
    // ĐĂNG KÝ USER
    // =========================

    @Override
    public void register(User user) {

        String sql =
                "INSERT INTO users "
                + "(email, username, fullname, password, avatar, roleid, phone, createdDate) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (
            Connection conn =
                    new DBconnection().getConnection();

            PreparedStatement ps =
                    conn.prepareStatement(sql)
        ) {

            ps.setString(1, user.getEmail());

            ps.setString(2, user.getUserName());

            ps.setString(3, user.getFullName());

            ps.setString(4, user.getPassWord());

            ps.setString(5, user.getAvatar());

            ps.setInt(6, user.getRoleid());

            ps.setString(7, user.getPhone());

            ps.setDate(8, user.getCreatedDate());

            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    // =========================
    // KIỂM TRA USERNAME
    // =========================

    @Override
    public boolean checkUsername(String username) {

        String sql =
                "SELECT id FROM users WHERE username = ?";

        try (
            Connection conn =
                    new DBconnection().getConnection();

            PreparedStatement ps =
                    conn.prepareStatement(sql)
        ) {

            ps.setString(1, username);

            ResultSet rs =
                    ps.executeQuery();

            return rs.next();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }


    // =========================
    // KIỂM TRA EMAIL
    // =========================

    @Override
    public boolean checkEmail(String email) {

        String sql =
                "SELECT id FROM users WHERE email = ?";

        try (
            Connection conn =
                    new DBconnection().getConnection();

            PreparedStatement ps =
                    conn.prepareStatement(sql)
        ) {

            ps.setString(1, email);

            ResultSet rs =
                    ps.executeQuery();

            return rs.next();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }
}