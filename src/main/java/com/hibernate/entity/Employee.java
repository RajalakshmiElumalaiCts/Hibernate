package com.hibernate.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.hibernate.DateConverter;

import lombok.Data;

@Data
@Entity(name = "employee")
public class Employee {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "city")
	private String city;
	
	@Column(name = "doj")
	@Convert(converter = DateConverter.class)
	private LocalDate doj;
	
	@Column(name = "sex")
	private char sex;
	

}
