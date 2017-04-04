package io.robusta.funko.application;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import io.robusta.funko.EmFactory;
import io.robusta.funko.entities.FunkoPop;
import io.robusta.funko.entities.Universe;

public class JpaApplication {

	public static void main(String[] args) {
		System.out.println("ddl complete");
		// We start
		EntityManager em = EmFactory.createEntityManager();
		em.getTransaction().begin();
		System.out.println("  ========== STARTING WORK ======= ");

		Universe lotr = new Universe("LotR");

		FunkoPop funkoPop = new FunkoPop("Gandalf",lotr);

		em.persist(lotr);
		em.persist(funkoPop);

		FunkoPop gandalf = em.find(FunkoPop.class, 1);
		System.out.println("Gandalf ?"+gandalf);
		
		
		System.out.println("  ========== COMMIT ======= ");
		em.getTransaction().commit();
		em.close();

		System.out.println("  ========== NEW QUERY ======= ");

		//FunkoPop gandalf = EmFactory.transaction(e -> e.find(FunkoPop.class, 1));

		// needed to close transaction
		EmFactory.getInstance().close();

	}

}
