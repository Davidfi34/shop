package com.latam.alura.shop.test;

import com.latam.alura.shop.model.Category;
import com.latam.alura.shop.util.JPAUtils;

import javax.persistence.EntityManager;

public class TestDeleteEntity {
    public static void main(String[] args) {

        Category smartphone = new Category("smartphones");
        EntityManager em = JPAUtils.getEntityManger();
        em.getTransaction().begin();

        em.persist(smartphone);

        smartphone.setName("cell");
        em.flush();//synchronizes entity changes with the database before committing the transaction
        em.clear();//unlinks all managed entities and clears the cache, which removes any pending changes without affecting the database.
        smartphone = em.merge(smartphone);
        smartphone.setName("new smartphone");

        em.flush();
        em.clear();
        smartphone = em.merge(smartphone);//change manager status => search registration 'SELECT'
        em.remove(smartphone);//delete
        em.flush();




    }
}
