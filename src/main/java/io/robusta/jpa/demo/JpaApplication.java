package io.robusta.jpa.demo;

import java.util.List;

import javax.persistence.EntityManager;

import io.robusta.fora.EmFactory;
import io.robusta.jpa.demo.entities.FunkoPop;

public class JpaApplication {

	public static void main(String[] args) {
		System.out.println("ddl complete");
		// We start
		EntityManager em = EmFactory.createEntityManager();
		em.getTransaction().begin();
		System.out.println("  ========== STARTING WORK ======= ");

		 FunkoPop funkoPop = new FunkoPop();
	        funkoPop.setName("Gandalf");
	        funkoPop.setUniverse("LOTR");
	        funkoPop.setWaterproof(true);
	        em.persist(funkoPop);
		
		System.out.println("  ========== COMMIT ======= ");
		em.getTransaction().commit();
		em.close();
		
		System.out.println("  ========== NEW QUERY ======= ");
		
		
		EmFactory.transaction( e -> {
			
			String query = "SELECT f FROM FunkoPop f ";
					
			
			List<FunkoPop> list = 
					e.createQuery(query, FunkoPop.class).getResultList();
			
			System.out.println(list);
			
		});
		
		
		System.out.println(">>>>>>");
		
		
		// needed to close transaction
		EmFactory.getInstance().close();

			
	}
}
