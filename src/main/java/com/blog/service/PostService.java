package com.blog.service;

import java.util.List;

import com.blog.exception.PostException;
import com.blog.exception.UserException;
import com.blog.model.Post;

public interface PostService {
	
	public Post createPost(Post post, String key)throws PostException,UserException;
	
	public List<Post> getAllPost(String key)throws PostException,UserException;
	
	public Post getPost(Integer id, String key)throws PostException,UserException;
	
	public Post deletePost(Integer id, String key)throws PostException,UserException;
	
	public Post updatePost(Integer id, Post post, String key)throws PostException,UserException;

}
