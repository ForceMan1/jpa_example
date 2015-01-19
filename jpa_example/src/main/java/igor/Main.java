package igor;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import igor.jpa.*;

public class Main {

	public static void main(String[] args) {
		Book book = new Book("H2G2", "The Hitchhiker's Guide to the Galaxy", 12.5F, 
				"1-84023-742-2", 354, false);
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("chapter04PU");
		EntityManager em = emf.createEntityManager();
		
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		em.persist(book);
		tx.commit();
		em.close();
		emf.close();
	}

}
