package com.github.rinnn31.pttkhttt.quanlysieuthi.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.github.rinnn31.pttkhttt.quanlysieuthi.dao.UserDAO2;
import com.github.rinnn31.pttkhttt.quanlysieuthi.model.Staff2;
import com.github.rinnn31.pttkhttt.quanlysieuthi.model.User2;

@WebServlet(name = "AuthServlet2", urlPatterns = { "/login", "/logout", "/home" })
public class AuthServlet2 extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String path = request.getServletPath();

        switch (path) {
            case "/login":
                request.getSession().invalidate();
                request.getRequestDispatcher("/user/loginFrm2.jsp").forward(request, response);    
                break;
            case "/home":
                HttpSession session = request.getSession(false);
                if (session == null || session.getAttribute("user") == null) {
                    response.sendRedirect(request.getContextPath() + "/login");
                    return;
                }

                User2 user = (User2) session.getAttribute("user");
                if (User2.TYPE_CUSTOMER.equals(user.getType())) {
                    request.getRequestDispatcher("/customer/customerHomeFrm2.jsp").forward(request, response);
                } else {
                    Staff2 staff = (Staff2) user;
                    if (Staff2.ROLE_WAREHOUSE.equals(staff.getRole())) {
                        request.getRequestDispatcher("/warehouse/warehouseHomeFrm2.jsp").forward(request, response);
                    } else {
                        response.sendError(HttpServletResponse.SC_FORBIDDEN, "Bạn không có quyền truy cập trang này.");
                    }
                }
                break;
            default:
                response.sendError(HttpServletResponse.SC_NOT_FOUND, "Trang không tồn tại: " + path);
        }        
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String path = request.getServletPath();

        switch (path) {
            case "/login":
                String username = request.getParameter("username");
                String password = request.getParameter("password");
                if (username == null || password == null || username.trim().isEmpty() || password.trim().isEmpty()) {
                    request.setAttribute("errorMessage", "Vui lòng nhập đầy đủ thông tin đăng nhập.");
                    request.getRequestDispatcher("/user/loginFrm2.jsp").forward(request, response);
                    return;
                }

                UserDAO2 userDAO = new UserDAO2();
                User2 user = userDAO.getUserByUsername(username);
                if (user == null || !user.getPassword().equals(password)) {
                    request.setAttribute("errorMessage", "Tên đăng nhập hoặc mật khẩu không đúng.");
                    request.getRequestDispatcher("/user/loginFrm2.jsp").forward(request, response);
                    return;
                }

                HttpSession session = request.getSession();
                session.setAttribute("user", user);

                response.sendRedirect(request.getContextPath() + "/home");
                
                break;
            case "/logout":
                request.getSession().invalidate();
                response.sendRedirect(request.getContextPath() + "/login");
                break;
            default:
                break;
        }
    }
}
