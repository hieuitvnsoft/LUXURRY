/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import DAO.NewsDAO;
import DAO.TypeNewsDAO;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author Admin
 */
@Controller
@RequestMapping(value = "")
public class FontendNewsController {

    NewsDAO newsDAO = new NewsDAO();
    TypeNewsDAO typeNewsDAO = new TypeNewsDAO();

    @RequestMapping(value = "/News")
    public String productDetail(HttpSession session, Model model) {
        model.addAttribute("listTypeNews", typeNewsDAO.getAll());
        model.addAttribute("listNews", newsDAO.getAll());
        return "Fontend/news";
    }
}
