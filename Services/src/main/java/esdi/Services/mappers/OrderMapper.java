package esdi.Services.mappers;

import esdi.Services.common.GenericaMapper;
import esdi.Services.dtos.OrderDTO;
import esdi.Services.models.Order;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public abstract class OrderMapper implements GenericaMapper<OrderDTO, Order> {
}
