package io.robusta.funko.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

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

public class UniverseDaoTest {

	Universe u = new Universe("Maths");
	EntityManager em;
	UniverseDao dao;
	Universe marvel = new Universe("Marvel");
	FunkoPop loki = new FunkoPop("Loki",marvel);
	FunkoPop hulk = new FunkoPop("Hulk",marvel);
	FunkoPop thor = new FunkoPop("Thor",marvel);

	@Before
	public void setUp() {
		em = EmFactory.createEntityManager();
		dao = new UniverseDao(em);

	}

	@After
	public void tearDown() {
		if (em.isOpen()){
		em.close();}
	}

	@AfterClass
	public static void close() {
		EmFactory.getInstance().close();
	}

	@Test
	public void create() {
		em.getTransaction().begin();
		dao.create(u);

		assertTrue(u.getId() > 0);

		dao.delete(u);

		assertEquals(Optional.empty(), dao.findById(u.getId()));
		em.getTransaction().commit();
	}

	@Test
	public void update() {

		em.getTransaction().begin();
		em.persist(u);

		if (!em.contains(u)) {
			System.out.println("need to merge u");
			em.merge(u);
		}
		u.setName("Litterature");

		boolean found = dao.findById(u.getId()).map(universe -> universe.getName())
				.filter(name -> name.equals("Litterature")).isPresent();

		assertTrue(found);
		em.getTransaction().commit();

	}

	@Test
	public void find() {
		
		Universe physics = new Universe("Physics");
		Universe physiotherapy = new Universe("Physiotherapy");
		Universe physiosanitary = new Universe("Physiosanitary");
		Universe bioPhysics = new Universe("BioPhysics");
		Universe phyleasFog = new Universe("PhyleasFog");
		
		em.getTransaction().begin();
		
		dao.create(physiotherapy);
		dao.create(physiosanitary);
		dao.create(physics);
		dao.create(bioPhysics);
		dao.create(phyleasFog);

		em.getTransaction().commit();
		em.close();
		
		em=EmFactory.createEntityManager();
		dao=new UniverseDao(em);
		em.getTransaction().begin();
		List<Universe> selection = dao.find("Phy",0,3);
		assertTrue(selection.size()==3);
		assertTrue(selection.contains(bioPhysics));
		assertFalse(selection.contains(physiosanitary));
		
		em.getTransaction().commit();
		em.close();


	}

	@Test
	public void findById() {
		em.getTransaction().begin();
		em.persist(u);
		assertTrue(u.getId()>0);

		assertTrue(dao.findById(u.getId()).isPresent());
		
		em.getTransaction().commit();
		
		

	}

	@Test
	public void findPops() {
		em.getTransaction().begin();
		dao.create(marvel);
		em.persist(loki);
		em.persist(hulk);
		em.persist(thor);
		em.getTransaction().commit();
		em.close();
		
		em = EmFactory.createEntityManager();
		dao = new UniverseDao(em);
		em.getTransaction().begin();
		List<FunkoPop> pops= dao.findPops(marvel);
		em.getTransaction().commit();
		em.close();
		
		assertTrue(pops.size()==3);
		assertTrue(pops.contains(hulk));
		
		
		
		
		

	}

}
