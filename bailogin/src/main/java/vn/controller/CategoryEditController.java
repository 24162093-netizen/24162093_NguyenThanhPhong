package vn.controller;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
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

@WebServlet("/admin/category/edit")
public class CategoryEditController extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private CategoryService categoryService =
            new CategoryServiceImpl();

    // Hiển thị form sửa
    @Override
    protected void doGet(
            HttpServletRequest req,
            HttpServletResponse resp)
            throws ServletException, IOException {

        try {

            int id = Integer.parseInt(
                    req.getParameter("id"));

            Category category =
                    categoryService.get(id);

            if (category == null) {

                resp.sendRedirect(
                        req.getContextPath()
                        + "/admin/category/list");

                return;
            }

            req.setAttribute(
                    "category",
                    category);

            req.getRequestDispatcher(
                    "/views/admin/edit-category.jsp")
                    .forward(req, resp);

        } catch (Exception e) {

            e.printStackTrace();

            resp.sendRedirect(
                    req.getContextPath()
                    + "/admin/category/list");
        }
    }

    // Xử lý cập nhật
    @Override
    protected void doPost(
            HttpServletRequest req,
            HttpServletResponse resp)
            throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");

        try {

            // Tạo đối tượng upload
            DiskFileItemFactory factory =
                    DiskFileItemFactory.builder().get();

            JakartaServletFileUpload upload =
                    new JakartaServletFileUpload(factory);

            // Đọc dữ liệu từ form
            List<FileItem> items =
                    upload.parseRequest(req);

            int id = 0;
            String name = null;
            String newIcon = null;

            // ==========================================
            // LẤY ID VÀ NAME
            // ==========================================

            for (FileItem item : items) {

                if (item.isFormField()) {

                    // Lấy ID
                    if ("id".equals(
                            item.getFieldName())) {

                        id = Integer.parseInt(
                                item.getString(
                                        StandardCharsets.UTF_8));
                    }

                    // Lấy tên Category
                    if ("name".equals(
                            item.getFieldName())) {

                        name = item.getString(
                                StandardCharsets.UTF_8);
                    }
                }
            }

            // ==========================================
            // LẤY CATEGORY CŨ
            // ==========================================

            Category oldCategory =
                    categoryService.get(id);

            if (oldCategory == null) {

                resp.sendRedirect(
                        req.getContextPath()
                        + "/admin/category/list");

                return;
            }

            // ==========================================
            // KIỂM TRA TÊN
            // ==========================================

            if (name == null ||
                name.trim().isEmpty()) {

                req.setAttribute(
                        "category",
                        oldCategory);

                req.setAttribute(
                        "error",
                        "Tên danh mục không được để trống.");

                req.getRequestDispatcher(
                        "/views/admin/edit-category.jsp")
                        .forward(req, resp);

                return;
            }

            // ==========================================
            // XỬ LÝ ẢNH MỚI
            // ==========================================

            for (FileItem item : items) {

                if (!item.isFormField()
                        && "icon".equals(
                                item.getFieldName())
                        && item.getSize() > 0) {

                    // Lấy tên file gốc
                    String originalFileName =
                            new File(
                                    item.getName())
                                    .getName();

                    // Lấy phần mở rộng
                    String extension = "";

                    int dot =
                            originalFileName.lastIndexOf(".");

                    if (dot >= 0) {

                        extension =
                                originalFileName
                                        .substring(dot);
                    }

                    // Tạo tên file mới
                    String fileName =
                            System.currentTimeMillis()
                            + extension;

                    // Tạo thư mục upload
                    File uploadDir =
                            new File(Constant.DIR);

                    if (!uploadDir.exists()) {

                        uploadDir.mkdirs();
                    }

                    // Tạo file
                    File file =
                            new File(
                                    uploadDir,
                                    fileName);

                    // Lưu file
                    item.write(file.toPath());

                    // Lưu tên file mới
                    newIcon = fileName;

                    break;
                }
            }

            // ==========================================
            // NẾU KHÔNG CHỌN ẢNH MỚI
            // GIỮ LẠI ẢNH CŨ
            // ==========================================

            if (newIcon == null ||
                newIcon.isEmpty()) {

                newIcon =
                        oldCategory.getIcon();
            }

            // ==========================================
            // TẠO CATEGORY MỚI
            // ==========================================

            Category category =
                    new Category();

            category.setId(id);

            category.setName(
                    name.trim());

            category.setIcon(
                    newIcon);

            // ==========================================
            // UPDATE DATABASE
            // ==========================================

            categoryService.edit(category);

            // Quay lại danh sách
            resp.sendRedirect(
                    req.getContextPath()
                    + "/admin/category/list");

        } catch (Exception e) {

            e.printStackTrace();

            resp.sendRedirect(
                    req.getContextPath()
                    + "/admin/category/list");
        }
    }
}