package vn.controller;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import vn.entity.User;
import vn.service.UserService;
import vn.service.impl.UserServiceImpl;

@WebServlet("/login")
public class LoginController extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private UserService userService =
            new UserServiceImpl();

    @Override
    protected void doGet(
            HttpServletRequest req,
            HttpServletResponse resp)
            throws ServletException, IOException {

        // Kiểm tra Cookie username
        Cookie[] cookies = req.getCookies();

        if (cookies != null) {

            for (Cookie cookie : cookies) {

                if ("username".equals(
                        cookie.getName())) {

                    req.setAttribute(
                            "savedUsername",
                            cookie.getValue());

                    break;
                }
            }
        }

        req.getRequestDispatcher(
                "/views/login.jsp"
        ).forward(req, resp);
    }

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

        String remember =
                req.getParameter("remember");

        // Kiểm tra dữ liệu nhập
        if (username == null ||
            username.trim().isEmpty() ||
            password == null ||
            password.trim().isEmpty()) {

            req.setAttribute(
                    "alert",
                    "Vui lòng nhập đầy đủ tài khoản và mật khẩu."
            );

            req.setAttribute(
                    "savedUsername",
                    username
            );

            req.getRequestDispatcher(
                    "/views/login.jsp"
            ).forward(req, resp);

            return;
        }

        // Đăng nhập
        User user =
                userService.login(
                        username.trim(),
                        password
                );

        if (user == null) {

            req.setAttribute(
                    "alert",
                    "Tài khoản hoặc mật khẩu không đúng."
            );

            req.setAttribute(
                    "savedUsername",
                    username
            );

            req.getRequestDispatcher(
                    "/views/login.jsp"
            ).forward(req, resp);

            return;
        }

        // =========================
        // KIỂM TRA QUYỀN ADMIN
        // =========================

        if (user.getRoleid() != 1) {

            req.setAttribute(
                    "alert",
                    "Tài khoản này không có quyền truy cập Admin."
            );

            req.getRequestDispatcher(
                    "/views/login.jsp"
            ).forward(req, resp);

            return;
        }

        // =========================
        // TẠO SESSION
        // =========================

        HttpSession session =
                req.getSession(true);

        session.setAttribute(
                "account",
                user
        );

        // =========================
        // COOKIE REMEMBER ME
        // =========================

        if ("on".equals(remember)) {

            Cookie cookie =
                    new Cookie(
                            "username",
                            username.trim()
                    );

            // 30 ngày
            cookie.setMaxAge(
                    30 * 24 * 60 * 60
            );

            cookie.setPath(
                    req.getContextPath()
            );

            resp.addCookie(cookie);

        } else {

            // Nếu bỏ Remember Me
            // thì xóa cookie cũ

            Cookie cookie =
                    new Cookie(
                            "username",
                            ""
                    );

            cookie.setMaxAge(0);

            cookie.setPath(
                    req.getContextPath()
            );

            resp.addCookie(cookie);
        }

        // =========================
        // VÀO ADMIN
        // =========================

        resp.sendRedirect(
                req.getContextPath()
                + "/admin/category/list"
        );
    }
}