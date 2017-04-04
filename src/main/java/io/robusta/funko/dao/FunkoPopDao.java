package io.robusta.funko.dao;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;

import io.robusta.funko.entities.FunkoPop;
import io.robusta.funko.entities.Universe;

public class FunkoPopDao {

	EntityManager em;

	public FunkoPopDao(EntityManager em) {
		this.em = em;
	}

	public void create(FunkoPop funkopop) {
		em.persist(funkopop);

	}

	public void update(FunkoPop funkopop) {

	}

	public void delete(FunkoPop funkopop) {
		em.remove(funkopop);

	}

	public Optional<FunkoPop> findById(Integer id) {
		FunkoPop f = em.find(FunkoPop.class, id);

		return Optional.ofNullable(f);

	}
	
public List<FunkoPop> find(String name, int start, int quantity) {
	
		
		String query="SELECT f from FunkoPop f WHERE f.name like :name ORDER BY f.name";
		return em.createQuery(query,FunkoPop.class)
		.setParameter("name","%"+name+"%")
		.setFirstResult(start)
		.setMaxResults(quantity)
		.getResultList();
	

	}
}
