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
public interface ImplProductPage {

    public List<TblProducts> findAll(Integer offset, Integer maxResult);

    public Long count();
}
