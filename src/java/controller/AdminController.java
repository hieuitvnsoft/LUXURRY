/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import DAO.AdminDAO;
import DAO.OrderDAO;
import DAO.ProductDAO;
import DAO.TrademarkDAO;
import DAO.TypeProductDAO;
import DAO.UserDAO;
import entities.TblAdmin;
import entities.TblProducts;
import entities.TblTrademark;
import entities.TblTypeProduct;
import entities.TblUser;
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
public class AdminController {
//

    AdminDAO ad = new AdminDAO();
    ProductDAO pd = new ProductDAO();
    TypeProductDAO tpd = new TypeProductDAO();
    TrademarkDAO tmd = new TrademarkDAO();
    UserDAO ud = new UserDAO();

    @RequestMapping(value = "/home")
    public String homeAdmin(HttpSession session, Model model) {
        if (session.getAttribute("loginAdmin") == null) {
            session.setAttribute("user", new TblAdmin());
            return "Admin/login";
        } else {

            TblAdmin a = (TblAdmin) session.getAttribute("loginAdmin");
            model.addAttribute("adminName", a.getFullName());
            model.addAttribute("adminImage", a.getAvatar());
            int i1 = new OrderDAO().countOrderNew();
            int i2 = new OrderDAO().countOrderDetroy();
            session.setAttribute("orderNew", i1);
            session.setAttribute("orderDetroy", i2);
            return "Admin/home";
        }
    }

    @RequestMapping(value = "/listUser")
    public String listUser(HttpSession session, Model model) {
        List<TblUser> allProduct = ud.getAll();
        int i1 = new OrderDAO().countOrderNew();
        int i2 = new OrderDAO().countOrderDetroy();
        session.setAttribute("orderNew", i1);
        session.setAttribute("orderDetroy", i2);
        model.addAttribute("listUser", allProduct);
        return "Admin/listUser";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(@RequestParam("username") String username, @RequestParam("password") String password, HttpSession session, Model m) {

        TblAdmin admin = ad.checkLogin(username, password);
        if (admin != null) {
            //HttpSession loginAdmin = (HttpSession) list;
            session.setAttribute("loginAdmin", admin);
            session.setAttribute("adminName", admin.getFullName());
            session.setAttribute("adminImage", admin.getAvatar());
            int i1 = new OrderDAO().countOrderNew();
            int i2 = new OrderDAO().countOrderDetroy();
            session.setAttribute("orderNew", i1);
            session.setAttribute("orderDetroy", i2);
            return "Admin/home";
        } else {
            m.addAttribute("messs", "Tài khoản hoặc mật khẩu chưa đúng! Xin kiểm tra lại");
            return "Admin/login";
        }
    }

    @RequestMapping(value = "/logout")
    public String logout(HttpSession session, Model m) {
        session.setAttribute("loginAdmin", null);
        return "redirect:/Admin/home.htm";

    }

    @RequestMapping(value = "/listTrademark")
    public String listTrademark(HttpSession session, Model model) {
        List<TblTrademark> allProduct = tmd.getAll();
        model.addAttribute("listTrademark", allProduct);
        int i1 = new OrderDAO().countOrderNew();
        int i2 = new OrderDAO().countOrderDetroy();
        session.setAttribute("orderNew", i1);
        session.setAttribute("orderDetroy", i2);
        return "Admin/listTrademark";

    }

    @RequestMapping(value = "/addTrademark")
    public String addTrademark(HttpSession session, Model model) {
        model.addAttribute("trademark", new TblTrademark());
        int i1 = new OrderDAO().countOrderNew();
        int i2 = new OrderDAO().countOrderDetroy();
        session.setAttribute("orderNew", i1);
        session.setAttribute("orderDetroy", i2);
        return "Admin/addTrademark";

    }

    @RequestMapping(value = "/detailTrademark")
    public String detailTrademark(HttpSession session, Model model, @RequestParam("Id") int id) {
        TblTrademark td = tmd.getById(id);
        model.addAttribute("trademark", td);
        int i1 = new OrderDAO().countOrderNew();
        int i2 = new OrderDAO().countOrderDetroy();
        session.setAttribute("orderNew", i1);
        session.setAttribute("orderDetroy", i2);
        return "Admin/detailTrademark";

    }

    @RequestMapping(value = "/insertTrademark", method = RequestMethod.POST)
    public String insertTrademark(HttpSession session, Model model, @ModelAttribute("trademark") TblTrademark p) {
        String ps = p.getTrademark();
        try {
            p.setTrademark(new String(ps.getBytes("iso-8859-1"), "UTF-8"));
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(AdminController.class.getName()).log(Level.SEVERE, null, ex);
        }

        boolean a = tmd.insert(p);
        if (a) {
            List<TblTrademark> allProduct = tmd.getAll();
            model.addAttribute("listTrademark", allProduct);
            int i1 = new OrderDAO().countOrderNew();
            int i2 = new OrderDAO().countOrderDetroy();
            session.setAttribute("orderNew", i1);
            session.setAttribute("orderDetroy", i2);
            return "redirect: listTrademark.htm";
        } else {
            model.addAttribute("messs", "Thêm mới thất bại");
            int i1 = new OrderDAO().countOrderNew();
            int i2 = new OrderDAO().countOrderDetroy();
            session.setAttribute("orderNew", i1);
            session.setAttribute("orderDetroy", i2);
            return "Admin/addTrademark";
        }

    }

    @RequestMapping(value = "/listTypeProduct")
    public String listTypeProduct(HttpSession session, Model model) {
        List<TblTypeProduct> allProduct = tpd.getAll();
        model.addAttribute("listTypeProduct", allProduct);
        int i1 = new OrderDAO().countOrderNew();
        int i2 = new OrderDAO().countOrderDetroy();
        session.setAttribute("orderNew", i1);
        session.setAttribute("orderDetroy", i2);
        return "Admin/listTypeProduct";

    }

    @RequestMapping(value = "/addTypeProduct")
    public String addTypeProduct(HttpSession session, Model model) {
        model.addAttribute("typeProduct", new TblTypeProduct());
        int i1 = new OrderDAO().countOrderNew();
        int i2 = new OrderDAO().countOrderDetroy();
        session.setAttribute("orderNew", i1);
        session.setAttribute("orderDetroy", i2);
        return "Admin/addTypeProduct";

    }

    @RequestMapping(value = "/detailTypeProduct")
    public String detailTypeProduct(HttpSession session, Model model, @RequestParam("Id") int id) {
        TblTypeProduct td = tpd.getById(id);
        model.addAttribute("typeProduct", td);
        int i1 = new OrderDAO().countOrderNew();
        int i2 = new OrderDAO().countOrderDetroy();
        session.setAttribute("orderNew", i1);
        session.setAttribute("orderDetroy", i2);
        return "Admin/detailTypeProduct";

    }

    @RequestMapping(value = "/insertTypeProduct", method = RequestMethod.POST)
    public String insertTypeProduct(HttpSession session, Model model, @ModelAttribute("typeProduct") TblTypeProduct p) {

        String ps = p.getTypeName();
        try {
            p.setTypeName(new String(ps.getBytes("iso-8859-1"), "UTF-8"));
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(AdminController.class.getName()).log(Level.SEVERE, null, ex);
        }
        boolean a = tpd.insert(p);
        if (a) {
            List<TblTypeProduct> allProduct = tpd.getAll();
            model.addAttribute("listTypeProduct", allProduct);
            int i1 = new OrderDAO().countOrderNew();
            int i2 = new OrderDAO().countOrderDetroy();
            session.setAttribute("orderNew", i1);
            session.setAttribute("orderDetroy", i2);
            return "redirect: listTypeProduct.htm";
        } else {
            model.addAttribute("messs", "Thêm mới thất bại");
            int i1 = new OrderDAO().countOrderNew();
            int i2 = new OrderDAO().countOrderDetroy();
            session.setAttribute("orderNew", i1);
            session.setAttribute("orderDetroy", i2);
            return "Admin/addTypeProduct";
        }

    }

}
