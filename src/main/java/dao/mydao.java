package dao;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import dto.customer;
import dto.task;

public class mydao {
	EntityManagerFactory factory = Persistence.createEntityManagerFactory("dev");
	EntityManager manager = factory.createEntityManager();

	public void saveCustomer(customer customer) {
		manager.getTransaction().begin();
		manager.persist(customer);
		manager.getTransaction().commit();
	}

	public customer findByEmail(String email) {
		List<customer> customers = manager.createQuery("select x from customer x where email=?1").setParameter(1, email).getResultList();
		if (customers.isEmpty())
			return null;
		else
			return customers.get(0);
	}
	public void saveTask(task task) {
		manager.getTransaction().begin();
		manager.persist(task);
		manager.getTransaction().commit();
	}
	public List<task>fetchTasks(int id)
	{
		return manager.createQuery("select x from task x  where customer_id=?1").setParameter(1, id).getResultList();
	}

	public task findById(int id) {
		return manager.find(task.class, id);
	}

	public void updateTask(task task) {
		manager.getTransaction().begin();
		manager.merge(task);
		manager.getTransaction().commit();
	}

	public void deleteTask(task task) {
		manager.getTransaction().begin();
		manager.remove(task);
		manager.getTransaction().commit();
	}

	
	}
   
	


