package service.impl;

import java.sql.Connection;
import java.util.List;

import dao.impl.BookOrdersDetailDaoImpl;
import model.BookOrdersDetail;
import service.BookOrdersDetailService;

public class BookOrdersDetailServiceImpl implements BookOrdersDetailService {
	
    private static BookOrdersDetailDaoImpl bodoi = new BookOrdersDetailDaoImpl();

    @Override
    public void insert(Connection conn, BookOrdersDetail orders) {
    	bodoi.insert(conn,orders);
    }

    @Override
    public void deleteById(Connection conn, Integer id) {
    	bodoi.deleteById(conn,id);

    }

    @Override
    public void deleteById(Connection conn, String bookOrdersId) {
    	bodoi.deleteById(conn,bookOrdersId);
    }

	@Override
	public void insertAll(Connection conn, List<BookOrdersDetail> details) {
	    // 遍歷傳入的 details 列表
	    for (BookOrdersDetail orders : details) {
	        // 直接插入每一個 BookOrdersDetail
	    	bodoi.insert(conn, orders); // 呼叫插入方法，將每個訂單資料插入資料庫
	    }
	}
}