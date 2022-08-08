package com.springboot.demo.services;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.demo.entities.Person;
import com.springboot.demo.exceptions.ResourceNotFoundException;
import com.springboot.demo.model.PersonDto;
import com.springboot.demo.repository.PersonRepository;

@Service
public class PersonServiceImpl implements PersonService{
	
	@Autowired
	private PersonRepository personRepository;
	
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public PersonDto createPerson(PersonDto personDto) {
		Person person = this.modelMapper.map(personDto, Person.class);
		
		 Person save = this.personRepository.save(person);
		 
		 return this.modelMapper.map(save, PersonDto.class);
	}

	@Override
	public PersonDto getPerson(Integer mobile_no, PersonDto personDto) {
		 Person person = this.personRepository.findById(mobile_no).orElseThrow(()->new ResourceNotFoundException("Person", "PersonId", mobile_no));
		 
		 return this.modelMapper.map(person, PersonDto.class);
	}
	

	@Override
	public List<PersonDto> listOfPersons() {
		List<Person> allPersons = (List<Person>) this.personRepository.findAll();
		
//		List<PersonDto> personDtos = new ArrayList<PersonDto>();
//		
//		for (Person person : allPersons) {
//			PersonDto personDto = this.modelMapper.map(person, PersonDto.class);
//			
//			personDtos.add(personDto);
//		}
//		
//		return personDtos;
		
		List<PersonDto> dtoList = allPersons.stream().map(e->this.modelMapper.map(e, PersonDto.class)).collect(Collectors.toList());
	
		return dtoList;
	}

	@Override
	public PersonDto updatePerson(Integer mobile_no,PersonDto personDto) {
		Person person = this.personRepository.findById(mobile_no).orElseThrow(()->new ResourceNotFoundException("Person", "PersonId", mobile_no));
	
		person.setDOB(personDto.getDOB());
		person.setName(personDto.getName());
		person.setMobile_no(personDto.getMobile_no());

		Person updatedPerson = this.personRepository.save(person);
		
		return this.modelMapper.map(updatedPerson, PersonDto.class);
	}

	@Override
	public void deletePerson(Integer mobile_no) {
		Person deleting = this.personRepository.findById(mobile_no).orElseThrow(()->new ResourceNotFoundException("Person", "PersonId", mobile_no));
	
		this.personRepository.delete(deleting);
	}

}
