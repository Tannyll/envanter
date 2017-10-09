package com.emirci.envanter.Repository;

import com.emirci.envanter.model.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by serdaremirci on 9/18/17.
 */

public interface InventoryRepository extends JpaRepository<Inventory, Long> {

    Inventory findByBarcode(String barcode);
    Inventory findByInventoryId(Integer inventoryId);

    @Override
    List<Inventory> findAll();
}
