package com.bankpr.service;

import java.util.Optional;

import com.bankpr.model.User;

public interface UserService {

	// Method to authenticate a user by email and password
    // Returns an Optional containing the user if authenticated successfully, otherwise returns an empty Optional
	public Optional<User> login(String email, String password);
}
