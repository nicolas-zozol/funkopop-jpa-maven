package io.robusta.funko.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;

import io.robusta.funko.entities.FunkoPop;
import io.robusta.funko.entities.Universe;

public class UniverseDao {


    EntityManager em;

    public UniverseDao(EntityManager em) {
        this.em = em;
    }

    public void create(Universe universe) {
        em.persist(universe);

    }

    public void update(Universe universe) {

    }

    public void delete(Universe universe) {
        em.remove(universe);

    }

    public Optional<Universe> findById(Integer id) {
        Universe u = em.find(Universe.class, id);

        return Optional.ofNullable(u);

    }

    public List<Universe> find(String name, int start, int quantity) {

        String query = "SELECT u from Universe u WHERE u.name like :name ORDER BY u.name";
        return em.createQuery(query, Universe.class)
                .setParameter("name", "%" + name + "%")
                .setFirstResult(start)
                .setMaxResults(quantity)
                .getResultList();


    }

    public List<Universe> findAll() {

        String query = "SELECT u from Universe u  ORDER BY u.name";
        return em.createQuery(query, Universe.class)
                .getResultList();


    }

    public List<FunkoPop> findPops(Universe universe) {

        String jpql = "SELECT p from FunkoPop p where p.universe = :universe";

        return em.createQuery(jpql, FunkoPop.class)
                .setParameter("universe", universe)
                .getResultList();
    }

    public HashMap<Universe, List<FunkoPop>> findUniverseAndPops(String name) {

        String jpql = "SELECT p from FunkoPop p JOIN FETCH p.universe " +
                "where p.universe.name LIKE :name";

        List<FunkoPop>pops =  em.createQuery(jpql, FunkoPop.class)
                .setParameter("name", "%" + name + "%")
                .getResultList();

        System.out.println(pops);

        HashMap<Universe, List<FunkoPop>> map = new HashMap<>();

        for(FunkoPop pop : pops){
            List<FunkoPop> resultPops = map.get(pop.getUniverse());
            if (resultPops == null){
                resultPops = new ArrayList<>();
            }
            map.put(pop.getUniverse(), resultPops);
            resultPops.add(pop);
        }
        return map;
    }

}
