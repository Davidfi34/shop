package com.latam.alura.shop.dao;


import com.latam.alura.shop.model.Product;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.util.List;

/**
 * ProductDao allows to save the operations in the database
 */
public class ProductDao {

    private EntityManager em;

    public ProductDao(EntityManager em){
        this.em = em;
    }

    /**
     * save product
     * @param product
     */
    public void save(Product product){
        this.em.persist(product);
    }

    /**
     * Get all products
     * @return List<Product>
     */
    public List<Product> getAll(){
        String jpql = "SELECT P FROM Product AS P";
        return em.createQuery(jpql,Product.class).getResultList();
    }

    /**
     * get product by id
     * @param id
     * @return Product
     */
    public Product getById(Long id){
        return em.find(Product.class, id);
    }


    /**
     * get product by product name
     * @param name
     * @return List<Product>
     */
    public List<Product>getByName(String name){
        String jpql = "SELECT P FROM Product AS P WHERE P.name =:name";
        return em.createQuery(jpql, Product.class).setParameter("name",name).getResultList();
    }

    /**
     * get the products by category name
     * @param name - category
     * @return List<Product>
     */
    public List<Product>getByNameCategory(String name){
        String jpql = "SELECT P FROM Product AS P WHERE P.category.name=:name";
        return em.createQuery(jpql, Product.class).setParameter("name", name).getResultList();
    }

    /**
     * get product price by product name
     * @param name
     * @return BigDecimal - price
     */
    public BigDecimal getPriceByName( String name ){
        String jpql = "SELECT P.price FROM Product AS P WHERE P.name = :name";
        return em.createQuery(jpql, BigDecimal.class).setParameter("name", name).getSingleResult();
    }


    /**
     * update product
     * @param product
     */
    public void update(Product product){
        this.em.merge(product);
    }

    /**
     * delete product
     * @param product
     */
    public void delete(Product product){
        product = this.em.merge(product);
        this.em.remove(product);
    }
}
