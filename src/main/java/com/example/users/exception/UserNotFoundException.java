package com.example.users.exception;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(Integer id) {
        super("Usuário com ID " + id + " não encontrado.");
    }
}
