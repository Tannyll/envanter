package com.emirci.envanter.dao;


import com.emirci.envanter.model.Inventory;

/**
 * Created by serdaremirci on 9/19/17.
 */

public interface InventoryDao extends GenericDao<Inventory, Long> {

    public boolean removeInventory(Long id);

    public boolean isInventory(Long id);

    public Inventory getInventory(Long id);


}
