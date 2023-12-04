package dao;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import dto.customer;

public class mydao {
	EntityManagerFactory factory = Persistence.createEntityManagerFactory("dev");
	EntityManager manager = factory.createEntityManager();

	public void savecustomer(customer customer) {
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
}

