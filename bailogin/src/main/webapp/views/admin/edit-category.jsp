<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html>

<head>

    <meta charset="UTF-8">

    <title>Sửa danh mục</title>

    <style>

        body {
            font-family: Arial, sans-serif;
            background-color: #f5f5f5;
            margin: 0;
            padding: 40px;
        }

        .container {
            width: 600px;
            margin: 0 auto;
            background-color: white;
            padding: 30px;
            border-radius: 8px;
            box-shadow: 0 2px 10px rgba(0,0,0,0.1);
        }

        h2 {
            margin-bottom: 25px;
            color: #333;
        }

        .form-group {
            margin-bottom: 20px;
        }

        .form-group label {
            display: block;
            margin-bottom: 8px;
            font-weight: bold;
            color: #333;
        }

        .form-group input {
            width: 100%;
            padding: 10px;
            border: 1px solid #ccc;
            border-radius: 5px;
            box-sizing: border-box;
            font-size: 14px;
        }

        .form-group input:focus {
            outline: none;
            border-color: #0798f5;
        }

        .current-image {
            width: 120px;
            height: 120px;
            object-fit: contain;
            border: 1px solid #ddd;
            padding: 5px;
            margin-top: 5px;
        }

        .error {
            color: red;
            margin-bottom: 15px;
        }

        .button-group {
            margin-top: 25px;
        }

        .btn {
            display: inline-block;
            padding: 10px 20px;
            border: none;
            border-radius: 5px;
            text-decoration: none;
            cursor: pointer;
            font-size: 14px;
        }

        .btn-save {
            background-color: #0798f5;
            color: white;
        }

        .btn-save:hover {
            background-color: #057ac7;
        }

        .btn-back {
            background-color: #777;
            color: white;
            margin-left: 10px;
        }

        .btn-back:hover {
            background-color: #555;
        }

    </style>

</head>

<body>

<div class="container">

    <h2>Sửa danh mục</h2>

    <!-- Thông báo lỗi -->
    <c:if test="${not empty error}">

        <div class="error">
            ${error}
        </div>

    </c:if>

<form
    action="${pageContext.request.contextPath}/admin/category/edit"
    method="post"
    enctype="multipart/form-data">

    <!-- ID -->
    <input
        type="hidden"
        name="id"
        value="${category.id}">


    <!-- Tên -->
    <div class="form-group">

        <label for="name">
            Tên danh mục
        </label>

        <input
            type="text"
            id="name"
            name="name"
            value="${category.name}"
            required>

    </div>


    <!-- Ảnh hiện tại -->
    <div class="form-group">

        <label>
            Hình ảnh hiện tại
        </label>

        <c:if test="${not empty category.icon}">

            <img
                class="current-image"
                src="${pageContext.request.contextPath}/image?fname=${category.icon}"
                alt="${category.name}">

        </c:if>

    </div>


    <!-- Chọn ảnh mới -->
    <div class="form-group">

        <label for="icon">
            Chọn hình ảnh mới
        </label>

        <input
            type="file"
            id="icon"
            name="icon"
            accept="image/*">

        <small>
            Không chọn ảnh nếu muốn giữ ảnh hiện tại.
        </small>

    </div>


    <!-- Button -->
    <div class="button-group">

        <button
            type="submit"
            class="btn btn-save">

            Lưu thay đổi

        </button>

        <a
            href="${pageContext.request.contextPath}/admin/category/list"
            class="btn btn-back">

            Quay lại

        </a>

    </div>

</form>
</div>

</body>

</html>