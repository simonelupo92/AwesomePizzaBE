package com.example.AwesomePizzaBE.restcontroller;

import com.example.AwesomePizzaBE.dto.PizzaOrderDTO;
import com.example.AwesomePizzaBE.exceptions.OrderNotFoundException;
import com.example.AwesomePizzaBE.iservice.PizzaOrderIService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class PizzaOrderController {
    @Autowired
    PizzaOrderIService orderService;

    /***** createOrder() crea un nuovo ordine assegnando un orderCode randomico *****/
    @PostMapping(value = "/save")
    public ResponseEntity<PizzaOrderDTO> createOrder() {
        PizzaOrderDTO order = orderService.createOrder();
        return ResponseEntity.status(HttpStatus.CREATED).body(order);
    }

    /***** getOrder() è una chiamata get che serve per vedere a che punto di lavorazione si trova
     * l'ordine e la data dell'ultimo aggiornamento su quell'ordine *****/
    @GetMapping("/getcode")
    public PizzaOrderDTO getOrder(@RequestParam String orderCode) throws OrderNotFoundException{
        PizzaOrderDTO order = orderService.getOrder(orderCode);
        return order;
    }

    /***** getAll() è una get utilizzata per reperire tutti gli ordini, anche quelli già completati *****/
    @GetMapping("/getall")
    public List<PizzaOrderDTO> getAll() {
        List<PizzaOrderDTO> orders = orderService.getAll();
        return orders;
    }

    /***** takeNextOrder() è utilizzata dal pizzaiolo per prendere in carico l'ordine successivo.
     * Se ha già un ordine in corso di lavorazione non ne può prendere altri *****/
    @PostMapping("/nextorder")
    public ResponseEntity<PizzaOrderDTO> takeNextOrder() {
        PizzaOrderDTO order = orderService.nextOrder();
        return ResponseEntity.ok(order);
    }

    /***** completeOrder(orderCode) è utilizzata per comunicare che un'ordine è stato completato *****/
    @PostMapping("/{orderCode}/complete")
    public ResponseEntity<PizzaOrderDTO> completeOrder(@PathVariable String orderCode) throws OrderNotFoundException {
        PizzaOrderDTO order = orderService.completeOrder(orderCode);
        return ResponseEntity.ok(order);
    }

}
