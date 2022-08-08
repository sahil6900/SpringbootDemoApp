package com.springboot.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;


import com.springboot.demo.entities.Address;
import com.springboot.demo.entities.Person;

public interface AddressRepository extends JpaRepository<Address, Integer>{
	List<Address> findByPerson(Person person);
}
