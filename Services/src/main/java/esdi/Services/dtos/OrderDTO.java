package esdi.Services.dtos;

import esdi.Services.dtos.devices.DeviceDTO;
import esdi.Services.dtos.devices.DeviceForOrderDTO;
import esdi.Services.enums.OrderType;
import esdi.Services.enums.Priority;
import esdi.Services.enums.Status;
import esdi.Services.models.Order;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class OrderDTO {

    private long id;
    private int orderNumber;
    private Status status;
    private Priority priority;
    private OrderType orderType;
    private LocalDateTime joinDate;
    private LocalDateTime outDate;
    private String comments;
    private DeviceForOrderDTO device;
    private ClientDTO client;

    private TechnicianDTO technician;

}
