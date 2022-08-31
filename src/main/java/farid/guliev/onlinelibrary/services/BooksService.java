package farid.guliev.onlinelibrary.services;

import farid.guliev.onlinelibrary.models.Book;
import farid.guliev.onlinelibrary.repos.BooksRepo;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.comparator.Comparators;

import java.util.*;

@Service
@Transactional(readOnly = true)
public class BooksService {
    private final BooksRepo booksRepo;

    public BooksService(BooksRepo booksRepo) {
        this.booksRepo = booksRepo;
    }

    public List<Book> findAll(boolean hasSortByYear) {
        if(hasSortByYear)
            return booksRepo.findAll(Sort.by("year"));
        else
            return booksRepo.findAll();
    }

    public List<Book> getWithPagination(int page, boolean hasSortByYear) {
        if(hasSortByYear)
            return booksRepo.findAll(PageRequest.of(page,5,Sort.by("year"))).getContent();
        else
            return booksRepo.findAll(PageRequest.of(page,5)).getContent();
    }

    public Book findOne(int id) {
        return booksRepo.findById(id).stream().findFirst().orElse(null);
    }
    @Transactional
    public void delete(int id) {
        booksRepo.deleteById(id);
    }

    @Transactional
    public void save(Book book) {
        booksRepo.save(book);
    }

    public List<Book> getBooksById(int id) {
        return booksRepo.getBooksByOwner(id);
    }

    public List<Book> findByTitle(String query) {
        return booksRepo.findAllByTitleContainingIgnoreCase(query);
    }

}
