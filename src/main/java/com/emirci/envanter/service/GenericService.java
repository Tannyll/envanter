package com.emirci.envanter.service;

import java.util.List;

/**
 * Created by serdaremirci on 10/10/17.
 */
public interface GenericService<E, K> {


    public void saveOrUpdate(E entity);

    public void saveOrUpdate(List<E> entityes);

    public List<E> getAll();

    public E get(K key);

    public void add(E entity);

    public void update(E entity);

    public void remove(E entity);


}