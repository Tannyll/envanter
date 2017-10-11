package com.emirci.envanter.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

/**
 * Created by serdaremirci on 10/10/17.
 */
@SuppressWarnings("unchecked")
@Repository
@Transactional
public abstract class GenericDaoImpl<E, K extends Serializable> implements GenericDao<E, K> {

    //private static final Logger LOGGER = LoggerFactory.getLogger(GenericDaoImpl.class);

    private SessionFactory _sessionFactory;


    protected Session currentSession() {
        _sessionFactory.openSession();
        return _sessionFactory.getCurrentSession();

    }

    protected Class<? extends E> daoType;


    public GenericDaoImpl() {
        Type t = getClass().getGenericSuperclass();

        ParameterizedType parametrizedType = null;

        while (parametrizedType == null) {
            if ((t instanceof ParameterizedType)) {
                parametrizedType = (ParameterizedType) t;
            } else {
                t = ((Class<?>) t).getGenericSuperclass();
            }
        }


        daoType = (Class) parametrizedType.getActualTypeArguments()[0];
    }


    @Override
    public void add(E entity) {
        currentSession().save(entity);
    }

    @Override
    public void saveOrUpdate(E entity) {
        currentSession().saveOrUpdate(entity);
    }

    @Override
    public void update(E entity) {
        currentSession().saveOrUpdate(entity);
    }

    @Override
    public void remove(E entity) {
        currentSession().delete(entity);
    }

    @Override
    public E find(K key) {
        return (E) currentSession().get(daoType, key);
    }

    @Override
    public List<E> getAll() {
        return currentSession().createQuery("from " + daoType).list();
    }
}
