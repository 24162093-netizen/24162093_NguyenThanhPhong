package vn.controller;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import vn.entity.Category;
import vn.service.CategoryService;
import vn.service.impl.CategoryServiceImpl;

@WebServlet("/admin/category/add")
public class CategoryAddController extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private CategoryService categoryService =
            new CategoryServiceImpl();

    @Override
    protected void doGet(
            HttpServletRequest req,
            HttpServletResponse resp)
            throws ServletException, IOException {

        req.getRequestDispatcher(
                "/views/admin/add-category.jsp"
        ).forward(req, resp);
    }

    @Override
    protected void doPost(
            HttpServletRequest req,
            HttpServletResponse resp)
            throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");

        String name =
                req.getParameter("name");

        String icon =
                req.getParameter("icon");

        if (name == null || name.trim().isEmpty()) {

            req.setAttribute(
                    "error",
                    "Tên danh mục không được để trống."
            );

            req.getRequestDispatcher(
                    "/views/admin/add-category.jsp"
            ).forward(req, resp);

            return;
        }

        Category category =
                new Category();

        category.setName(name.trim());
        category.setIcon(icon);

        categoryService.insert(category);

        resp.sendRedirect(
                req.getContextPath()
                + "/admin/category/list"
        );
    }
}