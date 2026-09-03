package vn.controller;

import java.io.IOException;
import java.sql.Date;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import vn.model.User;
import vn.service.Userservice;
import vn.service.impl.Userserviceimpl;

@WebServlet("/register")
public class RegisterController extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private Userservice userService =
            new Userserviceimpl();


    // =========================
    // HIỂN THỊ TRANG REGISTER
    // =========================

    @Override
    protected void doGet(
            HttpServletRequest req,
            HttpServletResponse resp)
            throws ServletException, IOException {

        req.getRequestDispatcher(
                "/views/register.jsp"
        ).forward(req, resp);
    }


    // =========================
    // XỬ LÝ REGISTER
    // =========================

    @Override
    protected void doPost(
            HttpServletRequest req,
            HttpServletResponse resp)
            throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");


        String username =
                req.getParameter("username");

        String password =
                req.getParameter("password");

        String email =
                req.getParameter("email");

        String fullname =
                req.getParameter("fullname");

        String phone =
                req.getParameter("phone");


        // =========================
        // KIỂM TRA RỖNG
        // =========================

        if (username == null ||
            username.trim().isEmpty() ||

            password == null ||
            password.trim().isEmpty() ||

            email == null ||
            email.trim().isEmpty() ||

            fullname == null ||
            fullname.trim().isEmpty()) {

            req.setAttribute(
                    "alert",
                    "Vui lòng nhập đầy đủ thông tin."
            );

            req.getRequestDispatcher(
                    "/views/register.jsp"
            ).forward(req, resp);

            return;
        }


        // =========================
        // TẠO USER
        // =========================

        User user = new User();

        user.setUserName(
                username.trim()
        );

        user.setPassWord(
                password
        );

        user.setEmail(
                email.trim()
        );

        user.setFullName(
                fullname.trim()
        );

        user.setPhone(
                phone
        );


        // Không có avatar lúc đăng ký
        user.setAvatar(null);


        /*
         * roleid:
         * 1 = Admin
         * 2 = User thường
         *
         * Nếu database của bạn quy định
         * roleid khác thì đổi số này.
         */
        user.setRoleid(2);


        // Ngày đăng ký
        user.setCreatedDate(
                new Date(System.currentTimeMillis())
        );


        // =========================
        // ĐĂNG KÝ
        // =========================

        boolean success =
                userService.register(user);


        if (success) {

            // Đăng ký thành công
            resp.sendRedirect(
                    req.getContextPath()
                    + "/login"
            );

        } else {

            // Username hoặc email đã tồn tại
            req.setAttribute(
                    "alert",
                    "Username hoặc Email đã tồn tại."
            );

            req.getRequestDispatcher(
                    "/views/register.jsp"
            ).forward(req, resp);
        }
    }
}