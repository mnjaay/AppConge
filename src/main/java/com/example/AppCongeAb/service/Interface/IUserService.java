package com.example.AppCongeAb.service.Interface;


import com.example.AppCongeAb.model.Users;

import java.util.List;

public interface IUserService {
    List<Users> getAllUsers();
    Users getUserById(Long id);
    Users createUser(Users user);
    Users updateUser(Long id, Users user);
    void deleteUser(Long id);
    Users login(String email, String password);
}
