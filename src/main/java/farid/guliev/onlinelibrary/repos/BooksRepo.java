package farid.guliev.onlinelibrary.repos;

import farid.guliev.onlinelibrary.models.Book;
import farid.guliev.onlinelibrary.models.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BooksRepo extends JpaRepository<Book, Integer> {
    List<Book> getBooksByOwner(int id);
    List<Book> findByTitleLikeIgnoreCase(String query);
    List<Book> findAllByOwner(Person person);
}
