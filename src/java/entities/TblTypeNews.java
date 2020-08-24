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
@Table(name = "tblTypeNews")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TblTypeNews.findAll", query = "SELECT t FROM TblTypeNews t"),
    @NamedQuery(name = "TblTypeNews.findByTypeNewId", query = "SELECT t FROM TblTypeNews t WHERE t.typeNewId = :typeNewId"),
    @NamedQuery(name = "TblTypeNews.findByTypeNewName", query = "SELECT t FROM TblTypeNews t WHERE t.typeNewName = :typeNewName")})
public class TblTypeNews implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "TypeNewId")
    private Integer typeNewId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 64)
    @Column(name = "TypeNewName")
    private String typeNewName;
    @OneToMany(mappedBy = "typeId")
    private Collection<TblNews> tblNewsCollection;

    public TblTypeNews() {
    }

    public TblTypeNews(Integer typeNewId) {
        this.typeNewId = typeNewId;
    }

    public TblTypeNews(Integer typeNewId, String typeNewName) {
        this.typeNewId = typeNewId;
        this.typeNewName = typeNewName;
    }

    public Integer getTypeNewId() {
        return typeNewId;
    }

    public void setTypeNewId(Integer typeNewId) {
        this.typeNewId = typeNewId;
    }

    public String getTypeNewName() {
        return typeNewName;
    }

    public void setTypeNewName(String typeNewName) {
        this.typeNewName = typeNewName;
    }

    @XmlTransient
    public Collection<TblNews> getTblNewsCollection() {
        return tblNewsCollection;
    }

    public void setTblNewsCollection(Collection<TblNews> tblNewsCollection) {
        this.tblNewsCollection = tblNewsCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (typeNewId != null ? typeNewId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TblTypeNews)) {
            return false;
        }
        TblTypeNews other = (TblTypeNews) object;
        if ((this.typeNewId == null && other.typeNewId != null) || (this.typeNewId != null && !this.typeNewId.equals(other.typeNewId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.TblTypeNews[ typeNewId=" + typeNewId + " ]";
    }

}
