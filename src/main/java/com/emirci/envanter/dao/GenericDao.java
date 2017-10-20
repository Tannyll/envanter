package com.emirci.envanter.dao;

import java.util.List;

/**
 * Created by serdaremirci on 10/10/17.
 */

public interface GenericDao<E, K> {


    public void add(E entity);

    public void saveOrUpdate(E entity);

    public void saveOrUpdate(List<E> entity);

    public void update(E entity);

    public void remove(E entity);

    public E find(K key);

    public List<E> getAll();

    public List<E> get(String queryString);

}
