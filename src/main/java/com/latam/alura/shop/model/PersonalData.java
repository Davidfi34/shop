package com.latam.alura.shop.model;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class PersonalData implements Serializable {
    private String name;
    private String dni;

    public PersonalData(String name, String dni) {
        this.name = name;
        this.dni = dni;
    }

    public PersonalData() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }
}
