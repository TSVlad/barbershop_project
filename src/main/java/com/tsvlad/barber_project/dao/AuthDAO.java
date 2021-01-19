package com.tsvlad.barber_project.dao;

import com.tsvlad.barber_project.entity.Role;
import com.tsvlad.barber_project.entity.User;

public interface AuthDAO {
    User getUserByUsername(String username);
    void saveUser(User user);
    void deleteUser(User user);

    void saveRole(Role role);
    Role getRoleByName(String name);
}
