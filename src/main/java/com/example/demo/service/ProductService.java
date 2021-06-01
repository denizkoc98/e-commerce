package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.example.demo.Product;
import com.example.demo.repository.ProductRepository;
import com.example.demo.repository.UserRepository;


@Service
@CrossOrigin("http://localhost:4200")
public class ProductService {
	
	@Autowired
	public ProductRepository productRepository;
	
	@Autowired
	public UserRepository userRepository;
	
	
	public List<Product> getAllProducts(){
	 List<Product> products = new ArrayList<>();
		productRepository.findAll().forEach(products :: add);
		return products;

	}
	
	public String addProduct(Product product) {
	productRepository.save(product);
    return "Hi your Registration process successfully completed";
	
	}
	
	public Product findProduct(int rest_id) {
		
		/*Optional<Restaurant> r = restaurantRepository.findById(rest_id);
		List<MenuItem> ms =r.get().getRest_menu().getMenuitem();
		for (Iterator iterator = ms.iterator(); iterator.hasNext();) {
			MenuItem menuItem = (MenuItem) iterator.next();
			System.out.println(menuItem.getItemName());
		}
		*/
		return productRepository.findById(rest_id);
        
    }
	
	public List<Product> update(int id, int stock) {
		Product product= productRepository.findById(id);
		product.setQuantity(stock);
		
		List<Product> products = new ArrayList<>();
		productRepository.findAll().forEach(products :: add);
		return products;
	}
	public List<Product> updatePrice(int id, int stock) {
		Product product= productRepository.findById(id);
		product.setPrice(stock);
		
		List<Product> products = new ArrayList<>();
		productRepository.findAll().forEach(products :: add);
		return products;
	}
	
	 public List<Product> cancelRegistration(int rest_id) {
	        productRepository.deleteById(rest_id);
	        List<Product> products = new ArrayList<>();
			productRepository.findAll().forEach(products :: add);
			return products;
	    }
	 
	 public void populatedata() {

			com.example.demo.User user= new com.example.demo.User(0, "alo","alo","alo","888","hhh","PManager","alo");
			
			userRepository.save(user);
			Product product= new Product(0,"Nutuk","Ataturk","ozgur","Revolutionary","./../../../assets/img/shop01.png", "it",30, 50,2);
			productRepository.save(product);
	 }
	 
	 
	 

}
