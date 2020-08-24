/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import entities.TblProducts;
import java.util.List;

/**
 *
 * @author Admin
 */
public class ProductDAOImplService implements ImplProductPageService {

    ProductDAO productDAO = new ProductDAO();

    @Override
    public List<TblProducts> findAll(Integer offset, Integer maxResult) {
        return productDAO.findAll(offset, maxResult);
    }

    @Override
    public Long count() {
        return productDAO.count();
    }

}
