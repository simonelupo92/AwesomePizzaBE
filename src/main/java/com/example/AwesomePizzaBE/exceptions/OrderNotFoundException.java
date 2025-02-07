package com.example.AwesomePizzaBE.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)

public class OrderNotFoundException extends Exception{
    public OrderNotFoundException() {
        super("Ordine non trovato.");
    }
}
