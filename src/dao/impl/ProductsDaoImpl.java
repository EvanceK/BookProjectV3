package dao.impl;

import java.sql.Connection;
import java.util.List;

import dao.BaseDao;
import dao.ProductsDao;
import model.Products;

public class ProductsDaoImpl extends BaseDao implements ProductsDao {

    @Override
    public void insertProducts(Connection conn, Products book) {
        String sql = "insert into products(isbn,bookName, bookNameEng,author, originalAuthor, translated,publisher, publicationDate,language,listPrice,bestOffer, description)values(?,?,?,?,?,?,?,?,?,?,?,?)";
        update(conn, sql, book.getIsbn(),book.getBookName(),book.getBookNameEng(),book.getAuthor(),book.getOriginalAuthor()
        ,book.getTranslated(),book.getPublisher(),book.getPublicationDate(),book.getLanguage(),book.getListPrice(),book.getBestOffer(),book.getDescription());
    }

    @Override
    public void deleteProductsById(Connection conn, Integer id) {
        String sql = "delete from products where id = ?";
        update(conn, sql, id);
    }

    @Override
    public void updateProducts(Connection conn, Products book) {
        String sql = "update products set isbn=?,bookName=?,bookNameEng=? ,author=?,originalAuthor=?,translated=?,publisher=?,publicationDate=?,language=?,listPrice=?,bestOffer=?,description=?where id =?";
        update(conn, sql,book.getIsbn(),book.getBookName(),book.getBookNameEng(),book.getAuthor(),book.getOriginalAuthor()
                ,book.getTranslated(),book.getPublisher(),book.getPublicationDate(),book.getLanguage(),book.getListPrice(),book.getBestOffer(),book.getDescription());
    }

    @Override//用於管理者
    public List<Products> getAll(Connection conn) {
        String sql = "SELECT id, isbn,bookName,bookNameEng ,author,originalAuthor,translated,publisher,publicationDate,language,listPrice,bestOffer,description FROM products";
        List<Products> bookList = getForList(conn, Products.class, sql);
        return bookList;
    }

    @Override
    public List<Products> getAllbyLimit6(Connection conn, Integer BOOKS_PER_PAGE, Integer pageNumber) {
        int offset = (pageNumber - 1) * BOOKS_PER_PAGE;
        String sql = "SELECT isbn,bookName,bookNameEng ,author,originalAuthor,translated,publisher,publicationDate,language,listPrice,bestOffer,description FROM products ORDER BY id LIMIT ? OFFSET ?";
        List<Products> bookList = getForList(conn, Products.class, sql, BOOKS_PER_PAGE, offset);
        return bookList;
    }

    @Override
    public Products getMemberByBookName(Connection conn, String bookName) {
        return null;
    }

    @Override
    public Products getMemberByUserID(Connection conn, String userID) {
        return null;
    }

    @Override
    public List<Products> getAllOrderByBestOffer(Connection conn) {
        return null;
    }

    @Override
    public List<Products> getAllOrderByBestOfferDESC(Connection conn) {
        return null;
    }
}
