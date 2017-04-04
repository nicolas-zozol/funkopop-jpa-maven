package io.robusta.funko.dao;

import static org.junit.Assert.*;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;

import io.robusta.funko.EmFactory;
import io.robusta.funko.entities.FunkoPop;
import io.robusta.funko.entities.Universe;

public class FunkoPopDaoTest {

	Universe marvel = new Universe("Marvel");
	FunkoPop loki = new FunkoPop("Loki", marvel);
	FunkoPop hulk = new FunkoPop("Hulk", marvel);
	FunkoPop thor = new FunkoPop("Thor", marvel);
	EntityManager em;
	FunkoPopDao fDao;
	UniverseDao uDao;

	@Before
	public void setUp() {
		em = EmFactory.createEntityManager();
		fDao = new FunkoPopDao(em);
		uDao = new UniverseDao(em);
		uDao.create(marvel);

	}

	@After
	public void tearDown() {
		if (em.isOpen()) {
			em.close();
		}
	}

	@AfterClass
	public static void close() {
		EmFactory.getInstance().close();
	}

	@Test
	public void create() {

		em.getTransaction().begin();

		fDao.create(hulk);

		assertTrue(hulk.getId() > 0);
		
	
		fDao.delete(hulk);
		assertEquals(Optional.empty(), fDao.findById(hulk.getId()));

		em.getTransaction().commit();
	}

	@Test
	public void update() {
		em.getTransaction().begin();

		fDao.create(hulk);

		if (!em.contains(hulk)) {
			System.out.println("need to merge hulk");
			em.merge(hulk);
		}

		hulk.setName("Bioman");

		boolean found = fDao.findById(hulk.getId()).map(funkopop -> funkopop.getName())
				.filter(name -> name.equals("Bioman")).isPresent();

		assertTrue(found);
		em.getTransaction().commit();
	}

	
	@Test
	public void find(){
		em.getTransaction().begin();
		FunkoPop spiderman = new FunkoPop("Spiderman", marvel);
		FunkoPop ironMan = new FunkoPop("Ironman", marvel);
		FunkoPop humanTorch = new FunkoPop("Human Torch", marvel);
		FunkoPop radioactiveMan = new FunkoPop("Radioactiveman", marvel);
		FunkoPop winterSoldier = new FunkoPop("Winter Soldier", marvel);
		
		fDao.create(spiderman);
		fDao.create(ironMan);
		fDao.create(humanTorch);
		fDao.create(radioactiveMan);
		fDao.create(winterSoldier);
		
		em.getTransaction().commit();
		em.close();
		
		em=EmFactory.createEntityManager();
		fDao=new FunkoPopDao(em);
		em.getTransaction().begin();
		
		List<FunkoPop> selection = fDao.find("man", 0, 3);
		
		assertTrue(selection.size()==3);
		assertTrue(selection.contains(ironMan));
		assertFalse(selection.contains(spiderman));
		

		em.getTransaction().commit();		
		
	}
	
	@Test
	public void findById(){
		
		em.getTransaction().begin();
		
		fDao.create(hulk);
		
		assertTrue(fDao.findById(hulk.getId()).isPresent());	
		
	
			
			boolean found =fDao.findById(hulk.getId())
					.filter(f ->f.getId() == hulk.getId())
					.isPresent();
			assertTrue(found);
		
		
		
		em.getTransaction().commit();
		
		
	}
	
}
