package com.example.AwesomePizzaBE.dao;

import com.example.AwesomePizzaBE.entity.OrderStatus;
import com.example.AwesomePizzaBE.entity.PizzaOrder;
import com.example.AwesomePizzaBE.exceptions.OrderNotFoundException;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PizzaOrderRepo extends JpaRepository<PizzaOrder, Integer> {
    public List<PizzaOrder> findByStatus(OrderStatus status);
    public Optional<PizzaOrder> findByOrderCode(String code) throws OrderNotFoundException;
}
