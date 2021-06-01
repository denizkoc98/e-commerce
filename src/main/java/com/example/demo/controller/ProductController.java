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

import com.example.demo.Product;
import com.example.demo.User;
import com.example.demo.service.ProductService;
import com.example.demo.service.UserService;




@RestController
@CrossOrigin("http://localhost:4200")
@RequestMapping("/product")

public class ProductController {
	
	@Autowired
	ProductService productService;
	
	
	
	 @PostMapping("/register")
	    public String register(@RequestBody Product product) {
			return productService.addProduct(product);
	        
	    }
	 
	 @GetMapping("/generatedata")
	    public void generatedata() {
		  productService.populatedata();
		
	    }
	
	 @GetMapping("/getAllProducts")
	    public List<Product> getAllProducts() {
		 return productService.getAllProducts();
		
	    }
	
	 @GetMapping("/findProduct/{rest_id}")
	    public Product findProduct(@PathVariable  ("rest_id") int rest_id) {
		
		 return productService.findProduct(rest_id);
	       
	    }
	 @GetMapping("/updateProduct/{rest_id}/{stock}")
	    public List<Product> findProduct(@PathVariable  ("rest_id") int rest_id, @PathVariable  ("stock") int stock) {
		
		 return productService.update(rest_id, stock);
	       
	    }
	 @GetMapping("/updatePrice/{rest_id}/{stock}")
	    public List<Product> updatePrice(@PathVariable  ("rest_id") int rest_id, @PathVariable  ("stock") int stock) {
		
		 return productService.updatePrice(rest_id, stock);
	       
	    }
	 @DeleteMapping("/cancel/{rest_id}")
	    public List<Product> cancelRegistration(@PathVariable("rest_id") int rest_id) {
	       return productService.cancelRegistration(rest_id);
	    }


}
