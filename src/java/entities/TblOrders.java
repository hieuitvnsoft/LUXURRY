/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Admin
 */
@Entity
@Table(name = "tblOrders")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TblOrders.findAll", query = "SELECT t FROM TblOrders t"),
    @NamedQuery(name = "TblOrders.findByOrderId", query = "SELECT t FROM TblOrders t WHERE t.orderId = :orderId"),
    @NamedQuery(name = "TblOrders.findByUserId", query = "SELECT t FROM TblOrders t WHERE t.userId = :userId"),
    @NamedQuery(name = "TblOrders.findByFullName", query = "SELECT t FROM TblOrders t WHERE t.fullName = :fullName"),
    @NamedQuery(name = "TblOrders.findByAddressShip", query = "SELECT t FROM TblOrders t WHERE t.addressShip = :addressShip"),
    @NamedQuery(name = "TblOrders.findByPhone", query = "SELECT t FROM TblOrders t WHERE t.phone = :phone"),
    @NamedQuery(name = "TblOrders.findByTotalAmountOrder", query = "SELECT t FROM TblOrders t WHERE t.totalAmountOrder = :totalAmountOrder"),
    @NamedQuery(name = "TblOrders.findByDateOrder", query = "SELECT t FROM TblOrders t WHERE t.dateOrder = :dateOrder"),
    @NamedQuery(name = "TblOrders.findByDescription", query = "SELECT t FROM TblOrders t WHERE t.description = :description"),
    @NamedQuery(name = "TblOrders.findByDescription2", query = "SELECT t FROM TblOrders t WHERE t.description2 = :description2"),
    @NamedQuery(name = "TblOrders.findByStatus", query = "SELECT t FROM TblOrders t WHERE t.status = :status")})
public class TblOrders implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "OrderId")
    private Integer orderId;
    @Column(name = "UserId")
    private Integer userId;
    @Size(max = 100)
    @Column(name = "FullName")
    private String fullName;
    @Size(max = 255)
    @Column(name = "AddressShip")
    private String addressShip;
    // @Pattern(regexp="^\\(?(\\d{3})\\)?[- ]?(\\d{3})[- ]?(\\d{4})$", message="Invalid phone/fax format, should be as xxx-xxx-xxxx")//if the field contains phone or fax number consider using this annotation to enforce field validation
    @Size(max = 20)
    @Column(name = "Phone")
    private String phone;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "TotalAmountOrder")
    private Double totalAmountOrder;
    @Column(name = "DateOrder")
    @Temporal(TemporalType.DATE)
    private Date dateOrder;
    @Size(max = 255)
    @Column(name = "Description")
    private String description;
    @Size(max = 255)
    @Column(name = "Description2")
    private String description2;
    @Column(name = "Status")
    private Integer status;
    @OneToMany(mappedBy = "orderId")
    private Collection<TblOrderDetail> tblOrderDetailCollection;
    @JoinColumn(name = "PaymentMethod", referencedColumnName = "PaymentMethodId")
    @ManyToOne
    private TblPaymentMethod paymentMethod;
    @JoinColumn(name = "ShipMethod", referencedColumnName = "ShipMethodId")
    @ManyToOne
    private TblShipMethod shipMethod;

    public TblOrders() {
    }

    public TblOrders(Integer orderId) {
        this.orderId = orderId;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getAddressShip() {
        return addressShip;
    }

    public void setAddressShip(String addressShip) {
        this.addressShip = addressShip;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Double getTotalAmountOrder() {
        return totalAmountOrder;
    }

    public void setTotalAmountOrder(Double totalAmountOrder) {
        this.totalAmountOrder = totalAmountOrder;
    }

    public Date getDateOrder() {
        return dateOrder;
    }

    public void setDateOrder(Date dateOrder) {
        this.dateOrder = dateOrder;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription2() {
        return description2;
    }

    public void setDescription2(String description2) {
        this.description2 = description2;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @XmlTransient
    public Collection<TblOrderDetail> getTblOrderDetailCollection() {
        return tblOrderDetailCollection;
    }

    public void setTblOrderDetailCollection(Collection<TblOrderDetail> tblOrderDetailCollection) {
        this.tblOrderDetailCollection = tblOrderDetailCollection;
    }

    public TblPaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(TblPaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public TblShipMethod getShipMethod() {
        return shipMethod;
    }

    public void setShipMethod(TblShipMethod shipMethod) {
        this.shipMethod = shipMethod;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (orderId != null ? orderId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TblOrders)) {
            return false;
        }
        TblOrders other = (TblOrders) object;
        if ((this.orderId == null && other.orderId != null) || (this.orderId != null && !this.orderId.equals(other.orderId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.TblOrders[ orderId=" + orderId + " ]";
    }

}
