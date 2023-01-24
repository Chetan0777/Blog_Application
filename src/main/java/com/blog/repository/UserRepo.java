package com.blog.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.blog.model.User;

@Repository
public interface UserRepo extends JpaRepository<User, Integer>{
	
	public List<User> findByEmail(String mobile);

}
