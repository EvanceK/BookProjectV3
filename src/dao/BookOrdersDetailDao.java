package dao;

import java.sql.Connection;

import model.BookOrdersDetail;

public interface BookOrdersDetailDao {

    //新增 Create
    //訂單資訊添加到數據庫中
    public abstract void insert(Connection conn, BookOrdersDetail orders);


    //刪除 Delete
    //將指定id的訂單紀錄，從數據庫中刪除
    public abstract void deleteById(Connection conn, Integer id);
    public abstract void deleteById(Connection conn, String bookOrdersId);
}
