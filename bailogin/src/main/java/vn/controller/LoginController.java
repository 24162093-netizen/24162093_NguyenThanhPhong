package vn.controller;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import vn.model.User;
import vn.service.Userservice;
import vn.service.impl.Userserviceimpl;

@WebServlet("/login")
public class LoginController extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private Userservice userService =
            new Userserviceimpl();

    @Override
    protected void doGet(
            HttpServletRequest req,
            HttpServletResponse resp)
            throws ServletException, IOException {

        Cookie[] cookies = req.getCookies();

        if (cookies != null) {

            for (Cookie cookie : cookies) {

                if ("username".equals(cookie.getName())) {

                    req.setAttribute(
                            "savedUsername",
                            cookie.getValue()
                    );

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

        if (username == null ||
            username.trim().isEmpty() ||
            password == null ||
            password.trim().isEmpty()) {

            req.setAttribute(
                    "alert",
                    "Vui lòng nhập đầy đủ tài khoản và mật khẩu"
            );

            req.getRequestDispatcher(
                    "/views/login.jsp"
            ).forward(req, resp);

            return;
        }

        User user =
                userService.login(
                        username,
                        password
                );

        if (user != null) {

            // Tạo Session
            HttpSession session =
                    req.getSession(true);

            session.setAttribute(
                    "account",
                    user
            );

            // Nếu chọn Remember Me
            if ("on".equals(remember)) {

                Cookie cookie =
                        new Cookie(
                                "username",
                                username
                        );

                cookie.setMaxAge(
                        30 * 60
                );

                resp.addCookie(cookie);
            }

            resp.sendRedirect(
                    req.getContextPath()
                    + "/waiting"
            );

        } else {

            req.setAttribute(
                    "alert",
                    "Tài khoản hoặc mật khẩu không đúng"
            );

            req.getRequestDispatcher(
                    "/views/login.jsp"
            ).forward(req, resp);
        }
    }
}