package vn.controller;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
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

        // Lấy Session hiện tại
        HttpSession session = req.getSession(false);

        // Nếu có Session thì hủy Session
        if (session != null) {
            session.invalidate();
        }

        // Quay về trang Login
        resp.sendRedirect(
                req.getContextPath() + "/login"
        );
    }
}