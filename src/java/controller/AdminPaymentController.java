/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import DAO.OrderDAO;
import DAO.PaymentMethodDAO;
import DAO.ShipMethodDAO;
import entities.TblOrderDetail;
import entities.TblOrders;
import entities.TblPaymentMethod;
import entities.TblShipMethod;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author Admin
 */
@Controller
@RequestMapping(value = "/Admin")
public class AdminPaymentController {

    ShipMethodDAO shipMethodDAO = new ShipMethodDAO();
    PaymentMethodDAO paymentMethodDAO = new PaymentMethodDAO();
    OrderDAO orderDAO = new OrderDAO();

    @RequestMapping(value = "/listShipMethod")
    public String listTrademark(HttpSession session, Model model) {
        List<TblShipMethod> allProduct = shipMethodDAO.getAll();
        model.addAttribute("listShipMethod", allProduct);
        return "Admin/listShipMethod";

    }

    @RequestMapping(value = "/addNewShipMethod")
    public String addTrademark(HttpSession session, Model model) {
        model.addAttribute("ship", new TblShipMethod());
        return "Admin/addShipMethod";

    }

    @RequestMapping(value = "/detailShipMethod")
    public String detailTrademark(HttpSession session, Model model, @RequestParam("Id") int id) {
        TblShipMethod td = shipMethodDAO.getById(id);
        model.addAttribute("shipMethod", td);
        return "Admin/detailShipMethod";

    }

    @RequestMapping(value = "/insertShipMethod", method = RequestMethod.POST)
    public String insertTrademark(HttpSession session, Model model, @ModelAttribute("ship") TblShipMethod p) {
        String ps = p.getShipMethodName();
        try {
            p.setShipMethodName(new String(ps.getBytes("iso-8859-1"), "UTF-8"));
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(AdminController.class.getName()).log(Level.SEVERE, null, ex);
        }

        boolean a = shipMethodDAO.insert(p);
        if (a) {
            List<TblShipMethod> allProduct = shipMethodDAO.getAll();
            model.addAttribute("listShipMethod", allProduct);
            return "redirect: listShipMethod.htm";
        } else {
            model.addAttribute("messs", "Thêm mới thất bại");
            return "Admin/addTShipMethod";
        }

    }

    @RequestMapping(value = "/listPaymentMethod")
    public String listPaymentMethod(HttpSession session, Model model) {
        List<TblPaymentMethod> allProduct = paymentMethodDAO.getAll();
        model.addAttribute("listPaymentMethod", allProduct);
        return "Admin/listPaymentMethod";

    }

    @RequestMapping(value = "/addPaymentMethod")
    public String addPaymentMethod(HttpSession session, Model model) {
        model.addAttribute("paymentMethod", new TblPaymentMethod());
        return "Admin/addPaymentMethod";

    }

    @RequestMapping(value = "/detailPaymentMethod")
    public String detailPaymentMethod(HttpSession session, Model model, @RequestParam("Id") int id) {
        TblPaymentMethod td = paymentMethodDAO.getById(id);
        model.addAttribute("paymentMethod", td);
        return "Admin/detailPaymentMethod";

    }

    @RequestMapping(value = "/insertPaymentMethod", method = RequestMethod.POST)
    public String insertPaymentMethod(HttpSession session, Model model, @ModelAttribute("paymentMethod") TblPaymentMethod p) {
        String ps = p.getPaymentMethodName();
        try {
            p.setPaymentMethodName(new String(ps.getBytes("iso-8859-1"), "UTF-8"));
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(AdminController.class.getName()).log(Level.SEVERE, null, ex);
        }

        boolean a = paymentMethodDAO.insert(p);
        if (a) {
            List<TblPaymentMethod> allProduct = paymentMethodDAO.getAll();
            model.addAttribute("listPaymentMethod", allProduct);
            return "redirect: listPaymentMethod.htm";
        } else {
            model.addAttribute("messs", "Thêm mới thất bại");
            return "Admin/addPaymentMethod";
        }

    }

    @RequestMapping(value = "/listOrder")
    public String viewListOrder(HttpSession session, Model model) {

        List<TblOrders> allProduct = orderDAO.getAll();
//         model.addAllAttributes("list",allProduct);
        model.addAttribute("listOrder", allProduct);
        int i1 = new OrderDAO().countOrderNew();
        int i2 = new OrderDAO().countOrderDetroy();
        session.setAttribute("orderNew", i1);
        session.setAttribute("orderDetroy", i2);
        return "Admin/ListOrder";
    }

    @RequestMapping(value = "/viewOrderDetail")
    public String viewListOrderDetail(HttpSession session, Model model, @RequestParam("Id") int id) {

        List<TblOrderDetail> allProduct = orderDAO.getOrderDetailbyId(id);
        TblOrders order = orderDAO.getOrderbyId(id);
        model.addAttribute("order", order);
        model.addAttribute("listOrderDetail", allProduct);
        int i1 = new OrderDAO().countOrderNew();
        int i2 = new OrderDAO().countOrderDetroy();
        session.setAttribute("orderNew", i1);
        session.setAttribute("orderDetroy", i2);
        return "Admin/ListOrderDetail";
    }
    @RequestMapping(value = "/updateOrder", method = RequestMethod.POST)
    public String UpdateProduct(HttpSession session, Model model, @ModelAttribute("order") TblOrders p, HttpServletRequest request) {

        int i1 = new OrderDAO().countOrderNew();
        int i2 = new OrderDAO().countOrderDetroy();
        session.setAttribute("orderNew", i1);
        session.setAttribute("orderDetroy", i2);
        String path = request.getRealPath("/Views/Admin/images/product");
        path = path.substring(0, path.indexOf("\\build"));
        path = path + "\\web\\Views\\Admin\\images\\product";
        DiskFileItemFactory factory = new DiskFileItemFactory();
        ServletFileUpload upload = new ServletFileUpload(factory);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd-hh-mm-ss");

        String dateString = format.format(new Date());

        try {
            List<FileItem> list = upload.parseRequest(request);
            for (FileItem fi : list) {
                if (fi.isFormField() == false) {
                    String name = fi.getName();
                    String extension = name.substring(name.lastIndexOf("."));

                    fi.write(new File(path + "/" + dateString + extension));
                } else {
                    {
                        String name = fi.getFieldName();
                        String value = fi.getString();
                        switch (name) {
                            case "orderId":
                                p.setOrderId(Integer.parseInt(value));
                                break;
                            case "status":
                                p.setStatus(Integer.parseInt(value));
                                break;

                        }
                    }

                }
            }
        } catch (FileUploadException ex) {
            Logger.getLogger(AdminPaymentController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(AdminPaymentController.class.getName()).log(Level.SEVERE, null, ex);
        }

        boolean a = orderDAO.updateOrder(p);
        if (a) {
            OrderDAO ord = new OrderDAO();
            List<TblOrderDetail> allProduct = ord.getOrderDetailbyId(p.getOrderId());
            TblOrders order = ord.getOrderbyId(p.getOrderId());
            model.addAttribute("order", order);
            model.addAttribute("listOrderDetail", allProduct);
            model.addAttribute("messs", "Đã xác nhận trạng thái");
            return "redirect: listOrder.htm";
        } else {
            model.addAttribute("messs", "Cập nhật thất bại");
            return "edirect: listOrder.htm";
        }
    }
}
