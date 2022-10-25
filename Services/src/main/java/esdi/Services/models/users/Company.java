package esdi.Services.models.users;

import esdi.Services.enums.UserType;
import esdi.Services.models.Comment;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
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

    @OneToMany(mappedBy = "company", fetch = FetchType.LAZY)
    private List<Staff> staffs = new ArrayList<>();

    @OneToMany(mappedBy = "company", fetch = FetchType.LAZY)
    private List<Client> clients = new ArrayList<>();

    public void addStaff(Staff staff){
        staff.setCompany(this);
        staffs.add(staff);
    }

    public void addClient(Client client){
        client.setCompany(this);
        clients.add(client);
    }


}
