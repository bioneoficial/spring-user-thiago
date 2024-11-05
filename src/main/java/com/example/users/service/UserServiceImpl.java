package com.example.users.service;

import com.example.users.dto.CreateUserDto;
import com.example.users.dto.UpdateUserDto;
import com.example.users.exception.UserAlreadyExistsException;
import com.example.users.exception.UserNotFoundException;
import com.example.users.model.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    private final List<User> userList;

    public UserServiceImpl() {
        userList = new ArrayList<>();
        User user1 = new User(1, 21, "Maria", "maria@gmail.com");
        User user2 = new User(2, 22, "João", "joao@gmail.com");
        User user3 = new User(3, 23, "José", "jose@gmail.com");
        User user4 = new User(4, 24, "Luiz", "luiz@gmail.com");
        User user5 = new User(5, 25, "Joice", "joice@gmail.com");
        userList.addAll(List.of(user1, user2, user3, user4, user5));
    }

    @Override
    public Optional<User> getUserById(Integer id) {
        return userList.stream().filter(user -> user.getId().equals(id)).findFirst();
    }

    @Override
    public List<User> getAllUsers() {
        return new ArrayList<>(userList);
    }

    @Override
    public User createUser(CreateUserDto createUserDto) {
        Optional<User> existingUser = getUserById(createUserDto.id());
        if (existingUser.isPresent()) {
            throw new UserAlreadyExistsException(createUserDto.id());
        }
        User user = new User(createUserDto.id(), createUserDto.age(), createUserDto.name(), createUserDto.email());
        userList.add(user);
        return user;
    }

    @Override
    public User updateUser(Integer id, UpdateUserDto updateUserDto) {
        User existingUser = getUserById(id).orElseThrow(() -> new UserNotFoundException(id));
        existingUser.setName(updateUserDto.name());
        existingUser.setEmail(updateUserDto.email());
        existingUser.setAge(updateUserDto.age());
        return existingUser;
    }

    @Override
    public void deleteUser(Integer id) {
        User user = getUserById(id).orElseThrow(() -> new UserNotFoundException(id));
        userList.remove(user);
    }
}
