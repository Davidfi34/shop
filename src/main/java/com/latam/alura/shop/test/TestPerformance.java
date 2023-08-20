package com.latam.alura.shop.test;

import com.latam.alura.shop.dao.OrderDao;
import com.latam.alura.shop.model.Order;
import com.latam.alura.shop.util.JPAUtils;

import javax.persistence.EntityManager;
import java.io.FileNotFoundException;

public class TestPerformance {

    //Lazy or Eager loading performance ( @ManyToOne or @OneToOne )
    public static void main(String[] args) throws FileNotFoundException {

        LoadRecords.loadRegisters();

        EntityManager em = JPAUtils.getEntityManger();

        OrderDao orderDao = new OrderDao(em);

        Order orderWithClient = orderDao.consultClientOrder(2l);

        em.close();

        System.out.println(orderWithClient.getClient().getName());
    }
}
