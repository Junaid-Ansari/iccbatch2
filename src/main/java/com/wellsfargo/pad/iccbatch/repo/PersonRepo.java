package com.wellsfargo.pad.iccbatch.repo;

import com.wellsfargo.pad.iccbatch.domain.Person;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface PersonRepo extends MongoRepository<Person, String> {

    @Query("{name:'?0'}")
    Person findItemByName(String name);

    /*@Query(value="{firstname:'?0'}", fields="{'firstname' : 1, 'quantity' : 1}")
    List<Person> findAll(String category);
    */
    public long count();

}