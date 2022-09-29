package com.revature.services;

import com.revature.models.Product;
import com.revature.models.User;
import com.revature.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@Service
public class AuthService {

    private final UserService userService;
    private static UserRepository userRepository;


    public AuthService(UserService userService, UserRepository userRepository) {
        this.userService = userService;
        this.userRepository = userRepository;
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public Optional<User> findByCredentials(String email, String password) {
        return userService.findByCredentials(email, password);
    }

    public User register(User user) {
        return userService.save(user);
    }
    public int updateUserProfile(User user){
        return userRepository.profileUpdate(user.getAddress(), user.getEmail(),user.getFirstName(),user.getLastName(),user.getPassword(),user.getId());
    }

    public int updateAdmin(User user){
        return userRepository.updateToAdmin(user.isAdmin(),user.getId());
    }

    public int resetPassword(User user){ return userRepository.resetPassword(user.getPassword(), user.getEmail());}
}
