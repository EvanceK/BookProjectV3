package model;

public class BookOrdersDetail {
    private Integer id;
    private String  bookOrdersId;
    private String  bookName;
    private Integer  bookPrice;
    private Integer  bookAmount;

    public BookOrdersDetail() {
    }

    public BookOrdersDetail(Integer id, String bookOrdersId, String bookName, Integer bookPrice, Integer bookAmount) {
        this.id = id;
        this.bookOrdersId = bookOrdersId;
        this.bookName = bookName;
        this.bookPrice = bookPrice;
        this.bookAmount = bookAmount;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBookOrdersId() {
        return bookOrdersId;
    }

    public void setBookOrdersId(String bookOrdersId) {
        this.bookOrdersId = bookOrdersId;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public Integer getBookPrice() {
        return bookPrice;
    }

    public void setBookPrice(Integer bookPrice) {
        this.bookPrice = bookPrice;
    }

    public Integer getBookAmount() {
        return bookAmount;
    }

    public void setBookAmount(Integer bookAmount) {
        this.bookAmount = bookAmount;
    }
}