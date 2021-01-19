package com.tsvlad.barber_project.dao;

import com.tsvlad.barber_project.entity.Service;

import java.util.List;

public interface ServiceDAO {
    List<Service> getServices();
    Service getService(int id);
    void deleteService(int id);
    void saveService(Service service);
}
