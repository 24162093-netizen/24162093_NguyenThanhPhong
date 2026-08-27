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

@WebServlet("/admin/category/edit")
public class CategoryEditController extends HttpServlet {

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

            Category category =
                    categoryService.findById(id);

            if (category == null) {

                resp.sendRedirect(
                        req.getContextPath()
                        + "/admin/category/list"
                );

                return;
            }

            req.setAttribute(
                    "category",
                    category
            );

            req.getRequestDispatcher(
                    "/views/admin/edit-category.jsp"
            ).forward(req, resp);

        } catch (Exception e) {

            e.printStackTrace();

            resp.sendRedirect(
                    req.getContextPath()
                    + "/admin/category/list"
            );
        }
    }

    @Override
    protected void doPost(
            HttpServletRequest req,
            HttpServletResponse resp)
            throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");

        try {

            int id = Integer.parseInt(
                    req.getParameter("id")
            );

            String name =
                    req.getParameter("name");

            String icon =
                    req.getParameter("icon");

            if (name == null ||
                name.trim().isEmpty()) {

                Category category =
                        categoryService.findById(id);

                req.setAttribute(
                        "category",
                        category
                );

                req.setAttribute(
                        "error",
                        "Tên danh mục không được để trống."
                );

                req.getRequestDispatcher(
                        "/views/admin/edit-category.jsp"
                ).forward(req, resp);

                return;
            }

            Category category =
                    new Category();

            category.setId(id);
            category.setName(name.trim());
            category.setIcon(icon);

            categoryService.update(category);

            resp.sendRedirect(
                    req.getContextPath()
                    + "/admin/category/list"
            );

        } catch (Exception e) {

            e.printStackTrace();

            resp.sendRedirect(
                    req.getContextPath()
                    + "/admin/category/list"
            );
        }
    }
}