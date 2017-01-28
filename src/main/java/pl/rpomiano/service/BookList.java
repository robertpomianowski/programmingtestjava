package pl.rpomiano.service;

import org.springframework.stereotype.Service;
import pl.rpomiano.model.entity.Book;

/**
 * Created by aaroon on 25/01/2017.
 */
@Service
public interface BookList {


    Book[] list(String searchString);
    boolean add(Book book, int quantity);
    int[] buy(Book... books);
}
