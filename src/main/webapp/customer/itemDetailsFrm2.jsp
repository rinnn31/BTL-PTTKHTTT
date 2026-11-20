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
    <title>${item.name} - Hệ thống siêu thị điện máy</title>
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
            gap: 12px;
        }

        .nav-btn {
            background-color: rgba(255, 255, 255, 0.15);
            color: #ffffff;
            padding: 6px 14px;
            border-radius: 4px;
            text-decoration: none;
            font-size: 13px;
            font-weight: 500;
            transition: background-color 0.2s;
        }

        .nav-btn:hover {
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
            padding: 28px;
            box-shadow: 0 2px 10px rgba(0, 0, 0, 0.05);
            margin-bottom: 24px;
        }

        .product-main {
            display: grid;
            grid-template-columns: 1fr 1.15fr;
            gap: 36px;
            align-items: start;
        }

        @media (max-width: 768px) {
            .product-main {
                grid-template-columns: 1fr;
            }
        }

        .image-box {
            width: 100%;
            aspect-ratio: 1 / 1;
            border: 1px solid #e2e8f0;
            border-radius: 8px;
            overflow: hidden;
            background-color: #fff;
            display: flex;
            align-items: center;
            justify-content: center;
            padding: 12px;
        }

        .image-box img {
            width: 100%;
            height: 100%;
            object-fit: contain;
        }

        .info-box {
            display: flex;
            flex-direction: column;
            gap: 14px;
        }

        .badge-category {
            display: inline-block;
            align-self: flex-start;
            padding: 4px 10px;
            font-size: 12px;
            font-weight: 600;
            border-radius: 4px;
            background-color: #dbeafe;
            color: #1e40af;
            text-transform: uppercase;
        }

        .product-title {
            font-size: 26px;
            font-weight: 700;
            color: #0f172a;
            line-height: 1.3;
        }

        .meta-list {
            display: flex;
            flex-wrap: wrap;
            gap: 20px;
            font-size: 14px;
            color: #475569;
            padding-bottom: 14px;
            border-bottom: 1px solid #e2e8f0;
        }

        .meta-list span strong {
            color: #1e293b;
        }

        .price-section {
            background-color: #f8fafc;
            padding: 16px 20px;
            border-radius: 8px;
            display: flex;
            align-items: baseline;
            gap: 14px;
            border: 1px solid #e2e8f0;
        }

        .sale-price {
            font-size: 20px;
            font-weight: 700;
            color: #0b8dff;
        }

        .original-price {
            font-size: 16px;
            color: #dc2626;
            text-decoration: line-through;
        }

        .stock-row {
            font-size: 14px;
            color: #475569;
        }

        .stock-available {
            color: #059669;
            font-weight: 700;
        }

        .stock-out {
            color: #dc2626;
            font-weight: 700;
        }

        .cart-badge-info {
            font-size: 13px;
            color: #2563eb;
            background-color: #eff6ff;
            padding: 8px 12px;
            border-radius: 6px;
            border: 1px solid #bfdbfe;
        }

        .add-cart-form {
            display: flex;
            align-items: flex-start;
            gap: 14px;
            margin-top: 8px;
            flex-wrap: wrap;
            flex-direction: column;
        }

        .qty-group {
            display: flex;
            align-items: center;
            border: 1px solid #cbd5e1;
            border-radius: 6px;
            overflow: hidden;
            background: #fff;
        }

        .qty-btn {
            width: 38px;
            height: 42px;
            border: none;
            background-color: #f1f5f9;
            font-size: 18px;
            font-weight: 600;
            color: #334155;
            cursor: pointer;
            transition: background-color 0.2s;
        }

        .qty-btn:hover {
            background-color: #e2e8f0;
        }

        .qty-input {
            width: 64px;
            height: 42px;
            border: none;
            border-left: 1px solid #cbd5e1;
            border-right: 1px solid #cbd5e1;
            text-align: center;
            font-size: 15px;
            font-weight: 600;
            outline: none;
        }

        .btn-add-cart {
            background-color: #2563eb;
            color: #ffffff;
            height: 42px;
            padding: 0 28px;
            border: none;
            border-radius: 6px;
            font-size: 15px;
            font-weight: 600;
            cursor: pointer;
            transition: background-color 0.2s;
        }

        .btn-add-cart:hover {
            background-color: #1d4ed8;
        }

        .btn-add-cart:disabled {
            background-color: #94a3b8;
            cursor: not-allowed;
        }

        .section-title {
            font-size: 18px;
            font-weight: 700;
            color: #1e293b;
            margin-bottom: 16px;
            padding-bottom: 10px;
            border-bottom: 2px solid #e2e8f0;
        }

        .rich-description {
            font-size: 15px;
            line-height: 1.7;
            color: #334155;
            white-space: pre-line;
        }

        .specs-table {
            width: 100%;
            border-collapse: collapse;
            font-size: 14px;
        }

        .specs-table tr:nth-child(odd) {
            background-color: #f8fafc;
        }

        .specs-table td {
            padding: 12px 16px;
            border: 1px solid #e2e8f0;
        }

        .specs-table td.spec-key {
            width: 32%;
            font-weight: 600;
            color: #475569;
        }

        .specs-table td.spec-val {
            color: #1e293b;
        }
    </style>
</head>
<body>
    <nav class="navbar">
        <div class="navbar-brand">
            Hệ thống siêu thị điện máy
        </div>
        <div class="navbar-user">
            <a href="${pageContext.request.contextPath}/items/list" class="nav-btn">Danh sách sản phẩm</a>
            <a href="${pageContext.request.contextPath}/cart/view" class="nav-btn">
                Giỏ hàng
                <c:if test="${not empty sessionScope.cartDetails}">
                    (${sessionScope.cartDetails.size()})
                </c:if>
            </a>
        </div>
    </nav>

    <div class="container">
        <div class="card">
            <div class="product-main">
                <div class="image-box">
                    <c:choose>
                        <c:when test="${not empty item.image}">
                            <img src="${pageContext.request.contextPath}/${item.image}" alt="${item.name}">
                        </c:when>
                        <c:otherwise>
                            <img src="${pageContext.request.contextPath}/images/default-product.png" alt="No image">
                        </c:otherwise>
                    </c:choose>
                </div>

                <div class="info-box">
                    <span class="badge-category">${item.category}</span>
                    <h1 class="product-title">${item.name}</h1>

                    <div class="meta-list">
                        <span>Mã mặt hàng: <strong>${item.model}</strong></span>
                        <span>Thương hiệu: <strong>${item.brand}</strong></span>
                    </div>

                    <div class="price-section">
                        <span class="sale-price">
                            <fmt:formatNumber value="${item.price}" type="number" groupingUsed="true"/> đ
                        </span>
                        <c:if test="${item.originalPrice != null && item.originalPrice > item.price}">
                            <span class="original-price">
                                <fmt:formatNumber value="${item.originalPrice}" type="number" groupingUsed="true"/> đ
                            </span>
                        </c:if>
                    </div>

                    <div class="stock-row">
                        Tình trạng kho: 
                        <c:choose>
                            <c:when test="${item.stocks > 0}">
                                <span class="stock-available">Còn ${item.stocks} sản phẩm</span>
                            </c:when>
                            <c:otherwise>
                                <span class="stock-out">Hết hàng</span>
                            </c:otherwise>
                        </c:choose>
                    </div>

                    <form action="${pageContext.request.contextPath}/cart/add" method="post" class="add-cart-form">
                        <input type="hidden" name="itemId" value="${item.id}">
                        
                        <div class="qty-group">
                            <button type="button" class="qty-btn" onclick="changeQty(-1)">−</button>
                            <input type="number" id="quantityInput" name="quantity" class="qty-input"
                                   value="1" min="1" max="${item.stocks > 0 ? item.stocks : 1}" required>
                            <button type="button" class="qty-btn" onclick="changeQty(1)">+</button>
                        </div>

                        <button type="submit" class="btn-add-cart" ${item.stocks <= 0 ? 'disabled' : ''}>
                            Thêm vào giỏ hàng
                        </button>
                    </form>
                </div>
            </div>
        </div>

        <div class="card">
            <h2 class="section-title">Mô tả sản phẩm</h2>
            <div class="rich-description">
                <c:choose>
                    <c:when test="${not empty item.description}">
                        <c:out value="${item.description}" escapeXml="false" />
                    </c:when>
                    <c:otherwise>
                        <span style="color: #64748b;">Chưa có mô tả chi tiết cho sản phẩm này.</span>
                    </c:otherwise>
                </c:choose>
            </div>
        </div>

        <div class="card">
            <h2 class="section-title">Thông số kỹ thuật</h2>
            <table class="specs-table">
                <tbody id="specsTableBody">
                </tbody>
            </table>
        </div>
    </div>

    <script>
        const maxStock = ${item.stocks != null ? item.stocks : 0};
        const rawSpecsJson = `<c:out value="${item.specs}" escapeXml="false" />`;

        function changeQty(delta) {
            const input = document.getElementById('quantityInput');
            let current = parseInt(input.value) || 1;
            let next = current + delta;
            if (next < 1) next = 1;
            if (maxStock > 0 && next > maxStock) next = maxStock;
            input.value = next;
        }

        function renderSpecsTable() {
            const tbody = document.getElementById('specsTableBody');
            tbody.innerHTML = '';

            try {
                if (rawSpecsJson && rawSpecsJson.trim() !== '') {
                    const specsObj = JSON.parse(rawSpecsJson);
                    const entries = Object.entries(specsObj);

                    if (entries.length > 0) {
                        entries.forEach(([key, value]) => {
                            const tr = document.createElement('tr');
                            const tdKey = document.createElement('td');
                            tdKey.className = 'spec-key';
                            tdKey.textContent = key;

                            const tdVal = document.createElement('td');
                            tdVal.className = 'spec-val';
                            tdVal.textContent = value;

                            tr.appendChild(tdKey);
                            tr.appendChild(tdVal);
                            tbody.appendChild(tr);
                        });
                        return;
                    }
                }
            } catch (e) {
                console.error("Lỗi parse JSON specs:", e);
            }

            tbody.innerHTML = `<tr><td colspan="2" style="text-align: center; color: #64748b;">Chưa có thông số kỹ thuật.</td></tr>`;
        }

        window.onload = renderSpecsTable;
    </script>
</body>
</html>