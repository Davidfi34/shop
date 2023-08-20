package com.latam.alura.shop.model;


import javax.persistence.*;

@Entity
@Table(name="client")
public class Client {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @Embedded //delegates personal data to the class PersonalData
    private PersonalData personalData;


    public Client(String name, String dni) {
        this.personalData = new PersonalData(name,dni);
    }

    public Client(){}

    public String getName() {
        return personalData.getName();
    }

    public void setName(String name) {
        this.personalData.setName(name);
    }

    public String getDni() {
        return personalData.getDni();
    }

    public void setDni(String dni) {
        this.personalData.setDni(dni);
    }

}
