package com.example.demo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
public class PurchaseDetail {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int purId;
	private LocalDate orderDate;
	private float bill;
	private int customerId;
	private String address;
	private String deliveryStatus;

	
	 @ManyToMany
	 @JsonIgnoreProperties("purchase")
	 List<Purchased> purchaseItem = new ArrayList<> ();
	 
	 

	public PurchaseDetail(int purId, LocalDate orderDate, float bill, int customerId, String address,
			String deliveryStatus, List<Purchased> purchaseItem) {
		super();
		this.purId = purId;
		this.orderDate = orderDate;
		this.bill = bill;
		this.customerId = customerId;
		this.address = address;
		this.deliveryStatus = deliveryStatus;
		this.purchaseItem = purchaseItem;
	}

	public int getPurId() {
		return purId;
	}

	public void setPurId(int purId) {
		this.purId = purId;
	}

	

	public LocalDate getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(LocalDate orderDate) {
		this.orderDate = orderDate;
	}

	public float getBill() {
		return bill;
	}

	public void setBill(float bill) {
		this.bill = bill;
	}

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public List<Purchased> getPurchaseItem() {
		return purchaseItem;
	}

	public void setPurchaseItem(List<Purchased> purchaseItem) {
		this.purchaseItem = purchaseItem;
	}

	

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getDeliveryStatus() {
		return deliveryStatus;
	}

	public void setDeliveryStatus(String deliveryStatus) {
		this.deliveryStatus = deliveryStatus;
	}

	public PurchaseDetail(){
		 
	 }

}
