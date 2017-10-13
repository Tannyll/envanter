package com.emirci.envanter.dao;

import com.emirci.envanter.model.InventoryType;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;

@Repository
public class InventoryTypeDaoImpl extends GenericDaoImpl<InventoryType, Long> implements InventoryTypeDao {


    @Override
    public boolean removeInventoryType(Long id) {
        Query query = currentSession().createQuery(
                "select from XAttInventoryType u where inventoryTypeId:id", InventoryType.class);
        query.setParameter("inventoryTypeId", id);
        return query.executeUpdate() > 0;
    }

    @Override
    public boolean isInventoryType(Long id) {

        Query query = currentSession().createQuery(
                "select 'A' from XAttInventoryType u where inventoryTypeId=:id", InventoryType.class);
        query.setParameter("inventoryTypeId", id);

        return query.getResultList().size() > 0;
    }

    @Override
    public InventoryType getInventoryType(Long id) {
        Query query = currentSession().createQuery(
                "select from XAttInventoryType" + "where inventoryTypeId=:id", InventoryType.class);
        query.setParameter("inventoryTypeId", id);

        return (InventoryType) query.getSingleResult();

    }
}
