package vn.controller;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import vn.service.CategoryService;
import vn.service.impl.CategoryServiceImpl;

@WebServlet("/admin/category/delete")
public class CategoryDeleteController extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private CategoryService categoryService =
            new CategoryServiceImpl();

    @Override
    protected void doGet(
            HttpServletRequest req,
            HttpServletResponse resp)
            throws ServletException, IOException {

        try {

            int id = Integer.parseInt(
                    req.getParameter("id")
            );

            categoryService.delete(id);

        } catch (Exception e) {

            e.printStackTrace();
        }

        resp.sendRedirect(
                req.getContextPath()
                + "/admin/category/list"
        );
    }
}