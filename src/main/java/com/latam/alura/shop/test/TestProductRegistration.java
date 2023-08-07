package com.latam.alura.shop.test;

import com.latam.alura.shop.dao.CategoryDao;
import com.latam.alura.shop.dao.ProductDao;
import com.latam.alura.shop.model.Category;
import com.latam.alura.shop.model.Product;
import com.latam.alura.shop.util.JPAUtils;


import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.util.List;

public class TestProductRegistration {

    public static void main(String[] args) {

        RegisterProduct();

        EntityManager em = JPAUtils.getEntityManger();
        ProductDao productDao = new ProductDao(em);

        //get a product by id
        Product product = productDao.getById(1l);
        System.out.printf(product.getName());

        //get all products
        List<Product>products = productDao.getAll();
        products.forEach(prod->System.out.println(prod.getName()));

        //get records by name
        List<Product> productName = productDao.getByName("Samsung");
        productName.forEach(prod-> System.out.printf(prod.getDescription()));


        //for category name
        List<Product> productCategory = productDao.getByNameCategory("smartphone");
        productCategory.forEach(prod-> System.out.printf(prod.getName()));


        //get Price By Name
        //return BigDecimal
        BigDecimal priceProduct = productDao.getPriceByName("Samsung");
        System.out.printf("Price: "+priceProduct);



    }

    private static void RegisterProduct() {
        Category smartphones = new Category("smartphone");
        Product mobile = new Product("Samsung", "S20",new BigDecimal("1050"), smartphones);

        EntityManager em = JPAUtils.getEntityManger();
        ProductDao productDao = new ProductDao(em);

        CategoryDao categoryDao = new CategoryDao(em);

        em.getTransaction().begin();
        categoryDao.save(smartphones);
        productDao.save(mobile);
        em.getTransaction().commit();
        em.close();
    }
}
