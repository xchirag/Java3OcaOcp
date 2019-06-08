package com.fdmgroup.classimp;

import java.util.List;


public interface ShoppingBasketDao {
	
	public void addShoppingBasket(ShoppingBasket basket);
	public ShoppingBasket getShoppingBasket(int basketId);
	public void removeShoppingBasket(int basketId);
	public void updateShoppingBasket(ShoppingBasket basket);
	public List<ShoppingBasket> listOfBaskets();

}
