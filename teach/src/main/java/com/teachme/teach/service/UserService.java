package com.teachme.teach.service;

import com.teachme.teach.model.Users;

public interface UserService {
    public Users findUserByEmail(String email);
    public void saveUser(Users user);
}
