package com.hibernate.services;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hibernate.entity.Employee;

@Service
public class DatabaseService {

	@Autowired
	private SessionFactory sessionFactory;
	
	public int saveEmployee(Employee employee){
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		int id = (int) session.save(employee);
		transaction.commit();
		session.close();
		return id;
		
	}
	
	public int persistEmployee(Employee employee){
		Session session = sessionFactory.getCurrentSession();
		Transaction transaction = session.beginTransaction();
		session.persist(employee);
		Employee emp = (Employee) session.get(Employee.class, employee.getId());
		transaction.commit();
		session.close();
		return emp.getId();
		
	}
}
