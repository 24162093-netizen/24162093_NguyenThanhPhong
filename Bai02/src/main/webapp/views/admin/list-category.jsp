<%@ page contentType="text/html; charset=UTF-8" %>

<%@ taglib prefix="c"
    uri="jakarta.tags.core" %>

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

            height: 70px;

            background-color: #0798f5;

            color: white;

            display: flex;

            align-items: center;

            justify-content: space-between;

            padding: 0 30px;

            margin-bottom: 30px;
        }

        .header-title {

            font-size: 28px;

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

            border-radius: 5px;
        }

        .logout:hover {

            background-color: #d93636;
        }


        /* ================= CONTENT ================= */

        .content {

            margin: 30px auto;

            width: 90%;

            background-color: white;

            padding: 30px;

            min-height: 500px;
        }


        h2 {

            color: #111;

            margin-bottom: 30px;
        }


        /* ================= ADD ================= */

        .add-button {

            display: inline-block;

            margin-bottom: 25px;

            color: #0798f5;

            text-decoration: none;

            font-size: 18px;
        }

        .add-button:hover {

            text-decoration: underline;
        }


        /* ================= TABLE ================= */

        table {

            width: 100%;

            border-collapse: collapse;
        }

        th,
        td {

            border: 1px solid #ddd;

            padding: 15px;

            text-align: left;
        }

        th {

            background-color: #eeeeee;

            text-align: center;
        }

        td:first-child {

            text-align: center;

            width: 100px;
        }


        /* ================= ACTION ================= */

        .action {

            text-decoration: none;

            margin-right: 8px;
        }

        .action:hover {

            text-decoration: underline;
        }


        /* ================= EMPTY ================= */

        .empty {

            text-align: center;

            padding: 30px;

            color: #888;
        }

    </style>

</head>


<body>


<!-- ================================================= -->
<!-- HEADER -->
<!-- ================================================= -->

<div class="header">

    <div class="header-title">

        ADMIN

    </div>


    <div class="header-right">

        <span>

            Xin chào,

            <b>

                ${sessionScope.account.fullName}

            </b>

        </span>


        <a
            class="logout"
            href="${pageContext.request.contextPath}/logout"
            onclick="return confirm('Bạn có chắc chắn muốn đăng xuất không?');">

            Đăng xuất

        </a>

    </div>

</div>


<!-- ================================================= -->
<!-- CONTENT -->
<!-- ================================================= -->

<div class="content">


    <h2>

        QUẢN LÝ DANH MỤC

    </h2>


    <a
        class="add-button"
        href="${pageContext.request.contextPath}/admin/category/add">

        + Thêm danh mục

    </a>


    <table>

        <thead>

            <tr>

                <th>ID</th>

                <th>Tên danh mục</th>

                <th>Icon</th>

                <th>Hành động</th>

            </tr>

        </thead>


        <tbody>


            <c:choose>


                <c:when test="${not empty cateList}">


                    <c:forEach
                        items="${cateList}"
                        var="cate">


                        <tr>


                            <td>

                                ${cate.id}

                            </td>


                            <td>

                                ${cate.name}

                            </td>


                            <td>

                                ${cate.icon}

                            </td>


                            <td>


                                <a
                                    class="action"
                                    href="${pageContext.request.contextPath}/admin/category/edit?id=${cate.id}">

                                    Sửa

                                </a>

                                |

                                <a
                                    class="action"
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


</body>

</html>