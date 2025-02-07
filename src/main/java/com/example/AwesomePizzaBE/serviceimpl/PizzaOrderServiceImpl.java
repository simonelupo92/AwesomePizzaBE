package com.example.AwesomePizzaBE.serviceimpl;

import com.example.AwesomePizzaBE.dao.PizzaOrderRepo;
import com.example.AwesomePizzaBE.dto.PizzaOrderDTO;
import com.example.AwesomePizzaBE.entity.OrderStatus;
import com.example.AwesomePizzaBE.entity.PizzaOrder;
import com.example.AwesomePizzaBE.exceptions.OrderNotFoundException;
import com.example.AwesomePizzaBE.iservice.PizzaOrderIService;
import com.example.AwesomePizzaBE.mapper.PizzaOrderMapper;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.*;

@Service
public class PizzaOrderServiceImpl implements PizzaOrderIService {
    @Autowired
    PizzaOrderRepo orderRepo;

    @Autowired
    PizzaOrderMapper orderMapper;

    @Override
    @Transactional
    public PizzaOrderDTO createOrder() {
        //String orderCode = RandomStringUtils.randomAlphanumeric(16);
        //PizzaOrder order = new PizzaOrder(orderCode, OrderStatus.PENDING, );

        PizzaOrder order = new PizzaOrder(UUID.randomUUID().toString(), OrderStatus.PENDING);
        return orderMapper.convertFromEntity(orderRepo.save(order));
    }

    @Override
    @Transactional
    public PizzaOrderDTO nextOrder() {
        if(!orderRepo.findByStatus(OrderStatus.IN_PROGRESS).isEmpty()) {
            throw new IllegalStateException("L'ordine è in fase di lavorazione.");
        }
        List<PizzaOrder> pendingOrders = orderRepo.findByStatus(OrderStatus.PENDING);
        if (pendingOrders.isEmpty()) {
            throw new NoSuchElementException("Nessun ordine in coda.");
        }
        PizzaOrder order = pendingOrders.get(0);
        order.setStatus(OrderStatus.IN_PROGRESS);
        order.setUpdatedIl(LocalDateTime.now());
        return orderMapper.convertFromEntity(orderRepo.save(order));
    }

    @Override
    @Transactional(rollbackOn = {OrderNotFoundException.class, SQLException.class})
    public PizzaOrderDTO completeOrder(String orderCode) throws OrderNotFoundException {
        PizzaOrder order = orderRepo.findByOrderCode(orderCode)
                .orElseThrow(OrderNotFoundException::new);
        if(order.getStatus() != OrderStatus.IN_PROGRESS) {
            throw new IllegalStateException("L'ordine non è in lavorazione.");
        }
        order.setStatus(OrderStatus.COMPLETED);
        order.setUpdatedIl(LocalDateTime.now());
        return orderMapper.convertFromEntity(orderRepo.save(order));
    }

    @Override
    public List<PizzaOrderDTO> getAll(){
        List<PizzaOrder> orders = orderRepo.findAll();
        List<PizzaOrderDTO> ordersDTO = new ArrayList<>();
        for(PizzaOrder po : orders){
            ordersDTO.add(orderMapper.convertFromEntity(po));
        }
        return ordersDTO;
    }

    @Override
    public PizzaOrderDTO getOrder(String orderCode) throws OrderNotFoundException {
        return orderMapper.convertFromEntity(orderRepo.findByOrderCode(orderCode).orElseThrow(OrderNotFoundException::new));
    }
}
