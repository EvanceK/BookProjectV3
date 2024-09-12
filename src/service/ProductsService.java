package service;

import java.sql.Connection;
import java.util.List;

import model.Products;

public interface ProductsService {
	
	 public List<Products> showbyLimit6(Connection conn, Integer BOOKS_PER_PAGE, Integer pageNumber);
	 public List<Products> showAll(Connection conn);

	 
	 public List<Products> CreateShoppingCar();
	 public List<Products> addShoppingCar(List<Products> shoppingCarlist, Products book);
	 public List<Products> deleteShoppingCar(List<Products> shoppingCarlist,Products book);
	 public List<Products> showShoppingCar(List<Products> shoppingCarlist);



}
