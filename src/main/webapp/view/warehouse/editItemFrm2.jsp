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
    <title>Chỉnh sửa mặt hàng - Hệ thống siêu thị điện máy</title>
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

        .container {
            max-width: 800px;
            margin: 30px auto;
            padding: 0 20px;
        }

        .card {
            background-color: #ffffff;
            border-radius: 8px;
            padding: 30px;
            box-shadow: 0 2px 10px rgba(0, 0, 0, 0.05);
        }

        .card-header {
            margin-bottom: 24px;
            border-bottom: 1px solid #e2e8f0;
            padding-bottom: 15px;
        }

        .card-header h2 {
            color: #1e293b;
            font-size: 22px;
        }

        .alert {
            padding: 12px 16px;
            border-radius: 6px;
            margin-bottom: 20px;
            font-size: 14px;
        }

        .alert-success {
            background-color: #d1fae5;
            color: #065f46;
            border: 1px solid #a7f3d0;
        }

        .alert-danger {
            background-color: #fee2e2;
            color: #b91c1c;
            border: 1px solid #fecaca;
        }

        .form-row {
            display: grid;
            grid-template-columns: 1fr 1fr;
            gap: 16px;
        }

        @media (max-width: 600px) {
            .form-row {
                grid-template-columns: 1fr;
            }
        }

        .form-group {
            margin-bottom: 20px;
        }

        label {
            display: block;
            font-size: 13px;
            font-weight: 600;
            color: #475569;
            margin-bottom: 8px;
            text-transform: uppercase;
            letter-spacing: 0.5px;
        }

        input[type="text"],
        input[type="number"],
        textarea,
        select {
            width: 100%;
            padding: 10px 14px;
            font-size: 14px;
            border: 1px solid #cbd5e1;
            border-radius: 6px;
            outline: none;
            transition: border-color 0.2s;
            background-color: #fff;
        }

        input:focus, textarea:focus, select:focus {
            border-color: #2563eb;
            box-shadow: 0 0 0 3px rgba(37, 99, 235, 0.1);
        }

        textarea {
            resize: vertical;
            min-height: 100px;
        }

        .image-upload-container {
            display: flex;
            flex-direction: column;
            align-items: center;
            gap: 10px;
        }

        .preview-img-large {
            width: 180px;
            height: 180px;
            object-fit: cover;
            border-radius: 8px;
            border: 2px dashed #cbd5e1;
            cursor: pointer;
            transition: all 0.2s;
            background-color: #f8fafc;
        }

        .preview-img-large:hover {
            border-color: #2563eb;
            opacity: 0.9;
        }

        input[type="file"] {
            display: none;
        }

        .specs-container {
            border: 1px solid #e2e8f0;
            border-radius: 6px;
            padding: 15px;
            background-color: #f8fafc;
        }

        .specs-table {
            width: 100%;
            border-collapse: collapse;
            margin-bottom: 12px;
        }

        .specs-table th, .specs-table td {
            padding: 8px 10px;
            border-bottom: 1px solid #e2e8f0;
        }

        .specs-table th {
            background-color: #edf2f7;
            font-size: 12px;
            color: #475569;
            text-transform: uppercase;
        }

        .btn-sm {
            padding: 6px 12px;
            font-size: 13px;
            border-radius: 4px;
            cursor: pointer;
            font-weight: 600;
            border: none;
            transition: background-color 0.2s;
        }

        .btn-add {
            background-color: #10b981;
            color: white;
        }

        .btn-add:hover {
            background-color: #059669;
        }

        .btn-danger {
            background-color: #ef4444;
            color: white;
        }

        .btn-danger:hover {
            background-color: #dc2626;
        }

        .form-actions {
            display: flex;
            justify-content: flex-end;
            gap: 12px;
            margin-top: 30px;
            border-top: 1px solid #e2e8f0;
            padding-top: 20px;
        }

        .btn-submit {
            background-color: #2563eb;
            color: white;
            padding: 10px 20px;
            border: none;
            border-radius: 6px;
            font-weight: 600;
            cursor: pointer;
            transition: background-color 0.2s;
        }

        .btn-submit:hover {
            background-color: #1d4ed8;
        }

        .btn-back {
            background-color: #64748b;
            color: white;
            padding: 10px 20px;
            border-radius: 6px;
            text-decoration: none;
            font-weight: 600;
            display: inline-block;
            transition: background-color 0.2s;
        }

        .btn-back:hover {
            background-color: #475569;
        }
    </style>
</head>
<body>
    <nav class="navbar">
        <div class="navbar-brand">
            Hệ thống siêu thị điện máy
        </div>
    </nav>

    <div class="container">
        <div class="card">
            <div class="card-header">
                <h2>Chỉnh sửa thông tin mặt hàng</h2>
            </div>

            <c:if test="${not empty successMessage}">
                <div class="alert alert-success">${successMessage}</div>
            </c:if>
            <c:if test="${not empty errorMessage}">
                <div class="alert alert-danger">${errorMessage}</div>
            </c:if>
            <c:if test="${not empty param.success}">
                <div class="alert ${param.success == 'true' ? 'alert-success' : 'alert-danger'}">
                    ${param.success == 'true' ? 'Lưu mặt hàng thành công!' : 'Đã có lỗi xảy ra, vui lòng thử lại!'}
                </div>
            </c:if>

            <form action="${pageContext.request.contextPath}/items/edit?id=${item.id}" method="post" enctype="multipart/form-data" onsubmit="prepareSpecsJson()">
                <input type="hidden" name="id" value="${item.id}">
                <input type="hidden" name="image" value="${item.image}">
                <input type="hidden" name="specs" id="specsJsonInput" value="">

                <div class="form-group">
                    <label>Hình ảnh sản phẩm</label>
                    <div class="image-upload-container">
                        <c:choose>
                            <c:when test="${not empty item.image}">
                                <img id="imagePreview" src="${pageContext.request.contextPath}/${item.image}" alt="${item.name}" class="preview-img-large" onclick="triggerFileInput()" title="Nhấn để chọn ảnh mới">
                            </c:when>
                            <c:otherwise>
                                <img id="imagePreview" src="${pageContext.request.contextPath}/images/default-product.png" alt="No image" class="preview-img-large" onclick="triggerFileInput()" title="Nhấn để chọn ảnh mới">
                            </c:otherwise>
                        </c:choose>
                        <input type="file" id="imageFile" name="imageFile" accept="image/*" onchange="previewSelectedImage(this)">
                    </div>
                </div>

                <div class="form-group">
                    <label for="name">Tên mặt hàng</label>
                    <input type="text" id="name" name="name" value="${item.name}" required>
                </div>

                <div class="form-row">
                    <div class="form-group">
                        <label for="model">Mã mặt hàng</label>
                        <input type="text" id="model" name="model" value="${item.model}" required>
                    </div>

                    <div class="form-group">
                        <label for="brand">Thương hiệu</label>
                        <input type="text" id="brand" name="brand" value="${item.brand}" required>
                    </div>
                </div>

                <div class="form-row">
                    <div class="form-group">
                        <label for="category">Danh mục</label>
                        <input type="text" id="category" name="category" value="${item.category}" required>
                    </div>

                    <div class="form-group">
                        <label for="stocks">Tồn kho</label>
                        <input type="number" id="stocks" name="stocks" min="0" value="${item.stocks}" required>
                    </div>
                </div>

                <div class="form-row">
                    <div class="form-group">
                        <label for="price">Đơn giá bán (VNĐ)</label>
                        <input type="number" step="any" min="0" id="price" name="price" value="${item.price}" required>
                    </div>

                    <div class="form-group">
                        <label for="originalPrice">Đơn giá gốc (VNĐ)</label>
                        <input type="number" step="any" min="0" id="originalPrice" name="originalPrice" value="${item.originalPrice}" required>
                    </div>
                </div>

                <div class="form-row">
                    <div class="form-group">
                        <label for="weight">Trọng lượng (kg)</label>
                        <input type="number" step="any" min="0" id="weight" name="weight" value="${item.weight}" required>
                    </div>

                    <div class="form-group">
                        <label for="volumeM3">Thể tích (m3)</label>
                        <input type="number" step="any" min="0" id="volumeM3" name="volumeM3" value="${item.volumeM3}" required>
                    </div>
                </div>

                <div class="form-group">
                    <label for="description">Mô tả sản phẩm</label>
                    <textarea id="description" name="description">${item.description}</textarea>
                </div>

                <div class="form-group">
                    <label>Thông số kỹ thuật</label>
                    <div class="specs-container">
                        <table class="specs-table" id="specsTable">
                            <thead>
                                <tr>
                                    <th style="width: 40%;">Thông số</th>
                                    <th style="width: 50%;">Giá trị</th>
                                    <th style="width: 10%; text-align: center;">Hành động</th>
                                </tr>
                            </thead>
                            <tbody id="specsTableBody"></tbody>
                        </table>
                        <button type="button" class="btn-sm btn-add" onclick="addSpecRow('', '')">+ Thêm thông số</button>
                    </div>
                </div>

                <div class="form-actions">
                    <a href="${pageContext.request.contextPath}/items/manage" class="btn-back">Quay lại</a>
                    <button type="submit" class="btn-submit">Lưu thay đổi</button>
                </div>
            </form>
        </div>
    </div>

    <script>
        const initialSpecsJson = `<c:out value="${item.specs}" escapeXml="false" />`;

        function triggerFileInput() {
            document.getElementById('imageFile').click();
        }

        function previewSelectedImage(input) {
            if (input.files && input.files[0]) {
                const reader = new FileReader();
                reader.onload = function(e) {
                    document.getElementById('imagePreview').src = e.target.result;
                };
                reader.readAsDataURL(input.files[0]);
            }
        }

        function loadSpecs() {
            const tbody = document.getElementById('specsTableBody');
            tbody.innerHTML = '';

            try {
                if (initialSpecsJson && initialSpecsJson.trim() !== '') {
                    const specsObj = JSON.parse(initialSpecsJson);
                    for (const [key, value] of Object.entries(specsObj)) {
                        addSpecRow(key, value);
                    }
                }
            } catch (e) {
                console.error("Lỗi phân tích JSON specs:", e);
            }

            if (tbody.children.length === 0) {
                addSpecRow('', '');
            }
        }

        function addSpecRow(key, value) {
            const tbody = document.getElementById('specsTableBody');
            const row = document.createElement('tr');

            const tdKey = document.createElement('td');
            const inputKey = document.createElement('input');
            inputKey.type = 'text';
            inputKey.className = 'spec-key';
            inputKey.value = key;
            tdKey.appendChild(inputKey);

            const tdVal = document.createElement('td');
            const inputVal = document.createElement('input');
            inputVal.type = 'text';
            inputVal.className = 'spec-value';
            inputVal.value = value;
            tdVal.appendChild(inputVal);

            const tdAction = document.createElement('td');
            tdAction.style.textAlign = 'center';
            const btnDel = document.createElement('button');
            btnDel.type = 'button';
            btnDel.className = 'btn-sm btn-danger';
            btnDel.textContent = 'Xóa';
            btnDel.onclick = function() { row.remove(); };
            tdAction.appendChild(btnDel);

            row.appendChild(tdKey);
            row.appendChild(tdVal);
            row.appendChild(tdAction);
            tbody.appendChild(row);
        }

        function prepareSpecsJson() {
            const rows = document.querySelectorAll('#specsTableBody tr');
            const specsObj = {};

            rows.forEach(row => {
                const keyInput = row.querySelector('.spec-key');
                const valInput = row.querySelector('.spec-value');
                if (keyInput && valInput) {
                    const key = keyInput.value.trim();
                    const val = valInput.value.trim();
                    if (key !== '') {
                        specsObj[key] = val;
                    }
                }
            });

            document.getElementById('specsJsonInput').value = JSON.stringify(specsObj);
        }

        window.onload = loadSpecs;
    </script>
</body>
</html>