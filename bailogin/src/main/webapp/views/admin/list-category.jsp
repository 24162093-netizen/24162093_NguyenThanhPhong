<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>

<html>

<head>

    <meta charset="UTF-8">

    <title>Quản lý danh mục</title>

    <style>

        * {
            box-sizing: border-box;
        }

        body {
            margin: 0;
            font-family: Arial, Helvetica, sans-serif;
            background-color: #f5f5f5;
        }


        /* ================= HEADER ================= */

        .header {
            height: 75px;
            background-color: #0798f5;
            color: white;
            display: flex;
            align-items: center;
            justify-content: space-between;
            padding: 0 35px;
        }

        .header-title {
            font-size: 30px;
            font-weight: bold;
        }

        .header-right {
            display: flex;
            align-items: center;
            gap: 20px;
            font-size: 15px;
        }

        .logout {
            background-color: #ff4b4b;
            color: white;
            text-decoration: none;
            padding: 10px 18px;
            border-radius: 2px;
        }

        .logout:hover {
            background-color: #e63b3b;
        }


        /* ================= LAYOUT ================= */

        .main {
            display: flex;
            min-height: calc(100vh - 75px);
        }


        /* ================= SIDEBAR ================= */

        .sidebar {
            width: 255px;
            background-color: #0798f5;
            color: white;
            flex-shrink: 0;
        }


        /* PROFILE */

        .profile {
            text-align: center;
            padding: 30px 10px 25px;
        }

        .profile-image {
            width: 125px;
            height: 125px;
            border-radius: 50%;
            object-fit: cover;
            border: 5px solid #1bc6ed;
            background-color: white;
        }

        .profile-name {
            margin-top: 18px;
            font-size: 15px;
        }


        /* MENU */

        .menu {
            list-style: none;
            margin: 0;
            padding: 0;
        }

        .menu-item {
            border-bottom: 1px solid rgba(255,255,255,0.12);
        }

        .menu-link {
            display: block;
            color: white;
            text-decoration: none;
            padding: 18px 20px;
            font-size: 15px;
        }

        .menu-link:hover {
            background-color: #0786d5;
        }

        .menu-link.active {
            background-color: #111111;
        }


        /* SUB MENU */

        .submenu {
            list-style: none;
            margin: 0;
            padding: 0;
        }

        .submenu li {
            border-bottom: 1px solid rgba(255,255,255,0.08);
        }

        .submenu a {
            display: block;
            color: white;
            text-decoration: none;
            padding: 12px 35px;
            font-size: 14px;
        }

        .submenu a:hover {
            background-color: #0786d5;
        }


        /* ================= CONTENT ================= */

        .content {
            flex: 1;
            background-color: white;
            margin: 30px;
            padding: 0 10px 30px 10px;
            min-height: 650px;
        }


        /* TITLE */

        .content-title {
            padding: 20px 0;
            border-bottom: 1px solid #eeeeee;
            margin-bottom: 18px;
        }

        .content-title h2 {
            margin: 0;
            color: red;
            font-size: 28px;
            font-weight: normal;
        }

        .content-title p {
            margin: 8px 0 0;
            color: #777777;
            font-size: 14px;
        }


        /* ================= CARD ================= */

        .card {
            border: 1px solid #dddddd;
            border-radius: 3px;
            background-color: white;
        }

        .card-title {
            background-color: #f5f5f5;
            border-bottom: 1px solid #dddddd;
            padding: 13px;
            color: #555555;
            font-size: 14px;
        }


        /* ================= TABLE TOP ================= */

        .table-top {
            display: flex;
            justify-content: space-between;
            align-items: center;
            padding: 14px;
        }

        .records {
            color: #555555;
            font-size: 13px;
        }

        .records select {
            padding: 6px 25px 6px 8px;
            border: 1px solid #cccccc;
            border-radius: 3px;
        }


        /* SEARCH */

        .search {
            display: flex;
            align-items: center;
            gap: 5px;
            font-size: 13px;
        }

        .search input {
            width: 145px;
            height: 30px;
            padding: 5px 8px;
            border: 1px solid #cccccc;
            border-radius: 3px;
        }


        /* ================= ADD BUTTON ================= */

        .add-button-area {
            padding: 0 14px 15px;
        }

        .add-button {
            display: inline-block;
            background-color: #0798f5;
            color: white;
            text-decoration: none;
            padding: 9px 15px;
            border-radius: 3px;
            font-size: 14px;
        }

        .add-button:hover {
            background-color: #0786d5;
        }


        /* ================= TABLE ================= */

        .category-table {
            width: 100%;
            border-collapse: collapse;
            table-layout: fixed;
        }

        .category-table th,
        .category-table td {
            border: 1px solid #dddddd;
            padding: 12px;
            vertical-align: middle;
        }

        .category-table th {
            background-color: #f8f8f8;
            color: #444444;
            font-size: 14px;
            text-align: left;
        }

        .category-table td {
            font-size: 14px;
            color: #555555;
        }


        /* WIDTH */

        .col-stt {
            width: 80px;
        }

        .col-image {
            width: 35%;
        }

        .col-name {
            width: 28%;
        }

        .col-action {
            width: 180px;
        }


        /* ================= CATEGORY IMAGE ================= */

        .category-image {
            width: 120px;
            height: 120px;
            object-fit: contain;
            border-radius: 50%;
            background-color: #f5f5f5;
            padding: 5px;
        }

        .no-image {
            color: #999999;
        }


        /* ================= ACTION ================= */

        .action-edit,
        .action-delete {
            color: #168de2;
            text-decoration: none;
            margin-right: 5px;
        }

        .action-edit:hover,
        .action-delete:hover {
            text-decoration: underline;
        }


        /* ================= EMPTY ================= */

        .empty {
            text-align: center;
            padding: 30px;
            color: #888888;
        }

    </style>

</head>


<body>


<!-- ================================================= -->
<!-- HEADER -->
<!-- ================================================= -->

<div class="header">

    <div class="header-title">
        Dashboard
    </div>

    <div class="header-right">

        <span>
            Xin chào
            <b>
                ${sessionScope.account.fullName}
            </b>
        </span>

        <a
            class="logout"
            href="${pageContext.request.contextPath}/logout">

            Đăng xuất

        </a>

    </div>

</div>



<!-- ================================================= -->
<!-- MAIN -->
<!-- ================================================= -->

<div class="main">


    <!-- ================================================= -->
    <!-- SIDEBAR -->
    <!-- ================================================= -->

    <div class="sidebar">


        <!-- PROFILE -->

        <div class="profile">

            <img
                class="profile-image"
                src="https://ui-avatars.com/api/?name=Admin&size=150"
                alt="Admin">

            <div class="profile-name">

                Bạn là Admin

            </div>

        </div>



        <!-- MENU -->

        <ul class="menu">


            <!-- DASHBOARD -->

            <li class="menu-item">

                <a
                    class="menu-link"
                    href="#">

                    🏠 &nbsp;&nbsp; Dashboard

                </a>

            </li>



            <!-- CATEGORY -->

            <li class="menu-item">

                <a
                    class="menu-link active"
                    href="#">

                    📁 &nbsp;&nbsp; Quản lý Danh mục

                </a>

                <ul class="submenu">

                    <li>

                        <a
                            href="${pageContext.request.contextPath}/admin/category/add">

                            ├─ Thêm danh mục mới

                        </a>

                    </li>

                    <li>

                        <a
                            href="${pageContext.request.contextPath}/admin/category/list">

                            ├─ Danh sách danh mục

                        </a>

                    </li>

                </ul>

            </li>



            <!-- PRODUCT -->

            <li class="menu-item">

                <a
                    class="menu-link"
                    href="#">

                    🖥 &nbsp;&nbsp; Quản lý sản phẩm

                </a>

            </li>



            <!-- ACCOUNT -->

            <li class="menu-item">

                <a
                    class="menu-link"
                    href="#">

                    ▣ &nbsp;&nbsp; Quản lý tài khoản

                </a>

            </li>


        </ul>

    </div>



    <!-- ================================================= -->
    <!-- CONTENT -->
    <!-- ================================================= -->

    <div class="content">


        <!-- TITLE -->

        <div class="content-title">

            <h2>
                Quản lý danh mục
            </h2>

            <p>
                Nơi bạn có thể quản lý danh mục của mình
            </p>

        </div>



        <!-- CARD -->

        <div class="card">


            <div class="card-title">

                Danh sách danh mục

            </div>



            <!-- TOP -->

            <div class="table-top">

                <div class="records">

                    <select>

                        <option>
                            10
                        </option>

                        <option>
                            25
                        </option>

                        <option>
                            50
                        </option>

                    </select>

                    records per page

                </div>



                <div class="search">

                    Search:

                    <input
                        type="text"
                        id="searchInput"
                        placeholder="">

                </div>

            </div>



            <!-- ADD BUTTON -->

            <div class="add-button-area">

                <a
                    class="add-button"
                    href="${pageContext.request.contextPath}/admin/category/add">

                    + Thêm danh mục mới

                </a>

            </div>



            <!-- TABLE -->

            <table
                class="category-table"
                id="categoryTable">


                <thead>

                    <tr>

                        <th class="col-stt">
                            STT
                        </th>

                        <th class="col-image">
                            Hình ảnh
                        </th>

                        <th class="col-name">
                            Tên danh mục
                        </th>

                        <th class="col-action">
                            Hành động
                        </th>

                    </tr>

                </thead>


                <tbody>


                    <c:choose>


                        <c:when test="${not empty cateList}">


                            <c:forEach
                                items="${cateList}"
                                var="cate"
                                varStatus="status">


                                <tr>


                                    <!-- STT -->

                                    <td>

                                        ${status.index + 1}

                                    </td>



                                    <!-- IMAGE -->

                                    <td>

                                        <c:choose>


                                            <c:when test="${not empty cate.icon}">

                                                <img
                                                    class="category-image"
                                                    src="${pageContext.request.contextPath}/image?fname=${cate.icon}"
                                                    alt="${cate.name}">

                                            </c:when>


                                            <c:otherwise>

                                                <span class="no-image">

                                                    Chưa có hình ảnh

                                                </span>

                                            </c:otherwise>


                                        </c:choose>

                                    </td>



                                    <!-- NAME -->

                                    <td>

                                        ${cate.name}

                                    </td>



                                    <!-- ACTION -->

                                    <td>


                                        <a
                                            class="action-edit"
                                            href="${pageContext.request.contextPath}/admin/category/edit?id=${cate.id}">

                                            Sửa

                                        </a>


                                        |


                                        <a
                                            class="action-delete"
                                            href="${pageContext.request.contextPath}/admin/category/delete?id=${cate.id}"
                                            onclick="return confirm('Bạn có chắc chắn muốn xóa danh mục này không?');">

                                            Xóa

                                        </a>


                                    </td>


                                </tr>


                            </c:forEach>


                        </c:when>


                        <c:otherwise>


                            <tr>

                                <td
                                    colspan="4"
                                    class="empty">

                                    Chưa có danh mục nào.

                                </td>

                            </tr>


                        </c:otherwise>


                    </c:choose>


                </tbody>


            </table>


        </div>


    </div>


</div>



<!-- ================================================= -->
<!-- SEARCH -->
<!-- ================================================= -->

<script>

    document
        .getElementById("searchInput")
        .addEventListener("keyup", function () {

            let keyword =
                this.value.toLowerCase();

            let rows =
                document.querySelectorAll(
                    "#categoryTable tbody tr"
                );


            rows.forEach(function (row) {

                let text =
                    row.innerText.toLowerCase();

                if (text.includes(keyword)) {

                    row.style.display = "";

                } else {

                    row.style.display = "none";

                }

            });

        });

</script>


</body>

</html>