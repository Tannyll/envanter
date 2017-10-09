package com.emirci.envanter.bean;

import com.emirci.envanter.Repository.InventoryRepository;
import com.emirci.envanter.model.Inventory;

import javax.faces.bean.ManagedBean;
import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;
import java.util.List;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

/**
 * Created by serdaremirci on 9/25/17.
 */
@ManagedBean(name = "InventoryBean")
@ViewScoped
public class InventoryBean implements Serializable {

    private InventoryRepository repo;

    public void setInventoryList(List<Inventory> inventoryList) {
        this.inventoryList = inventoryList;
    }

    private List<Inventory> inventoryList;

    public InventoryBean(InventoryRepository repo) {
        this.repo = repo;
    }

    public List<Inventory> getInventoryList() {
        return inventoryList;
    }

    @PostConstruct
    public void Init(){

        inventoryList = repo.findAll();

    }

    public String getHello() {
        return "Hello from PrimeFaces and Spring Boot!";
    }
}
