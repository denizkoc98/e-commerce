package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Comment;
import com.example.demo.User;
import com.example.demo.service.UserService;



@RestController
@CrossOrigin("http://localhost:4200")
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	UserService userService;
	@Autowired
	com.example.demo.service.CustomerDetailsService detailsService;
	
	
	
	
	 @PostMapping("/add")
	    public String register(@RequestBody User user) {
			return userService.addUser(user);
	        
	    }
	 @GetMapping(value="/login", produces= "application/json")
	 public String login() {
		 return userService.login();
	 }
	
	 @GetMapping("/getAllusers")
	    public List<User> getAllusers() {
		 return userService.getAllUsers();
		
	    }

	 @GetMapping("/findUser/{rest_id}")
	    public User finduser(@PathVariable  ("rest_id") int rest_id) {
		
		 return userService.finduser(rest_id);
	       
	    }
	 
	 @DeleteMapping("/cancel/{rest_id}")
	    public List<User> cancelRegistration(@PathVariable("rest_id") int rest_id) {
	       return userService.cancelRegistration(rest_id);
	    }
	 @GetMapping("/finduserbyname/{username}")
	    public Optional <User> finduser(@PathVariable  ("username") String username) {
		
	
				 
		return userService.findCustByUsername(username);
		
	       
	    }
	 
	 @PostMapping("/comment/{cId}/{pId}")
	    public void postComment(@PathVariable  ("cId") int cId,@RequestBody Comment comment, 
	    		@PathVariable  ("pId") int pId) {
			userService.postComment(cId, pId, comment);
			
	        
	    }
	 
	 @GetMapping("/getcomments")
	    public List<Comment> getAllComments() {
			return userService.getAllComments();
	        
	    }
	
	 @GetMapping("/approvecomments/{comId}")
	    public List<Comment> approveComments(@PathVariable  ("comId")int comId) {
			return userService.approveComments(comId);
	        
	    }
	 @GetMapping("/getcomment/{pId}")
	    public List<Comment> getComment(@PathVariable  ("pId")int pId) {
			return userService.getComment(pId);
	        
	    }

}
