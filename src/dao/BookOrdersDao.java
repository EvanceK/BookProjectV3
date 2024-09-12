package dao;

import java.sql.Connection;
import java.util.List;

import model.BookOrders;
import model.Member;

public interface BookOrdersDao {

    //新增 Create
    //訂單添加到數據庫中
    public abstract void insert(Connection conn, BookOrders orders);


    //刪除 Delete
    //將指定id的訂單紀錄，從數據庫中刪除
    public abstract void deleteById(Connection conn, Integer id);
    public abstract void deleteById(Connection conn, String orderId);
    //為了動態的訂單編號
    public abstract List<BookOrders> getlastid(Connection conn);
//    有異常待確認....
//    public BookOrders getBookOrderId(Connection conn);
    
    public abstract List<BookOrders> getAll(Connection conn);
    
    //給會員查自己的所有清單
    public List<BookOrders> getAllbyuserId(Connection conn, String userId);



}
