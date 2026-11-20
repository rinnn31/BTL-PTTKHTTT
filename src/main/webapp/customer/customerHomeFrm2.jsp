<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:if test="${empty sessionScope.user or sessionScope.user.type ne 'customer'}">
    <c:redirect url="/login" />
</c:if>

<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Trang chủ khách hàng - Hệ thống siêu thị điện máy</title>
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

        .logout-form {
            display: inline;
        }

        .logout-btn {
            background-color: rgba(255, 255, 255, 0.15);
            color: #ffffff;
            padding: 6px 14px;
            border: none;
            border-radius: 4px;
            font-size: 13px;
            font-weight: 500;
            cursor: pointer;
            transition: background-color 0.2s;
        }

        .logout-btn:hover {
            background-color: rgba(255, 255, 255, 0.25);
        }

        .container {
            max-width: 1100px;
            margin: 50px auto;
            padding: 0 20px;
        }

        .welcome-card {
            background-color: #ffffff;
            border-radius: 8px;
            padding: 32px;
            box-shadow: 0 2px 10px rgba(0, 0, 0, 0.05);
            text-align: center;
        }

        .welcome-card h2 {
            color: #1e293b;
            margin-bottom: 12px;
            font-size: 24px;
        }

        .welcome-card p {
            color: #64748b;
            margin-bottom: 28px;
            font-size: 15px;
        }

        .btn-primary {
            display: inline-block;
            background-color: #2563eb;
            color: #ffffff;
            padding: 12px 28px;
            font-size: 16px;
            font-weight: 600;
            border: none;
            border-radius: 6px;
            cursor: pointer;
            text-decoration: none;
            transition: background-color 0.2s, transform 0.1s;
            box-shadow: 0 2px 4px rgba(37, 99, 235, 0.3);
        }

        .btn-primary:hover {
            background-color: #1d4ed8;
        }

        .btn-primary:active {
            transform: scale(0.98);
        }
    </style>
</head>
<body>
    <nav class="navbar">
        <div class="navbar-brand">
            Hệ thống siêu thị điện máy
        </div>
        <div class="navbar-user">
            <c:if test="${not empty sessionScope.user}">
                <span>Xin chào, <strong>${sessionScope.user.fullName}</strong></span>
            </c:if>
            <form action="${pageContext.request.contextPath}/logout" method="post" class="logout-form">
                <button type="submit" class="logout-btn">Đăng xuất</button>
            </form>
        </div>
    </nav>

    <div class="container">
        <div class="welcome-card">
            <h2 style="margin-bottom: 50px;">Trang chủ khách hàng</h2>
            
            <a href="${pageContext.request.contextPath}/items/list" class="btn-primary">Mua hàng</a>
        </div>
    </div>

</body>
</html>