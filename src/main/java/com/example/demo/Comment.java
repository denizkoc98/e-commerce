package com.example.demo;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;


import com.fasterxml.jackson.annotation.JsonBackReference;
@Entity
public class Comment {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int commentId;
	private int rating;
	private String commentText;
	private String approve;
	private String userName;
	private LocalDate date;
	
	@ManyToOne
	@JsonBackReference(value="comment-product")
	private Product product;
	
	@ManyToOne
	@JsonBackReference(value="comment-user")
	private User user;
	
	

	public Comment(int commentId, int rating, String commentText, Product product) {
		super();
		this.commentId = commentId;
		this.rating = rating;
		this.commentText = commentText;
		this.product = product;
		
		
		
		//this.product = product;
		//this.user = user;
	}
	
	

	
	
	
	public LocalDate getDate() {
		return date;
	}






	public void setDate(LocalDate date) {
		this.date = date;
	}






	public String getUserName() {
		return userName;
	}




	public void setUserName(String userName) {
		this.userName = userName;
	}




	public String getApprove() {
		return approve;
	}




	public void setApprove(String approve) {
		this.approve = approve;
	}




	public int getCommentId() {
		return commentId;
	}

	public void setCommentId(int commentId) {
		this.commentId = commentId;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	public String getCommentText() {
		return commentText;
	}

	public void setCommentText(String commentText) {
		this.commentText = commentText;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	

public Comment() {
		
	}
}
