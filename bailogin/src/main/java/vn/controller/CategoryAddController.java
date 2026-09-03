package vn.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.commons.fileupload2.core.DiskFileItemFactory;
import org.apache.commons.fileupload2.core.FileItem;
import org.apache.commons.fileupload2.jakarta.servlet6.JakartaServletFileUpload;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import vn.constant.Constant;
import vn.model.Category;
import vn.service.CategoryService;
import vn.service.impl.CategoryServiceImpl;
import java.nio.charset.StandardCharsets;

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
                "/views/admin/add-category.jsp")
                .forward(req, resp);
    }

    @Override
    protected void doPost(
            HttpServletRequest req,
            HttpServletResponse resp)
            throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");

        Category category = new Category();

        DiskFileItemFactory factory =
                DiskFileItemFactory.builder().get();

        JakartaServletFileUpload upload =
                new JakartaServletFileUpload(factory);

        try {

            List<FileItem> items =
                    upload.parseRequest(req);

            for (FileItem item : items) {

                // Input text
                if (item.isFormField()) {

                    if ("name".equals(
                            item.getFieldName())) {
                    	String name =
                    	        item.getString(StandardCharsets.UTF_8);
                        if (name == null ||
                            name.trim().isEmpty()) {

                            req.setAttribute(
                                    "error",
                                    "Tên danh mục không được để trống.");

                            req.getRequestDispatcher(
                                    "/views/admin/add-category.jsp")
                                    .forward(req, resp);

                            return;
                        }

                        category.setName(
                                name.trim());
                    }

                }

                // File ảnh
                else {

                    if ("icon".equals(
                            item.getFieldName())
                            && item.getSize() > 0) {

                        String originalFileName =
                                new File(
                                        item.getName())
                                        .getName();

                        String extension = "";

                        int dot =
                                originalFileName.lastIndexOf(".");

                        if (dot >= 0) {

                            extension =
                                    originalFileName
                                            .substring(dot);
                        }

                        String fileName =
                                System.currentTimeMillis()
                                + extension;

                        File uploadDir =
                                new File(Constant.DIR);

                        if (!uploadDir.exists()) {
                            uploadDir.mkdirs();
                        }

                        File file =
                                new File(
                                        uploadDir,
                                        fileName);

                        item.write(file.toPath());

                        category.setIcon(
                                fileName);
                    }
                }
            }

            categoryService.insert(category);

            resp.sendRedirect(
                    req.getContextPath()
                    + "/admin/category/list");

        } catch (Exception e) {

            e.printStackTrace();

            req.setAttribute(
                    "error",
                    "Upload hình ảnh thất bại.");

            req.getRequestDispatcher(
                    "/views/admin/add-category.jsp")
                    .forward(req, resp);
        }
    }
}