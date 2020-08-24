/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import DAO.ColorDAO;
import DAO.ProductDAO;
import DAO.ProductDetailDAO;
import DAO.TrademarkDAO;
import DAO.TypeProductDAO;
import com.google.gson.Gson;

import com.google.gson.JsonObject;
import entities.TblAdmin;
import entities.TblProductDetail;
import entities.TblUser;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author Admin
 */
@Controller
@RequestMapping(value = "")
public class FontendController {

    TrademarkDAO tdd = new TrademarkDAO();
    TypeProductDAO tpd = new TypeProductDAO();
    ProductDAO pd = new ProductDAO();
    ProductDetailDAO detailDAO = new ProductDetailDAO();
    ColorDAO colorDAO = new ColorDAO();

    @RequestMapping(value = "/home")
    public String homeFontend(HttpSession session, Model model, @RequestParam(value = "offset", required = false) Integer offset, Integer maxResults) {
        if (session == null || session.getAttribute("loginUser") == null) {
            model.addAttribute("listProduct", pd.findAll(offset, maxResults));

            model.addAttribute("count", pd.count());
            model.addAttribute("offset", offset);

            session.setAttribute("listTrademark", tdd.getAll());
            session.setAttribute("listType", tpd.getAll());
            //model.addAttribute("page", "index");
            return "Fontend/home";
        } else {

            TblUser a = (TblUser) session.getAttribute("loginUser");

            model.addAttribute("listProduct", pd.getAll());
            session.setAttribute("listTrademark", tdd.getAll());
            session.setAttribute("listType", tpd.getAll());

            return "Fontend/home";
        }

    }
    @RequestMapping(value = "/search")
    public String homeFontend(HttpSession session, Model model, @RequestParam(value = "search", defaultValue = "") String search) {
        if (session == null || session.getAttribute("loginUser") == null) {
            model.addAttribute("listProduct", pd.findAllbySearch(search));

          

            session.setAttribute("listTrademark", tdd.getAll());
            session.setAttribute("listType", tpd.getAll());
//            model.addAttribute("page", "index");
            return "Fontend/search";
        } else {

            TblUser a = (TblUser) session.getAttribute("loginUser");

            model.addAttribute("listProduct", pd.getAll());
            session.setAttribute("listTrademark", tdd.getAll());
            session.setAttribute("listType", tpd.getAll());

            return "Fontend/search";
        }

    }

    @RequestMapping(value = "/ProductDetail")
    public String productDetail(HttpSession session, Model model, @RequestParam("productId") int id) {
        session.setAttribute("listTrademark", tdd.getAll());
        session.setAttribute("listType", tpd.getAll());

        model.addAttribute("listDetail", detailDAO.getAllById(id));
        model.addAttribute("listColor", colorDAO.getAll());
        model.addAttribute("product", pd.getById(id));
        return "Fontend/productDetail";
    }

    @RequestMapping(value = "/Trademark")
    public String Trademark(HttpSession session, Model model, @RequestParam("id") int id) {
        session.setAttribute("listTrademark", tdd.getAll());
        session.setAttribute("listType", tpd.getAll());
        model.addAttribute("listProductTramark", pd.getAll(id));
        model.addAttribute("trademark", tdd.getById(id).getTrademark());
        return "Fontend/trademark";
    }

    @RequestMapping(value = "/TypeProduct")
    public String TypeProduct(HttpSession session, Model model, @RequestParam("id") int id) {
        session.setAttribute("listTrademark", tdd.getAll());
        session.setAttribute("listType", tpd.getAll());
        model.addAttribute("listTypeProduct", pd.getAllType(id));
        model.addAttribute("typeProduct", tpd.getById(id).getTypeName());
        return "Fontend/typeProduct";
    }

    @RequestMapping(value = "/Contact")
    public String Contact(HttpSession session, Model model) {
        session.setAttribute("listTrademark", tdd.getAll());
        session.setAttribute("listType", tpd.getAll());
//        model.addAttribute("listTypeProduct", pd.getAllType(id));
//        model.addAttribute("typeProduct", tpd.getById(id).getTypeName());
        return "Fontend/contact";
    }

    @RequestMapping(value = "/getPrice", method = RequestMethod.GET)
    public @ResponseBody
    String getPrice(HttpServletRequest request, @RequestParam("id") int id) {

        TblProductDetail p = detailDAO.getById(id);
        ProductDetail ps = new ProductDetail();
        ps.setId(p.getId());
        ps.setPriceNew(p.getPriceNew());
        ps.setQuantity(p.getQuantity());
        ps.setSale(p.getSale());
        ps.setStatus(p.getStatus());
        ps.setImageProduct(p.getImageProduct());
        ps.setColorId(p.getColorId().getId());
        ps.setProductId(p.getProductId().getProductId());
        ps.setId(p.getId());
        ps.setId(p.getId());
        ps.setId(p.getId());
        return new Gson().toJson(ps);

    }

    class ProductDetail {

        private Integer id;
        private Double priceNew;
        private Integer quantity;
        private Integer sale;
        private Integer status;
        private String imageProduct;
        private Integer colorId;
        private Integer productId;

        public ProductDetail() {
        }

        public int getProductId() {
            return productId;
        }

        public void setProductId(Integer productId) {
            this.productId = productId;
        }

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public Double getPriceNew() {
            return priceNew;
        }

        public void setPriceNew(Double priceNew) {
            this.priceNew = priceNew;
        }

        public Integer getQuantity() {
            return quantity;
        }

        public void setQuantity(Integer quantity) {
            this.quantity = quantity;
        }

        public Integer getSale() {
            return sale;
        }

        public void setSale(Integer sale) {
            this.sale = sale;
        }

        public Integer getStatus() {
            return status;
        }

        public void setStatus(Integer status) {
            this.status = status;
        }

        public String getImageProduct() {
            return imageProduct;
        }

        public void setImageProduct(String imageProduct) {
            this.imageProduct = imageProduct;
        }

        public int getColorId() {
            return colorId;
        }

        public void setColorId(Integer colorId) {
            this.colorId = colorId;
        }

    }
}
