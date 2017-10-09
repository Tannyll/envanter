package com.emirci.envanter.Repository;

import com.emirci.envanter.model.InventoryType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by serdaremirci on 9/20/17.
 */
public interface InventoryTypeRepository extends JpaRepository<InventoryType, Long> {

    @Override
    List<InventoryType> findAll();
}
