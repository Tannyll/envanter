package com.emirci.envanter.service;

import com.emirci.envanter.model.Trademark;

public interface TrademarkService extends GenericService<Trademark, Long> {

    public boolean removeTrademark(Long id);

    public boolean isTrademark(Long id);

    public Trademark getTrademark(Long id);

}
