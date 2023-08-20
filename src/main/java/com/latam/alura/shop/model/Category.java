package com.latam.alura.shop.model;


import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "category")
public class Category {

    @EmbeddedId
    private CategoryId categoryId;


    public Category(){}

    public Category(String name) {
        this.categoryId = new CategoryId(name,"456");
    }


    public String getName() {
        return this.categoryId.getName();
    }

    public void setName(String name) {
        this.categoryId.setName(name);
    }
}
