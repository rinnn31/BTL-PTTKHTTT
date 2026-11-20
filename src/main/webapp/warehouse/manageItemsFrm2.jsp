<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="com.github.rinnn31.pttkhttt.quanlysieuthi.model.User2" %>
<%@ page import="com.github.rinnn31.pttkhttt.quanlysieuthi.model.Staff2" %>
<%
    Object u = session.getAttribute("user");
    boolean allowed = false;
    if (u instanceof Staff2) {
        Staff2 s = (Staff2) u;
        allowed = Staff2.ROLE_WAREHOUSE.equalsIgnoreCase(s.getRole());
    }
    if (!allowed) {
        response.sendRedirect(request.getContextPath() + "/login");
        return;
    }
%>


<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Quản lý mặt hàng - Hệ thống siêu thị điện máy</title>
    <style>
        * {
            margin: 0;
            padding: 0;
            box-sizing: border-box;
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
        }

        body {
            background-color: #f4f6f9;
            color: #333;
        }

        .navbar {
            background-color: #1e3a8a;
            color: #ffffff;
            padding: 16px 32px;
            display: flex;
            justify-content: space-between;
            align-items: center;
            box-shadow: 0 2px 6px rgba(0, 0, 0, 0.15);
        }

        .navbar-brand {
            font-size: 20px;
            font-weight: 700;
            letter-spacing: 0.5px;
            text-decoration: none;
            color: #ffffff;
        }

        .navbar-user {
            font-size: 14px;
            display: flex;
            align-items: center;
            gap: 16px;
        }

        .back-btn {
            background-color: rgba(255, 255, 255, 0.15);
            color: #ffffff;
            padding: 6px 14px;
            border-radius: 4px;
            text-decoration: none;
            font-size: 13px;
            transition: background-color 0.2s;
        }

        .back-btn:hover {
            background-color: rgba(255, 255, 255, 0.25);
        }

        .container {
            max-width: 1200px;
            margin: 30px auto;
            padding: 0 20px;
        }

        .card {
            background-color: #ffffff;
            border-radius: 8px;
            padding: 24px;
            box-shadow: 0 2px 10px rgba(0, 0, 0, 0.05);
        }

        .card-header {
            display: flex;
            justify-content: space-between;
            align-items: center;
            margin-bottom: 24px;
            flex-wrap: wrap;
            gap: 15px;
        }

        .card-header h2 {
            color: #1e293b;
            font-size: 22px;
        }
        
        .search-form {
            display: flex;
            gap: 10px;
        }

        .search-input {
            padding: 8px 14px;
            font-size: 14px;
            border: 1px solid #cbd5e1;
            border-radius: 6px;
            width: 260px;
            outline: none;
            transition: border-color 0.2s;
        }

        .search-input:focus {
            border-color: #2563eb;
        }

        .btn-search {
            background-color: #2563eb;
            color: #ffffff;
            padding: 8px 16px;
            border: none;
            border-radius: 6px;
            font-weight: 600;
            cursor: pointer;
            transition: background-color 0.2s;
        }

        .btn-search:hover {
            background-color: #1d4ed8;
        }

        .table-responsive {
            overflow-x: auto;
        }

        table {
            width: 100%;
            border-collapse: collapse;
            text-align: left;
            font-size: 14px;
        }

        th, td {
            padding: 12px 16px;
            border-bottom: 1px solid #e2e8f0;
        }

        th {
            background-color: #f8fafc;
            color: #475569;
            font-weight: 600;
            text-transform: uppercase;
            font-size: 12px;
            letter-spacing: 0.5px;
        }

        tr:hover {
            background-color: #f8fafc;
        }

        .product-img {
            width: 160px;
            height: 160px;
            object-fit: cover;
            border-radius: 8px;
            border: 1px solid #cbd5e1;
            display: block;
            background-color: #fff;
        }

        .btn-edit {
            background-color: #0ea5e9;
            color: white;
            padding: 6px 12px;
            border-radius: 4px;
            text-decoration: none;
            font-size: 13px;
            font-weight: 500;
            transition: background-color 0.2s;
            display: inline-block;
        }

        .btn-edit:hover {
            background-color: #0284c7;
        }

        .text-center {
            text-align: center;
        }
    </style>
</head>
<body>
    <nav class="navbar">
        <div class="navbar-brand">
            Hệ thống siêu thị điện máy
        </div>
        <div class="navbar-user">
            <a href="${pageContext.request.contextPath}/home" class="back-btn">Trang chủ</a>
        </div>
    </nav>

    <div class="container">
        <div class="card">
            <div class="card-header">
                <h2>Quản lý Mặt hàng</h2>
                
                <form action="${pageContext.request.contextPath}/items/manage" method="get" class="search-form">
                    <input type="text" name="keyword" class="search-input" value="${param.keyword}" placeholder="Nhập tên mặt hàng cần tìm...">
                    <button type="submit" class="btn-search">Tìm kiếm</button>
                </form>
            </div>

            <div class="table-responsive">
                <table>
                    <thead>
                        <tr>
                            <th class="text-center" style="width: 60px;">STT</th>
                            <th style="width:200px;">Hình ảnh</th>
                            <th>Mã mặt hàng</th>
                            <th>Tên mặt hàng</th>
                            <th>Đơn giá bán (VNĐ)</th>
                            <th class="text-center">Tồn kho</th>
                            <th class="text-center" style="width: 100px;">Hành động</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:if test="${items == null}">
                            <tr>
                                <td colspan="7" class="text-center" style="padding: 20px; color: #64748b;">
                                    Vui lòng nhập từ khóa tìm kiếm để hiển thị mặt hàng.
                                </td>
                            </tr>
                        </c:if>
                        <c:if test="${items != null and empty items}">
                            <tr>
                                <td colspan="7" class="text-center" style="padding: 20px; color: #64748b;">
                                    Không tìm thấy mặt hàng nào.
                                </td>
                            </tr>
                        </c:if>

                        <c:forEach var="item" items="${items}" varStatus="status">
                            <tr>
                                <td class="text-center">${status.index + 1}</td>
                                <td>
                                    <c:choose>
                                        <c:when test="${not empty item.image}">
                                            <img src="${pageContext.request.contextPath}/${item.image}" alt="${item.name}" class="product-img">
                                        </c:when>
                                        <c:otherwise>
                                            <img src="${pageContext.request.contextPath}/images/default-product.png" alt="No image" class="product-img">
                                        </c:otherwise>
                                    </c:choose>
                                </td>
                                <td>${item.model}</td>
                                <td>${item.name}</td>
                                <td>
                                    <fmt:formatNumber value="${item.price}" type="number" groupingUsed="true"/> đ
                                </td>
                                <td class="text-center">${item.stocks}</td>
                                <td class="text-center">
                                    <a href="${pageContext.request.contextPath}/items/edit?id=${item.id}" class="btn-edit">Sửa</a>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
    </div>

</body>
</html>