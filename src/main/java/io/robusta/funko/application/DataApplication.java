package io.robusta.funko.application;

import javax.persistence.EntityManager;

import io.robusta.funko.EmFactory;
import io.robusta.funko.entities.FunkoPop;
import io.robusta.funko.entities.Universe;

public class DataApplication {
	
	Universe marvel = new Universe("Marvel");
	FunkoPop loki = new FunkoPop("Loki",marvel);
	FunkoPop hulk = new FunkoPop("Hulk",marvel);
	FunkoPop thor = new FunkoPop("Thor",marvel);
	
	
	Universe starWars = new Universe("Star Wars");
	FunkoPop jabba = new FunkoPop("Jabba",starWars);
	FunkoPop boba = new FunkoPop("Boba Fett",starWars);
	
	public static void main(String[] args) {
		try{
			DataApplication app = new DataApplication();
			app.createData();
			//app.playData();
		}finally{
			EmFactory.getInstance().close();
		}

		
	}
		public void createData(){
			EntityManager em = EmFactory.createEntityManager();
			em.getTransaction().begin();
			System.out.println("  ========== STARTING WORK ======= ");
			
			em.persist(marvel);
			em.persist(starWars);
			em.persist(loki);
			em.persist(hulk);
			em.persist(thor);
			em.persist(jabba);
			em.persist(boba);		
			
			em.getTransaction().commit();
			em.close();
			
		}
		
		public void playData(){
			
			EntityManager em = EmFactory.createEntityManager();
			em.getTransaction().begin();
			System.out.println("  ========== BOX N°2 ======= ");
			
			starWars=em.find(Universe.class, starWars.getId());
			
			em.getTransaction().commit();
			em.close();
			
			
			
		}
		

		
		
		
		
		
		
	

}
