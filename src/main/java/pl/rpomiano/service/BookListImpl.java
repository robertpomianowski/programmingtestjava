package pl.rpomiano.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.rpomiano.model.Basket;
import pl.rpomiano.model.entity.Book;
import pl.rpomiano.repository.BookRepository;

import java.util.List;

/**
 * Created by aaroon on 25/01/2017.
 */
@Service
public class BookListImpl implements BookList {

    //buy statuses
    private final static int OK = 0;
    private final static int NOT_IN_STOCK = 1;
    private final static int DOES_NOT_EXIST = 2;

    @Autowired
    private Basket basket;

    @Autowired
    private BookRepository bookRepository;

    public Book[] list(String searchString) {
        List<Book> list = bookRepository.searchBooks(searchString);
        return list.toArray(new Book[list.size()]);
    }

    public boolean add(Book book, int quantity) {
        book.setQuantity(quantity);
        boolean result;
        try {
            bookRepository.save(book);
            result = true;
        } catch (Exception e) {
            result = false;
        }

        return result;
    }

    public int[] buy(Book... books) {

        int[] buyStatuses = new int[books.length];


        for (int i = 0; i < books.length; i++) {

            if (books[i] == null) {
                buyStatuses[i] = DOES_NOT_EXIST;
            } else {
                Integer quantity = bookRepository.findById(books[i].getId()).getQuantity();

                if (quantity > 0) {
                    basket.addBook(books[i]);
                    buyStatuses[i] = OK;
                } else {
                    buyStatuses[i] = NOT_IN_STOCK;
                }
            }

        }
        return buyStatuses;
    }
}
