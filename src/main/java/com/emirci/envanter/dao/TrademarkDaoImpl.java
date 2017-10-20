package com.emirci.envanter.dao;

import com.emirci.envanter.model.Trademark;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;

/**
 * Created by serdaremirci on 10/10/17.
 */

@Repository
public class TrademarkDaoImpl extends GenericDaoImpl<Trademark, Long> implements TrademarkDao {


    @Override
    public boolean removeTrademark(Long id) {
        Query query = currentSession().createQuery(
                "select from Trademark u where trademarkId:id", Trademark.class);
        query.setParameter("trademarkId", id);
        return query.executeUpdate() > 0;
    }

    @Override
    public boolean isTrademark(Long id) {

        Query query = currentSession().createQuery(
                "select 'A' from Trademark u where trademarkId=:id", Trademark.class);
        query.setParameter("trademarkId", id);

        return query.getResultList().size() > 0;
    }

    @Override
    public Trademark getTrademark(Long id) {
        Query query = currentSession().createQuery(
                "select from Trademark" + "where trademarkId=:id", Trademark.class);
        query.setParameter("trademarkId", id);

        return (Trademark) query.getSingleResult();

    }

}
