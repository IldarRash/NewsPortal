package ru.ilsach.repositories;


import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import ru.ilsach.models.Person;

/**
 * Created by student on 4/6/17.
 */
@RepositoryRestResource(path = "people", collectionResourceRel = "people" )
public interface PersonRepository extends PagingAndSortingRepository<Person, Long> {

    Person findByFirstName(@Param("name") String name);
    Person findByLogin(@Param("login") String login);
}
