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
@Table(name = "tblTrademark")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TblTrademark.findAll", query = "SELECT t FROM TblTrademark t"),
    @NamedQuery(name = "TblTrademark.findByTrademarkId", query = "SELECT t FROM TblTrademark t WHERE t.trademarkId = :trademarkId"),
    @NamedQuery(name = "TblTrademark.findByTrademark", query = "SELECT t FROM TblTrademark t WHERE t.trademark = :trademark")})
public class TblTrademark implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "TrademarkId")
    private Integer trademarkId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 64)
    @Column(name = "Trademark")
    private String trademark;
    @OneToMany(mappedBy = "trademarkId")
    private Collection<TblProducts> tblProductsCollection;

    public TblTrademark() {
    }

    public TblTrademark(Integer trademarkId) {
        this.trademarkId = trademarkId;
    }

    public TblTrademark(Integer trademarkId, String trademark) {
        this.trademarkId = trademarkId;
        this.trademark = trademark;
    }

    public Integer getTrademarkId() {
        return trademarkId;
    }

    public void setTrademarkId(Integer trademarkId) {
        this.trademarkId = trademarkId;
    }

    public String getTrademark() {
        return trademark;
    }

    public void setTrademark(String trademark) {
        this.trademark = trademark;
    }

    @XmlTransient
    public Collection<TblProducts> getTblProductsCollection() {
        return tblProductsCollection;
    }

    public void setTblProductsCollection(Collection<TblProducts> tblProductsCollection) {
        this.tblProductsCollection = tblProductsCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (trademarkId != null ? trademarkId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TblTrademark)) {
            return false;
        }
        TblTrademark other = (TblTrademark) object;
        if ((this.trademarkId == null && other.trademarkId != null) || (this.trademarkId != null && !this.trademarkId.equals(other.trademarkId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.TblTrademark[ trademarkId=" + trademarkId + " ]";
    }

}
