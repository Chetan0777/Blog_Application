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

import com.blog.exception.CommentException;
import com.blog.exception.PostException;
import com.blog.exception.UserException;
import com.blog.model.Comment;
import com.blog.service.CommentService;

@RestController
@RequestMapping("/comment")
public class CommentController {
	
	@Autowired
	private CommentService cs;
	
	@GetMapping("/getAllComment/{id}")
	public ResponseEntity<List<Comment>> getAllComment(@PathVariable("id") Integer postId)throws PostException,CommentException{
		List<Comment> c=cs.getAllComment(postId);
		return new ResponseEntity<List<Comment>>(c,HttpStatus.OK);
	}
	
	@GetMapping("/getComment/{postId}/{commentId}")
	public ResponseEntity<Comment> getComment(@PathVariable("postId") Integer postId, @PathVariable("commentId") Integer commentId)throws PostException,CommentException {
		Comment c=cs.getComment(postId, commentId);
		return new ResponseEntity<Comment>(c,HttpStatus.OK);
	}
	
	@PostMapping("/createComment/{postId}/{uuidKey}")
	public ResponseEntity<Comment> createComment(@RequestBody Comment comment, @PathVariable("postId") Integer postId,@PathVariable("uuidKey") String key)throws PostException, UserException {
		Comment c=cs.createComment(comment, postId,key);
		return new ResponseEntity<Comment>(c,HttpStatus.CREATED);
	}
	
	@PutMapping("updateComment/{postId}")
	public ResponseEntity<Comment> updateComment(@RequestBody Comment comment, @PathVariable("postId") Integer postId)throws PostException,CommentException {
		Comment c=cs.updateComment(comment, postId);
		return new ResponseEntity<Comment>(c,HttpStatus.OK);
	}
	
	@DeleteMapping("/deleteComment/{commentId}/{postId}")
	public ResponseEntity<Comment> deleteComment(@PathVariable("commentId") Integer commentId, @PathVariable("postId") Integer postId)throws PostException,CommentException {
		Comment c=cs.deleteComment(commentId, postId);
		return new ResponseEntity<Comment>(c,HttpStatus.OK);
	}

}
