package com.firstproject.myproject.services;


import com.firstproject.myproject.model.User;

import java.util.List;

public interface UserServices {
    void add(User user);

    List<User> getListUser();

    User getById(int id);

    void update(int id, User user);

    void delete(int id);
}
