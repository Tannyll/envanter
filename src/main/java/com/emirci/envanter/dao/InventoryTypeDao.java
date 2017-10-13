package com.emirci.envanter.dao;

import com.emirci.envanter.model.InventoryType;

public interface InventoryTypeDao extends GenericDao<InventoryType, Long> {

    public boolean removeInventoryType(Long id);

    public boolean isInventoryType(Long id);

    public InventoryType getInventoryType(Long id);


}
