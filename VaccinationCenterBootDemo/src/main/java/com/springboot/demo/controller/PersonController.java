package com.springboot.demo.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.demo.helper.ApiResponse;
import com.springboot.demo.model.PersonDto;
import com.springboot.demo.services.PersonService;

@RestController
@RequestMapping("/person")
public class PersonController {
	
	@Autowired
	private PersonService personService;
	
	Logger logger = LoggerFactory.getLogger(PersonController.class);
	
	@PostMapping("/create")
	public ResponseEntity<PersonDto> createPerson(@RequestBody PersonDto personDto){
		PersonDto createPerson = this.personService.createPerson(personDto);
		
		logger.info("Person Created");
		
		return new ResponseEntity<PersonDto>(createPerson,HttpStatus.CREATED);
	}
	
	@GetMapping("/single/{mobile_no}")
	public ResponseEntity<PersonDto> getPerson(@PathVariable Integer mobile_no,@RequestBody PersonDto personDto){
		PersonDto person = this.personService.getPerson(mobile_no, personDto);
		
		logger.info("Getting single person");
		
		return new ResponseEntity<PersonDto>(person,HttpStatus.OK);
	}
	
	@GetMapping("/list")
	public ResponseEntity<List<PersonDto>> getAllPersons(){
		List<PersonDto> listOfPersons = this.personService.listOfPersons();
		
		return new ResponseEntity<List<PersonDto>>(listOfPersons,HttpStatus.OK);
	}
	
	@PutMapping("/update/{mobile_no}")
	public ResponseEntity<PersonDto> updatePerson(@PathVariable Integer mobile_no,@RequestBody PersonDto personDto){
		PersonDto updatedPerson = this.personService.updatePerson(mobile_no, personDto);
		
		return new ResponseEntity<PersonDto>(updatedPerson,HttpStatus.ACCEPTED);
	}
	
	@DeleteMapping("/delete/{mobile_no}")
	public ResponseEntity<ApiResponse> deletePerson(@PathVariable Integer mobile_no){
		
		
		this.personService.deletePerson(mobile_no);
		
		return new ResponseEntity<ApiResponse>(new ApiResponse("UserDeleted Succesfully", true),HttpStatus.OK);
	}
}
