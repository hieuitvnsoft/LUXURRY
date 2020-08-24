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
@Table(name = "tblProducts")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TblProducts.findAll", query = "SELECT t FROM TblProducts t"),
    @NamedQuery(name = "TblProducts.findByProductId", query = "SELECT t FROM TblProducts t WHERE t.productId = :productId"),
    @NamedQuery(name = "TblProducts.findByProductName", query = "SELECT t FROM TblProducts t WHERE t.productName = :productName"),
    @NamedQuery(name = "TblProducts.findByPriceOlder", query = "SELECT t FROM TblProducts t WHERE t.priceOlder = :priceOlder"),
    @NamedQuery(name = "TblProducts.findByDescription", query = "SELECT t FROM TblProducts t WHERE t.description = :description"),
    @NamedQuery(name = "TblProducts.findBySpecifications", query = "SELECT t FROM TblProducts t WHERE t.specifications = :specifications"),
    @NamedQuery(name = "TblProducts.findByImgProduct", query = "SELECT t FROM TblProducts t WHERE t.imgProduct = :imgProduct"),
    @NamedQuery(name = "TblProducts.findByStatus", query = "SELECT t FROM TblProducts t WHERE t.status = :status")})
public class TblProducts implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ProductId")
    private Integer productId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 256)
    @Column(name = "ProductName")
    private String productName;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "PriceOlder")
    private Double priceOlder;
    @Size(max = 1073741823)
    @Column(name = "Description")
    private String description;
    @Size(max = 1073741823)
    @Column(name = "Specifications")
    private String specifications;
    @Size(max = 1073741823)
    @Column(name = "ImgProduct")
    private String imgProduct;
    @Column(name = "Status")
    private Integer status;
    @JoinColumn(name = "TrademarkId", referencedColumnName = "TrademarkId")
    @ManyToOne
    private TblTrademark trademarkId;
    @JoinColumn(name = "TypeId", referencedColumnName = "Id")
    @ManyToOne
    private TblTypeProduct typeId;
    @OneToMany(mappedBy = "productId")
    private Collection<TblProductDetail> tblProductDetailCollection;
    @OneToMany(mappedBy = "productId")
    private Collection<TblLiked> tblLikedCollection;

    public TblProducts() {
    }

    public TblProducts(Integer productId) {
        this.productId = productId;
    }

    public TblProducts(Integer productId, String productName) {
        this.productId = productId;
        this.productName = productName;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Double getPriceOlder() {
        return priceOlder;
    }

    public void setPriceOlder(Double priceOlder) {
        this.priceOlder = priceOlder;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSpecifications() {
        return specifications;
    }

    public void setSpecifications(String specifications) {
        this.specifications = specifications;
    }

    public String getImgProduct() {
        return imgProduct;
    }

    public void setImgProduct(String imgProduct) {
        this.imgProduct = imgProduct;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public TblTrademark getTrademarkId() {
        return trademarkId;
    }

    public void setTrademarkId(TblTrademark trademarkId) {
        this.trademarkId = trademarkId;
    }

    public TblTypeProduct getTypeId() {
        return typeId;
    }

    public void setTypeId(TblTypeProduct typeId) {
        this.typeId = typeId;
    }

    @XmlTransient
    public Collection<TblProductDetail> getTblProductDetailCollection() {
        return tblProductDetailCollection;
    }

    public void setTblProductDetailCollection(Collection<TblProductDetail> tblProductDetailCollection) {
        this.tblProductDetailCollection = tblProductDetailCollection;
    }

    @XmlTransient
    public Collection<TblLiked> getTblLikedCollection() {
        return tblLikedCollection;
    }

    public void setTblLikedCollection(Collection<TblLiked> tblLikedCollection) {
        this.tblLikedCollection = tblLikedCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (productId != null ? productId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TblProducts)) {
            return false;
        }
        TblProducts other = (TblProducts) object;
        if ((this.productId == null && other.productId != null) || (this.productId != null && !this.productId.equals(other.productId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.TblProducts[ productId=" + productId + " ]";
    }

}
