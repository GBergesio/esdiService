package esdi.Services.models;
import esdi.Services.enums.OrderType;
import esdi.Services.enums.Priority;
import esdi.Services.enums.Status;
import esdi.Services.models.budgets.Budget;
import esdi.Services.models.devices.Device;
import esdi.Services.models.users.Client;
import esdi.Services.models.users.Staff;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

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
    private String orderDetails;
    private String passwordDevice;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="client_id")
    private Client client;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="staff_id")
    private Staff staff;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="device_id")
    private Device device;

    @OneToMany(mappedBy = "order", fetch = FetchType.EAGER)
    private Set<Comment> comments = new HashSet<>();

    @OneToOne(mappedBy="order")
    private Budget budget;

//    public Order(int orderNumber, Status status, Priority priority, OrderType orderType, LocalDateTime joinDate, LocalDateTime outDate, String orderDetails, String passwordDevice){
//        this.orderNumber = orderNumber;
//        this.status = status;
//        this.priority = priority;
//        this.orderType = orderType;
//        this.joinDate = joinDate;
//        this.outDate = outDate;
//        this.orderDetails = orderDetails;
//        this.passwordDevice = passwordDevice;
//    }

    public void setClient(Client client) {
        this.client = client;
    }

    public void setStaff(Staff staff){
        this.staff = staff;
    }

    public void setDevice(Device device){ this.device = device;}

    public void addComment(Comment comment){
        comment.setOrder(this);
        comments.add(comment);
    }




}