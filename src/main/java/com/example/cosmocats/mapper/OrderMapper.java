package com.example.cosmocats.mapper;

import com.example.cosmocats.domain.Order;
import com.example.cosmocats.dto.order.OrderDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OrderMapper {
    OrderDTO toDTO(Order order);
    Order toEntity(OrderDTO orderDTO);
}
