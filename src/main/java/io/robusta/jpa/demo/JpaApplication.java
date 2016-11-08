package io.robusta.jpa.demo;

import java.util.List;

import javax.persistence.EntityManager;

import io.robusta.fora.EmFactory;
import io.robusta.jpa.demo.entities.FunkoPop;

public class JpaApplication {

	public static void main(String[] args) {

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
		
		/*
		EmFactory.transaction(e ->{
			
			String query  =" SELECT c.products FROM Caddie c "
					+ "JOIN c.products prods "
					+ "WHERE c.id = 1 AND prods.price > :price";
			
			
			List<Collection> result = e.createQuery(query, Collection.class )
					.setParameter("price", 2f)
					.getResultList();
			System.out.println(">>>>>result");
			System.out.println(result);
				
		});*/
		
		
		
		
		
		
		
		
		
		
	
		
		
	}
}
