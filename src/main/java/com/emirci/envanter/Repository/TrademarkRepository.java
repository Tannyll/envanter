package com.emirci.envanter.Repository;

import com.emirci.envanter.model.Trademark;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by serdaremirci on 9/20/17.
 */
public interface TrademarkRepository extends JpaRepository<Trademark, Long> {

    @Override
    List<Trademark> findAll();
}
