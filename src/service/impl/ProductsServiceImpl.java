package service.impl;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import dao.impl.ProductsDaoImpl;
import model.Products;
import service.ProductsService;
import utils.DbConnection;
import utils.ImageDisplay;

public class ProductsServiceImpl implements ProductsService{
	
	private ProductsDaoImpl pdi = new ProductsDaoImpl();
	private Container forbooklist;
	private List<Products> currentBooks;

	@Override
	public List<Products> showbyLimit6(Connection conn, Integer BOOKS_PER_PAGE, Integer pageNumber) {
		List<Products> allbyLimit6 = pdi.getAllbyLimit6(conn, BOOKS_PER_PAGE, pageNumber);
		return allbyLimit6;
	}

	
	
	
	@Override
	public List<Products> CreateShoppingCar() {
		List<Products> shoppingCarlist = new ArrayList<>();
		return shoppingCarlist;
	}


	@Override
	public List<Products> addShoppingCar(List<Products> shoppingCarlist, Products book) {
		shoppingCarlist.add(book);
		System.out.println("加入購物車");
		return shoppingCarlist;
	}

	@Override
	public List<Products> deleteShoppingCar(List<Products> shoppingCarlist, Products book) {
		return null;
	}

	@Override
	public List<Products> showShoppingCar(List<Products> shoppingCarlist) {
		return null;

	}




	@Override
	public List<Products> showAll(Connection conn) {
		return pdi.getAll(conn);
	}

	


}
