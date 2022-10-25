package esdi.Services.models.users;

import esdi.Services.enums.UserType;
import esdi.Services.models.Comment;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "company")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private long id;

    private String cuit;
    private String name;
    private String email;
    private String phone;
    private String sector;
    private String user;
    private String password;
    private UserType userType;
    private boolean active;

    @OneToMany(mappedBy = "company", fetch = FetchType.EAGER)
    private Set<Staff> staffs = new HashSet<>();

    @OneToMany(mappedBy = "company", fetch = FetchType.EAGER)
    private Set<Client> clients = new HashSet<>();

    public void addStaff(Staff staff){
        staff.setCompany(this);
        staffs.add(staff);
    }

    public void addClient(Client client){
        client.setCompany(this);
        clients.add(client);
    }


}
