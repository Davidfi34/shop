package com.latam.alura.shop.model;


import javax.persistence.*;

@Entity
@Table
public class Category {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id_category;
    private String name;

    public Category(String name) {
        this.name = name;
    }

    public Category(){}

    public Long getId_category() {
        return id_category;
    }

    public void setId_category(Long id_category) {
        this.id_category = id_category;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
