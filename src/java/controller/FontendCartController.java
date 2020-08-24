/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import DAO.OrderDAO;
import DAO.PaymentMethodDAO;
import DAO.ProductDAO;
import DAO.ProductDetailDAO;
import DAO.ShipMethodDAO;
import common.EmailUtil;
import entities.TblColor;
import entities.TblOrderDetail;
import entities.TblOrderDetail_;
import entities.TblOrders;
import entities.TblPaymentMethod;
import entities.TblProductDetail;
import entities.TblProducts;
import entities.TblShipMethod;
import entities.TblTrademark;
import entities.TblUser;
import entities_extend.Cart;
import java.io.File;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.MessagingException;
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
@RequestMapping(value = "")
public class FontendCartController {
    
    ProductDetailDAO productDetailDAO = new ProductDetailDAO();
    ProductDAO productDAO = new ProductDAO();
    OrderDAO orderDAO = new OrderDAO();
    
    @RequestMapping(value = "/checkout")
    public String Checkout(HttpSession session, Model model) {

        //session.getAttribute("cart") == null
        return "Fontend/checkout";
    }
    
    @RequestMapping(value = "/addCart")
    public String AddCart(HttpSession session, Model model, @RequestParam("productId") int productId) {
        float totalAmount = 0; //tổng tiền thanh toán
        //Theo request lấy dc chi tiết sản phẩm
        TblProductDetail product = productDetailDAO.getById(productId);

        //Kiểm tra nếu chưa có session listCat thì tạo session và add chi tiết vào listCat
        if (session.getAttribute("listCart") == null) {
            Cart cart = new Cart();
            cart.setProductDetailId(product.getId());
            cart.setPriceNew(product.getPriceNew());
            cart.setProductName(product.getProductId().getProductName() + "-" + product.getColorId().getColorName());
            cart.setSale(product.getSale());
            cart.setImageProduct(product.getImageProduct());
            cart.setQuantity(1);
            List<Cart> listCart = new ArrayList<>();
            listCart.add(cart);
            
            int quantityproduct = 0;
            for (Cart listCart1 : listCart) {//Duyệt để đếm số lượng sản phẩm người mua 

                totalAmount += (listCart1.getPriceNew() - (listCart1.getPriceNew() * listCart1.getSale() / 100)) * listCart1.getQuantity();
                quantityproduct += listCart1.getQuantity();
            }
            session.setAttribute("listCart", listCart);
            session.setAttribute("totalAmount", totalAmount);
            session.setAttribute("cartquantity", quantityproduct);
        } else { //Ngược lại có thì chúng ta ép session về 1 List <Cart>
            List<Cart> listCart = (List<Cart>) session.getAttribute("listCart");
            
            boolean check = false; //Biến kiểm tra sự tồn tại của chi tiết sp trong listCart

            Cart cart = new Cart();
            cart.setProductDetailId(product.getId());
            cart.setPriceNew(product.getPriceNew());
            cart.setProductName(product.getProductId().getProductName() + "-" + product.getColorId().getColorName());
            cart.setSale(product.getSale());
            cart.setImageProduct(product.getImageProduct());
            cart.setQuantity(1);
            //Duyệt 1 lượt trong List<Cart> xem có chi tiết nào có id == cái add mới thì trả check =true và dừng
            for (Cart listCart1 : listCart) {
                if (listCart1.getProductDetailId() == product.getId()) {
                    check = true;
                    break;
                }
            }
//Kiểm tra check nếu ==true thì thì duyệt listCart và tăng số lượng sp chi tiết lên +1;
            if (check) {
                for (Cart listCart1 : listCart) {
                    if (listCart1.getProductDetailId() == product.getId()) {
                        listCart1.setQuantity(listCart1.getQuantity() + 1);
                    }
                }
            } else {//Ngược lại add new
                listCart.add(cart);
                
            }
            int quantityproduct = 0;
            for (Cart listCart1 : listCart) {//Duyệt để đếm số lượng sản phẩm người mua 
                quantityproduct += listCart1.getQuantity();
                totalAmount += (listCart1.getPriceNew() - (listCart1.getPriceNew() * listCart1.getSale() / 100)) * listCart1.getQuantity();
            }
            session.setAttribute("listCart", listCart);
            session.setAttribute("cartquantity", quantityproduct);
            session.setAttribute("totalAmount", totalAmount);
        }

        //session.getAttribute("cart") == null
        return "Fontend/checkout";
    }
    
    @RequestMapping(value = "/updateCart", method = RequestMethod.POST)
    public String UpdateCart(HttpSession session, HttpServletRequest request) {
        List<Cart> listCart = (List<Cart>) session.getAttribute("listCart");
        String[] quantity = request.getParameterValues("quantity");
        for (int i = 0; i < listCart.size(); i++) {
            Cart z = listCart.get(i);
            if (Integer.parseInt(quantity[i]) <= 0) {
                listCart.remove(z);
            } else {
                z.setQuantity(Integer.parseInt(quantity[i]));
            }
        }
        float totalAmount = 0;
        int quantityproduct = 0;
        for (Cart listCart1 : listCart) {//Duyệt để đếm số lượng sản phẩm người mua 
            quantityproduct += listCart1.getQuantity();
            totalAmount += (listCart1.getPriceNew() - (listCart1.getPriceNew() * listCart1.getSale() / 100)) * listCart1.getQuantity();
        }
        if (listCart.size() == 0) {
            session.setAttribute("listCart", null);
            session.setAttribute("cartquantity", 0);
            session.setAttribute("totalAmount", 0);
        } else {
            session.setAttribute("listCart", listCart);
            session.setAttribute("cartquantity", quantityproduct);
            session.setAttribute("totalAmount", totalAmount);
            
        }
        return "Fontend/checkout";
    }
    
    @RequestMapping(value = "/removeProduct")
    public String RemoveProduct(HttpSession session, @RequestParam("id") int productId) {
        if (session.getAttribute("listCart") == null) {
            return "redirect: home.htm";
        } else {
            List<Cart> listCart = (List<Cart>) session.getAttribute("listCart");
            
            int index = -1;
            
            for (int i = 0; i < listCart.size(); i++) {
                Cart get = listCart.get(i);
                if (get.getProductDetailId() == productId) {
                    index = i;
                    
                }
            }
            if (index >= 0) {
                listCart.remove(index);
            }
            float totalAmount = 0;
            int quantityproduct = 0;
            for (Cart listCart1 : listCart) {//Duyệt để đếm số lượng sản phẩm người mua 
                quantityproduct += listCart1.getQuantity();
                totalAmount += (listCart1.getPriceNew() - (listCart1.getPriceNew() * listCart1.getSale() / 100)) * listCart1.getQuantity();
            }
            
            if (listCart.size() == 0) {
                session.setAttribute("listCart", null);
                session.setAttribute("cartquantity", 0);
                session.setAttribute("totalAmount", 0);
            } else {
                session.setAttribute("listCart", listCart);
                session.setAttribute("cartquantity", quantityproduct);
                session.setAttribute("totalAmount", totalAmount);
                
            }
            return "Fontend/checkout";
        }
    }
    
    PaymentMethodDAO paymentMethodDAO = new PaymentMethodDAO();
    ShipMethodDAO shipMethodDAO = new ShipMethodDAO();
    
    @RequestMapping(value = "/payment")
    public String Payment(HttpSession session, Model model) {
        model.addAttribute("order", new TblOrders());
        model.addAttribute("listPaymenMethod", paymentMethodDAO.getAll());
        model.addAttribute("listShipMethod", shipMethodDAO.getAll());
        return "Fontend/payment";
    }
    
    @RequestMapping(value = "/order")
    public String Order(HttpSession session, Model model, @ModelAttribute("order") TblOrders p, HttpServletRequest request) {
        
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
                    // p.setImageProduct("LUXURY/Views/Image/" + dateString + extension);
                    fi.write(new File(path + "/" + dateString + extension));
                } else {
                    {
                        String name = fi.getFieldName();
                        String value = fi.getString();
                        switch (name) {
                            case "fullName":
                                p.setFullName(new String(value.getBytes("iso-8859-1"), "UTF-8"));
                                break;
                            case "addressShip":
                                p.setAddressShip(new String(value.getBytes("iso-8859-1"), "UTF-8"));
                                break;
                            case "phone":
                                
                                p.setPhone(new String(value.getBytes("iso-8859-1"), "UTF-8"));
                                
                                break;
                            
                            case "description":
                                
                                p.setDescription(new String(value.getBytes("iso-8859-1"), "UTF-8"));
                                break;
                            case "paymentMethod.paymentMethodId":
                                TblShipMethod ship = new TblShipMethod();
                                ship.setShipMethodId(Integer.parseInt(value));
                                p.setShipMethod(ship);
                                break;
                            case "shipMethod.shipMethodId":
                                TblPaymentMethod pm = new TblPaymentMethod();
                                pm.setPaymentMethodId(Integer.parseInt(value));
                                p.setPaymentMethod(pm);
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
        
        if (session.getAttribute("loginUser") != null) {
            TblUser u = (TblUser) session.getAttribute("loginUser");
            
            p.setUserId(u.getUserId());
        }
        p.setDateOrder(new Date());
        Float sss = (Float) session.getAttribute("totalAmount");
        double totalAmountOrder = sss;
        p.setStatus(0);
        p.setTotalAmountOrder(totalAmountOrder);
        List<Cart> listCart = (List<Cart>) session.getAttribute("listCart");
        List<TblOrderDetail> detaillist = new ArrayList<>();
        for (Cart listCart1 : listCart) {
            TblOrderDetail k = new TblOrderDetail();
            k.setProductName(listCart1.getProductName());
            TblProductDetail productDetail = new TblProductDetail(listCart1.getProductDetailId());
            k.setProductId(productDetail);
            k.setQuantity(listCart1.getQuantity());
            k.setPriceProductOrder(listCart1.getPriceNew() - (listCart1.getPriceNew() * listCart1.getSale() / 100));
            k.setAmount((listCart1.getPriceNew() - (listCart1.getPriceNew() * listCart1.getSale() / 100)) * listCart1.getQuantity());
            k.setStatus(1);
            detaillist.add(k);
        }
        
        boolean a = orderDAO.insert(p, detaillist);
        if (a) {
            if (session.getAttribute("loginUser") != null) {
                model.addAttribute("messs", "Đặt hàng thành công check mail hoặc check đơn hàng để xem chi tiết!");
                
                TblUser u = (TblUser) session.getAttribute("loginUser");
                model.addAttribute("mess", "Đặt hàng thành công check mail hoặc check đơn hàng để xem chi tiết!");
                NumberFormat fomart = NumberFormat.getInstance(new Locale("vi", "VN"));
                String text = "Bạn vừa đặt hàng tại app LUXURY - Chúng tôi xin chân thành cảm ơn bạn đã tin tưởng LUXURY<br/>"
                        + "Tổng số tiền hóa đơn của bạn là: " + fomart.format(totalAmountOrder) + "<br/>"
                        + "Chúng tôi sẽ sớm tiến hành check đơn hàng và vận chuyển cho bạn sớm nhất";
                try {
                    EmailUtil.sendMail(u.getEmail(), "Thông báo đơn đặt hàng LUXURY", text);
                } catch (MessagingException ex) {
                    Logger.getLogger(FontendCartController.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                
                model.addAttribute("messs", "Đặt hàng thành công chúng tôi sẽ liên lạc sớm với bạn");
            }
            session.setAttribute("listCart", null);
            session.setAttribute("cartquantity", null);
            session.setAttribute("totalAmount", null);
            return "redirect: home.htm";
        } else {
            model.addAttribute("messs", "Lỗi server xin thử lại");
            return "redirect: home.htm";
        }
        
    }
    
    @RequestMapping(value = "/checkOder")
    public String checkOder(HttpSession session, Model model) {
        
        TblUser u = (TblUser) session.getAttribute("loginUser");
        
        List<TblOrders> oder = orderDAO.getAllbyUser(u.getUserId());
        List<TblOrderDetail> listoderDetail = new ArrayList<>();
        
        for (TblOrders order1 : oder) {
            List<TblOrderDetail> i = orderDAO.getOrderDetailbyId(order1.getOrderId());
            listoderDetail.addAll(i);
            
        }
        model.addAttribute("listOrder", oder);
        model.addAttribute("listOrderDetail", listoderDetail);

//        model.addAttribute("order", new TblOrders());
//        model.addAttribute("listPaymenMethod", paymentMethodDAO.getAll());
//        model.addAttribute("listShipMethod", shipMethodDAO.getAll());
        return "Fontend/checkOder";
    }
    
    @RequestMapping(value = "/cancleOrder")
    public String cancleOrder(HttpSession session, Model model, @RequestParam("id") int id) {
        
        TblOrders o = orderDAO.getOrderbyId(id);
        o.setStatus(4);
        orderDAO.updateOrder(o);
        return "redirect: checkOder.htm";
    }
}
