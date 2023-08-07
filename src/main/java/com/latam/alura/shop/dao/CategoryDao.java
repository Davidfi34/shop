package com.latam.alura.shop.dao;

import com.latam.alura.shop.model.Category;

import javax.persistence.EntityManager;
import java.util.List;

public class CategoryDao {

    private EntityManager em;

    public CategoryDao(EntityManager em){
        this.em = em;
    }

    public void save(Category category){
        this.em.persist(category);
    }

    public List<Category> getAll(){
        String jpql = "SELECT C FROM Category AS C";
        return em.createQuery(jpql,Category.class).getResultList();
    }

    public Category getById(Long id){
        return em.find(Category.class, id);
    }

    public void update(Category category){
        this.em.merge(category);
    }

    public void delete(Category category){
        category = this.em.merge(category);
        this.em.remove(category);
    }
}
