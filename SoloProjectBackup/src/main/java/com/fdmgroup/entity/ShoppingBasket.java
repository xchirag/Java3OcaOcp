package com.fdmgroup.entity;

import java.util.List;
import java.util.Map;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapKeyJoinColumn;

//import com.fdmgroup.customerDao.Customer;
import com.fdmgroup.entity.Product;

@Entity(name="ng_shoppingbasket")
public class ShoppingBasket {
	
	@Id
	private int basketId;
	
	@Column
	private Double basketPrice;
	
	@ManyToOne
	@JoinColumn(name="CustomerNumber")
	private Customer customer;
	
	@Column
	private Double discountedBasketPrice;
	
	
	@Column
	private int discountPercentage;
	
	
	@Override
	public String toString() {
		return "ShoppingBasket [basketId=" + basketId + ", basketPrice=" + basketPrice + ", customer=" + customer
				+ ", discountedBasketPrice=" + discountedBasketPrice + ", discountPercentage=" + discountPercentage
				+ ", listOfAddedItems=" + listOfAddedItems + "]";
	}
	@ElementCollection	
	@CollectionTable(name="ng_shoppingbasket_products") // name of joining table
	@MapKeyJoinColumn(name="Product_ID")
	@Column(name="Quantity")
		private Map<Product, Integer> listOfAddedItems;
	public ShoppingBasket(){
		
	}
	public ShoppingBasket(int basketId, Customer customer, Double basketPrice, int discountPercentage,
			Double discountedBasketPrice, Map<Product, Integer> listOfAddedItems) {
		super();
		this.basketId = basketId;
		this.customer = customer;
		this.basketPrice = basketPrice;
		this.discountPercentage = discountPercentage;
		this.discountedBasketPrice = discountedBasketPrice;
		this.listOfAddedItems = listOfAddedItems;
	}
	
	public void addProduct() {
		
	}
	public int getBasketId() {
		return basketId;
	}
	
	public Double getBasketPrice() {
		return basketPrice;
	}
	
	public Customer getCustomer() {
		return customer;
	}
	public Double getDiscountedBasketPrice() {
		return discountedBasketPrice;
	}

	public int getDiscountPercentage() {
		return discountPercentage;
	}
	public Map<Product, Integer> getListOfAddedItems() {
		return listOfAddedItems;
	}
	public void setBasketId(int basketId) {
		this.basketId = basketId;
	}
	public void setBasketPrice(Double basketPrice) {
		this.basketPrice = basketPrice;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	public void setDiscountedBasketPrice(Double discountedBasketPrice) {
		this.discountedBasketPrice = discountedBasketPrice;
	}
	public void setDiscountPercentage(int discountPercentage) {
		this.discountPercentage = discountPercentage;
	}
	public void setListOfAddedItems(Map<Product, Integer> listOfAddedItems) {
		this.listOfAddedItems = listOfAddedItems;
	}
	public List<Product> viewProductsInShoppingBasket(){
		return null;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + basketId;
		result = prime * result + ((basketPrice == null) ? 0 : basketPrice.hashCode());
		result = prime * result + ((customer == null) ? 0 : customer.hashCode());
		result = prime * result + discountPercentage;
		result = prime * result + ((discountedBasketPrice == null) ? 0 : discountedBasketPrice.hashCode());
		result = prime * result + ((listOfAddedItems == null) ? 0 : listOfAddedItems.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ShoppingBasket other = (ShoppingBasket) obj;
		if (basketId != other.basketId)
			return false;
		if (basketPrice == null) {
			if (other.basketPrice != null)
				return false;
		} else if (!basketPrice.equals(other.basketPrice))
			return false;
		if (customer == null) {
			if (other.customer != null)
				return false;
		} else if (!customer.equals(other.customer))
			return false;
		if (discountPercentage != other.discountPercentage)
			return false;
		if (discountedBasketPrice == null) {
			if (other.discountedBasketPrice != null)
				return false;
		} else if (!discountedBasketPrice.equals(other.discountedBasketPrice))
			return false;
		if (listOfAddedItems == null) {
			if (other.listOfAddedItems != null)
				return false;
		} else if (!listOfAddedItems.equals(other.listOfAddedItems))
			return false;
		return true;
	}
	
}
	