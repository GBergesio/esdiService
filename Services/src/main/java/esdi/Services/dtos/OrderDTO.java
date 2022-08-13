package esdi.Services.dtos;

import esdi.Services.enums.OrderType;
import esdi.Services.enums.Priority;
import esdi.Services.enums.Status;
import esdi.Services.models.Client;
import esdi.Services.models.Order;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
public class OrderDTO {

    private long id;
    private int orderNumber;
    private Status status;
    private Priority priority;
    private OrderType orderType;
    private LocalDateTime joinDate;
    private LocalDateTime outDate;
    private String comments;

    private ClientDTO clientDTO;

    private TechnicianDTO technicianDTO;

    public OrderDTO() {}

    public OrderDTO(Order order) {
        this.id = order.getId();
        this.orderNumber = order.getOrderNumber();
        this.status = order.getStatus();
        this.priority = order.getPriority();
        this.orderType = order.getOrderType();
        this.joinDate = order.getJoinDate();
        this.outDate = order.getOutDate();
        this.comments = order.getComments();
        this.clientDTO = new ClientDTO(order.getClient());
//        this.technicianDTO = new TechnicianDTO(order.getTechnician());
        //asi lo deja null a todos, probar si despues se puede setear
//        this.technicianDTO = null;
        this.technicia
    }

}
