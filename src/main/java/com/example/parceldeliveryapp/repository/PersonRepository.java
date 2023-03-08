package com.example.parceldeliveryapp.repository;


import com.example.parceldeliveryapp.model.Person;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PersonRepository extends CrudRepository<Person, Integer> {

    @Override
    Optional<Person> findById(Integer id);

    Optional<Person> findByEmail(String email);

    Optional<Person> findByEmailAndPassword(String email, String password);

    Optional<Person> findByAccessMode(Integer accessMode);

    @Query("SELECT max(u.id) FROM Person u")
    Integer findMaxId();
}
