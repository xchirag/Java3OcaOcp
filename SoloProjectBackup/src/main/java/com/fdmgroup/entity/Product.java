package com.fdmgroup.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name="ng_product")
public class Product {

	@Id
	@Column(name="Product_ID")
	private int id;
	
	
	@Column(name="Product_Name")
	private String productName;
	
	
	@Column(name="Price")
	private double price;
	//private List<Product> productCatalogue; this cannot be here and should be another class as productInventory (seperate class)
	
	
	
	public int getId() {
		return id;
	}
	public double getPrice() {
		return price;
	}
//	public List<Product> getProductCatalogue() {
//		return productCatalogue;
//	}
	public String getProductName() {
		return productName;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void setPrice(double price) {
		this.price = price;
	}
//	public void setProductCatalogue(List<Product> productCatalogue) {
//		this.productCatalogue = productCatalogue;
//	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	@Override
	public String toString() {
		return "Product [id=" + id + ", productName=" + productName + ", price=" + price + "]";
	}
	public Product(int id, String productName, double price) {
		super();
		this.id = id;
		this.productName = productName;
		this.price = price;
	}
	
	public Product(){
		//empty constructor which is require by JPA to run it successfully
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		long temp;
		temp = Double.doubleToLongBits(price);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((productName == null) ? 0 : productName.hashCode());
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
		Product other = (Product) obj;
		if (id != other.id)
			return false;
		if (Double.doubleToLongBits(price) != Double.doubleToLongBits(other.price))
			return false;
		if (productName == null) {
			if (other.productName != null)
				return false;
		} else if (!productName.equals(other.productName))
			return false;
		return true;
	}
	
	
}
