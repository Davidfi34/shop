package com.latam.alura.shop.test;

import com.latam.alura.shop.VO.SaleReport;
import com.latam.alura.shop.dao.CategoryDao;
import com.latam.alura.shop.dao.ClientDao;
import com.latam.alura.shop.dao.OrderDao;
import com.latam.alura.shop.dao.ProductDao;
import com.latam.alura.shop.model.*;
import com.latam.alura.shop.util.JPAUtils;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.util.List;

public class TestOrderRegistration {
    public static void main(String[] args) {
        RegisterProduct();

        EntityManager em = JPAUtils.getEntityManger();
        ProductDao productDao = new ProductDao(em);

        Product product = productDao.getById(1l);
        ClientDao clientDao = new ClientDao(em);
        OrderDao orderDao = new OrderDao(em);

        Client client = new Client("Juan", "1234");
        Order order = new Order(client);
        order.addItem(new OrderItems(5,product,order));

        em.getTransaction().begin();//init
        clientDao.save(client);
        orderDao.save(order);
        em.getTransaction().commit();//commit

        BigDecimal totalValueSale = orderDao.totalValueSale();

        System.out.printf("total: "+totalValueSale);


        List<SaleReport> salesReport = orderDao.salesReportVO();
        salesReport.forEach(System.out::println);





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

