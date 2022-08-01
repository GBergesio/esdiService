package esdi.Services.models;

import esdi.Services.enums.OrderType;
import esdi.Services.enums.Priority;
import esdi.Services.enums.Status;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "order")
@Getter
@Setter
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private long id;

    private int order;
    private Status status;
    private Priority priority;
    private OrderType orderType;
    private LocalDateTime joinDate;
    private LocalDateTime outDate;
    private String comments;

    public Order() {}

    public Order(int order,Status status,Priority priority,OrderType orderType,LocalDateTime joinDate,LocalDateTime outDate,String comments) {
        this.order = order;
        this.status = status;
        this.priority = priority;
        this.orderType = orderType;
        this.joinDate = joinDate;
        this.outDate = outDate;
        this.comments = comments;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "client_id")
    private Client client;



}
