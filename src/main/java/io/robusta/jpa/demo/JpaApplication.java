package io.robusta.jpa.demo;

import javax.persistence.EntityManager;

import io.robusta.fora.EmFactory;
import io.robusta.jpa.demo.entities.Category;
import io.robusta.jpa.demo.entities.Product;

public class JpaApplication {

	public static void main(String[] args) {

		//Category standard
		Product ketchup = new Product("Ketchup", 2.35f);
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
		
		luxe.setParent(csp);
		premium.setParent(csp);
		standard.setParent(csp);
		lowcost.setParent(csp);
		
		

		// We start
		EntityManager em = EmFactory.createEntityManager();
		em.getTransaction().begin();
		System.out.println("  ========== STARTING WORK ======= ");
		
		standard.getProducts().add(ketchup);
		standard.getProducts().add(milk);
		premium.getProducts().add(watch);
		
		em.persist(csp);
		em.persist(luxe);
		em.persist(premium);
		em.persist(standard);
		em.persist(lowcost);
		
		em.persist(ketchup);
		em.persist(milk);
		em.persist(watch);
		
		System.out.println("  ========== COMMIT ======= ");
		em.getTransaction().commit();
		em.close();
		
		System.out.println("  ========== NEW QUERY ======= ");
		em = EmFactory.createEntityManager();
		em.getTransaction().begin();

		Category mergedStandard = em.merge(standard);
		System.out.println(mergedStandard.getProducts());
		
		em.getTransaction().commit();
		em.close();

	
		
		
	}
}
