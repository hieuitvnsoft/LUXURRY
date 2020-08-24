/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import DAO.NewsDAO;
import DAO.TypeNewsDAO;
import entities.TblAdmin;
import entities.TblColor;
import entities.TblNews;
import entities.TblProductDetail;
import entities.TblProducts;
import entities.TblTrademark;
import entities.TblTypeNews;
import entities.TblTypeProduct;
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
public class AdminNewsController {

    TypeNewsDAO tnd = new TypeNewsDAO();
    NewsDAO nd = new NewsDAO();

    @RequestMapping(value = "/listTypeNews")
    public String listTrademark(HttpSession session, Model model) {
        List<TblTypeNews> allProduct = tnd.getAll();
        model.addAttribute("listTypeNews", allProduct);
        return "Admin/listTypeNews";

    }

    @RequestMapping(value = "/addTypeNews")
    public String addTypeNews(HttpSession session, Model model) {

        model.addAttribute("typeNews", new TblTypeNews());
        return "Admin/addTypeNews";

    }

    @RequestMapping(value = "/insertTypeNews", method = RequestMethod.POST)
    public String insertTypeNews(HttpSession session, Model model, @ModelAttribute("typeNews") TblTypeNews p) {

        String ps = p.getTypeNewName();
        try {
            p.setTypeNewName(new String(ps.getBytes("iso-8859-1"), "UTF-8"));
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(AdminController.class.getName()).log(Level.SEVERE, null, ex);
        }
        boolean a = tnd.insert(p);
        if (a) {
            List<TblTypeNews> allProduct = tnd.getAll();
            model.addAttribute("listTypeNews", allProduct);
            return "redirect: listTypeNews.htm";
        } else {
            model.addAttribute("messs", "Thêm mới thất bại");
            return "Admin/addTypeNews";
        }

    }

    @RequestMapping(value = "/updateTypeNew")
    public String updateTypeNew(HttpSession session, Model model, @RequestParam("Id") int id) {
        TblTypeNews tn = tnd.getById(id);

        model.addAttribute("typeNews", tn);
        return "Admin/updateTypeNews";

    }

    @RequestMapping(value = "/detailTypeNew")
    public String detailTypeNew(HttpSession session, Model model, @RequestParam("Id") int id) {
        TblTypeNews tn = tnd.getById(id);

        model.addAttribute("typeNews", tn);
        return "Admin/detailTypeNew";

    }

    @RequestMapping(value = "/listNews")
    public String listNews(HttpSession session, Model model) {
        List<TblNews> allProduct = nd.getAll();
        model.addAttribute("listTypeNews", tnd.getAll());
        model.addAttribute("listNews", allProduct);
        return "Admin/listNews";

    }

    @RequestMapping(value = "/addNew")
    public String addNew(HttpSession session, Model model) {
        model.addAttribute("new", new TblNews());
        model.addAttribute("listTypeNews", tnd.getAll());
        return "Admin/addNew";

    }

    @RequestMapping(value = "/editTypeNews", method = RequestMethod.POST)
    public String editTypeNews(HttpSession session, Model model, @ModelAttribute("typeNews") TblTypeNews p) {

        String ps = p.getTypeNewName();
        try {
            p.setTypeNewName(new String(ps.getBytes("iso-8859-1"), "UTF-8"));
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(AdminController.class.getName()).log(Level.SEVERE, null, ex);
        }
        boolean a = tnd.update(p);
        if (a) {
            List<TblTypeNews> allProduct = tnd.getAll();
            model.addAttribute("listTypeNews", allProduct);
            return "redirect: listTypeNews.htm";
        } else {
            model.addAttribute("messs", "Cập nhật thất bại");
            return "redirect: updateTypeNew.htm?Id=" + p.getTypeNewId();
        }

    }

    @RequestMapping(value = "/insertNew", method = RequestMethod.POST)
    public String insertNew(HttpSession session, Model model, @ModelAttribute("new") TblNews p, HttpServletRequest request) {
        String path = request.getRealPath("/Views/Image");
        path = path.substring(0, path.indexOf("\\build"));
        path = path + "\\web\\Views\\Image";
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
                    p.setNewImage("LUXURY/Views/Image/" + dateString + extension);
                    fi.write(new File(path + "/" + dateString + extension));
                } else {
                    {
                        String name = fi.getFieldName();
                        String value = fi.getString();
                        switch (name) {
                            case "title":
                                p.setTitle(new String(value.getBytes("iso-8859-1"), "UTF-8"));
                                break;
                            case "content":
                                p.setContent(new String(value.getBytes("iso-8859-1"), "UTF-8"));
                                break;
                            case "typeId.typeNewId":
                                TblTypeNews px = new TblTypeNews();
                                px.setTypeNewId(Integer.parseInt(value));
                                p.setTypeId(px);

                                break;

                            case "status":
                                p.setStatus(Integer.parseInt(value));
                                break;
                        }
                    }

                }
            }
        } catch (FileUploadException ex) {
            Logger.getLogger(AdminProductController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(AdminProductController.class.getName()).log(Level.SEVERE, null, ex);
        }
        p.setDateWrite(new Date());
        TblAdmin as = (TblAdmin) session.getAttribute("loginAdmin");
        p.setAdminId(as);
        boolean a = nd.insert(p);
        if (a) {
            List<TblNews> allProduct = nd.getAll();
            model.addAttribute("listNews", allProduct);
            model.addAttribute("listTypeNews", tnd.getAll());
            return "Admin/listNews";
        } else {
            model.addAttribute("messs", "Thêm mới thất bại");
            return "redirect: addNew.htm";
        }
    }
}
