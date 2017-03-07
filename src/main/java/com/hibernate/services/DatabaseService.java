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
		
		Session session2 = sessionFactory.openSession();
		employee.setCity("Thanipadi 2");
		Transaction transaction2 = session2.beginTransaction();
		session2.save(employee); // this will update the city of the same record, as long as there is a live session and transaction. 
		//So save() works across the session/ transaction 
		transaction2.commit();
		session2.close();
		
		return id;
		
	}
	
	public int persistEmployee(Employee employee){
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		session.persist(employee);
		Employee emp = (Employee) session.get(Employee.class, employee.getId());
		
		transaction.commit();
		session.close();
		
		/*Session session2 = sessionFactory.openSession();
		Transaction transaction2 = session2.beginTransaction();
		employee.setCity("Chennai 2");
		session2.persist(employee);// this will throw exception, because the object is detached from session1, and you are trying to save it through another session2 which has no idea of session1 objects.
		transaction2.commit();	
		session2.close();*/
		
		return emp.getId();
		
	}
}
