package com.example.bloggerswebsitebackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.bloggerswebsitebackend.model.User;

public interface UserRepository extends JpaRepository <User, Long>{
	User findByUsername(String username);
	//User save(user);
}

