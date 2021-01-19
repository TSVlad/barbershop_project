package com.tsvlad.barber_project.dao;

import com.tsvlad.barber_project.entity.Barber;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BarberDAOImpl implements BarberDAO{

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<Barber> getBarbers() {
        Session session = sessionFactory.getCurrentSession();
        Query<Barber> query = session.createQuery("FROM Barber WHERE userInfo.isActive = true");
        return query.getResultList();
    }

    @Override
    public List<Barber> getArchivedBarbers() {
        Session session = sessionFactory.getCurrentSession();
        Query<Barber> query = session.createQuery("FROM Barber WHERE userInfo.isActive = false");
        return query.getResultList();
    }

    @Override
    public Barber getBarber(int id) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(Barber.class, id);
    }

    @Override
    public Barber getBarberByUsername(String username) {
        Session session = sessionFactory.getCurrentSession();
        Query<Barber> query = session.createQuery("FROM Barber WHERE userInfo.username = :username AND userInfo.isActive = true");
        query.setParameter("username", username);
        return query.getSingleResult();
    }

    @Override
    public Barber saveBarber(Barber barber) {
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(barber);
        return barber;
    }
}
