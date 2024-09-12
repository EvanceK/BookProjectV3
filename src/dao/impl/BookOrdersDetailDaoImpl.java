package dao.impl;

import java.sql.Connection;

import dao.BaseDao;
import dao.BookOrdersDetailDao;
import model.BookOrdersDetail;

public class BookOrdersDetailDaoImpl extends BaseDao implements BookOrdersDetailDao {
    @Override
    public void insert(Connection conn, BookOrdersDetail ordersdetail) {
        String sql = "insert into bookordersdetail (bookordersid, bookname, bookprice,bookamount)values(?,?,?,?)";
        update(conn, sql, ordersdetail.getBookOrdersId(),ordersdetail.getBookName(),ordersdetail.getBookPrice(),ordersdetail.getBookAmount());
    }

    @Override
    public void deleteById(Connection conn, Integer id) {
        String sql = "delete from bookordersdetail where id = ?";
        update(conn, sql, id);
    }

    @Override
    public void deleteById(Connection conn, String bookOrdersId) {
        String sql = "delete from bookordersdetail where orderid = ?";
        update(conn, sql, bookOrdersId);
    }
}
