package com.generation.cities.model.database.dao;

import java.util.List;

import com.generation.cities.model.entities.User;

public interface UserDAO
{
    User getUser(String email);
    List<User> getUsers();
    boolean saveUser(User user);
    boolean deleteUser(String ID);
}
