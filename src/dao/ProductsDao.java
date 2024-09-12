package dao;

import java.sql.Connection;
import java.util.List;

import model.Products;

public interface ProductsDao {

    //員工及管理者專用
    //新增產品
    public abstract void insertProducts(Connection conn, Products book);
    //刪除產品by id
    public abstract void deleteProductsById(Connection conn, Integer id);
    //更新書本資訊
    public abstract void updateProducts(Connection conn, Products book);




    //查詢全部
    public abstract List<Products> getAll(Connection conn);
    //查詢全部 limit6嘗試分頁
    public abstract List<Products> getAllbyLimit6(Connection conn, Integer BOOKS_PER_PAGE, Integer pageNumber);

    //用書名查詢
    public abstract Products getMemberByBookName(Connection conn, String bookName);
    //用作者查詢
    public abstract Products getMemberByUserID(Connection conn, String userID);

    //按價錢排序
    public abstract List<Products> getAllOrderByBestOffer(Connection conn);
    public abstract List<Products> getAllOrderByBestOfferDESC(Connection conn);




}