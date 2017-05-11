package io.robusta.funko.cdi;

import io.robusta.funko.entities.FunkoPop;
import io.robusta.funko.entities.Universe;
import io.robusta.funko.service.UniverseService;

import javax.ejb.EJB;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

/**
 * Created by Nicolas Zozol on 05/04/2017.
 */
@Named
@RequestScoped
public class FavoritesBean{

    String test = "Testing";
    int index = 0;

    @EJB
    UniverseService universeService;

    public String getTest() {
        index++;
        return test + index;
    }

    public List<Universe> getFavorites() {
        return universeService.find("ar", 0, 5);
    }

    HashMap<Universe, List<FunkoPop>> cache;
    public HashMap<Universe, List<FunkoPop>> getFavoritesFast() {
        System.out.println("========FAST=====");
        if (this.cache == null){
            System.out.println("+++Creating cache");
            this.cache = universeService.findUniverseAndPops("ar");
        }
        return this.cache;
    }


    public List<Universe> getUniversesFast(){
        return new ArrayList<Universe>(this.getFavoritesFast().keySet());
    }

    public List<FunkoPop> getPopsFast(Universe universe){
        return this.getFavoritesFast().get(universe);
    }



    public void setTest(String test) {
        this.test = test;
    }
}
