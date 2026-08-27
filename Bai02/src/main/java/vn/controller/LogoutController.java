package vn.controller;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/logout")
public class LogoutController extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(
            HttpServletRequest req,
            HttpServletResponse resp)
            throws ServletException, IOException {

        // Xóa Session
        HttpSession session =
                req.getSession(false);

        if (session != null) {
            session.invalidate();
        }

        // Xóa Cookie username
        Cookie cookie =
                new Cookie("username", "");

        cookie.setMaxAge(0);

        cookie.setPath(
                req.getContextPath()
        );

        resp.addCookie(cookie);

        // Quay về Login
        resp.sendRedirect(
                req.getContextPath()
                + "/login"
        );
    }
}