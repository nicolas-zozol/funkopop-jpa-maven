package io.robusta.funko.service;

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import io.robusta.funko.dao.UniverseDao;
import io.robusta.funko.entities.FunkoPop;
import io.robusta.funko.entities.Universe;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Stateless
@Named
public class UniverseService {

    UniverseDao dao;
    @PersistenceContext
    EntityManager em;

    @PostConstruct
    void after(){
        System.out.println("=====================@PostConstruct Find=====================");
        this.dao= new UniverseDao(em);
    }

    public Optional<Universe> findById(int id) {
        return dao.findById(id);

    }

    public List<Universe> findAll() {
        return dao.findAll();
    }

    public List<FunkoPop> findPops(Universe u){
        return dao.findPops(u);
    }


    public List<Universe> find(String ar, int start, int quantity) {
        return dao.find(ar, start, quantity);
    }

    public HashMap<Universe, List<FunkoPop>> findUniverseAndPops(String name){
        return dao.findUniverseAndPops(name);
    }

}
