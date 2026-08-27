<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <title>Sửa danh mục</title>
</head>

<body>

<h2>SỬA DANH MỤC</h2>

<c:if test="${not empty error}">
    <p style="color:red;">
        ${error}
    </p>
</c:if>

<form
    action="${pageContext.request.contextPath}/admin/category/edit"
    method="post">

    <input
        type="hidden"
        name="id"
        value="${category.id}">

    <div>

        <label>Tên danh mục:</label>

        <input
            type="text"
            name="name"
            value="${category.name}"
            required>

    </div>

    <br>

    <div>

        <label>Tên hình ảnh:</label>

        <input
            type="text"
            name="icon"
            value="${category.icon}">

    </div>

    <br>

    <button type="submit">
        Lưu thay đổi
    </button>

    <a href="${pageContext.request.contextPath}/admin/category/list">
        Quay lại
    </a>

</form>

</body>
</html>