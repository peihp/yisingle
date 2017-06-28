package com.yisingle.webapp.dao;

import com.yisingle.webapp.entity.OrderEntity;
import com.yisingle.webapp.entity.UserEntity;
import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by jikun on 17/6/25.
 */
@Repository
public class OrderDaoImpl implements OrderDao {


    @Autowired
    private SessionFactory sessionFactory;


    public int save(OrderEntity entity) {
        return (Integer) sessionFactory.getCurrentSession().save(entity);

    }

    public void update(OrderEntity entity) {
        sessionFactory.getCurrentSession().update(entity);
    }

    public void delete(OrderEntity entity) {
        sessionFactory.getCurrentSession().delete(entity);

    }

    public List<OrderEntity> findAll() {
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(OrderEntity.class);
        return criteria.list();
    }

    public OrderEntity load(int id) {
        OrderEntity orderEntity = (OrderEntity) sessionFactory.getCurrentSession().load(OrderEntity.class, id);
        return orderEntity;
    }

    public OrderEntity find(int id) {

        OrderEntity orderEntity = (OrderEntity) sessionFactory.getCurrentSession().get(OrderEntity.class, id);

        return orderEntity;
    }

    public List<OrderEntity> findWaitState() {
        Session session = sessionFactory.getCurrentSession();
        SQLQuery sqlQuery = session.createSQLQuery("SELECT * from t_order WHERE orderState IN (:valueList)").addEntity(OrderEntity.class);

        sqlQuery.setParameterList("valueList", new Integer[]{OrderEntity.OrderState.State.WATI_NEW.value(), OrderEntity.OrderState.State.WATI_OLD.value()});
        List<OrderEntity> list = sqlQuery.list();
        return list;
    }

    public List<OrderEntity> findWaitStateAndUserId(Integer[] state, int userid) {
        Session session = sessionFactory.getCurrentSession();
        SQLQuery sqlQuery = session.createSQLQuery("SELECT * from t_order WHERE orderState IN (:valueList) AND userid= (:valueId)").addEntity(OrderEntity.class);

        sqlQuery.setParameterList("valueList", state);
        sqlQuery.setParameter("valueId", userid);
        List<OrderEntity> list = sqlQuery.list();
        return list;

    }
}
