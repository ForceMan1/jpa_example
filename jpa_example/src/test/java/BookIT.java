
import static org.junit.Assert.*;
import igor.jpa.Book;

import javax.validation.*;
import javax.persistence.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class BookIT {
	private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("chapter04TestPU");
	private static EntityManager em;
	private static EntityTransaction tx;
	
	@BeforeClass
	public static void initEntityManager() throws Exception {
		em = emf.createEntityManager();
		tx = em.getTransaction();
	}
	
	@AfterClass
	public static void closeEntityManager(){
		em.close();
		emf.close(); 
	}
	
	@Test
	public void shouldFindJavaEE7Book() throws Exception {
		Book book = em.find(Book.class, 1001L);
		assertEquals("Beginning Java EE 7", book.getTitle());
	}
	
	@Test
	public void shouldCreateH2G2Book() throws Exception {
		Book book = new Book("H2G2", "The Hitchhiker's Guide to the Galaxy", 12.5F,
				"1-84023-742-2", 354, false);
		tx.begin();
		em.persist(book);
		tx.commit();
		assertNotNull("Id should not be null", book.getId());
		
		book = em.createNamedQuery("findBookH2G2", Book.class).getSingleResult();
		assertEquals("The Hitchhiker's Guide to the Galaxy", book.getDescription());
	}
	
	@Test(expected = ConstraintViolationException.class)
	public void shouldRaiseConstraintViolationExceptionCauseNullTitle(){
		Book book = new Book(null, "Null title", 12.5f, "1-84023-742-2", 354, false);
		em.persist(book);
	}
}
