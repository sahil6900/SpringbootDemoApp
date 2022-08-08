package com.springboot.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.demo.model.AddressDto;
import com.springboot.demo.services.AddressService;

@RestController
@RequestMapping("/addr")
public class AddressController {

	@Autowired
	private AddressService addressService;
	
	@PostMapping("/person/{mobile_no}/address")
	public ResponseEntity<AddressDto> createAddress(@PathVariable Integer mobile_no, @RequestBody AddressDto addressDto){
		AddressDto createAddress = addressService.createAddress(addressDto,mobile_no);
		
		return new ResponseEntity<AddressDto>(createAddress,HttpStatus.CREATED);
	}
	
	@GetMapping("/person/{mobile_no}/address")
	public ResponseEntity<AddressDto> getAddress(@PathVariable Integer id,@RequestBody AddressDto addressDto){
		AddressDto address = this.addressService.getAddress(id, addressDto);
		
		return new ResponseEntity<AddressDto>(address,HttpStatus.OK);
		
		
	}
	
	@GetMapping("/person/{personId}/addr")
	public ResponseEntity<List<AddressDto>> getAddressesByPerson(@PathVariable Integer personId){
		List<AddressDto> addressByPerson = this.addressService.getAddressByPerson(personId);
		
		return new ResponseEntity<List<AddressDto>>(addressByPerson,HttpStatus.OK);
	}
	
	
}
