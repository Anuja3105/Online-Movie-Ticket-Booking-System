package com.showbuzz.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.showbuzz.entity.User;

public interface IUserDao extends JpaRepository<User, Integer> {

	User findByEmail(String email);
	User findById(int id);
	
}
