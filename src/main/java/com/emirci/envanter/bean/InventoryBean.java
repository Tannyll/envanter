package com.emirci.envanter.bean;

import com.emirci.envanter.model.Inventory;
import com.emirci.envanter.service.InventoryService;

import javax.faces.bean.ManagedBean;
import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;
import java.util.List;
import java.io.Serializable;

/**
 * Created by serdaremirci on 9/25/17.
 */
@ManagedBean(name = "InventoryBean")
@ViewScoped
public class InventoryBean implements Serializable {

    private InventoryService service;

    public void setInventoryList(List<Inventory> inventoryList) {
        this.inventoryList = inventoryList;
    }

    private List<Inventory> inventoryList;

    public InventoryBean() {

    }

    public List<Inventory> getInventoryList() {
        return inventoryList;
    }

    @PostConstruct
    public void Init(){

        inventoryList = service.getAll();

    }

    public String getHello() {
        return "Hello from PrimeFaces and Spring Boot!";
    }
}
