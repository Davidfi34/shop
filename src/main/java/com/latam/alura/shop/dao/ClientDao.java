package com.latam.alura.shop.dao;


import com.latam.alura.shop.model.Client;
import com.latam.alura.shop.model.Order;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.util.List;

/**
 * ClientDao allows to save the operations in the database
 */
public class ClientDao {

    private EntityManager em;

    public ClientDao(EntityManager em){
        this.em = em;
    }

    /**
     * save client
     * @param client
     */
    public void save(Client client){
        this.em.persist(client);
    }

    /**
     * Get all client
     * @return List<Client>
     */
    public List<Client> getAll(){
        String jpql = "SELECT C FROM Client AS C";
        return em.createQuery(jpql, Client.class).getResultList();
    }

    /**
     * get Client by id
     * @param id
     * @return Client
     */
    public Client getById(Long id){
        return em.find(Client.class, id);
    }


    /**
     * get Client by clinet name
     * @param name
     * @return List<Clinet>
     */
    public List<Client>getByName(String name){
        String jpql = "SELECT C FROM Client AS C WHERE P.name =:name";
        return em.createQuery(jpql, Client.class).setParameter("name",name).getResultList();
    }





    /**
     * update client
     * @param client
     */
    public void update(Client client){
        this.em.merge(client);
    }

    /**
     * delete client
     * @param client
     *
     */
    public void delete(Client client){
        client = this.em.merge(client);
        this.em.remove(client);
    }
}
