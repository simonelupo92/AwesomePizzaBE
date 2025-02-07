package com.example.AwesomePizzaBE.dto;

import com.example.AwesomePizzaBE.entity.OrderStatus;
import lombok.Getter;
import lombok.Setter;
import jakarta.validation.constraints.NotNull;


import java.time.LocalDateTime;

//@Getter
//@Setter
public class PizzaOrderDTO {
    private Integer id;
    @NotNull
    private String orderCode;
    @NotNull
    private OrderStatus status;
    private LocalDateTime creatoIl;
    private LocalDateTime updatedIl;

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
