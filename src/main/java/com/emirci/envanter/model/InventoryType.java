package com.emirci.envanter.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;


/**
 * Created by serdaremirci on 9/20/17.
 */
@Entity
//@Table(name = "XAttInventoryType", uniqueConstraints = @UniqueConstraint(columnNames = "InventoryTypeId", name = "PK_Inventory"))
@Table(name = "XAttInventoryType")
public class InventoryType implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "InventoryTypeId", unique = true)
    private Long inventoryTypeId;

    @Column(name = "InventoryType")
    private String inventoryType;

    @OneToMany(mappedBy = "inventoryTypes", cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private Set<Inventory> inventoryes;

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

    public InventoryType() {

    }

    public InventoryType(String inventoryType) {
        this.inventoryType = inventoryType;
    }

    @Override
    public String toString() {
        return "InventoryType{" +
                "inventoryType='" + inventoryType + '\'' +
                '}';
    }
}
