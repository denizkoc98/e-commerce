package com.example.demo.service;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.demo.Basket;
import com.example.demo.EmailRequestDto;
import com.example.demo.InvoicePdfExporter;
import com.example.demo.Product;
import com.example.demo.PurchaseDetail;
import com.example.demo.Purchased;
import com.example.demo.SalesItem;
import com.example.demo.User;
import com.example.demo.repository.BasketRepository;
import com.example.demo.repository.ProductRepository;
import com.example.demo.repository.PurchasedDetailsRepository;
import com.example.demo.repository.PurchasedRepository;
import com.example.demo.repository.SalesItemRepository;

import com.example.demo.repository.UserRepository;








@Service
@CrossOrigin("http://localhost:4200")

public class BasketService {
	
	@Autowired
	public UserRepository userRepository ;
	@Autowired
	public ProductRepository productRepository ;
	@Autowired
	public BasketRepository basketRepository ;
	@Autowired
	public PurchasedRepository pRepository ;
	@Autowired
	public PurchasedDetailsRepository pdRepository ;
	
	
	
	@Autowired
	public SalesItemRepository itemRepository ;
	
	public SalesItem findInList(List <SalesItem> salesitems, int pId){
		   
		   for (SalesItem salesitem : salesitems) {
	           if (salesitem.getProduct().getId()== pId) {
	              
	              return salesitem;
	              
	           }
	           
	           
	           	
	       }
		return null;
	       
	   }
	
	 public Basket add2(int menuItemId, int customerID) {
		 addToCart( menuItemId,  customerID);
		 User user = userRepository.findById(customerID);//find the customer
	 	Basket basket = user.getBasket();
	 	return basket;
		 
		 
	 } 
	public void addToCart(int pId, int customerID) {
	    
    	//basket = basketRepository.findById(basketId);
    	
    	User user = userRepository.findById(customerID);//find the customer
    	Basket basket = user.getBasket();
    	if (basket==null) {//if no basket before create new one
        	basket = new Basket();
        	basket.setUser(user);
        	}
    	//product adding
    	Product product = productRepository.findById(pId);//hangi producti ekliycez buldu idden
    	List<SalesItem> salesitems = new ArrayList<>();
    	salesitems= basket.getHasItem();
    	basket.setTotalExpenses(basket.getTotalOrderPrice());
    	
    	SalesItem si=findInList(salesitems, pId);
    	
    	if (si==null) {
    		
    		SalesItem s = new SalesItem(0,1,product);
    		itemRepository.save(s);
    		salesitems.add(s);
    		
    	}
    	
    	else {
    		si.setQuantity(si.getQuantity()+1);
    		
    	}
    	
    	
    	
    	
    	basketRepository.save(basket);
    	
    }
	
	
    
    
    public Basket myCart(int custId){
    	
    	User user = userRepository.findById(custId);
    	Basket basket =user.getBasket();    	
    
    	
    	return basket;
    	

    }
    
    public SalesItem findInList2(List <SalesItem> basketitems, int mId){
 	   
 	   for (SalesItem basketitem : basketitems) {
            if (basketitem.getProduct().getId()== mId) {
               
               return basketitem;
               
            }
            
            
            	
        }
 	return null;
        
    }
    
    public Purchased findInList3(List <Purchased> basketitems, int mId){
  	   
  	   for (Purchased basketitem : basketitems) {
             if (basketitem.getProduct().getId()== mId) {
                
                return basketitem;
                
             }
             
             
             	
         }
  	return null;
         
     }
    
   public List<SalesItem> newList(List <SalesItem> basketitems, int deletedId){
 	   
 	  List<SalesItem> newbasketItems = new ArrayList<>();
 	   for (SalesItem basketitem : basketitems) {
            if (basketitem.getSalesItemId() != deletedId) {
         	   newbasketItems.add(basketitem);

               
            }

            	
        }
 	   return newbasketItems;

        
    }
   
   public PurchaseDetail askRefund(int purId, int pId){
	  PurchaseDetail pd= pdRepository.findById(purId);
	  List <Purchased> purchased= pd.getPurchaseItem();
	  Purchased refundItem= findInList3(purchased, pId);
	  refundItem.setRefund("TAKEN");
	  pRepository.save(refundItem);
	  pdRepository.save(pd);
	  int id =pd.getCustomerId();
	 
	  
	  return pd;
	  
	   
   }
   public PurchaseDetail getPurchaseById(int purId) {
	   
	   return pdRepository.findById(purId);
   }
   
   
    public Basket removeItem(int pId, int cId) {
 	   User user = userRepository.findById(cId);
    		Basket basket = user.getBasket();
    		List<SalesItem> salesItems = new ArrayList<>();
     	salesItems= basket.getHasItem();
     	SalesItem si= findInList2(salesItems, pId);
     	int q =si.getQuantity();
     	if (q==1) {
     		
     		
     		
     		List<SalesItem> salesItems2 = new ArrayList<>();
     		
     		salesItems2 = newList(salesItems,si.getSalesItemId());
     		
     		basket.setHasItem(salesItems2);
     		
     		
     		//finding the rate
     		float totalEx= basket.getTotalOrderPrice();//price without rate
     		
     		basket.setTotalExpenses(totalEx);
     		basketRepository.save(basket);
     		return basket;
     		
     	}
     	else {
     		si.setQuantity((si.getQuantity())-1);
     		
     		float totalEx= basket.getTotalOrderPrice();//price without rate
     		
     		basket.setTotalExpenses(totalEx);
     		
     		
         	itemRepository.save(si);
         	basketRepository.save(basket);
         	return basket;
     	}
    }   	
    
    public PurchaseDetail savePurchase(List <SalesItem> basketitems, int cId, float totalprice)
    {
    	List <Purchased> purchased=new ArrayList<>(); 
    	for (SalesItem basketitem: basketitems) { 
  		  Product product = basketitem.getProduct();
  		  
  		  int quantity= basketitem.getQuantity();
  		  Purchased object = new Purchased(0,quantity, product);
  		 pRepository.save(object);
  		  purchased.add(object);
  		 
  		  
  	  }
    	User user = userRepository.findById(cId);
    	String address=user.getAddress();
    	
    	
    	
    	PurchaseDetail pDetail = new PurchaseDetail(0, LocalDate.now(), totalprice, cId,address, "TAKEN",purchased);
    	pdRepository.save(pDetail);
    	return pDetail;
    	
    	
    }
    public void stockChange(List <SalesItem> basketitems) {
    	
    	for (SalesItem basketitem: basketitems) { 
    		  Product product = basketitem.getProduct();
    		  
    		  int quantity= product.getQuantity();
    		  int pq= basketitem.getQuantity();
    		  product.setQuantity(quantity- pq);
    		  productRepository.save(product);
    		 
    		  
    	  }
    	
    	
    	
    }
    
 public void stockChange2(List <Purchased> basketitems) {
    	
    	for (Purchased basketitem: basketitems) { 
    		  Product product = basketitem.getProduct();
    		  
    		  int quantity= product.getQuantity();
    		  int pq= basketitem.getQuantityOrdered();
    		  product.setQuantity(quantity+ pq);
    		  productRepository.save(product);
    		 
    		  
    	  }
    	
    	
    	
    }
 public PurchaseDetail getOnePurchase(int purId) {
	 
	 return pdRepository.findById(purId);
 }
    public List<PurchaseDetail> cancelOrder(int purId) {
    	PurchaseDetail pd =pdRepository.findById(purId);
    	List <Purchased> purchased= new ArrayList<>();
    	purchased= pd.getPurchaseItem();
    	stockChange2(purchased);
    	
    	pd.setDeliveryStatus("CANCELED");
    	pdRepository.save(pd);
    	int id= pd.getCustomerId();
    	return myPurchase(id);
    	
    }
    
    
    public List<PurchaseDetail> myPurchase(int cId) {
    	return pdRepository.findByCustomerId(cId);
    	
    }
     	
     	public PurchaseDetail doPurchase (int customerId) {
        	User user = userRepository.findById(customerId);
        	Basket basket = user.getBasket();
        	List<SalesItem> basketitems =basket.getHasItem();
        	float ex= basket.getTotalOrderPrice();
        	
        	stockChange(basketitems);
        
        	
        	//saving the purchase to OrderDetails
        	PurchaseDetail pd=savePurchase(basketitems,customerId, ex);
        	
        	
        
        	basket.setHasItem(null);
        	basket.setTotalExpenses(0);
        	
        	
        	basketRepository.save(basket);
        	return pd;
        	
        }
     	
     	
     	  
     	
     	public List<PurchaseDetail> getPurchases(){
     		
     		List<PurchaseDetail> purchases = new ArrayList<>();
    		pdRepository.findAll().forEach(purchases :: add);
    		return purchases;
     	}
        
     
     	
	
    }   


