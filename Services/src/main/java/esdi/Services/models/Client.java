package esdi.Services.models;

import esdi.Services.enums.Neighborhood;
import esdi.Services.enums.UserType;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;


@Entity
@Table(name = "users")
@Getter
@Setter
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private long id;

    private String dni;
    private String firstName;
    private String lastName;
    private String address;
    private Neighborhood neighborhood;
    private String phone;
    private String cellphone;
    private String email;
    private String user;
    private String password;
    private UserType userType;

    @OneToMany(mappedBy = "client", fetch = FetchType.EAGER)
    private Set<Order> orders = new HashSet<>();

    public Client(){}

    public Client(String dni, String firstName, String lastName, String address, Neighborhood neighborhood, String phone, String cellphone, String email, String user, String password, UserType userType) {
        this.dni = dni;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.neighborhood = neighborhood;
        this.phone = phone;
        this.cellphone = cellphone;
        this.email = email;
        this.user = user;
        this.password = password;
        this.userType = userType;
    }

    public void addOrder(Order order){
        order.setClient(this);
        orders.add(order);
    }

}


//    public void addPurchase (Purchase purchase){
//        purchase.setClient(this);
//        purchases.add(purchase);
//    }
