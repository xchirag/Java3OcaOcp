package com.fdmgroup.Dao;

import java.util.List;

import com.fdmgroup.entity.ShoppingBasket;


public interface ShoppingBasketDao {
	
	public void addShoppingBasket(ShoppingBasket basket);
	public ShoppingBasket getShoppingBasket(int basketId);
	public void removeShoppingBasket(int basketId);
	public void updateShoppingBasket(ShoppingBasket basket);
	public List<ShoppingBasket> listOfBaskets();

}
