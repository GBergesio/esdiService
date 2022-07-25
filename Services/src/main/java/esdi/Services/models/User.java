package esdi.Services.models;

import esdi.Services.enums.Neighborhood;
import esdi.Services.enums.UserType;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import javax.persistence.*;

@Entity
@Table(name = "users")
@Getter
@Setter
public class User {

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

    public User(){}

    public User(String dni, String firstName, String lastName, String address, Neighborhood neighborhood, String phone, String cellphone, String email, String user, String password, UserType userType) {
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



}
