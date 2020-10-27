package com.dev.cinema.dao.impl;

import com.dev.cinema.dao.MovieSessionDao;
import com.dev.cinema.exception.DataProcessingException;
import com.dev.cinema.model.MovieSession;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

@Repository
public class MovieSessionDaoImpl implements MovieSessionDao {
    private SessionFactory sessionFactory;

    public MovieSessionDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public MovieSession add(MovieSession movieSession) {
        Transaction transaction = null;
        Session session = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.save(movieSession);
            transaction.commit();
            return movieSession;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessingException("Can't insert Movie Session entity "
                    + movieSession, e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public List<MovieSession> findAvailableSessions(Long movieId, LocalDate date) {
        try (Session session = sessionFactory.openSession()) {
            Query<MovieSession> query = session.createQuery("from MovieSession ms "
                    + "join fetch ms.movie m "
                    + "where m.id = :movieId "
                    + "and ms.sessionTime between :from AND :to", MovieSession.class);
            query.setParameter("movieId", movieId);
            query.setParameter("from", date.atTime(LocalTime.MIN));
            query.setParameter("to", date.atTime(LocalTime.MAX));
            return query.getResultList();
        }
    }

    @Override
    public Optional<MovieSession> findById(Long id) {
        try (Session session = sessionFactory.openSession()) {
            return Optional.of(session.get(MovieSession.class, id));
        } catch (Exception e) {
            throw new DataProcessingException("Can't find a movie session by ID "
                    + id, e);
        }
    }
}
