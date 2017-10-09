package com.emirci.envanter.model;

import javax.persistence.*;


/**
 * Created by serdaremirci on 9/20/17.
 */
@Entity
//@Table(name = "XAttInventoryType", uniqueConstraints = @UniqueConstraint(columnNames = "InventoryTypeId", name = "PK_Inventory"))
@Table(name = "XAttInventoryType")
public class InventoryType {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "InventoryTypeId", unique = true)
    private Long inventoryTypeId;

    @Column(name = "InventoryType")
    private String inventoryType;

    @OneToOne(mappedBy = "inventoryTypes")
    @PrimaryKeyJoinColumn
    private Inventory inventory;

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

    public Inventory getInventory() {
        return inventory;
    }

    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
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
