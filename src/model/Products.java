package model;

import java.sql.Date;
import java.io.Serializable;


public class Products implements Serializable {
    private Integer id;
    private String isbn;
    private String bookName;
    private String bookNameEng;
    private String author;
    private String originalAuthor;
    private String translated;
    private String publisher;
    private Date publicationDate;
    private String language;
    private Integer listPrice;
    private Integer bestOffer;
    private String description;


    public Products() {
    }

    public Products(String isbn, String bookName, String bookNameEng, String author, String originalAuthor, String translated, String publisher, Date publicationDate, String language, Integer listPrice, Integer bestOffer, String description) {
        this.isbn = isbn;
        this.bookName = bookName;
        this.bookNameEng = bookNameEng;
        this.author = author;
        this.originalAuthor = originalAuthor;
        this.translated = translated;
        this.publisher = publisher;
        this.publicationDate = publicationDate;
        this.language = language;
        this.listPrice = listPrice;
        this.bestOffer = bestOffer;
        this.description = description;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getBookNameEng() {
        return bookNameEng;
    }

    public void setBookNameEng(String bookNameEng) {
        this.bookNameEng = bookNameEng;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getOriginalAuthor() {
        return originalAuthor;
    }

    public void setOriginalAuthor(String originalAuthor) {
        this.originalAuthor = originalAuthor;
    }

    public String getTranslated() {
        return translated;
    }

    public void setTranslated(String translated) {
        this.translated = translated;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public Date getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(Date publicationDate) {
        this.publicationDate = publicationDate;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public Integer getListPrice() {
        return listPrice;
    }

    public void setListPrice(Integer listPrice) {
        this.listPrice = listPrice;
    }

    public Integer getBestOffer() {
        return bestOffer;
    }

    public void setBestOffer(Integer bestOffer) {
        this.bestOffer = bestOffer;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Products{" +
                "isbn='" + isbn + '\'' +
                ", bookName='" + bookName + '\'' +
                ", bookNameEng='" + bookNameEng + '\'' +
                ", author='" + author + '\'' +
                ", originalAuthor='" + originalAuthor + '\'' +
                ", translated='" + translated + '\'' +
                ", publisher='" + publisher + '\'' +
                ", publicationDate=" + publicationDate +
                ", language='" + language + '\'' +
                ", listPrice=" + listPrice +
                ", bestOffer=" + bestOffer +
                ", description='" + description + '\'' +
                '}';
    }
}
