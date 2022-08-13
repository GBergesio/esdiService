package esdi.Services.services;

import esdi.Services.dtos.OrderDTO;
import esdi.Services.dtos.TechnicianDTO;
import esdi.Services.models.Client;
import esdi.Services.models.Order;
import esdi.Services.models.Technician;

import java.util.List;

public interface OrderService {

    List<OrderDTO>getAllOrders();

    Order getOrderId(Long id);

    Order getOrderByNumber(int orderNumber);

    Order saveOrder(Order order);

//    Order saveChanges(Order order);

    void updateTechnician(Order order, Technician technician);
}
