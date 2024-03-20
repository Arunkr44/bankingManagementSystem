package com.bankpr.dao;

import java.util.Optional;

import com.bankpr.model.User;



public interface UserDao {
	// Method to find a user by username (email)
	Optional<User> findByUsername(String email);
}
