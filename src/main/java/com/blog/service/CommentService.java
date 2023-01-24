package com.blog.service;

import java.util.List;

import com.blog.exception.CommentException;
import com.blog.exception.PostException;
import com.blog.exception.UserException;
import com.blog.model.Comment;

public interface CommentService {
	
	public List<Comment> getAllComment(Integer postId)throws PostException,CommentException;
	
	public Comment getComment(Integer postId, Integer commentId)throws PostException,CommentException;
	
	public Comment createComment(Comment comment, Integer postId, String key)throws PostException, UserException;
	
	public Comment updateComment(Comment comment, Integer postId)throws PostException,CommentException;
	
	public Comment deleteComment(Integer commentId, Integer postId)throws PostException,CommentException;

}
