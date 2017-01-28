package pl.rpomiano.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pl.rpomiano.model.entity.Book;

import java.util.List;

/**
 * Created by aaroon on 25/01/2017.
 */
@Repository
@Transactional(readOnly = true)
public interface BookRepository extends JpaRepository<Book, Long> {

    @Query("from Book b where b.title like concat('%', ?1, '%') or b.author like concat('%', ?1, '%')")
    List<Book> searchBooks(String search);

    Book findById(Long id);



}

