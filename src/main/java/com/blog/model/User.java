package com.blog.model;

import java.util.List;


import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Entity
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	@NotNull
	private String name;
	
	@NotNull
	@Email(message =  "Email is not in 'example@email.com' format")
	private String email;
	
	private String password;
	
	@OneToMany(mappedBy = "user" ,cascade = CascadeType.ALL)
	private List<Post> posts;
	
}
