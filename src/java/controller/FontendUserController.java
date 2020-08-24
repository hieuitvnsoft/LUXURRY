/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import DAO.UserDAO;
import entities.TblAdmin;
import entities.TblColor;
import entities.TblProductDetail;
import entities.TblProducts;
import entities.TblUser;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletContext;
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
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author Admin
 */
@Controller
@RequestMapping(value = "")
public class FontendUserController {

    UserDAO userDAO = new UserDAO();

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(@RequestParam("username") String username, @RequestParam("password") String password, HttpSession session, Model m) {

        TblUser user = userDAO.checkLogin(username, password);
        if (user != null) {
            //HttpSession loginAdmin = (HttpSession) list;
            session.setAttribute("loginUser", user);
            session.setAttribute("adminName", user.getFullName());
            session.setAttribute("adminImage", user.getAvatar());
//            int i1 = new OrderDAO().countOrderNew();
//            int i2 = new OrderDAO().countOrderDetroy();
//            session.setAttribute("orderNew", i1);
//            session.setAttribute("orderDetroy", i2);
            return "redirect:/home.htm";
        } else {
            m.addAttribute("messs", "Tài khoản hoặc mật khẩu chưa đúng! Xin kiểm tra lại");
            return "Fontend/home";
        }
    }

    @RequestMapping(value = "/logout")
    public String logout(HttpSession session, Model m) {
        session.setAttribute("loginUser", null);
        return "redirect:/home.htm";

    }

//    @RequestMapping(value = "/register")
//    public ModelAndView saveimage(@RequestParam CommonsMultipartFile file, HttpSession session, @RequestParam("fullName") String fullName) throws Exception {
//
//        ServletContext context = session.getServletContext();
//        String path = context.getRealPath("/LUXURY/Views/Images");
//        String filename = file.getOriginalFilename();
//
//        System.out.println(path + " " + filename);
//
//        byte[] bytes = file.getBytes();
//        BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(
//                new File(path + File.separator + filename)));
//        stream.write(bytes);
//        stream.flush();
//        stream.close();
//
//        return new ModelAndView("home", "mess", "Đăng ký thành công");
//    }
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String insertProductDetail(HttpSession session, Model model, @ModelAttribute("user") TblUser p, HttpServletRequest request) {

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
                    p.setAvatar("LUXURY/Views/Image/" + dateString + extension);
                    fi.write(new File(path + "/" + dateString + extension));
                } else {
                    {
                        String name = fi.getFieldName();
                        String value = fi.getString();
                        switch (name) {
                            case "fullName":
                                p.setFullName(new String(value.getBytes("iso-8859-1"), "UTF-8"));
                                break;
                            case "address":
                                p.setAddress(new String(value.getBytes("iso-8859-1"), "UTF-8"));
                                break;
                            case "phone":
                                p.setPhone(new String(value.getBytes("iso-8859-1"), "UTF-8"));
                                break;
                            case "username":
                                p.setUserName(new String(value.getBytes("iso-8859-1"), "UTF-8"));

                                break;

                            case "password":
                                p.setPassword(new String(value.getBytes("iso-8859-1"), "UTF-8"));
                                break;
                            case "email":
                                p.setEmail(new String(value.getBytes("iso-8859-1"), "UTF-8"));
                                break;
                            case "birthday":
                                SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
                                Date date = format1.parse(value);

                                p.setBirthDay(date);
                                break;
                            case "sex":
                                if (Integer.parseInt(value) == 1) {
                                    p.setSex(true);
                                } else {
                                    p.setSex(false);
                                }

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

        boolean check = false;
        check = userDAO.checkExist(p.getUserName());
        if (check) {
            model.addAttribute("messs", "Tài khoản đã tồn tại xin kiểm tra lại");
            return "redirect: home.htm";
        } else {

            boolean a = userDAO.insert(p);
            if (a) {

                model.addAttribute("messs", "Đăng ký thành công");
                return "redirect: home.htm";
            } else {
                model.addAttribute("messs", "Đăng ký thất bại");
                return "redirect: home.htm";
            }
        }
    }
}
