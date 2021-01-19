package com.tsvlad.barber_project.dao;

import com.tsvlad.barber_project.entity.Service;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ServiceDAOImpl implements ServiceDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<Service> getServices() {
        Session session = sessionFactory.getCurrentSession();
        Query<Service> query = session.createQuery("FROM Service WHERE isActive = true");
        return query.getResultList();
    }

    @Override
    public Service getService(int id) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(Service.class, id);
    }

    @Override
    public void deleteService(int id) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("UPDATE Service set isActive = false WHERE id=:id");
        query.setParameter("id", id);
        query.executeUpdate();
    }

    @Override
    public void saveService(Service service) {
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(service);
    }
}
