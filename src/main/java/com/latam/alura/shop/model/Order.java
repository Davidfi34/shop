package com.latam.alura.shop.model;


import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="orders")
public class Order {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    private LocalDate datetime = LocalDate.now();
    private BigDecimal total = new BigDecimal(0);
    @ManyToOne(fetch = FetchType.LAZY)
    private Client client;
    @OneToMany(mappedBy = "order",cascade = CascadeType.ALL)
    private List<OrderItems> items = new ArrayList<>() ;


    public Order() {}

    public Order(Client client) {
        this.client = client;
    }

    public void addItem(OrderItems item){
        item.setOrder(this);
        this.items.add(item);
        this.total = this.total.add(item.getValue());
    }

    public Long getId() {
        return id;
    }

    public LocalDate getDatetime() {
        return datetime;
    }

    public void setDatetime(LocalDate datetime) {
        this.datetime = datetime;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public List<OrderItems> getItems() {
        return items;
    }

    public void setItems(List<OrderItems> items) {
        this.items = items;
    }
}
