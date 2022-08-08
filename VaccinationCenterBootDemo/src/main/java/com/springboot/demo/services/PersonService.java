package com.springboot.demo.services;

import java.util.List;

import com.springboot.demo.model.PersonDto;

public interface PersonService {
	public PersonDto createPerson(PersonDto personDto);
	
	public PersonDto getPerson(Integer mobile_no,PersonDto personDto);
	
	public List<PersonDto> listOfPersons();
	
	public PersonDto updatePerson(Integer mobile_no,PersonDto personDto);
	
	public void deletePerson(Integer mobile_no);
	
	
}
