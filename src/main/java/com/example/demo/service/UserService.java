package com.example.demo.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.demo.Comment;
import com.example.demo.Product;
import com.example.demo.SalesItem;
import com.example.demo.User;
import com.example.demo.repository.CommentRepository;
import com.example.demo.repository.ProductRepository;
import com.example.demo.repository.UserRepository;



@Service
@CrossOrigin("http://localhost:4200")
public class UserService {
	
	
	
	@Autowired
	public CommentRepository commentRepository;

	@Autowired
	public UserRepository userRepository;
	
	@Autowired
	public ProductRepository productRepository;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	public String login(){
		return "ok";

	}
	
	public String addUser(@RequestBody User customer) {
		String pwd = customer.getPassword(); 
		String encryptPwd = passwordEncoder.encode(pwd);
		customer.setPassword(encryptPwd);
		userRepository.save(customer);
		return "Hi " ;
	}
	
	
	public List<User> getAllUsers(){
	 List<User> customers = new ArrayList<>();
		userRepository.findAll().forEach(customers :: add);
		return customers;

	}
	
	
	
	public User finduser(int rest_id) {
		
		
        return userRepository.findById(rest_id);
    }
	
	public Optional <User> findCustByUsername(String username) {
		
		
        Optional <User> cust= userRepository.findByUsername(username);
        return cust;
    }
	 public List<User> cancelRegistration(int rest_id) {
	        userRepository.deleteById(rest_id);
	        List<User> customers = new ArrayList<>();
			userRepository.findAll().forEach(customers :: add);
			return customers;
	    }
	 
	 public void postComment(int cId, int pId, Comment comment ) {
		 
		 User user = userRepository.findById(cId);
		 Product product = productRepository.findById(pId);
		 comment.setUserName(user.getUsername());
		 
		 comment.setProduct(product);
		 comment.setUser(user);
		 comment.setDate(LocalDate.now());
		 commentRepository.save(comment);
		 
		 
		 
	 }
	 
	 public List<Comment> getAllComments(){
		 List<Comment> comment = new ArrayList<>();
			commentRepository.findAll().forEach(comment :: add);
			return comment;
		 
	 }
	 
	 public List<Comment> getComment(int pId){
		 
		 //Product product = productRepository.findById(pId);
			/*
			 * List<Comment> comments = new ArrayList<>(); comments=getAllComments();
			 * List<Comment> coms = new ArrayList<>();
			 * 
			 * for (Comment com : comments) { if (com.getProduct().getId()== pId) {
			 * 
			 * coms.add(com);
			 * 
			 * }
			 * 
			 * 
			 * }
			 * 
			 * return coms;
			 */
		 Product product = productRepository.findById(pId);
		 List<Comment> comment = new ArrayList<>();
		 comment= product.getComments();
		 return comment;
		 
		 
	 }
	 
	 public List<Comment> approveComments(int comId){
		 Comment comment= commentRepository.findById(comId);
		 comment.setApprove("APPROVED");
		 commentRepository.save(comment);
	
		 List<Comment> comments = new ArrayList<>();
			commentRepository.findAll().forEach(comments :: add);
			return comments;
		 
		 
	 }

}


