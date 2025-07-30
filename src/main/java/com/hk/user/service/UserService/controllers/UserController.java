package com.hk.user.service.UserService.controllers;

import com.hk.user.service.UserService.models.User;
import com.hk.user.service.UserService.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class UserController {

    @Autowired
    private UserRepository userRepository;

//    @GetMapping("greet")
    @RequestMapping(path = {"greet", "hello"}, method = RequestMethod.GET)
    public String greetings() {
        return "Hello, World !!";
    }

    @PostMapping("user")
    public ResponseEntity<User> addUser(@RequestBody User newUser) {
        try {
            User user = userRepository.save(newUser);
            return new ResponseEntity<>(user, HttpStatus.CREATED);
        } catch (Exception exception) {
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        }
    }

    @GetMapping("user/{id}")
    public ResponseEntity<User> getUser(@PathVariable("id") String userId) {
        try {
            Optional<User> user = userRepository.findById(Long.valueOf(userId));
            return user.map(value -> new ResponseEntity<>(value, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
        } catch (Exception exception) {
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        }
    }

    @PutMapping("user")
    public ResponseEntity<User> updateUser(@RequestBody User user) {
        try {
            User updatedUser = userRepository.save(user);
            return new ResponseEntity<>(updatedUser, HttpStatus.OK);
        } catch (Exception exception) {
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        }
    }

    @DeleteMapping("user/{id}")
    public ResponseEntity<User> deleteUser(@PathVariable("id") String userId) {
        try {
            userRepository.deleteById(Long.valueOf(userId));
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception exception) {
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        }
    }
}
