package io.robusta.funko.service;

import io.robusta.funko.entities.FunkoPop;
import io.robusta.funko.entities.Universe;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Created by Cleymobil on 05/04/2017.
 */

@Startup
@Singleton
public class StartUpService {

    @PersistenceContext
    EntityManager em;

    @PostConstruct
    void after() {
        System.out.println("====================AFTER STARTUP=======================");
        createData();

    }


    void createData(){

        Universe marvel = new Universe("Marvel");
        FunkoPop loki = new FunkoPop("Loki",marvel);
        FunkoPop hulk = new FunkoPop("Hulk",marvel);
        FunkoPop thor = new FunkoPop("Thor",marvel);


        Universe starWars = new Universe("Star Wars");
        FunkoPop jabba = new FunkoPop("Jabba",starWars);
        FunkoPop boba = new FunkoPop("Boba Fett",starWars);

        em.persist(marvel);
        em.persist(starWars);
        em.persist(loki);
        em.persist(hulk);
        em.persist(thor);
        em.persist(jabba);
        em.persist(boba);


    }
}
