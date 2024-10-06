package com.example.users.api;

import com.example.users.dto.CreateUserDto;
import com.example.users.dto.UpdateUserDto;
import com.example.users.model.User;
import com.example.users.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    // Get All Users
    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    // Get User by ID
    @GetMapping("/{id}")
    public ResponseEntity<User> getUser(@PathVariable Integer id) {
        Optional<User> userOpt = userService.getUserById(id);
        return userOpt.map(user -> new ResponseEntity<>(user, HttpStatus.OK))
                .orElseThrow(() -> new com.example.users.exception.UserNotFoundException(id));
    }

    // Create User
    @PostMapping
    public ResponseEntity<User> createUser(@Valid @RequestBody CreateUserDto createUserDto) {
        User createdUser = userService.createUser(createUserDto);
        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    }

    // Update User
    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Integer id, @Valid @RequestBody UpdateUserDto updateUserDto) {
        User updatedUser = userService.updateUser(id, updateUserDto);
        return new ResponseEntity<>(updatedUser, HttpStatus.OK);
    }

    // Delete User
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Integer id) {
        userService.deleteUser(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
