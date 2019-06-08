//saleItem
//saleItemDao
//HomeController
//ListSaleItems

// plain old java objects

// above classes will be created and put inside the entity package
// if we do not get import entity/id/column messages then put dependencies into pom.xml


package com.fdmgroup.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class SaleItem {
	
	@Id
	private int itemId;
	
	@Column
	private String itemName;

	// empty constructor or null constructor is required for JPA table constructor
	public SaleItem(){
		
	}

	public int getItemId() {
		return itemId;
	}

	public void setItemId(int itemId) {
		this.itemId = itemId;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

}
