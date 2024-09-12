package service;

import java.sql.Connection;
import java.util.List;

import model.BookOrders;
import model.Member;

public interface BookOrdersService {
	   //新增 Create
    //訂單添加到數據庫中
    public abstract void insert(Connection conn, BookOrders orders);


    //刪除 Delete
    //將指定id的訂單紀錄，從數據庫中刪除
    public abstract void deleteById(Connection conn, Integer id);
    public abstract void deleteById(Connection conn, String ordersId);
    
    //為了動態的訂單編號
    public abstract String getneworderno(Connection conn);
//	public abstract BookOrders getBookOrderId(Connection conn);

    
	 public List<BookOrders> showAll(Connection conn);
	 
	public List<BookOrders> getAllbyuserId(Connection conn, Member m);

}
