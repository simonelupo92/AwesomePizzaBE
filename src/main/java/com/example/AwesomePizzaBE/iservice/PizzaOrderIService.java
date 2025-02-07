package com.example.AwesomePizzaBE.iservice;

import com.example.AwesomePizzaBE.dto.PizzaOrderDTO;
import com.example.AwesomePizzaBE.exceptions.OrderNotFoundException;

import java.util.List;

public interface PizzaOrderIService {
    public PizzaOrderDTO createOrder();
    public PizzaOrderDTO getOrder(String orderCode) throws OrderNotFoundException;
    PizzaOrderDTO nextOrder();
    PizzaOrderDTO completeOrder(String code) throws OrderNotFoundException;
    List<PizzaOrderDTO> getAll();
}
