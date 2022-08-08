package com.springboot.demo.model;

import java.sql.Date;
import java.util.List;

import com.springboot.demo.entities.Address;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PersonDto {
	private String name;

	private Date DOB;
	
	private int mobile_no;
	
}
