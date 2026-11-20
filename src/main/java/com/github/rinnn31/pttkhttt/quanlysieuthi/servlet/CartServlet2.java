package com.github.rinnn31.pttkhttt.quanlysieuthi.servlet;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.github.rinnn31.pttkhttt.quanlysieuthi.dao.ItemDAO2;
import com.github.rinnn31.pttkhttt.quanlysieuthi.dao.OnlineOrderDAO2;
import com.github.rinnn31.pttkhttt.quanlysieuthi.model.Customer2;
import com.github.rinnn31.pttkhttt.quanlysieuthi.model.Item2;
import com.github.rinnn31.pttkhttt.quanlysieuthi.model.OnlineOrder2;
import com.github.rinnn31.pttkhttt.quanlysieuthi.model.Order2;
import com.github.rinnn31.pttkhttt.quanlysieuthi.model.OrderDetail2;
import com.github.rinnn31.pttkhttt.quanlysieuthi.model.User2;

@WebServlet(name = "CartServlet2", urlPatterns = { "/cart/*" })
public class CartServlet2 extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String path = request.getPathInfo();
        if (path == null || path.equals("/")) {
            path = "/view";
        }

        if ("/view".equals(path)) {
            openCartViewPage(request, response);
        } else {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "Trang không tồn tại: " + path);
        }
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String path = request.getPathInfo();
        if (path == null || path.equals("/")) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "Trang không tồn tại: " + path);
            return;
        }

        switch (path) {
            case "/add":
                addItemToCart(request, response);
                break;
            case "/submit":
                submitCart(request, response);
                break;
            case "/remove":
                removeItemFromCart(request, response);
                break;
            default:
                break;
        }
    }

    /**
     * Xử lí yêu cầu hiển thị thông tin giỏ hàng.
     * 
     * @param request
     * @param response
     * @throws IOException
     * @throws ServletException
     */
    private void openCartViewPage(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        HttpSession session = request.getSession();

        // Lây phía giao hàng từ session và chuyển sang request để hiển thị trên trang,
        // sau đó xóa khỏi session để tránh hiển thị lại khi người dùng làm mới trang.
        List<OrderDetail2> cartDetails = (List<OrderDetail2>) session.getAttribute("cartDetails");
        if (cartDetails != null) {
            BigDecimal fee = calculateDeliveryFee(cartDetails);
            request.setAttribute("deliveryFee", fee);
        }

        // Lấy thông tin thành công hoặc lỗi từ session và chuyển sang request để hiển thị trên trang, sau đó xóa khỏi session để tránh hiển thị lại khi người dùng làm mới trang.
        String successMessage = (String) session.getAttribute("successMessage");
        String errorMessage = (String) session.getAttribute("errorMessage");
        if (successMessage != null) {
            request.setAttribute("successMessage", successMessage);
            session.removeAttribute("successMessage");
        }
        if (errorMessage != null) {
            request.setAttribute("errorMessage", errorMessage);
            session.removeAttribute("errorMessage");
        }

        request.getRequestDispatcher("/customer/cartInfoFrm2.jsp").forward(request, response);
    }

    /**
     * Xử lí yêu cầu thêm sản phẩm vào giỏ hàng.
     * 
     * @param request
     * @param response
     * @throws IOException
     */
    private void addItemToCart(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();

        try {
            String itemIdStr = request.getParameter("itemId");
            String quantityStr = request.getParameter("quantity");
            if (itemIdStr == null || quantityStr == null) {
                throw new Exception("Thiếu thông tin sản phẩm hoặc số lượng.");
            }
            Integer itemId, quantity;
            try {
                itemId = Integer.parseInt(itemIdStr);
                quantity = Integer.parseInt(quantityStr);
            } catch (NumberFormatException e) {
                throw new Exception("Định dạng ID sản phẩm hoặc số lượng không hợp lệ.");
            }

            ItemDAO2 itemDAO = new ItemDAO2();
            Item2 item = itemDAO.getItemById(itemId);
            if (item == null) {
                throw new Exception("Sản phẩm không tồn tại.");
            }

            List<OrderDetail2> cartDetails = (List<OrderDetail2>) session.getAttribute("cartDetails");
            if (cartDetails == null) {
                cartDetails = new java.util.ArrayList<>();
                session.setAttribute("cartDetails", cartDetails);
            }

            boolean itemExistsInCart = false;
            for (OrderDetail2 detail : cartDetails) {
                if (detail.getItem().getId().equals(itemId)) {
                    detail.setQuantity(detail.getQuantity() + quantity);
                    // Cập nhật đơn giá trong trường hợp giá sản phẩm thay đổi
                    detail.setUnitPrice(item.getPrice());
                    itemExistsInCart = true;
                    break;
                }
            }

            if (!itemExistsInCart) {
                OrderDetail2 newDetail = new OrderDetail2(
                    0,
                    item.getPrice(), 
                    quantity,
                    item
                );
                cartDetails.add(newDetail);
            }

            response.sendRedirect(request.getContextPath() + "/items/details?id=" + itemId);

        } catch (Exception e) {
            response.sendRedirect(request.getContextPath() + "/items/list");
        }
    }

    /**
     * Xử lí yêu cầu đặt hàng từ giỏ hàng.
     * 
     * @param request
     * @param response
     * @throws IOException
     */
    private void submitCart(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            User2 user = (User2) request.getSession().getAttribute("user");
            if (user == null || !(user instanceof Customer2)) {
                throw new Exception("Bạn cần đăng nhập với tài khoản khách hàng để đặt hàng.");
            }
            Customer2 customer = (Customer2) user;

            request.setCharacterEncoding("UTF-8");

            String city = request.getParameter("city");
            String ward = request.getParameter("ward");
            String detailAddress = request.getParameter("detailAddress");
            if (city == null || city.trim().isEmpty() || ward == null || ward.trim().isEmpty() || detailAddress == null
                    || detailAddress.trim().isEmpty()) {
                throw new Exception("Địa chỉ giao hàng không được để trống.");
            }

            // Chuẩn hoá địa chỉ: loại bỏ khoảng trắng thừa, loại bỏ ký tự đặc biệt ở đầu và
            // cuối chuỗi
            city = city.trim().replaceAll("\\s+", " ").replaceAll("^[\\p{P}\\p{S}\\s]+|[\\p{P}\\p{S}\\s]+$", "");
            ward = ward.trim().replaceAll("\\s+", " ").replaceAll("^[\\p{P}\\p{S}\\s]+|[\\p{P}\\p{S}\\s]+$", "");
            detailAddress = detailAddress.trim().replaceAll("\\s+", " ")
                    .replaceAll("^[\\p{P}\\p{S}\\s]+|[\\p{P}\\p{S}\\s]+$", "");
            // Kiểm tra nếu địa chỉ giao hàng trống sau khi chuẩn hoá
            if (city.isEmpty() || ward.isEmpty() || detailAddress.isEmpty()) {
                throw new Exception("Địa chỉ giao hàng không được để trống.");
            }
            if (!"Hà Nội".equalsIgnoreCase(city)) {
                throw new Exception("Hiện tại chỉ hỗ trợ giao hàng trong khu vực Hà Nội.");
            }

            String fullAddress = detailAddress + ", " + ward + ", " + city;

            String contactPhone = request.getParameter("contactPhone");
            if (contactPhone == null || contactPhone.trim().isEmpty()) {
                throw new Exception("Số điện thoại liên hệ không được để trống.");
            }

            List<OrderDetail2> orderDetails = (List<OrderDetail2>) request.getSession().getAttribute("cartDetails");
            if (orderDetails == null || orderDetails.isEmpty()) {
                throw new Exception("Giỏ hàng của bạn đang trống.");
            }

            BigDecimal deliveryFee = calculateDeliveryFee(orderDetails);

            OnlineOrder2 onlineOrder = new OnlineOrder2();
            onlineOrder.setContactPhone(contactPhone);
            onlineOrder.setDeliveryAddress(fullAddress);
            onlineOrder.setDeliveryFee(deliveryFee);
            onlineOrder.setDetails(orderDetails);
            onlineOrder.setCreatedAt(java.time.Instant.now());
            onlineOrder.setStatus(Order2.STATUS_PENDING);
            onlineOrder.setType(Order2.TYPE_ONLINE);
            onlineOrder.setCustomer(customer);

            OnlineOrderDAO2 onlineOrderDAO = new OnlineOrderDAO2();
            if (onlineOrderDAO.saveOnlineOrder(onlineOrder)) {
                request.getSession().removeAttribute("cartDetails");
                request.getSession().setAttribute("successMessage",
                        "Đặt hàng thành công! Đơn hàng của bạn đang được xử lý.");

                response.sendRedirect(request.getContextPath() + "/cart/view");
            } else {
                throw new Exception("Đặt hàng không thành công. Vui lòng thử lại.");
            }

        } catch (Exception e) {
            e.printStackTrace();

            request.getSession().setAttribute("errorMessage", e.getMessage());
            response.sendRedirect(request.getContextPath() + "/cart/view");
        }
    }

    /**
     * Xử lí yêu cầu xóa sản phẩm khỏi giỏ hàng.
     * 
     * @param request
     * @param response
     * @throws IOException
     */
    private void removeItemFromCart(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String itemIdStr = request.getParameter("itemId");
        if (itemIdStr == null) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Thiếu thông tin ID sản phẩm.");
            return;
        }
        Integer itemId;
        try {
            itemId = Integer.parseInt(itemIdStr);
        } catch (NumberFormatException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Định dạng ID sản phẩm không hợp lệ.");
            return;
        }

        List<OrderDetail2> cartDetails = (List<OrderDetail2>) request.getSession().getAttribute("cartDetails");
        if (cartDetails != null) {
            cartDetails.removeIf(detail -> detail.getItem().getId().equals(itemId));
        }

        response.sendRedirect(request.getContextPath() + "/cart/view");
    }

    private BigDecimal calculateDeliveryFee(List<OrderDetail2> cart) {
        // Kiểm tra nếu ít hàng và tổng trọng lượng nhỏ hơn 5kg, phí giao hàng là 30000
        // (giao hàng bằng xe máy)
        boolean useMotorbike = true;
        // Kiểm tra số lượng mặt hàng và tổng trọng lượng
        int totalQuantity = 0;
        double totalWeight = 0.0, totalVolume = 0.0;
        for (OrderDetail2 detail : cart) {
            totalQuantity += detail.getQuantity();
            totalWeight += detail.getItem().getWeight() * detail.getQuantity();
            totalVolume += detail.getItem().getVolumeM3() * detail.getQuantity();
        }

        if (totalQuantity > 3 || totalWeight > 5.0 || totalVolume > 0.1) {
            useMotorbike = false;
        }

        if (useMotorbike) {
            return new BigDecimal("30000");
        } else {
            // Nếu dùng xe tải, tính phí dựa trên trọng lượng và thể tích
            double volumetricWeight = totalVolume * 250; // 1 m3 = 250 kg
            double chargeableWeight = Math.max(totalWeight, volumetricWeight);

            BigDecimal baseFee = new BigDecimal("50000"); // Phí cơ bản cho 1kg đầu tiên
            BigDecimal additionalFeePerKg = new BigDecimal("1500"); // Phí thêm

            if (chargeableWeight <= 1.0) {
                return baseFee;
            } else {
                BigDecimal additionalWeight = new BigDecimal(chargeableWeight - 1.0);
                return baseFee.add(additionalFeePerKg.multiply(additionalWeight));
            }

        }
    }
}
