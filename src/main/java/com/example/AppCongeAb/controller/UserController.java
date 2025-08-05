package com.example.AppCongeAb.controller;

import com.example.AppCongeAb.model.Users;
import com.example.AppCongeAb.service.Implement.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UsersService userService;

    // Récupérer tous les utilisateurs
    @GetMapping
    public List<Users> getAllUsers() {
        return userService.getAllUsers();
    }

    // Récupérer un utilisateur par ID
    @GetMapping("/{id}")
    public Users getUserById(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    // Créer un nouvel utilisateur
    @PostMapping
    public Users createUser(@RequestBody Users user) {
        return userService.createUser(user);
    }

    // Mettre à jour un utilisateur
    @PutMapping("/{id}")
    public Users updateUser(@PathVariable Long id, @RequestBody Users user) {
        return userService.updateUser(id, user);
    }

    // Supprimer un utilisateur
    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
    }

    // Login simple (email & mot de passe)
    @PostMapping("/login")
    public Users login(@RequestParam String email, @RequestParam String password) {
        return userService.login(email, password);
    }
}
