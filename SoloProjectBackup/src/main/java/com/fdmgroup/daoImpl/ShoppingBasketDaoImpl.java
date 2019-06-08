package com.fdmgroup.daoImpl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import org.junit.AfterClass;

import com.fdmgroup.Dao.ShoppingBasketDao;
import com.fdmgroup.entity.ShoppingBasket;

public class ShoppingBasketDaoImpl implements ShoppingBasketDao {

	private EntityManagerFactory entityManagerFactory;
	private EntityManager entityManager;
	private EntityTransaction transaction;

	public void setupEnvironment() {

		entityManagerFactory = Persistence.createEntityManagerFactory("soloprojectjpa1");
		entityManager = entityManagerFactory.createEntityManager();
		transaction = entityManager.getTransaction();
	}

//	@Override
	public void addShoppingBasket(ShoppingBasket basket) {

		setupEnvironment();
		ShoppingBasket tempBasket = entityManager.find(ShoppingBasket.class, basket.getBasketId());
		if (tempBasket == null) {
			transaction.begin();
			entityManager.merge(basket);
			transaction.commit();
		}
	}
	

//	@Override
	public ShoppingBasket getShoppingBasket(int basketId) {
		setupEnvironment();
		ShoppingBasket shoppingBasket = entityManager.find(ShoppingBasket.class, basketId);
		return shoppingBasket;

	}

//	@Override
	public void removeShoppingBasket(int basketId) {

		setupEnvironment();
		ShoppingBasket tempBasket = entityManager.find(ShoppingBasket.class, basketId);

		if (tempBasket != null) {
			transaction.begin();
			entityManager.remove(tempBasket);
			transaction.commit();
		}

	}

//	@Override
	public void updateShoppingBasket(ShoppingBasket basket) {

		setupEnvironment();
		int basketId = basket.getBasketId();
		ShoppingBasket tempBasket = entityManager.find(ShoppingBasket.class, basketId);
		if (tempBasket != null) {
			transaction.begin();
			entityManager.merge(basket);
			transaction.commit();
		}
	}

//	@Override
	public List<ShoppingBasket> listOfBaskets() {

		setupEnvironment();
		List<ShoppingBasket> resultList = entityManager
				.createQuery("select a from ng_shoppingbasket a", ShoppingBasket.class).getResultList();
		return resultList;
	}
	
	

}
