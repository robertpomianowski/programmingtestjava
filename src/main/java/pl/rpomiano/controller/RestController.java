package pl.rpomiano.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.rpomiano.model.Basket;
import pl.rpomiano.model.entity.Book;
import pl.rpomiano.model.request.AddBookRequest;
import pl.rpomiano.repository.BookRepository;
import pl.rpomiano.service.BookList;

/**
 * Created by aaroon on 25/01/2017.
 */
@org.springframework.web.bind.annotation.RestController
public class RestController {

    public static final String APPLICATION_JSON_CHARSET_UTF_8 = "application/json;charset=UTF-8";

    @Autowired
    private BookList bookList;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private Basket basket;

    @RequestMapping(value = "/book", method = RequestMethod.POST, produces = APPLICATION_JSON_CHARSET_UTF_8)
    @ResponseBody
    public boolean addBook(@RequestBody AddBookRequest addBookRequest) {
        Book book = new Book(addBookRequest);
        return bookList.add(book, Integer.valueOf(addBookRequest.getQuantity()));
    }

    @RequestMapping(value = "/book", params = {"search"}, method = RequestMethod.GET, produces = APPLICATION_JSON_CHARSET_UTF_8)
    @ResponseBody
    public Book[] searchBooks(@RequestParam(value = "search") String searchString) {

        return bookList.list(searchString);
    }

    @RequestMapping(value = "/book/buy", params = {"id"}, method = RequestMethod.POST, produces = APPLICATION_JSON_CHARSET_UTF_8)
    @ResponseBody
    public int[] buyBook(@RequestParam Long id) {

        return bookList.buy(bookRepository.findById(id));

    }

    @RequestMapping(value = "/basket", method = RequestMethod.GET, produces = APPLICATION_JSON_CHARSET_UTF_8)
    @ResponseBody
    public Basket getBasket() {
        return basket;
    }


    @RequestMapping(value = "/basket", params = {"id"}, method = RequestMethod.DELETE, produces = APPLICATION_JSON_CHARSET_UTF_8)
    @ResponseBody
    public void removeFromBasket(@RequestParam Long id) throws Exception {
        basket.removeBook(bookRepository.findById(id));
    }
}
