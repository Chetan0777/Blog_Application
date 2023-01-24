package com.blog.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blog.exception.CommentException;
import com.blog.exception.PostException;
import com.blog.exception.UserException;
import com.blog.model.Comment;
import com.blog.model.CurrentUserSession;
import com.blog.model.Post;
import com.blog.model.User;
import com.blog.repository.CommentRepo;
import com.blog.repository.PostRepo;
import com.blog.repository.UserRepo;
import com.blog.repository.UserSessionRepo;

@Service
public class CommentServiceImpl implements CommentService{
	
	@Autowired
	private PostRepo postRepo;
	
	@Autowired
	private CommentRepo crepo;
	
	@Autowired
	private UserRepo ur;
	
	@Autowired
	private UserSessionRepo usrRepo;
	
	
	
	@Override
	public Comment createComment(Comment comment, Integer postId, String key) throws PostException, UserException {
		
		List<CurrentUserSession>cUser=usrRepo.findByUuid(key);
		if(cUser.size()==0)
			throw new UserException("you are not logged in please log in");
		
		CurrentUserSession currentUser=cUser.get(0);
		
		Optional<User> op=ur.findById(currentUser.getUserId());
		
		if(op.isEmpty())
			throw new UserException("please log in");
		
		User user=op.get();
		
		comment.setUser(user);
		
		Optional<Post>p=postRepo.findById(postId);
		if(p.isEmpty())
			throw new PostException("post not found with id "+postId);
		
		List<Comment>list=p.get().getComments();
		list.add(comment);
		return crepo.save(comment);
	}

	@Override
	public List<Comment> getAllComment(Integer postId) throws PostException, CommentException {
		Optional<Post>p=postRepo.findById(postId);
		if(p.isEmpty())
			throw new PostException("post not found with id "+postId);
		
		List<Comment>list=p.get().getComments();
		
		if(list.size()==0)
			throw new CommentException("no comment on this post");
		
		return list;
	}

	@Override
	public Comment getComment(Integer postId, Integer commentId) throws PostException, CommentException {
		
		Optional<Post>p=postRepo.findById(postId);
		if(p.isEmpty())
			throw new PostException("post not found with id "+postId);
		
		List<Comment>list=p.get().getComments();
		
		if(list.size()==0)
			throw new CommentException("no comment on this post");
		Comment comment=null;
		for(Comment c:list) {
			if(c.getId()==commentId)
				comment=c;
		}
		
		if(comment==null)
			throw new CommentException("comment not found");
		
		return comment;
	}



	@Override
	public Comment updateComment(Comment comment, Integer postId) throws PostException, CommentException {
		Optional<Post>p=postRepo.findById(postId);
		if(p.isEmpty())
			throw new PostException("post not found with id "+postId);
		Post post=p.get();
		List<Comment>list=post.getComments();
		
		Comment comm=null;
		for(Comment c:list) {
			if(c.getId()==comment.getId()) {
				c=comment;
				comm=c;
			}
		}
		
		if(comm==null)
			throw new CommentException("comment not found");
		
		postRepo.save(post);
		
		return comment;
	}

	@Override
	public Comment deleteComment(Integer commentId, Integer postId) throws PostException, CommentException {
		Optional<Post>p=postRepo.findById(postId);
		if(p.isEmpty())
			throw new PostException("post not found with id "+postId);
		
		Post post=p.get();
		
		List<Comment>list=post.getComments();
		
		Comment comm=null;
		for(Comment c:list) {
			if(c.getId()==commentId)
				comm=c;
		}
		if(comm==null)
			throw new CommentException("comment not found");
		
		list.remove(comm);
		
		postRepo.save(post);
		
		return comm;
	}

}
