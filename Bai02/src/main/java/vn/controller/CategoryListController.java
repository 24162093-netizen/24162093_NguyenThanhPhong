package vn.controller;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import vn.entity.Category;
import vn.service.CategoryService;
import vn.service.impl.CategoryServiceImpl;

@WebServlet("/admin/category/list")
public class CategoryListController extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private CategoryService categoryService =
            new CategoryServiceImpl();

    @Override
    protected void doGet(
            HttpServletRequest req,
            HttpServletResponse resp)
            throws ServletException, IOException {

        List<Category> list =
                categoryService.findAll();

        req.setAttribute("cateList", list);

        req.getRequestDispatcher(
                "/views/admin/list-category.jsp"
        ).forward(req, resp);
    }
}