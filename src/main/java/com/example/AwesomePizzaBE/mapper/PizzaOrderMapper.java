package com.example.AwesomePizzaBE.mapper;

import com.example.AwesomePizzaBE.dto.PizzaOrderDTO;
import com.example.AwesomePizzaBE.entity.PizzaOrder;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PizzaOrderMapper {
    PizzaOrder convertFromDTO(PizzaOrderDTO dto);
    PizzaOrderDTO convertFromEntity(PizzaOrder entity);
}
