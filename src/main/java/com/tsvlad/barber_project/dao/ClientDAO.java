package com.tsvlad.barber_project.dao;

import com.tsvlad.barber_project.entity.Client;

public interface ClientDAO {
    Client getClient(String email);
    void saveClient(Client client);
}
