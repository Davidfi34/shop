package com.latam.alura.shop.util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JPAUtils {

    private static EntityManagerFactory FACTORY = Persistence.createEntityManagerFactory("shop");

    public static EntityManager getEntityManger(){
        return FACTORY.createEntityManager();
    }
}
