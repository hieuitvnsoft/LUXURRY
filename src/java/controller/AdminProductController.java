/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import DAO.AdminDAO;
import DAO.ColorDAO;
import DAO.ProductDAO;
import DAO.ProductDetailDAO;
import DAO.TrademarkDAO;
import DAO.TypeProductDAO;
import DAO.UserDAO;
import entities.TblColor;
import entities.TblProductDetail;
import entities.TblProducts;
import entities.TblTrademark;
import entities.TblTypeProduct;
import java.io.File;
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
public class AdminProductController {

    AdminDAO ad = new AdminDAO();
    ProductDAO pd = new ProductDAO();
    TypeProductDAO tpd = new TypeProductDAO();
    TrademarkDAO tmd = new TrademarkDAO();
    UserDAO ud = new UserDAO();
    ProductDetailDAO pddd = new ProductDetailDAO();
    ColorDAO cd = new ColorDAO();

    @RequestMapping(value = "/listProduct")
    public String listProduct(HttpSession session, Model model) {
        List<TblProducts> allProduct = pd.getAll();
        model.addAttribute("listProduct", allProduct);
        return "Admin/listProduct";

    }

    @RequestMapping(value = "/listProductDetail")
    public String listProductDetail(HttpSession session, Model model, @RequestParam("Id") int id) {
        List<TblProductDetail> allProduct = pddd.getAllById(id);
        model.addAttribute("listProductDetail", allProduct);
        model.addAttribute("id", id);
        return "Admin/listProductDetail";

    }

    @RequestMapping(value = "/addProductDetail")
    public String addProductDetail(HttpSession session, Model model, @RequestParam("Id") int id) {
//        List<TblProductDetail> allProduct = pddd.getById(id);
        TblProducts p = new TblProducts();
        p.setProductId(id);
        TblProductDetail productDetail = new TblProductDetail();
        productDetail.setProductId(p);
        model.addAttribute("productDetail", productDetail);
        model.addAttribute("listColor", cd.getAll());
        model.addAttribute("id", id);
        return "Admin/addProductDetail";

    }

    @RequestMapping(value = "/insertProductDetails", method = RequestMethod.POST)
    public String insertProductDetail(HttpSession session, Model model, @ModelAttribute("productDetail") TblProductDetail p, HttpServletRequest request) {

///
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
                    p.setImageProduct("LUXURY/Views/Image/" + dateString + extension);
                    fi.write(new File(path + "/" + dateString + extension));
                } else {
                    {
                        String name = fi.getFieldName();
                        String value = fi.getString();
                        switch (name) {
                            case "priceNew":
                                p.setPriceNew(Double.parseDouble(value));
                                break;
                            case "quantity":
                                p.setQuantity(Integer.parseInt(value));
                                break;
                            case "productId.productId":
                                TblProducts px = new TblProducts();
                                px.setProductId(Integer.parseInt(value));
                                p.setProductId(px);

                                break;

                            case "colorId.id":
                                TblColor spt = new TblColor();
                                spt.setId(Integer.parseInt(value));
                                p.setColorId(spt);
                                break;
                            case "sale":
                                p.setSale(Integer.parseInt(value));
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

        boolean a = pddd.insert(p);
        if (a) {
            List<TblProductDetail> pr = pddd.getAllById(p.getProductId().getProductId());

            model.addAttribute("id", p.getProductId().getProductId());
            model.addAttribute("productDetail", pr);
            model.addAttribute("messs", "Them moi ko thanh cong");
            return "redirect: /Admin/listProductDetail";
        } else {
            model.addAttribute("messs", "Them moi ko thanh cong");
            return "redirect: /Admin/ddProductDetail.htm?Id=" + p.getProductId().getProductId();
        }
    }

    @RequestMapping(value = "/addProduct")
    public String addProduct(HttpSession session, Model model) {
        List<TblTypeProduct> stp = tpd.getAll();
        List<TblTrademark> pdc = tmd.getAll();
        model.addAttribute("listTrademark", pdc);
        model.addAttribute("listType", stp);
        model.addAttribute("product", new TblProducts());
        return "Admin/addProduct";
    }

    @RequestMapping(value = "/viewProductDetail")
    public String addProduct(HttpSession session, Model model, @RequestParam("Id") int id) {

        model.addAttribute("productDetail", pddd.getById(id));

        return "Admin/viewProductDetail";
    }

    @RequestMapping(value = "/updateProduct")
    public String updateProduct(HttpSession session, Model model, @RequestParam("Id") int id) {
        List<TblTypeProduct> stp = tpd.getAll();
        List<TblTrademark> pdc = tmd.getAll();
        model.addAttribute("listTrademark", pdc);
        model.addAttribute("listType", stp);
        model.addAttribute("product", pd.getById(id));
        return "Admin/updateProduct";
    }

    @RequestMapping(value = "/insertProducts", method = RequestMethod.POST)
    public String insertProduct(HttpSession session, Model model, @ModelAttribute("product") TblProducts p, HttpServletRequest request) {
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
                    p.setImgProduct("LUXURY/Views/Image/" + dateString + extension);
                    fi.write(new File(path + "/" + dateString + extension));
                } else {
                    {
                        String name = fi.getFieldName();
                        String value = fi.getString();
                        switch (name) {
                            case "productName":
                                p.setProductName(new String(value.getBytes("iso-8859-1"), "UTF-8"));
                                break;
                            case "priceOlder":
                                p.setPriceOlder(Double.parseDouble(value));
                                break;
                            case "description":
                                p.setDescription(new String(value.getBytes("iso-8859-1"), "UTF-8"));
                                break;
                            case "specifications":
                                p.setSpecifications(new String(value.getBytes("iso-8859-1"), "UTF-8"));
                                break;
                            case "status":
                                p.setStatus(Integer.parseInt(value));
                                break;
                            case "trademarkId.trademarkId":
                                TblTrademark spt = new TblTrademark();
                                spt.setTrademarkId(Integer.parseInt(value));
                                p.setTrademarkId(spt);
                                break;
                            case "typeId.Id":
                                TblTypeProduct pdc = new TblTypeProduct();
                                pdc.setId(Integer.parseInt(value));
                                p.setTypeId(pdc);
                                break;
                        }
                    }

                }
            }
        } catch (FileUploadException ex) {
            Logger.getLogger(AdminController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(AdminController.class.getName()).log(Level.SEVERE, null, ex);
        }

        boolean a = pd.insert(p);
        if (a) {
            List<TblProducts> allProducer = pd.getAll();
//         model.addAllAttributes("list",allProduct);
            model.addAttribute("listProduct", allProducer);

            return "redirect: listProduct.htm";
        } else {
            model.addAttribute("messs", "Thêm mới thất bại");
            return "Admin/insertProduct";
        }

    }

    @RequestMapping(value = "/updatesProducts", method = RequestMethod.POST)
    public String updatesProducts(HttpSession session, Model model, @ModelAttribute("product") TblProducts p, HttpServletRequest request) {
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
                    p.setImgProduct("LUXURY/Views/Image/" + dateString + extension);
                    fi.write(new File(path + "/" + dateString + extension));
                } else {
                    {
                        String name = fi.getFieldName();
                        String value = fi.getString();
                        switch (name) {
                            case "productName":
                                p.setProductName(new String(value.getBytes("iso-8859-1"), "UTF-8"));
                                break;
                            case "priceOlder":
                                p.setPriceOlder(Double.parseDouble(value));
                                break;
                            case "description":
                                p.setDescription(new String(value.getBytes("iso-8859-1"), "UTF-8"));
                                break;
                            case "specifications":
                                p.setSpecifications(new String(value.getBytes("iso-8859-1"), "UTF-8"));
                                break;
                            case "status":
                                p.setStatus(Integer.parseInt(value));
                                break;
                            case "trademarkId.trademarkId":
                                TblTrademark spt = new TblTrademark();
                                spt.setTrademarkId(Integer.parseInt(value));
                                p.setTrademarkId(spt);
                                break;
                            case "typeId.Id":
                                TblTypeProduct pdc = new TblTypeProduct();
                                pdc.setId(Integer.parseInt(value));
                                p.setTypeId(pdc);
                                break;
                        }
                    }

                }
            }
        } catch (FileUploadException ex) {
            Logger.getLogger(AdminController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(AdminController.class.getName()).log(Level.SEVERE, null, ex);
        }

        boolean a = pd.update(p);
        if (a) {
            List<TblProducts> allProducer = pd.getAll();
//         model.addAllAttributes("list",allProduct);
            model.addAttribute("listProduct", allProducer);

            return "redirect: listProduct.htm";
        } else {
            model.addAttribute("messs", "Cập nhật thất bại");
            return "redirect: listProduct.htm?Id=" + p.getProductId();
        }

    }
}
