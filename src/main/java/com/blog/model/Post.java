package com.blog.model;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Entity
public class Post {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	@Size(min = 2 ,message = "title should be greater than 2")
	private String tital;
	
	@Size(min = 10 ,message = "description should be greater than 10")
	private String description;
	
	private LocalDateTime dateTime;
	
	@ManyToOne(cascade = CascadeType.ALL)
	private User user;
	
	@OneToMany
	private List<Comment> comments;

}
