package com.latam.alura.shop.model;


import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "product")
@NamedQuery(name = "Product.getPrice",query = "SELECT P.price FROM Product AS P WHERE P.name = :name")
@Inheritance(strategy=InheritanceType.JOINED)
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private BigDecimal price;
    private LocalDate datetime = LocalDate.now();
    @ManyToOne(fetch = FetchType.LAZY) //many products for a single category
    private Category category;


    public Product(String name, String description, BigDecimal price, Category category) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.category = category;
    }

    public Product() {}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Category getId_category() {
        return category;
    }

    public void setId_category(Category category) {
        this.category = category;
    }
}