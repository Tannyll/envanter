package com.emirci.envanter.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by serdaremirci on 9/18/17.
 */
@Entity
@Table(name = "XInventory")
public class Inventory implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "InventoryId", unique = true, nullable = false)
    private Long inventoryId;

    //@OneToOne(fetch = FetchType.LAZY, mappedBy = "inventory", cascade = CascadeType.ALL, optional = false)

    //@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER, optional = true)
    @ManyToOne(fetch = FetchType.EAGER, optional = true)
    @JoinColumn(name = "DepartmentId", foreignKey = @ForeignKey(name = "FK_XInventory_AttDepartment"), nullable = true)
    private Department depar;


    @ManyToOne(fetch = FetchType.EAGER, optional = true)
    @JoinColumn(name = "TrademarkId", foreignKey = @ForeignKey(name = "FK_XInventory_AttTrademark"), nullable = true)
    private Trademark trade;


    @ManyToOne(fetch = FetchType.EAGER, optional = true)
    @JoinColumn(name = "InventoryTypeId", foreignKey = @ForeignKey(name = "FK_XInventory_AttInventoryType"), nullable = true)
    private InventoryType invtyp;


    @Column(name = "UserId", length = 128)
    private String userId;

    @Column(name = "InsertUserId")
    private String insertUserId;

    @Column(name = "UsesUser")
    private String usesUser;

    @Column(name = "InsertDate", columnDefinition = "DATE")
    private Date insertDate;

    @Column(name = "Feature")
    private String feature;

    @Column(name = "Model")
    private String model;

    @Column(name = "InvoiceNumber")
    private String invoiceNumber;

    @Column(name = "InvoiceDate", columnDefinition = "DATE")
    private Date invoiceDate;

    @Column(name = "Price")
    private double price;

    @Column(name = "Barcode")
    private String barcode;

    @Column(name = "VERSION")
    private int version;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Long getInventoryId() {
        return inventoryId;
    }

    public void setInventoryId(Long inventoryId) {
        this.inventoryId = inventoryId;
    }

    public Department getDepar() {
        return depar;
    }

    public void setDepar(Department depar) {
        this.depar = depar;
    }

    public Trademark getTrade() {
        return trade;
    }

    public void setTrade(Trademark trade) {
        this.trade = trade;
    }

    public InventoryType getInvtyp() {
        return invtyp;
    }

    public void setInvtyp(InventoryType invtyp) {
        this.invtyp = invtyp;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getInsertUserId() {
        return insertUserId;
    }

    public void setInsertUserId(String insertUserId) {
        this.insertUserId = insertUserId;
    }

    public String getUsesUser() {
        return usesUser;
    }

    public void setUsesUser(String usesUser) {
        this.usesUser = usesUser;
    }

    public Date getInsertDate() {
        return insertDate;
    }

    public void setInsertDate(Date insertDate) {
        this.insertDate = insertDate;
    }

    public String getFeature() {
        return feature;
    }

    public void setFeature(String feature) {
        this.feature = feature;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getInvoiceNumber() {
        return invoiceNumber;
    }

    public void setInvoiceNumber(String invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
    }

    public Date getInvoiceDate() {
        return invoiceDate;
    }

    public void setInvoiceDate(Date invoiceDate) {
        this.invoiceDate = invoiceDate;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public Inventory() {


    }


    public Inventory(String userId, String insertUserId, String usesUser, Date insertDate, String feature, String model, String invoiceNumber, Date invoiceDate, double price, String barcode) {
        this.userId = userId;
        this.insertUserId = insertUserId;
        this.usesUser = usesUser;
        this.insertDate = insertDate;
        this.feature = feature;
        this.model = model;
        this.invoiceNumber = invoiceNumber;
        this.invoiceDate = invoiceDate;
        this.price = price;
        this.barcode = barcode;
    }

    @Override
    public String toString() {
        return "Inventory{" +
                "inventoryId=" + inventoryId +
                ", userId='" + userId + '\'' +
                ", insertUserId='" + insertUserId + '\'' +
                ", usesUser='" + usesUser + '\'' +
                ", insertDate=" + insertDate +
                ", feature='" + feature + '\'' +
                ", model='" + model + '\'' +
                ", invoiceNumber='" + invoiceNumber + '\'' +
                ", invoiceDate=" + invoiceDate +
                ", price=" + price +
                ", barcode='" + barcode + '\'' +
                '}';
    }
}
