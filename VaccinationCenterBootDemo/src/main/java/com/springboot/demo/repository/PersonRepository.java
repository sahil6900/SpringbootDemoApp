package com.springboot.demo.repository;

import org.springframework.data.repository.CrudRepository;

import com.springboot.demo.entities.Person;

public interface PersonRepository extends CrudRepository<Person, Integer> {

}
