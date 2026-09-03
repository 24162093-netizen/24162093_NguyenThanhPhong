<%@ page contentType="text/html; charset=UTF-8" %>

<%@ taglib prefix="c"
    uri="jakarta.tags.core" %>

<!DOCTYPE html>

<html>

<head>

    <meta charset="UTF-8">

    <title>Đăng ký</title>

</head>

<body>

    <h2>ĐĂNG KÝ TÀI KHOẢN</h2>


    <!-- Thông báo -->

    <c:if test="${not empty alert}">

        <p style="color:red;">
            ${alert}
        </p>

    </c:if>


    <form
        action="${pageContext.request.contextPath}/register"
        method="post">


        <!-- Username -->

        <div>

            <label>
                Username:
            </label>

            <input
                type="text"
                name="username"
                required>

        </div>

        <br>


        <!-- Password -->

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


        <!-- Email -->

        <div>

            <label>
                Email:
            </label>

            <input
                type="email"
                name="email"
                required>

        </div>

        <br>


        <!-- Full name -->

        <div>

            <label>
                Họ và tên:
            </label>

            <input
                type="text"
                name="fullname"
                required>

        </div>

        <br>


        <!-- Phone -->

        <div>

            <label>
                Số điện thoại:
            </label>

            <input
                type="text"
                name="phone">

        </div>

        <br>


        <!-- Button -->

        <button type="submit">
            Đăng ký
        </button>


    </form>


    <br>


    <a
        href="${pageContext.request.contextPath}/login">

        Đã có tài khoản? Đăng nhập

    </a>


</body>

</html>