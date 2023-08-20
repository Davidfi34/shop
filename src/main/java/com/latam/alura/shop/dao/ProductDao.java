package com.latam.alura.shop.dao;


import com.latam.alura.shop.model.Product;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.math.BigDecimal;
import java.time.LocalDate;
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
        //String jpql = "SELECT P.price FROM Product AS P WHERE P.name = :name";
        //return em.createQuery(jpql, BigDecimal.class).setParameter("name", name).getSingleResult();
        return em.createNamedQuery("Product.getPrice", BigDecimal.class).setParameter("name", name).getSingleResult();
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

    public List<Product>getByParameters(String name, BigDecimal price, LocalDate datetime){
        StringBuilder jpql = new StringBuilder( "SELECT p FROM Product p WHERE 1=1 ");

        if(name!=null && !name.trim().isEmpty()){
            jpql.append("AND p.name =:name");
        }
        if(price!=null && !price.equals(new BigDecimal(0))){
            jpql.append("AND p.price =:price");
        }
        if(datetime!=null){
            jpql.append("AND p.datetime =:datetime");
        }
        TypedQuery<Product> query =  em.createQuery(jpql.toString(),Product.class);

        if(name!=null && !name.trim().isEmpty()){
            query.setParameter("name",name);
        }
        if(price!=null && !price.equals(new BigDecimal(0))){
            query.setParameter("price",price);
        }
        if(datetime!=null){
            query.setParameter("datetime",datetime);
        }
        return query.getResultList();

    }




    public List<Product>getByParametersApiCriteria(String name, BigDecimal price, LocalDate datetime){

        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Product> query = builder.createQuery(Product.class);
        Root<Product> from = query.from(Product.class);

        Predicate filter = builder.and();

        if(name!=null && !name.trim().isEmpty()){
            filter = builder.and(filter,builder.equal(from.get("name"),name));
        }
        if(price!=null && !price.equals(new BigDecimal(0))){
            filter = builder.and(filter,builder.equal(from.get("price"),price));
        }
        if(datetime!=null){
            filter = builder.and(filter,builder.equal(from.get("datetime"),datetime));
        }

        query = query.where(filter);
        return em.createQuery(query).getResultList();




    }







}