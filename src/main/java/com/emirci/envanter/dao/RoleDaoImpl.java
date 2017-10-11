package com.emirci.envanter.dao;

import com.emirci.envanter.model.Role;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;

/**
 * Created by serdaremirci on 10/10/17.
 */

@Repository
public class RoleDaoImpl extends GenericDaoImpl<Role, Integer> implements RoleDao {


    @Override
    public boolean removeRole(Integer id) {
        Query query = currentSession().createQuery(
                "select from role u where role_id:id", Role.class);
        query.setParameter("inventoryId", id);
        return query.executeUpdate() > 0;
    }

    @Override
    public boolean isRole(Integer id) {

        Query query = currentSession().createQuery(
                "select 'active' from role u where role_id=:id", Role.class);

        query.setParameter("id", id);

        return query.getResultList().size() > 0;
    }

    @Override
    public Role getRole(Integer id) {
        Query query = currentSession().createQuery(
                "select from role " + " where role_id=:id", Role.class);
        query.setParameter("id", id);

        return (Role) query.getSingleResult();

    }

    @Override
    public Role findByRole(String roleName) {
        Query query = currentSession().createQuery(
                "select from role " + " where role=:roleName", Role.class);
        query.setParameter("roleName", roleName);

        return (Role) query.getSingleResult();

    }
}
