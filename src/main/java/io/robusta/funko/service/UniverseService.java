package io.robusta.funko.service;

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import io.robusta.funko.dao.UniverseDao;
import io.robusta.funko.entities.Universe;

import java.util.List;
import java.util.Optional;

@Stateless
public class UniverseService {

    UniverseDao dao;
    @PersistenceContext
    EntityManager em;

    @PostConstruct
    void after(){
        System.out.println("=====================@PostConstruct UniverseService=====================");
        this.dao= new UniverseDao(em);
    }

    public Optional<Universe> findById(int id) {
        return dao.findById(id);

    }


}
