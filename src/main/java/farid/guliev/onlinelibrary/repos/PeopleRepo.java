package farid.guliev.onlinelibrary.repos;

import farid.guliev.onlinelibrary.models.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PeopleRepo extends JpaRepository<Person, Integer> {
    List<Person> findByName(String name);
    List<Person> findByNameOrderByAge(String name);
}
