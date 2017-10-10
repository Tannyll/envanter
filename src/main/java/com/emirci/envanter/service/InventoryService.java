package com.emirci.envanter.service;

import com.emirci.envanter.model.Inventory;

/**
 * Created by serdaremirci on 10/10/17.
 */
public interface InventoryService extends GenericService<Inventory, Long> {

    public boolean removeInventory(Long id);

    public boolean isInventory(Long id);

    public Inventory getInventory(Long id);

}
