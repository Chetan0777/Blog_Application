package com.blog.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blog.exception.PostException;
import com.blog.exception.UserException;
import com.blog.model.CurrentUserSession;
import com.blog.model.Post;
import com.blog.model.User;
import com.blog.repository.PostRepo;
import com.blog.repository.UserRepo;
import com.blog.repository.UserSessionRepo;


@Service
public class PostServiceImpl implements PostService{
	
	@Autowired
	private PostRepo pr;
	
	@Autowired
	private UserRepo ur;
	
	@Autowired
	private UserSessionRepo usrRepo;

	@Override
	public Post createPost(Post post, String key) throws PostException ,UserException{
		
		List<CurrentUserSession>cUser=usrRepo.findByUuid(key);
		if(cUser.size()==0)
			throw new UserException("you are not logged in please log in");
		
		CurrentUserSession currentUser=cUser.get(0);
		
		Optional<User> op=ur.findById(currentUser.getUserId());
		
		if(op.isEmpty())
			throw new UserException("please log in");
		
		User user=op.get();
		
		post.setUser(user);
		user.getPosts().add(post);
		
		return pr.save(post);
	}

	@Override
	public List<Post> getAllPost(String key) throws PostException ,UserException{
		
		List<CurrentUserSession>cUser=usrRepo.findByUuid(key);
		if(cUser.size()==0)
			throw new UserException("you are not logged in please log in");
		
		List<Post> list=pr.findAll();
		if(list.size()==0)
			throw new PostException("no post found");
		return list;
	}

	@Override
	public Post getPost(Integer id, String key) throws PostException ,UserException{
		
		List<CurrentUserSession>cUser=usrRepo.findByUuid(key);
		if(cUser.size()==0)
			throw new UserException("you are not logged in please log in");
		
		Optional<Post>p=pr.findById(id);
		if(p.isEmpty())
			throw new PostException("no post found");
		return p.get();
	}

	@Override
	public Post deletePost(Integer id, String key) throws PostException ,UserException{
		
		List<CurrentUserSession>cUser=usrRepo.findByUuid(key);
		if(cUser.size()==0)
			throw new UserException("you are not logged in please log in");
		
		Optional<Post>p=pr.findById(id);
		if(p.isEmpty())
			throw new PostException("no post found");
		pr.delete(p.get());
		return p.get();
	}

	@Override
	public Post updatePost(Integer id, Post post, String key) throws PostException ,UserException{
		
		List<CurrentUserSession>cUser=usrRepo.findByUuid(key);
		if(cUser.size()==0)
			throw new UserException("you are not logged in please log in");
		
		Optional<Post>p=pr.findById(id);
		if(p.isEmpty())
			throw new PostException("no post found");
		return pr.save(post);
	}

}
