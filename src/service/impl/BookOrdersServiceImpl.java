package service.impl;

import java.sql.Connection;
import java.util.List;

import dao.impl.BookOrdersDaoImpl;
import model.BookOrders;
import model.Member;
import model.Products;
import service.BookOrdersService;

public class BookOrdersServiceImpl implements BookOrdersService {

    private static BookOrdersDaoImpl bodi = new BookOrdersDaoImpl();


    @Override
    public void insert(Connection conn, BookOrders orders) {
        bodi.insert(conn,orders);
    }

    @Override
    public void deleteById(Connection conn, Integer id) {
    bodi.deleteById(conn,id);
    }

    @Override
    public void deleteById(Connection conn, String ordersId) {
        bodi.deleteById(conn,ordersId);
    }

	@Override
	public String getneworderno(Connection conn) {
		List<BookOrders> getlastid = bodi.getlastid(conn);
		Integer id = getlastid.get(0).getId();
		int newId = id + 1; // 將 id 加 1
        String neworderno = String.format("o%04d", newId);
		return neworderno;
	}
	
	 public List<BookOrders> showAll(Connection conn){
		 List<BookOrders> all = bodi.getAll(conn);
		 return all;
	 }

	public List<BookOrders> getAllbyuserId(Connection conn, Member m){
		
		 List<BookOrders> allbyuserid = bodi.getAllbyuserId(conn, m.getUserId());
		 return allbyuserid;
	}
}
