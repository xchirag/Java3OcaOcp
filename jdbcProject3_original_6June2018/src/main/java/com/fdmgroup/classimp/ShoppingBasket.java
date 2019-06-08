package com.fdmgroup.classimp;

import java.util.List;
import java.util.Map;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapKeyJoinColumn;

import com.fdmgroup.customerDao.Customer;
import com.fdmgroup.productDao.Product;

@Entity(name="XShoppingBasket")
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
	
	
	@ElementCollection	
	@CollectionTable(name="XShoppingBasket_Products") // name of joining table
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
}
	