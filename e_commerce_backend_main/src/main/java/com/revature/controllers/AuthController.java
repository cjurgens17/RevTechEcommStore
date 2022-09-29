package com.revature.controllers;

import com.revature.annotations.Authorized;
import com.revature.dtos.LoginRequest;
import com.revature.dtos.RegisterRequest;
import com.revature.models.Product;
import com.revature.models.User;
import com.revature.services.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = {"http://localhost:4200", "http://localhost:3000"}, allowCredentials = "true")
public class AuthController {

    //Autowire maybe?
    private final AuthService authService;


    //Autowire maybe?
    public AuthController(AuthService authService) {
        this.authService = authService;
    }


    //Get All Users
    @GetMapping("/allUsers")
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(authService.findAll());
    }

    //Below removed from line 42 (removing HttpSession session from login parameters for postman testing)
    //Should be able to add in below once we get to testing methods with front end
    //    public ResponseEntity<User> login(@RequestBody LoginRequest loginRequest, HttpSession session) {
    @PostMapping("/login")
    public ResponseEntity<User> login(@RequestBody LoginRequest loginRequest) {
        Optional<User> optional = authService.findByCredentials(loginRequest.getEmail(), loginRequest.getPassword());

        if (!optional.isPresent()) {
            return ResponseEntity.badRequest().build();
        }

        //Maybe decide not to use sessions
        //session.setAttribute("user", optional.get());

        return ResponseEntity.ok(optional.get());
    }

    @PostMapping("/logout")
    public ResponseEntity<Void> logout(HttpSession session) {
        session.removeAttribute("user");

        return ResponseEntity.ok().build();
    }

    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody RegisterRequest registerRequest) {
        User created = new User(0,
                registerRequest.getEmail(),
                registerRequest.getPassword(),
                registerRequest.getFirstName(),
                registerRequest.getLastName(),
                registerRequest.getAddress(),
                registerRequest.isAdmin());
        return ResponseEntity.status(HttpStatus.CREATED).body(authService.register(created));}


    @Authorized
    @PutMapping("/updateUser")
    public ResponseEntity<User> updateUserProfile(@RequestBody User user) {
        int pk = authService.updateUserProfile(user);

        if (pk > 0) {
            return ResponseEntity.ok(user);
        }
        return null;
    }

    //Used to Update Admin Status
    @Authorized
    @PutMapping("/updateAdmin")
    public ResponseEntity<User> updateUserAdmin(@RequestBody User user) {
        int pk = authService.updateAdmin(user);

        if (pk > 0) {
            return ResponseEntity.ok(user);
        }
        return null;
    }

    //Used to reset password
    @Authorized
    @PutMapping("/resetPassword")
    public ResponseEntity<User> resetPassword(@RequestBody User user) {
        int pk = authService.resetPassword(user);

        if (pk > 0) {
            return ResponseEntity.ok(user);
        }
        return null;
    }
}
