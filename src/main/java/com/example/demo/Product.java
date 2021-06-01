package com.example.demo;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	
    private int id;
	private String title;
	private String author;
	private String vendor;
	private String description;
	private String image;
	private String slug;
	private int price;
	private int quantity;
	private int category;
//	
//	@ManyToMany(mappedBy="hasItem", cascade = CascadeType.ALL)
//	//@JsonBackReference(value="basket-item")
//	@JsonIgnoreProperties("hasItem")
//    List<Basket> belongs=  new ArrayList<> ();
	
	@OneToMany(mappedBy="product", cascade = CascadeType.ALL)
	@JsonManagedReference(value="comment-product")
	private List<Comment> comments = new ArrayList<> ();
	
	@OneToMany(mappedBy="product", cascade = CascadeType.ALL)
	@JsonIgnoreProperties("salesi-product")
	private List<SalesItem> salesItems = new ArrayList<> ();
	
	/*
	 * @OneToMany(mappedBy="product")
	 * 
	 * @JsonBackReference(value="prod-sales") private List<Sales> sales = new
	 * ArrayList<> ();
	 */

	public Product(int id, String title, String author, String vendor, String description, String image, String slug,
			int price, int quantity,int category) {
		super();
		this.id = id;
		this.title = title;
		this.author = author;
		this.vendor = vendor;
		this.description = description;
		this.image = image;
		this.slug = slug;
		this.price = price;
		this.quantity = quantity;
		this.category = category;
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getVendor() {
		return vendor;
	}

	public void setVendor(String vendor) {
		this.vendor = vendor;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getSlug() {
		return slug;
	}

	public void setSlug(String slug) {
		this.slug = slug;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	

	public List<Comment> getComments() {
		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}
	
	
public Product() {
		
	}
	
		  
}
