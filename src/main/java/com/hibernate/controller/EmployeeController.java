package com.hibernate.controller;
import java.time.LocalDate;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.hibernate.entity.Employee;
import com.hibernate.services.DatabaseService;


@RestController
@RequestMapping(value = "/employee")
public class EmployeeController {
	
	
	private  DatabaseService service;
	
	@Autowired
	public void setService(DatabaseService service) {
		this.service = service;
	}

	//URL : http://localhost:8080/myApp/employee/saveEmp/Hari/Thanigai/M/2011-10-10
	@RequestMapping(value = "/saveEmp/{name}/{city}/{sex}/{doj}", method = RequestMethod.POST)
	public  void saveEmployee(@PathVariable String name, @PathVariable String city,
			@PathVariable char sex, @PathVariable String doj) {
		Employee emp = new Employee();
		emp.setName(name);
		emp.setCity(city);
		emp.setSex(sex);
		emp.setDoj(LocalDate.parse(doj));
		System.out.println("Saved Employee ID --------------->{}"+service.saveEmployee(emp));
	}
	
	//URL : http://localhost:8080/myApp/employee/persistEmp/Harish/Thanipadi/M/2010-10-10
	@RequestMapping(value = "/persistEmp/{name}/{city}/{sex}/{doj}", method = RequestMethod.POST)
	public  void persistEmployee(@PathVariable String name, @PathVariable String city,
			@PathVariable char sex, @PathVariable String doj) {
		Employee emp = new Employee();
		emp.setName(name);
		emp.setCity(city);
		emp.setSex(sex);
		emp.setDoj(LocalDate.parse(doj));
		System.out.println("Persisted Employee ID --------------->{}"+service.persistEmployee(emp));
	}
	
	//URL : http://localhost:8080/myApp/employee/mergeEmp/9/Tirupathi 3
	@RequestMapping(value = "/mergeEmp/{id}/{city}", method = RequestMethod.PATCH)
	public  void mergeEmployee(@PathVariable int id, @PathVariable String city) {
		service.mergeEmployee(id, city);
	}
	
	//URL : http://localhost:8080/myApp/employee/dirtyCheck/9
	@RequestMapping(value = "/dirtyCheck/{id}", method = RequestMethod.PATCH)
	public  void dirtyCheck(@PathVariable int id) {			
			service.dirtyCheck(id);
		}
}
