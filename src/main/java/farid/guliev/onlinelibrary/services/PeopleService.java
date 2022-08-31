package farid.guliev.onlinelibrary.services;

import farid.guliev.onlinelibrary.models.Book;
import farid.guliev.onlinelibrary.models.Person;
import farid.guliev.onlinelibrary.repos.BooksRepo;
import farid.guliev.onlinelibrary.repos.PeopleRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class PeopleService {
    private final PeopleRepo peopleRepo;
    private final BooksRepo booksRepo;

    @Autowired
    public PeopleService(PeopleRepo peopleRepo, BooksRepo booksRepo) {
        this.peopleRepo = peopleRepo;
        this.booksRepo = booksRepo;
    }


    public List<Person> findAll() {
        return peopleRepo.findAll();
    }

    public Person findOne(int id) {
        return peopleRepo.findById(id).stream().findFirst().orElse(null);
    }

    @Transactional
    public void save(Person person) {
        peopleRepo.save(person);
    }

    @Transactional
    public void delete(int id) {
        peopleRepo.deleteById(id);
    }

    public List<Book> getBooksById(int id) {
        return booksRepo.findAllByOwner(findOne(id));
    }
}
