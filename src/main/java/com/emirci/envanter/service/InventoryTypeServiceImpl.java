package com.emirci.envanter.service;

import com.emirci.envanter.dao.GenericDao;
import com.emirci.envanter.dao.InventoryTypeDao;
import com.emirci.envanter.model.InventoryType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("inventoryTypeService")
public class InventoryTypeServiceImpl extends GenericServiceImpl<InventoryType, Long> implements InventoryTypeService {


    private InventoryTypeDao inventoryTypeDao;

    @Autowired
    public InventoryTypeServiceImpl(@Qualifier("inventoryTypeDaoImpl") GenericDao<InventoryType, Long> genericDao) {

        super(genericDao);
        this.inventoryTypeDao = (InventoryTypeDao) genericDao;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public boolean removeInventoryType(Long id) {
        return inventoryTypeDao.removeInventoryType(id);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public boolean isInventoryType(Long id) {
        return inventoryTypeDao.isInventoryType(id);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public InventoryType getInventoryType(Long id) {
        return inventoryTypeDao.getInventoryType(id);
    }

    @Override
    public void saveOrUpdateList(List<InventoryType> entity) {

    }
}
