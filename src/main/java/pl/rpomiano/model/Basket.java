package pl.rpomiano.model;

import pl.rpomiano.model.entity.Book;

import java.math.BigDecimal;
import java.util.ArrayList;

/**
 * Created by aaroon on 25/01/2017.
 */

public class Basket {

    public static final String BOOK_DOES_NOT_EXIST = "Book does not exist";

    private ArrayList<Book> bookList;
    private BigDecimal totalPrice;

    public Basket(){
        this.bookList = new ArrayList<Book>();
        this.totalPrice = BigDecimal.ZERO;
    }

    public void addBook(Book book) {
        this.bookList.add(book);
        this.totalPrice = totalPrice.add(book.getPrice());

    }

    public void removeBook(Book book) throws Exception {
        try {
            for (int i = 0 ; i < bookList.size() ; i++) {
                Book member = bookList.get(i);
                if(member.getId().equals(book.getId())) {
                    this.bookList.remove(i);
                    this.totalPrice = totalPrice.subtract(book.getPrice());
                }
            }
        } catch(Exception e) {
            throw new Exception(BOOK_DOES_NOT_EXIST);
        }
    }

    public ArrayList<Book> getBookList() {
        return bookList;
    }

    public void setBookList(ArrayList<Book> bookList) {
        this.bookList = bookList;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }
}
