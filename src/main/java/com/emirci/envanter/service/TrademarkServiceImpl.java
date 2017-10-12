package com.emirci.envanter.service;

import com.emirci.envanter.dao.GenericDao;
import com.emirci.envanter.dao.TrademarkDao;
import com.emirci.envanter.model.Trademark;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("trademarkService")
public class TrademarkServiceImpl extends GenericServiceImpl<Trademark, Long> implements TrademarkService {


    private TrademarkDao trademarkDao;

    @Autowired
    public TrademarkServiceImpl(@Qualifier("trademarkDaoImpl") GenericDao<Trademark, Long> genericDao) {

        super(genericDao);
        this.trademarkDao = (TrademarkDao) genericDao;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public boolean removeTrademark(Long id) {
        return trademarkDao.removeTrademark(id);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public boolean isTrademark(Long id) {
        return trademarkDao.isTrademark(id);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public Trademark getTrademark(Long id) {
        return trademarkDao.getTrademark(id);
    }

    @Override
    public void saveOrUpdateList(List<Trademark> entity) {

    }
}
