package br.com.fiap.controller;

import br.com.fiap.dto.UserReqDto;
import br.com.fiap.entity.User;
import br.com.fiap.service.UserService;
import jakarta.annotation.security.RolesAllowed;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> createUser(@RequestBody UserReqDto user) {
        try {
            userService.createUser(user);
        } catch (IllegalArgumentException ie) {
            return ResponseEntity.badRequest().body(ie.getMessage());
        }
        return ResponseEntity.ok().build();
    }

    @GetMapping("/all")
    @RolesAllowed("ADMIN")
    public List<User> findAll() {
        return userService.findAll();
    }
}
