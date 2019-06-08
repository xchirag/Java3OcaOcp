package com.fdmgroup.daoImpl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import com.fdmgroup.entity.SaleItem;

public class SaleItemDaoImpl {

	// EntityManager is passed via constructor of the class to improve performance and reduce code written into program
	// this is better way of doing 
	
	private EntityManager entityManager;
	
	public SaleItemDaoImpl (EntityManager entityManager){
		this.entityManager = entityManager;
	}
	
	public List<SaleItem> listTheItems(){
		TypedQuery<SaleItem> querySaleItems = 
				entityManager.createQuery("select s from SaleItem s", SaleItem.class);
		List<SaleItem> listItems = querySaleItems.getResultList();
		
		return listItems;
	}
}


// firstly created an object to get the model sorted
// then created a daoImpl to get the methods sorted
// put and manage controller (get the items from database first into controller ==> then pass the list to jsp page ===> display list on jsp using tag)
// sort out jsp page