package com.latam.alura.shop.VO;

import java.time.LocalDate;

public class SaleReport {

    private String name;
    private Long productQuantity;
    private LocalDate lastSaleDate;


    public SaleReport(String name, Long productQuantity, LocalDate lastSaleDate) {
        this.name = name;
        this.productQuantity = productQuantity;
        this.lastSaleDate = lastSaleDate;
    }


    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }

    public Long getProductQuantity() {
        return productQuantity;
    }

    public void setProductQuantity(Long productQuantity) {
        this.productQuantity = productQuantity;
    }

    public LocalDate getLastSaleDate() {
        return lastSaleDate;
    }

    public void setLastSaleDate(LocalDate lastSaleDate) {
        this.lastSaleDate = lastSaleDate;
    }


    @Override
    public String toString() {
        return "SaleReport{" +
                "name='" + name + '\'' +
                ", productQuantity=" + productQuantity +
                ", lastSaleDate=" + lastSaleDate +
                '}';
    }
}
