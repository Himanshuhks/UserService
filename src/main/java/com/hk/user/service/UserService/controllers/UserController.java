package com.hk.user.service.UserService.controllers;

import com.hk.user.service.UserService.exceptions.UserNotFoundException;
import com.hk.user.service.UserService.models.User;
import com.hk.user.service.UserService.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Value("${spring.application.name}")
    private String appName;

    @Autowired
    private Environment env;

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
    public ResponseEntity<User> getUser(@PathVariable("id") String userId) throws UserNotFoundException {
        try {
            Optional<User> user = userRepository.findById(Long.valueOf(userId));
            return new ResponseEntity<>(user.get(), HttpStatus.OK);
        } catch (Exception exception) {
            throw new UserNotFoundException("User Not found with Id: " + userId);
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

    @GetMapping("applicationName")
    public ResponseEntity<String> getAppName() {
//        return new ResponseEntity<>(appName, HttpStatus.OK);
        return new ResponseEntity<>(env.getProperty("spring.application.name"), HttpStatus.OK);
        /** Both of the above works same **/
    }
}
