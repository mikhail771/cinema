package com.dev.cinema.dao.impl;

import com.dev.cinema.dao.OrderDao;
import com.dev.cinema.exception.DataProcessingException;
import com.dev.cinema.model.Order;
import com.dev.cinema.model.User;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

@Repository
public class OrderDaoImpl implements OrderDao {
    private SessionFactory sessionFactory;

    public OrderDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Order add(Order order) {
        Transaction transaction = null;
        Session session = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.save(order);
            transaction.commit();
            return order;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessingException("Can't add new order " + order + "into DB", e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public List<Order> getUsersOrders(User user) {
        try (Session session = sessionFactory.openSession()) {
            Query<Order> query = session.createQuery(
                    "SELECT DISTINCT o FROM Order o "
                            + "LEFT JOIN FETCH o.tickets "
                            + "WHERE o.user = :user",
                    Order.class);
            query.setParameter("user", user);
            return query.getResultList();
        }
    }
}
