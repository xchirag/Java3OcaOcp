package com.fdmgroup.productDao;

import java.util.List;

public interface ProductDao {

	public void addProduct(Product product);

	public Product getProduct(int productId);

	public void removeProduct(int productId);

	public void updateProduct(Product product);

	public List<Product> listOfProducts();

	//test_listtAllStreamsReturnsListWithsizeOneWhenOneStreamsAdded()
	//test_listOfProductsReturnsListWithSizeOneWhenOneStreamsAdded()
}
