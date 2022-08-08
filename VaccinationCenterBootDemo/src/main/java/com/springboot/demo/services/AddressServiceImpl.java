package com.springboot.demo.services;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.demo.entities.Address;
import com.springboot.demo.entities.Person;
import com.springboot.demo.exceptions.ResourceNotFoundException;
import com.springboot.demo.helper.ApiResponse;
import com.springboot.demo.model.AddressDto;
import com.springboot.demo.repository.AddressRepository;
import com.springboot.demo.repository.PersonRepository;

@Service
public class AddressServiceImpl implements AddressService {

	@Autowired
	private AddressRepository addressRepository;
	
	@Autowired
	private PersonRepository personRepository;
	
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public AddressDto createAddress(AddressDto addressDto,Integer Person_mobile_no) {
		
		Person person= this.personRepository.findById(Person_mobile_no).orElseThrow(()->new ResourceNotFoundException("Person", "PersonId", Person_mobile_no));
	
		Address address= this.modelMapper.map(addressDto, Address.class);
		
		
		address.setPerson(person);
		
		Address createAddress = this.addressRepository.save(address);
		return this.modelMapper.map(createAddress, AddressDto.class);
	}

	@Override
	public AddressDto getAddress(Integer id, AddressDto addressDto) {
		Address address = this.addressRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Address", "AddressId", id));
	
		return this.modelMapper.map(address, AddressDto.class);
		
	}

	@Override
	public List<AddressDto> listOfAddress() {
		List<Address> findAll = this.addressRepository.findAll();
		
		List<AddressDto> collect = findAll.stream().map(e->this.modelMapper.map(e, AddressDto.class)).collect(Collectors.toList());
		
		return collect;
	}

	@Override
	public AddressDto updateAddress(Integer id, AddressDto addressDto) {
		Address address = this.addressRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Address", "AddressId", id));
	
		address.setCity(addressDto.getCity());
		address.setState(addressDto.getState());
		address.setCountry(addressDto.getCountry());
		
		Address address2 = this.addressRepository.save(address);
		
		return this.modelMapper.map(address2, AddressDto.class);
	}

	@Override
	public void deleteAddress(Integer id) {
		Address address = this.addressRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Address", "AddressId", id));
		
		this.addressRepository.delete(address);
	}

	@Override
	public List<AddressDto> getAddressByPerson(Integer Person_mobile_no) {
		Person person = this.personRepository.findById(Person_mobile_no).orElseThrow(()->new ResourceNotFoundException("Person", "PersonId", Person_mobile_no));
		
		List<Address> findByPerson = this.addressRepository.findByPerson(person);
		
		return findByPerson.stream().map(e->this.modelMapper.map(e, AddressDto.class)).collect(Collectors.toList());
		
	}
	
	

}
