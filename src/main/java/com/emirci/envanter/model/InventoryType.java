package com.emirci.envanter.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
//@Table(name = "XAttInventoryType", uniqueConstraints = @UniqueConstraint(columnNames = "InventoryTypeId", name = "PK_Inventory"))
@Table(name = "XAttInventoryType")
public class InventoryType implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "InventoryTypeId", unique = true, nullable = false)
    private Long inventoryTypeId;

    @Column(name = "InventoryType", unique = true, nullable = false)
    private String inventoryType;

    @OneToMany(mappedBy = "invtyp")
    @PrimaryKeyJoinColumn
    private Set<Inventory> inventoryes;

    public InventoryType() {

    }

    public InventoryType(String inventoryType) {
        this.inventoryType = inventoryType;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Long getInventoryTypeId() {
        return inventoryTypeId;
    }

    public void setInventoryTypeId(Long inventoryTypeId) {
        this.inventoryTypeId = inventoryTypeId;
    }

    public String getInventoryType() {
        return inventoryType;
    }

    public void setInventoryType(String inventoryType) {
        this.inventoryType = inventoryType;
    }

    public Set<Inventory> getInventoryes() {
        return inventoryes;
    }

    public void setInventoryes(Set<Inventory> inventoryes) {
        this.inventoryes = inventoryes;
    }

    @Override
    public String toString() {
        return "InventoryType{" +
                "inventoryType='" + inventoryType + '\'' +
                '}';
    }
}
