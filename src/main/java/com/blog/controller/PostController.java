package com.blog.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blog.exception.PostException;
import com.blog.exception.UserException;
import com.blog.model.Post;
import com.blog.service.PostService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/post")
public class PostController {
	
	@Autowired
	private PostService ps;
	
	@PostMapping("/createpost/{uuidKey}")
	public ResponseEntity<Post> createPost(@Valid @RequestBody Post post, @PathVariable("uuidKey") String key)throws PostException,UserException{
		Post p=ps.createPost(post,key);
		return new ResponseEntity<Post>(p,HttpStatus.CREATED);
	}
	
	@GetMapping("/getallposts/{uuidKey}")
	public ResponseEntity<List<Post>> getAllPost(@PathVariable("uuidKey") String key)throws PostException, UserException{
		List<Post> p=ps.getAllPost(key);
		return new ResponseEntity<List<Post>>(p,HttpStatus.OK);
	}
	
	@GetMapping("/getpost/{id}/{uuidKey}")
	public ResponseEntity<Post> getPost(@PathVariable("id") Integer id,@PathVariable("uuidKey") String key)throws PostException, UserException{
		Post p=ps.getPost(id,key);
		return new ResponseEntity<Post>(p,HttpStatus.OK);
	}
	
	@PutMapping("/update/{id}/{uuidKey}")
	public ResponseEntity<Post> upadtePost(@PathVariable("id") Integer id, @RequestBody Post post,@PathVariable("uuidKey") String key)throws PostException, UserException{
		Post p=ps.updatePost(id, post,key);
		return new ResponseEntity<Post>(p,HttpStatus.OK);
	}
	
	@DeleteMapping("/delete/{id}/{uuidKey}")
	public ResponseEntity<Post> deletePost(@PathVariable("id") Integer id,@PathVariable("uuidKey") String key)throws PostException, UserException{
		Post p=ps.deletePost(id,key);
		return new ResponseEntity<Post>(p,HttpStatus.OK);
	}
	

}
