<%@ page contentType="text/html; charset=UTF-8" %>

<%@ taglib prefix="c"
    uri="jakarta.tags.core" %>

<!DOCTYPE html>

<html>

<head>

    <meta charset="UTF-8">

    <title>Login</title>

</head>

<body>

    <h2>ĐĂNG NHẬP</h2>

    <c:if test="${not empty alert}">

        <p style="color:red;">
            ${alert}
        </p>

    </c:if>


    <form
        action="${pageContext.request.contextPath}/login"
        method="post">


        <div>

            <label>
                Username:
            </label>

            <input
                type="text"
                name="username"
                value="${savedUsername}">

        </div>


        <br>


        <div>

            <label>
                Password:
            </label>

            <input
                type="password"
                name="password">

        </div>


        <br>


        <div>

            <input
                type="checkbox"
                name="remember">

            Remember me

        </div>


        <br>


        <button type="submit">
    Login
</button>

<br><br>

<a href="${pageContext.request.contextPath}/register">
    Chưa có tài khoản? Đăng ký
</a>

    </form>

</body>

</html>