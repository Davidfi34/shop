package com.latam.alura.shop.test;

import com.latam.alura.shop.dao.CategoryDao;
import com.latam.alura.shop.dao.ClientDao;
import com.latam.alura.shop.dao.ProductDao;
import com.latam.alura.shop.model.Category;
import com.latam.alura.shop.model.Product;
import com.latam.alura.shop.util.JPAUtils;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.util.List;


public class TestApiCriteria {

    public static void main(String[] args) {
        dataLoading();
        EntityManager em = JPAUtils.getEntityManger();
        ProductDao productDao = new ProductDao(em);

        List<Product> result = productDao.getByParametersApiCriteria("Fifa 2023",null,null);

        System.out.println(result.get(0).getDescription());
        System.out.println(result.get(0).getPrice());

    }


    private static void dataLoading(){
        Category smartphones = new Category("smartphones");
        Category televisions = new Category("televisions");
        Category games = new Category("games");


        Product smartphone = new Product("S20","black color",new BigDecimal(1000),smartphones);
        Product tv = new Product("Lg","Tv 48 p ",new BigDecimal(2000),televisions);
        Product game = new Product("Fifa 2023","for pc ",new BigDecimal(50),games);

        EntityManager em = JPAUtils.getEntityManger();
        ProductDao productDao = new ProductDao(em);
        CategoryDao categoryDao = new CategoryDao(em);

        em.getTransaction().begin();

        categoryDao.save(smartphones);
        categoryDao.save(televisions);
        categoryDao.save(games);

        productDao.save(smartphone);
        productDao.save(tv);
        productDao.save(game);

        em.getTransaction().commit();
        em.close();



    }
}
