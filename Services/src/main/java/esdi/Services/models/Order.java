package esdi.Services.models;
import esdi.Services.enums.OrderType;
import esdi.Services.enums.Priority;
import esdi.Services.enums.Status;
import esdi.Services.models.users.Client;
import esdi.Services.models.users.Technician;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "orders")
@Getter
@Setter
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

    public Order() {}

    public Order(int orderNumber, Status status, Priority priority, OrderType orderType, LocalDateTime joinDate, LocalDateTime outDate, String comments){
        this.orderNumber = orderNumber;
        this.status = status;
        this.priority = priority;
        this.orderType = orderType;
        this.joinDate = joinDate;
        this.outDate = outDate;
        this.comments = comments;
//        this.technician = technician;
    }

    public void setClient(Client client) {
        this.client = client;
    }


    public void setTechnician(Technician technician){
        this.technician = technician;
    }


}


//public Object process() throws Exception {
//    Object result = doSomething();
//    if (result == null) {
//        throw new Exception("Processing fail. Got a null response");
//    } else {
//        return result;
//    }
//}