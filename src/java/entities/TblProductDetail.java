/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Admin
 */
@Entity
@Table(name = "tblProductDetail")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TblProductDetail.findAll", query = "SELECT t FROM TblProductDetail t"),
    @NamedQuery(name = "TblProductDetail.findById", query = "SELECT t FROM TblProductDetail t WHERE t.id = :id"),
    @NamedQuery(name = "TblProductDetail.findByPriceNew", query = "SELECT t FROM TblProductDetail t WHERE t.priceNew = :priceNew"),
    @NamedQuery(name = "TblProductDetail.findByQuantity", query = "SELECT t FROM TblProductDetail t WHERE t.quantity = :quantity"),
    @NamedQuery(name = "TblProductDetail.findBySale", query = "SELECT t FROM TblProductDetail t WHERE t.sale = :sale"),
    @NamedQuery(name = "TblProductDetail.findByStatus", query = "SELECT t FROM TblProductDetail t WHERE t.status = :status"),
    @NamedQuery(name = "TblProductDetail.findByImageProduct", query = "SELECT t FROM TblProductDetail t WHERE t.imageProduct = :imageProduct")})
public class TblProductDetail implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private Integer id;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "PriceNew")
    private Double priceNew;
    @Column(name = "Quantity")
    private Integer quantity;
    @Column(name = "Sale")
    private Integer sale;
    @Column(name = "Status")
    private Integer status;
    @Size(max = 1073741823)
    @Column(name = "ImageProduct")
    private String imageProduct;
    @OneToMany(mappedBy = "productId")
    private Collection<TblOrderDetail> tblOrderDetailCollection;
    @JoinColumn(name = "ColorId", referencedColumnName = "Id")
    @ManyToOne
    private TblColor colorId;
    @JoinColumn(name = "ProductId", referencedColumnName = "ProductId")
    @ManyToOne
    private TblProducts productId;

    public TblProductDetail() {
    }

    public TblProductDetail(Integer id) {
        this.id = id;
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

    @XmlTransient
    public Collection<TblOrderDetail> getTblOrderDetailCollection() {
        return tblOrderDetailCollection;
    }

    public void setTblOrderDetailCollection(Collection<TblOrderDetail> tblOrderDetailCollection) {
        this.tblOrderDetailCollection = tblOrderDetailCollection;
    }

    public TblColor getColorId() {
        return colorId;
    }

    public void setColorId(TblColor colorId) {
        this.colorId = colorId;
    }

    public TblProducts getProductId() {
        return productId;
    }

    public void setProductId(TblProducts productId) {
        this.productId = productId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TblProductDetail)) {
            return false;
        }
        TblProductDetail other = (TblProductDetail) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.TblProductDetail[ id=" + id + " ]";
    }

}
