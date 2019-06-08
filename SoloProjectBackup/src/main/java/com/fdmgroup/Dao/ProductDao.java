package com.fdmgroup.Dao;

import java.util.List;

import com.fdmgroup.entity.Product;

public interface ProductDao {

	public void addProduct(Product product);

	public Product getProduct(int productId);

	public void removeProduct(int productId);

	public void updateProduct(Product product);

	public List<Product> listOfProducts();

	//test_listtAllStreamsReturnsListWithsizeOneWhenOneStreamsAdded()
	//test_listOfProductsReturnsListWithSizeOneWhenOneStreamsAdded()
}
