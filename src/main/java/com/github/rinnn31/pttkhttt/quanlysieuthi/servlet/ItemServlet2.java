package com.github.rinnn31.pttkhttt.quanlysieuthi.servlet;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.github.rinnn31.pttkhttt.quanlysieuthi.dao.ItemDAO2;
import com.github.rinnn31.pttkhttt.quanlysieuthi.model.Item2;

@WebServlet(name = "ItemServlet2", urlPatterns = { "/items/*" })
@MultipartConfig(
    fileSizeThreshold = 1024 * 1024 * 2,  // 2MB
    maxFileSize = 1024 * 1024 * 10,       // 10MB
    maxRequestSize = 1024 * 1024 * 50     // 50MB
)
public class ItemServlet2 extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String path = request.getPathInfo();
        if (path == null || path.equals("/")) {
            path = "/manage";
            return;
        }

        switch (path) {
            case "/edit":
                openEditItemPage(request, response);
                break;
            case "/manage":
                openManageItemsPage(request, response);
                break;
            case "/details":
                openItemDetailsPage(request, response);
                break;
            case "/list":
                openItemListPage(request, response);
                break;
            default:
                response.sendError(HttpServletResponse.SC_NOT_FOUND, "Trang không tồn tại: "+   path);
        }
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String path = request.getPathInfo();
        if (!"/edit".equals(path)) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "Trang không tồn tại: " + path);
            return;
        }
        
        request.setCharacterEncoding("UTF-8");
        Integer id = null;

        try {
            String idStr = request.getParameter("id");
            if (idStr != null && !idStr.trim().isEmpty()) {
                id = Integer.parseInt(idStr);
            } else {
                throw new Exception("ID sản phẩm không hợp lệ.");
            }

            String name = request.getParameter("name");
            if (name == null || name.trim().isEmpty()) {
                throw new Exception("Tên sản phẩm không được để trống.");
            }

            String model = request.getParameter("model");
            if (model == null || model.trim().isEmpty()) {
                throw new Exception("Mẫu sản phẩm không được để trống.");
            }

            String brand = request.getParameter("brand");
            if (brand == null || brand.trim().isEmpty()) {
                throw new Exception("Thương hiệu sản phẩm không được để trống.");
            }

            String category = request.getParameter("category");
            if (category == null || category.trim().isEmpty()) {
                throw new Exception("Danh mục sản phẩm không được để trống.");
            }

            String description = request.getParameter("description");
            String specs = request.getParameter("specs");
            
            String image = request.getParameter("image"); 
            Part filePart = request.getPart("imageFile");
            
            if (filePart != null && filePart.getSize() > 0) {
                // Lấy tên file gốc và tạo tên file mới để tránh trùng lặp
                String submittedFileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
                if (!submittedFileName.isEmpty()) {
                    String ext = submittedFileName.substring(submittedFileName.lastIndexOf("."));
                    String newFileName = UUID.randomUUID().toString() + ext;

                    String uploadPath = getServletContext().getRealPath("") + File.separator + "images";
                    File uploadDir = new File(uploadPath);
                    if (!uploadDir.exists()) {
                        uploadDir.mkdirs();
                    }

                    filePart.write(uploadPath + File.separator + newFileName);
                    image = "images/" + newFileName;
                }
            }

            Integer stocks;
            try {
                stocks = Integer.parseInt(request.getParameter("stocks"));
                if (stocks < 0) throw new Exception("Số lượng tồn kho không được âm.");
            } catch (NumberFormatException e) {
                throw new Exception("Định dạng số lượng tồn kho không hợp lệ.");
            }

            BigDecimal price, originalPrice;
            try {
                price = new BigDecimal(request.getParameter("price"));
                originalPrice = new BigDecimal(request.getParameter("originalPrice"));
                if (price.compareTo(BigDecimal.ZERO) < 0 || originalPrice.compareTo(BigDecimal.ZERO) < 0) {
                    throw new Exception("Giá sản phẩm và giá gốc không được âm.");
                }
            } catch (NumberFormatException e) {
                throw new Exception("Định dạng giá sản phẩm hoặc giá gốc không hợp lệ.");
            }

            Double weight, volumeM3;
            try {
                weight = Double.parseDouble(request.getParameter("weight"));
                volumeM3 = Double.parseDouble(request.getParameter("volumeM3"));
                if (weight < 0 || volumeM3 < 0) {
                    throw new Exception("Trọng lượng và thể tích không được âm.");
                }
            } catch (NumberFormatException e) {
                throw new Exception("Định dạng trọng lượng hoặc thể tích không hợp lệ.");
            }

            Item2 item = new Item2(id, name, model, brand, category, description, specs, image, stocks, price, originalPrice, weight, volumeM3);

            ItemDAO2 itemDAO = new ItemDAO2();
            if (itemDAO.updateItem(item)) {
                request.setAttribute("item", item);
                request.setAttribute("successMessage", "Cập nhật sản phẩm thành công!");
                request.getRequestDispatcher("/view/warehouse/editItemFrm2.jsp").forward(request, response);
            } else {
                throw new Exception("Cập nhật sản phẩm thất bại. Vui lòng thử lại.");
            }

        } catch (Exception e) {
            if (id != null) {
                response.sendRedirect(request.getContextPath() + "/items/edit?id=" + id + "&success=false");
            } else {
                response.sendRedirect(request.getContextPath() + "/items/manage");
            }
     
        }
    }
    
    /**
     * Mở trang quản lý danh sách mặt hàng cho nhân viên kho
     * @param request
     * @param response
     * @throws IOException
     */
    private void openManageItemsPage(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String keyword = request.getParameter("keyword");
        if (keyword != null && !keyword.trim().isEmpty()) {
            ItemDAO2 itemDAO = new ItemDAO2();
            List<Item2> items = itemDAO.getItemsContainKeyword(keyword);

            request.setAttribute("items", items);
        }
        
        try {
            request.getRequestDispatcher("/view/warehouse/manageItemsFrm2.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Mở trang chỉnh sửa thông tin mặt hàng
     * @param request
     * @param response
     * @throws IOException
     */
    private void openEditItemPage(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String idStr = request.getParameter("id");
        if (idStr == null || idStr.trim().isEmpty()) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "ID sản phẩm không hợp lệ.");
            return;
        }

        int id;
        try {
            id = Integer.parseInt(idStr);
        } catch (NumberFormatException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Định dạng ID sản phẩm không hợp lệ.");
            return;
        }

        ItemDAO2 itemDAO = new ItemDAO2();
        Item2 item = itemDAO.getItemById(id);
        if (item == null) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "Không tìm thấy sản phẩm với ID: " + id);
            return;
        }

        request.setAttribute("item", item);
        try {
            request.getRequestDispatcher("/view/warehouse/editItemFrm2.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Mở trang chi tiết mặt hàng cho khách hàng
     * @param request
     * @param response
     * @throws IOException
     */
    private void openItemDetailsPage(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String idStr = request.getParameter("id");
        if (idStr == null || idStr.trim().isEmpty()) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "ID sản phẩm không hợp lệ.");
            return;
        }

        int id;
        try {
            id = Integer.parseInt(idStr);
        } catch (NumberFormatException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Định dạng ID sản phẩm không hợp lệ.");
            return;
        }

        ItemDAO2 itemDAO = new ItemDAO2();
        Item2 item = itemDAO.getItemById(id);
        if (item == null) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "Không tìm thấy sản phẩm với ID: " + id);
            return;
        }

        request.setAttribute("item", item);
        try {
            request.getRequestDispatcher("/view/customer/itemDetailsFrm2.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Mở trang danh sách mặt hàng cho khách hàng, có thể tìm kiếm theo từ khóa
     * @param request
     * @param response
     * @throws IOException
     */
    private void openItemListPage(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String keyword = request.getParameter("keyword");
        if (keyword != null && !keyword.trim().isEmpty()) {
            ItemDAO2 itemDAO = new ItemDAO2();
            List<Item2> items = itemDAO.getItemsContainKeyword(keyword);

            request.setAttribute("items", items);
        }

        try {
            request.getRequestDispatcher("/view/customer/listItemsFrm2.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
