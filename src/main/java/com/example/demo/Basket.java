package com.example.demo;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;

import org.springframework.data.annotation.Transient;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
public class Basket {
	
	 @Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	 private int basketId;
	 
	// private Float TotalOrderPrice;
	
	
	 
	 @ManyToMany
	 @JoinTable(name = "hasItem")
	 @JsonIgnoreProperties("belongs")
	 List<SalesItem> hasItem = new ArrayList<> ();
	 
	 
	 @OneToOne
	 @JsonBackReference(value="basket-cust") 
	 private User user;

	 private float totalExpenses;
	
	 
	public Basket(int basketId, List<SalesItem> hasItem, User user, float totalExpenses) {
		super();
		this.basketId = basketId;
		this.hasItem = hasItem;
		this.user = user;
		this.totalExpenses=totalExpenses;
	}



	
	

	public float getTotalExpenses() {
		return totalExpenses;
	}






	public void setTotalExpenses(float totalExpenses) {
		this.totalExpenses = totalExpenses;
	}






	@Transient
    public Float getTotalOrderPrice() {
        float sum = 0;
        List<SalesItem> salesItems = getHasItem();
        for (SalesItem op : salesItems) {
            sum += (op.getProduct().getPrice())* op.getQuantity();
        }
        return sum;
    }








	public Basket() {
		
	}








	public int getBasketId() {
		return basketId;
	}




	public void setBasketId(int basketId) {
		this.basketId = basketId;
	}




	public List<SalesItem> getHasItem() {
		return hasItem;
	}




	public void setHasItem(List<SalesItem> hasItem) {
		this.hasItem = hasItem;
	}




	public User getUser() {
		return user;
	}




	public void setUser(User user) {
		this.user = user;
	}





	
	 
	
	 

}
