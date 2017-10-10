package com.emirci.envanter.dao;

import com.emirci.envanter.model.Inventory;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;

/**
 * Created by serdaremirci on 10/10/17.
 */

@Repository
public class InventoryDaoImpl extends GenericDaoImpl<Inventory, Long> implements InventoryDao {


    @Override
    public boolean removeInventory(Long id) {
        Query query = currentSession().createQuery(
                "select from XInventory u where inventoryId:id", Inventory.class);
        query.setParameter("inventoryId", id);
        return query.executeUpdate() > 0;
    }

    @Override
    public boolean isInventory(Long id) {

        Query query = currentSession().createQuery(
                "select 'A' from XInventory u where inventoryId=:id", Inventory.class);
        query.setParameter("id", id);

        return query.getResultList().size() > 0;
    }

    @Override
    public Inventory getInventory(Long id) {
        Query query = currentSession().createQuery(
                "select from XInventory" + "where inventoryId=:id", Inventory.class);
        query.setParameter("id", id);

        return (Inventory) query.getSingleResult();

    }
}
