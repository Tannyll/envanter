package com.emirci.envanter.service;

import com.emirci.envanter.dao.GenericDao;
import com.emirci.envanter.dao.InventoryDao;
import com.emirci.envanter.model.Inventory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by serdaremirci on 10/10/17.
 */
@Service
public class InventoryServiceImpl extends GenericServiceImpl<Inventory, Long> implements InventoryService {

    private InventoryDao inventoryDao;

    @Autowired
    public InventoryServiceImpl(@Qualifier("inventoryDaoImpl") GenericDao<Inventory, Long> genericDao) {

        super(genericDao);
        this.inventoryDao = (InventoryDao) genericDao;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public boolean removeInventory(Long id) {
        return inventoryDao.removeInventory(id);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public boolean isInventory(Long id) {
        return inventoryDao.isInventory(id);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public Inventory getInventory(Long id) {
        return inventoryDao.getInventory(id);
    }
}
