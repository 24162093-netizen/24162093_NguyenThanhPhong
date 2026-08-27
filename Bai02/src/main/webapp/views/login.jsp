<%@ page contentType="text/html; charset=UTF-8" %>

<%@ taglib prefix="c"
    uri="jakarta.tags.core" %>

<!DOCTYPE html>

<html>

<head>

    <meta charset="UTF-8">

    <title>Đăng nhập</title>

</head>

<body>

    <h2>ĐĂNG NHẬP</h2>

    <!-- Thông báo -->

    <c:if test="${not empty alert}">

        <p style="color:red;">
            ${alert}
        </p>

    </c:if>


    <form
        action="${pageContext.request.contextPath}/login"
        method="post">

        <!-- USERNAME -->

        <div>

            <label>
                Username:
            </label>

            <input
                type="text"
                name="username"
                value="${savedUsername}"
                required>

        </div>

        <br>


        <!-- PASSWORD -->

        <div>

            <label>
                Password:
            </label>

            <input
                type="password"
                name="password"
                required>

        </div>

        <br>


        <!-- REMEMBER -->

        <div>

            <input
                type="checkbox"
                name="remember"
                value="on">

            Remember me

        </div>

        <br>


        <!-- BUTTON -->

        <button type="submit">
            Login
        </button>

    </form>

</body>

</html>