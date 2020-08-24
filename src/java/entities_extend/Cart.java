/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities_extend;

import entities.TblColor;
import entities.TblOrderDetail;
import entities.TblProductDetail;
import entities.TblProducts;
import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.Size;

/**
 *
 * @author Admin
 */
public class Cart implements Serializable {

   // TblProductDetail product;
    
    private Integer productDetailId;
    private Double priceNew;
    private Integer sale;
    private String imageProduct;
    private String  productName;
    private Integer quantity;

    public Cart() {
    }

    public Integer getProductDetailId() {
        return productDetailId;
    }

    public void setProductDetailId(Integer productDetailId) {
        this.productDetailId = productDetailId;
    }

    public Double getPriceNew() {
        return priceNew;
    }

    public void setPriceNew(Double priceNew) {
        this.priceNew = priceNew;
    }

    public Integer getSale() {
        return sale;
    }

    public void setSale(Integer sale) {
        this.sale = sale;
    }

    public String getImageProduct() {
        return imageProduct;
    }

    public void setImageProduct(String imageProduct) {
        this.imageProduct = imageProduct;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

   

}
