package esdi.Services.models.users;

import esdi.Services.enums.UserType;
import esdi.Services.models.Order;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "technicians")
@Getter
@Setter
public class Technician {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private long id;

    private String dni;
    private String firstName;
    private String lastName;
    private String email;
    private String user;
    private String password;
    private UserType userType;

    @OneToMany(mappedBy = "technician", fetch = FetchType.EAGER)
    private Set<Order> orders = new HashSet<>();

    public Technician(){}

    public Technician(String dni, String firstName, String lastName, String email, String user, String password, UserType userType) {
            this.dni = dni;
            this.firstName = firstName;
            this.lastName = lastName;
            this.email = email;
            this.user = user;
            this.password = password;
            this.userType = userType;
    }

    public void addOrder(Order order){
        order.setTechnician(this);
        orders.add(order);
    }

}
