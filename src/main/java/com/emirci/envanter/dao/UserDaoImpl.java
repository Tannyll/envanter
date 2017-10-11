package com.emirci.envanter.dao;

import com.emirci.envanter.model.AppUser;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;

/**
 * Created by serdaremirci on 10/10/17.
 */

@Repository
public class UserDaoImpl extends GenericDaoImpl<AppUser, Integer> implements UserDao {


    @Override
    public boolean removeUser(Integer id) {
        Query query = currentSession().createQuery(
                "select from appuser u where user_id:id", AppUser.class);
        query.setParameter("inventoryId", id);
        return query.executeUpdate() > 0;
    }

    @Override
    public boolean isUser(Integer id) {

        Query query = currentSession().createQuery(
                "select 'active' from appuser u where user_id=:id", AppUser.class);

        query.setParameter("id", id);

        return query.getResultList().size() > 0;
    }

    @Override
    public AppUser getUser(Integer id) {
        Query query = currentSession().createQuery(
                "select from appuser " + " where user_id=:id", AppUser.class);
        query.setParameter("id", id);

        return (AppUser) query.getSingleResult();

    }

    @Override
    public AppUser findByEmail(String email) {
        return null;
    }
}
