package com.firstproject.myproject.services;

import com.firstproject.myproject.model.User;
import com.firstproject.myproject.repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@Service
public class UserServicesImp implements UserServices {

    private UserRepo userRepo;

    @Autowired
    public UserServicesImp (UserRepo userRepo){
        this.userRepo = userRepo;
    }

    @Transactional
    @Override
    public void add(User user) {
        userRepo.save(user);
    }

    @Transactional
    @Override
    public List<User> getListUser() {
        return userRepo.findAll();
    }

    @Transactional
    @Override
    public User getById(int id) {
        Optional<User> foundUser = userRepo.findById(id);
        return foundUser.orElse(null);
    }

    @Transactional
    @Override
    public void update(int id, User user) {
        user.setId(id);
        userRepo.save(user);
    }

    @Transactional
    @Override
    public void delete(int id) {
        userRepo.deleteById(id);
    }

}