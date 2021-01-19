package com.tsvlad.barber_project.dao;

import com.tsvlad.barber_project.entity.Client;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ClientDAOImpl implements ClientDAO {
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public Client getClient(String email) {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("FROM Client WHERE email =:email", Client.class).setParameter("email", email).getSingleResult();
    }

    @Override
    public void saveClient(Client client) {
        Session session = sessionFactory.getCurrentSession();
        session.save(client);
    }
}
