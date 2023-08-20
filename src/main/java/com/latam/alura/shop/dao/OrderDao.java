package com.latam.alura.shop.dao;


import com.latam.alura.shop.VO.SaleReport;
import com.latam.alura.shop.model.Order;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.util.List;

/**
 * OrderDao allows to save the operations in the database
 */
public class OrderDao {

    private EntityManager em;

    public OrderDao(EntityManager em){
        this.em = em;
    }

    /**
     * save order
     * @param order
     */
    public void save(Order order){
        this.em.persist(order);
    }

    /**
     * Get all order
     * @return List<Order>
     */
    public List<Order> getAll(){
        String jpql = "SELECT Ord FROM Order AS Ord";
        return em.createQuery(jpql, Order.class).getResultList();
    }

    /**
     * get order by id
     * @param id
     * @return Order
     */
    public Order getById(Long id){
        return em.find(Order.class, id);
    }


    /**
     * update order
     * @param order
     */
    public void update(Order order){
        this.em.merge(order);
    }


    /**
     * delete order
     * @param order
     */
    public void delete(Order order){
        order = this.em.merge(order);
        this.em.remove(order);
    }


    public BigDecimal totalValueSale(){
        String jpql = "SELECT SUM(O.total) FROM Order AS O ";
        return em.createQuery(jpql,BigDecimal.class).getSingleResult();

    }



    public List<SaleReport> salesReportVO() {
        String jpql = "SELECT new com.latam.alura.shop.VO.SaleReport(product.name, " +
                "SUM(item.count), " +
                "MAX(O.datetime)) " +
                "FROM Order O " +
                "JOIN O.items item " +
                "JOIN item.product product " +
                "GROUP BY product.name " +
                "ORDER BY SUM(item.count) DESC";

        return em.createQuery(jpql, SaleReport.class).getResultList();
    }

    public Order consultClientOrder(Long id){
        String jpql = "SELECT o FROM Order o JOIN FETCH o.client WHERE o.id=:id";
        return em.createQuery(jpql,Order.class).
                setParameter("id",id).getSingleResult();
    }


}
