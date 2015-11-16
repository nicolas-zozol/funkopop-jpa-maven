package io.robusta.jpa.demo;

import java.util.Collection;
import java.util.List;

import javax.persistence.EntityManager;

import io.robusta.fora.EmFactory;
import io.robusta.jpa.demo.entities.Caddie;
import io.robusta.jpa.demo.entities.Category;
import io.robusta.jpa.demo.entities.Product;

public class JpaApplication {

	public static void main(String[] args) {

		//Category standard
		Product ketchup = new Product("Ketchup Tomato Heinz", 2.35f);
		Product watch = new Product("Watch", 19990f);
		Product milk = new Product("Milk", 1.15f);
		Product cornflakes = new Product("Corn Flakes", 1.55f);
		Product jeans = new Product("Jeans", 20.90f);
		Product shoes = new Product("Shoes", 15.00f);
		Product chair = new Product("Chair", 10.00f);
		Product hat = new Product("Hat", 70f);


		Category csp = new Category("CSP");
		Category luxe = new Category("luxory");
		Category premium = new Category("premium");
		Category standard = new Category("standard");
		Category lowcost = new Category("low-cost");
		Category fooding = new Category("fooding");
		
		luxe.setParent(csp);
		premium.setParent(csp);
		standard.setParent(csp);
		lowcost.setParent(csp);
		
		
		
		
		// We start
		EntityManager em = EmFactory.createEntityManager();
		em.getTransaction().begin();
		System.out.println("  ========== STARTING WORK ======= ");
		
		
		
		ketchup.setCategory(standard);
		milk.setCategory(standard);
		cornflakes.setCategory(standard);
		watch.setCategory(luxe);
		
		
		em.persist(csp);
		em.persist(luxe);
		em.persist(premium);
		em.persist(standard);
		em.persist(lowcost);
		em.persist(fooding);
		em.persist(jeans);
		em.persist(shoes);
		em.persist(chair);
		
		em.persist(ketchup);
		em.persist(milk);
		em.persist(watch);
		em.persist(hat);
		em.persist(cornflakes);
		
		System.out.println("  ========== COMMIT ======= ");
		em.getTransaction().commit();
		em.close();
		
		System.out.println("  ========== NEW QUERY ======= ");
		
		
		EmFactory.transaction( e -> {
			
			String query = "SELECT p FROM Product p";
			
			List<Product> result = e.createQuery(query, Product.class).getResultList();
			System.out.println(result.size());
			System.out.println(result);
			
			System.out.println("  ========== JOIN QUERY ======= ");
			
			String query2 = "SELECT p FROM Product p JOIN p.category c";
			
			result = e.createQuery(query2, Product.class).getResultList();
			
			for (Product p : result){
				System.out.println(p.getCategory());
			}
			
			System.out.println(result.size());
			System.out.println(result);
		
			
			
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
