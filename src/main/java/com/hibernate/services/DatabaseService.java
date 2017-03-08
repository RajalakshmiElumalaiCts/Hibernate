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
	//URL :  http://localhost:8080/myApp/employee/mergeEmp/9/Tirupathi 3
	public void mergeEmployee(int id, String city){
		
		Session session1 = sessionFactory.openSession();
		Employee emp1 = (Employee) session1.get(Employee.class, id);
		session1.close();
		emp1.setCity(city);
	
		Session session2 = sessionFactory.openSession();
		Transaction transaction2 = session2.beginTransaction();
		Employee emp2 = (Employee) session2.get(Employee.class, emp1.getId());	
		emp2.setCity("my city 2");
		session2.merge(emp1); // updates city with value which is in 'emp1' object
		//session2.update(emp1); // throws exception, as session2 already has object 'emp2' similar to 'emp1'
		transaction2.commit();
		session2.close();
		
	}

	public void dirtyCheck(int id) {
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		
		Employee emp = (Employee) session.get(Employee.class, id);	
		emp.setCity("dirty check city 1");
		//session.save(emp); - updates the same record, not creating new record
		transaction.commit(); // need not to call save() / update() method for dirty check.transaction commit will update
		session.close();
	}
}
