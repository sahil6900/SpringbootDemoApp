package com.springboot.demo.services;

import java.util.List;

import com.springboot.demo.model.AddressDto;

public interface AddressService {
	public AddressDto createAddress(AddressDto addressDto,Integer Person_mobile_no);
	
	public AddressDto getAddress(Integer id,AddressDto addressDto);
	
	public List<AddressDto> listOfAddress();
	
	public AddressDto updateAddress(Integer id,AddressDto addressDto);
	
	public void deleteAddress(Integer id);
	
	public List<AddressDto> getAddressByPerson(Integer Person_mobile_no);
}
