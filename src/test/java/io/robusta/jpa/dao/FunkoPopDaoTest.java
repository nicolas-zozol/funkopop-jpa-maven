package io.robusta.jpa.dao;

import static org.junit.Assert.*;

import javax.persistence.EntityManager;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import io.robusta.jpa.EmFactory;
import io.robusta.jpa.entities.HasId;
import io.robusta.jpa.IntegrationTest;
import io.robusta.jpa.entities.FunkoPop;
import io.robusta.jpa.entities.Universe;

@Category(IntegrationTest.class)
public class FunkoPopDaoTest {
	
	Universe lotr;
	Universe starWars;
	Universe starTrek;
	FunkoPop gandalf;
	FunkoPop aragorn;
	FunkoPop yoda;
	FunkoPop spock;
	
	@Before
	public void start(){
		System.out.println("Running @Before");
		lotr = new Universe("LOTR");
		starWars = new Universe("Star Wars");
		starTrek = new Universe("Star Trek");
		gandalf = new FunkoPop("Gandalf", lotr);
		aragorn = new FunkoPop("Aragorn", lotr);
		yoda = new FunkoPop("Yoda", starWars);
		spock = new FunkoPop("Spock", starTrek);
		
		EmFactory.voidTransaction(em->{
			em.persist(lotr);
			em.persist(starWars);
			em.persist(starTrek);
		});
	}
	
	@After
	public void clean(){
		cleanIfPresent(gandalf, aragorn, yoda, spock);
		removeDetached(Universe.class, lotr, starTrek, starWars);
		
		
	}
	
	void cleanIfPresent(FunkoPop... pops){
		EmFactory.voidTransaction(em->{
			for (FunkoPop pop : pops){
				cleanIfPresent(em, pop);
			}	
		});
		
	}
	
	void createGandalf(){
		final FunkoPop g = gandalf;
		EmFactory.voidTransaction(em->{
			new FunkoPopDao().createOrUpdate(g);
		});
	}
	

	final <T extends HasId>  void removeDetached( Class<T> clazz, HasId... entities){
		
		EmFactory.voidTransaction(em->{
			for (HasId entity : entities){
				entity = em.find(clazz, entity.getId());
				em.remove(entity);
			}
		});
	}
	
	
	
	void cleanIfPresent(EntityManager em, FunkoPop pop){
		if (pop.getId()!=null &&
				em.find(FunkoPop.class, pop.getId())!=null){
			em.remove(pop);
			fail("Funkopop "+pop+" should have been cleared");
		}
	}

	@Test
	public void testFindFunkoPopByIdInt() {
		System.out.println("starting");
		//assertNull(new FunkoPopDao().findFunkoPopById(15865));
		createGandalf();
		assertNotNull(new FunkoPopDao().findFunkoPopById(gandalf.getId()));
		removeDetached(FunkoPop.class, gandalf);
		System.out.println("found completed");
	}
/*
	@Test
	public void testFindFunkoPopByIdEntityManagerInt() {
		fail("Not yet implemented");
	}

	@Test
	public void testFindAll() {
		fail("Not yet implemented");
	}

	@Test
	public void testDelete() {
		fail("Not yet implemented");
	}

	@Test
	public void testCreateOrUpdate() {
		fail("Not yet implemented");
	}

	@Test
	public void testSearch() {
		fail("Not yet implemented");
	}
*/
}
