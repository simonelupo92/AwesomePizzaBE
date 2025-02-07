package com.example.AwesomePizzaBE.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor
// @NoArgsConstructor
@Entity
public class PizzaOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(unique = true, nullable = false)
    private String orderCode;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private OrderStatus status;

    private LocalDateTime creatoIl;
    private LocalDateTime updatedIl;

    public PizzaOrder() {
    }
    public PizzaOrder(String orderCode, OrderStatus status) {
        this.orderCode = orderCode;
        this.status = status;
        this.creatoIl = LocalDateTime.now();
        this.updatedIl = LocalDateTime.now();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public LocalDateTime getCreatoIl() {
        return creatoIl;
    }

    public void setCreatoIl(LocalDateTime creatoIl) {
        this.creatoIl = creatoIl;
    }

    public LocalDateTime getUpdatedIl() {
        return updatedIl;
    }

    public void setUpdatedIl(LocalDateTime updatedIl) {
        this.updatedIl = updatedIl;
    }
}
