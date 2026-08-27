<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <title>Thêm danh mục</title>
</head>

<body>

<h2>THÊM DANH MỤC</h2>

<c:if test="${not empty error}">
    <p style="color:red;">
        ${error}
    </p>
</c:if>

<form
    action="${pageContext.request.contextPath}/admin/category/add"
    method="post">

    <div>
        <label>Tên danh mục:</label>

        <input
            type="text"
            name="name"
            required>
    </div>

    <br>

    <div>
        <label>Tên hình ảnh:</label>

        <input
            type="text"
            name="icon"
            placeholder="laptop.jpg">
    </div>

    <br>

    <button type="submit">
        Thêm
    </button>

    <a href="${pageContext.request.contextPath}/admin/category/list">
        Quay lại
    </a>

</form>

</body>
</html>