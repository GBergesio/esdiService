package esdi.Services.models;
import esdi.Services.enums.OrderType;
import esdi.Services.enums.Priority;
import esdi.Services.enums.Status;
import esdi.Services.models.devices.Device;
import esdi.Services.models.users.Client;
import esdi.Services.models.users.Technician;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "orders")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private long id;

    private int orderNumber;
    private Status status;
    private Priority priority;
    private OrderType orderType;
    private LocalDateTime joinDate;
    private LocalDateTime outDate;
    private String comments;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="client_id")
    private Client client;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="technician_id")
    private Technician technician;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="device_id")
    private Device device;

//    public Order() {}

    public Order(int orderNumber, Status status, Priority priority, OrderType orderType, LocalDateTime joinDate, LocalDateTime outDate, String comments){
        this.orderNumber = orderNumber;
        this.status = status;
        this.priority = priority;
        this.orderType = orderType;
        this.joinDate = joinDate;
        this.outDate = outDate;
        this.comments = comments;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public void setTechnician(Technician technician){
        this.technician = technician;
    }

    public void setDevice(Device device){ this.device = device;}
}