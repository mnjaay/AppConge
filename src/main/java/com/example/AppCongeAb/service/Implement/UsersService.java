package com.example.AppCongeAb.service.Implement;

import com.example.AppCongeAb.model.Users;
import com.example.AppCongeAb.repository.UserRepository;
import com.example.AppCongeAb.service.Interface.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsersService implements IUserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<Users> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public Users getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Utilisateur non trouvé avec l'ID : " + id));
    }

    @Override
    public Users createUser(Users user) {
        return userRepository.save(user);
    }

    @Override
    public Users updateUser(Long id, Users userDetails) {
        Users existingUser = getUserById(id);
        existingUser.setNom(userDetails.getNom());
        existingUser.setPrenom(userDetails.getPrenom());
        existingUser.setEmail(userDetails.getEmail());
        existingUser.setPassword(userDetails.getPassword());
        existingUser.setRole(userDetails.getRole());
        return userRepository.save(existingUser);
    }

    @Override
    public void deleteUser(Long id) {
        Users existingUser = getUserById(id);
        userRepository.delete(existingUser);
    }

    @Override
    public Users login(String email, String password) {
        return userRepository.findByEmailAndPassword(email, password)
                .orElseThrow(() -> new RuntimeException("Email ou mot de passe incorrect"));
    }
}
