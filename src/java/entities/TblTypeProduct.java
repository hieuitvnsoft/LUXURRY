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
@Table(name = "tblTypeProduct")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TblTypeProduct.findAll", query = "SELECT t FROM TblTypeProduct t"),
    @NamedQuery(name = "TblTypeProduct.findById", query = "SELECT t FROM TblTypeProduct t WHERE t.id = :id"),
    @NamedQuery(name = "TblTypeProduct.findByTypeName", query = "SELECT t FROM TblTypeProduct t WHERE t.typeName = :typeName")})
public class TblTypeProduct implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private Integer id;
    @Size(max = 100)
    @Column(name = "TypeName")
    private String typeName;
    @OneToMany(mappedBy = "typeId")
    private Collection<TblProducts> tblProductsCollection;

    public TblTypeProduct() {
    }

    public TblTypeProduct(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
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
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TblTypeProduct)) {
            return false;
        }
        TblTypeProduct other = (TblTypeProduct) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.TblTypeProduct[ id=" + id + " ]";
    }

}
