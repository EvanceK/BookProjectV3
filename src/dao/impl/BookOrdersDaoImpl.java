package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import dao.BaseDao;
import dao.BookOrdersDao;
import model.BookOrders;
import model.Member;

public class BookOrdersDaoImpl extends BaseDao implements BookOrdersDao {
    @Override
    public void insert(Connection conn, BookOrders orders) {
            String sql = "insert into bookorders (orderId,userid, usepoint, finalamount,newpoint, ordertime, employeesid)values(?,?,?,?,?,?,?)";
            update(conn, sql, orders.getOrderId(),orders.getUserId(),orders.getUsePoint(),orders.getFinalAmount(),orders.getNewPoint(),orders.getOrderTime(),orders.getEmployeesId());
        }

    @Override
    public void deleteById(Connection conn, Integer id) {
        String sql = "delete from bookorders where id = ?";
        update(conn, sql, id);
    }

    @Override
    public void deleteById(Connection conn, String orderId) {
        String sql = "delete from bookorders where orderId = ?";
        update(conn, sql, orderId);
    }

	@Override
	public List<BookOrders> getlastid(Connection conn) {
		List<BookOrders> book = new ArrayList<>();
		try {
			String sql = "select id, orderid,userid,usepoint,finalamount,newpoint,ordertime,employeesid from bookorders order by id Desc Limit 1";
			PreparedStatement ps= conn.prepareStatement(sql);
			ResultSet resultSet = ps.executeQuery();
			if (resultSet.next()) {//next() 判斷結果級的下一條是否有數據，如果有數據返回true,且指針下移
			    //如果沒有，返回false
			    //獲取當前數據各字段值
				Integer id = resultSet.getInt(1);
			    String ordersid = resultSet.getString(2);
			    String userid = resultSet.getString(3);
			    Integer usepoint = resultSet.getInt(4);
			    Integer finalamount = resultSet.getInt(5);
			    Integer newpoint = resultSet.getInt(6);
			   
			    Timestamp orderTime =resultSet.getTimestamp(7);
			    LocalDateTime localDateTime = ((Timestamp) orderTime).toLocalDateTime();
			    
			    String employeesid = resultSet.getString(8);
			   BookOrders bookOrders = new BookOrders(id, ordersid, userid, usepoint,finalamount,newpoint,localDateTime,employeesid);
			   book.add(bookOrders);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return book;
	}
	
    public List<BookOrders> getAll(Connection conn) {
        String sql = "select id,orderId,userId,usePoint,finalAmount,newPoint,orderTime,employeesId from bookorders";
        List<BookOrders> orderlist = getForList(conn, BookOrders.class, sql);
        return orderlist;
    }
    
    public List<BookOrders> getAllbyuserId(Connection conn, String userId) {
        String sql = "select id,orderId,userId,usePoint,finalAmount,newPoint,orderTime,employeesId from bookorders where userId=?";
        List<BookOrders> orderlist = getForList(conn, BookOrders.class, sql, userId);
        return orderlist;
    }
	
////  有異常待確認....
//    @Override
//    public BookOrders getBookOrderId(Connection conn) {
//        String sql = "select id, orderId,userId,usePoint,finalAmount,newPoint,orderTime,employeesId from bookorders order by id Desc Limit 1";
//        BookOrders bookOrders = getInstance(conn, BookOrders.class, sql);
//        return bookOrders;      
//    }

}