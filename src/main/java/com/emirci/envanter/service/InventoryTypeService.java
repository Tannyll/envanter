package com.emirci.envanter.service;

import com.emirci.envanter.model.InventoryType;

public interface InventoryTypeService extends GenericService<InventoryType, Long> {

    public boolean removeInventoryType(Long id);

    public boolean isInventoryType(Long id);

    public InventoryType getInventoryType(Long id);

}
