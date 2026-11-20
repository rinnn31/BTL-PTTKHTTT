<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:if test="${empty sessionScope.user or sessionScope.user.type ne 'customer'}">
    <c:redirect url="/login" />
</c:if>

<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Giỏ hàng - Hệ thống siêu thị điện máy</title>
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

        .cart-layout {
            display: grid;
            grid-template-columns: 2fr 1.2fr;
            gap: 24px;
            align-items: start;
        }

        .cart-empty {
            display: flex;
            justify-content: center;
        }

        @media (max-width: 900px) {
            .cart-layout {
                grid-template-columns: 1fr;
            }
        }

        .card {
            background-color: #ffffff;
            border-radius: 8px;
            padding: 24px;
            box-shadow: 0 2px 10px rgba(0, 0, 0, 0.05);
        }

        .card h2 {
            color: #1e293b;
            font-size: 20px;
            margin-bottom: 20px;
            border-bottom: 2px solid #f1f5f9;
            padding-bottom: 10px;
        }

        table {
            width: 100%;
            border-collapse: collapse;
            text-align: left;
            font-size: 14px;
        }

        th, td {
            padding: 12px 10px;
            border-bottom: 1px solid #e2e8f0;
            vertical-align: middle;
        }

        th {
            background-color: #f8fafc;
            color: #475569;
            font-weight: 600;
            text-transform: uppercase;
            font-size: 11px;
            letter-spacing: 0.5px;
        }

        .product-img {
            width: 45px;
            height: 45px;
            object-fit: cover;
            border-radius: 4px;
            border: 1px solid #e2e8f0;
        }

        .btn {
            padding: 6px 12px;
            border: none;
            border-radius: 4px;
            font-weight: 600;
            cursor: pointer;
            font-size: 13px;
            text-decoration: none;
        }

        .btn-danger {
            background-color: #ef4444;
            color: white;
            padding: 5px 10px;
        }
        .btn-danger:hover { background-color: #dc2626; }

        .btn-primary {
            background-color: #2563eb;
            color: white;
            width: 100%;
            padding: 12px;
            font-size: 15px;
            margin-top: 15px;
        }
        .btn-primary:hover { background-color: #1d4ed8; }

        .form-group {
            margin-bottom: 14px;
        }

        .form-group label {
            display: block;
            font-size: 13px;
            font-weight: 600;
            color: #475569;
            margin-bottom: 5px;
        }

        .form-control {
            width: 100%;
            padding: 9px 12px;
            font-size: 14px;
            border: 1px solid #cbd5e1;
            border-radius: 6px;
            outline: none;
        }
        .form-control:focus {
            border-color: #2563eb;
        }

        .summary-line {
            display: flex;
            justify-content: space-between;
            margin-bottom: 8px;
            font-size: 14px;
            color: #475569;
        }

        .summary-total {
            font-size: 17px;
            font-weight: 700;
            color: #1e293b;
            border-top: 1px solid #e2e8f0;
            padding-top: 12px;
            margin-top: 10px;
        }

        .alert {
            padding: 10px 14px;
            border-radius: 6px;
            font-size: 13px;
            margin-bottom: 15px;
            border: 1px solid transparent;
        }
        .alert-success {
            background-color: #d1fae5;
            color: #065f46;
            border-color: #a7f3d0;
        }
        .alert-danger {
            background-color: #fee2e2;
            color: #b91c1c;
            border-color: #fecaca;
        }

        .text-center { text-align: center; }
        .text-right { text-align: right; }
    </style>
</head>
<body>
    <nav class="navbar">
        <div class="navbar-brand">
            Hệ thống siêu thị điện máy
        </div>
        <div class="navbar-user">
            <a href="${pageContext.request.contextPath}/items/list" class="back-btn">Tiếp tục mua sắm</a>
        </div>
    </nav>

    <div class="container">
        <c:if test="${not empty successMessage}">
            <div class="alert alert-success">
                ${successMessage}
            </div>
        </c:if>
        <c:if test="${not empty errorMessage}">
            <div class="alert alert-danger">
                ${errorMessage}
            </div>
        </c:if>

        <c:if test="${empty sessionScope.cartDetails}">
            <div class="cart-empty">
                <div class="card text-center" style="width: 100%; max-width: 500px; padding: 40px;">
                    <h2>Giỏ hàng của bạn</h2>
                    <p style="color: #64748b; margin-bottom: 20px;">Giỏ hàng của bạn hiện đang trống.</p>
                </div>
            </div>
        </c:if>

        <c:if test="${not empty sessionScope.cartDetails}">
            <div class="cart-layout">
                <div class="card">
                    <h2>Chi tiết giỏ hàng</h2>
                    <table>
                        <thead>
                            <tr>
                                <th style="width: 60px;">Ảnh</th>
                                <th>Tên mặt hàng</th>
                                <th style="min-width: 100px;">Đơn giá</th>
                                <th class="text-center" style="width: 80px;">Số lượng</th>
                                <th class="text-right" style="min-width: 110px;">Thành tiền</th>
                                <th class="text-center" style="width: 70px;">Xóa</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:set var="totalAmount" value="0" />
                            <c:forEach var="detail" items="${sessionScope.cartDetails}">
                                <c:set var="unitPrice" value="${detail.unitPrice != null ? detail.unitPrice : detail.item.price}" />
                                <c:set var="subTotal" value="${unitPrice * detail.quantity}" />
                                <c:set var="totalAmount" value="${totalAmount + subTotal}" />
                                <tr>
                                    <td>
                                        <c:choose>
                                            <c:when test="${not empty detail.item.image}">
                                                <img src="${pageContext.request.contextPath}/${detail.item.image}" alt="${detail.item.name}" class="product-img">
                                            </c:when>
                                            <c:otherwise>
                                                <img src="${pageContext.request.contextPath}/images/default-product.png" alt="No image" class="product-img">
                                            </c:otherwise>
                                        </c:choose>
                                    </td>
                                    <td>${detail.item.name}</td>
                                    <td><fmt:formatNumber value="${unitPrice}" type="number" groupingUsed="true"/> đ</td>
                                    <td class="text-center">${detail.quantity}</td>
                                    <td class="text-right"><fmt:formatNumber value="${subTotal}" type="number" groupingUsed="true"/> đ</td>
                                    <td class="text-center">
                                        <form action="${pageContext.request.contextPath}/cart/remove" method="post" style="display:inline;">
                                            <input type="hidden" name="itemId" value="${detail.item.id}">
                                            <button type="submit" class="btn btn-danger">Xóa</button>
                                        </form>
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                        <tfoot>
                            <tr>
                                <td colspan="4" class="text-right" style="font-weight: 700; color: #1e293b; padding-top: 14px;">
                                    Tổng tiền hàng:
                                </td>
                                <td class="text-right" style="font-weight: 700; font-size: 15px; color: #2563eb; padding-top: 14px;">
                                    <fmt:formatNumber value="${totalAmount}" type="number" groupingUsed="true"/> đ
                                </td>
                                <td></td>
                            </tr>
                        </tfoot>
                    </table>
                </div>

                <div class="card">
                    <h2>Thông tin đặt hàng</h2>

                    <c:set var="fee" value="${empty deliveryFee ? 0 : deliveryFee}" />

                    <form action="${pageContext.request.contextPath}/cart/submit" method="post">
                        <div class="form-group">
                            <label>Số điện thoại liên hệ (*)</label>
                            <input type="text" name="contactPhone" class="form-control"
                                   value="${param.contactPhone != null ? param.contactPhone : (sessionScope.customer != null ? sessionScope.customer.phone : '')}"
                                   required placeholder="Nhập số điện thoại...">
                        </div>

                        <div class="form-group">
                            <label>Tỉnh / Thành phố (*)</label>
                            <input type="text" id="city" name="city" class="form-control" required
                                   placeholder="VD: Hà Nội" value="${param.city}">
                        </div>

                        <div class="form-group">
                            <label>Phường / Xã (*)</label>
                            <input type="text" id="ward" name="ward" class="form-control" required
                                   placeholder="VD: Phường Bến Nghé" value="${param.ward}">
                        </div>

                        <div class="form-group">
                            <label>Địa chỉ chi tiết(*)</label>
                            <input type="text" id="detailAddress" name="detailAddress" class="form-control" required
                                   placeholder="VD: 123 Nguyễn Huệ" value="${param.detailAddress}">
                        </div>

                        <div class="summary-line">
                            <span>Tiền hàng:</span>
                            <span><fmt:formatNumber value="${totalAmount}" type="number" groupingUsed="true"/> đ</span>
                        </div>
                        <div class="summary-line">
                            <span>Phí vận chuyển:</span>
                            <span><fmt:formatNumber value="${fee}" type="number" groupingUsed="true"/> đ</span>
                        </div>
                        <div class="summary-line summary-total">
                            <span>Tổng thanh toán:</span>
                            <span style="color: #2563eb;"><fmt:formatNumber value="${totalAmount + fee}" type="number" groupingUsed="true"/> đ</span>
                        </div>

                        <button type="submit" class="btn btn-primary">Xác nhận đặt hàng</button>
                    </form>
                </div>
            </div>
        </c:if>
    </div>
</body>
</html>