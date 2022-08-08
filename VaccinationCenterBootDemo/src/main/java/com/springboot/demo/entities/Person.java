package com.springboot.demo.entities;


import java.sql.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Person {

	private String name;
	
	private Date DOB;

	@Id
	private int mobile_no;
	@OneToMany(mappedBy = "person",fetch = FetchType.EAGER)
	private List<Address> addresses;
	
	

}
