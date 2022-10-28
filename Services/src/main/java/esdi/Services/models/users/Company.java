package esdi.Services.models.users;

import esdi.Services.enums.UserType;
import esdi.Services.models.Comment;
import esdi.Services.models.Currency;
import esdi.Services.models.Dolar;
import esdi.Services.models.Order;
import esdi.Services.models.budgets.Budget;
import esdi.Services.models.budgets.OptionBudget;
import esdi.Services.models.budgets.OptionComponent;
import esdi.Services.models.devices.Device;
import esdi.Services.models.devices.DeviceCategory;
import esdi.Services.models.devices.DeviceModel;
import esdi.Services.models.products.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

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
    private List<Client> clients = new ArrayList<>();

    @OneToMany(mappedBy = "company", fetch = FetchType.LAZY)
    private List<Neighborhood> neighborhoods = new ArrayList<>();

    @OneToMany(mappedBy = "company", fetch = FetchType.LAZY)
    private List<Staff> staffs = new ArrayList<>();

    @OneToMany(mappedBy = "company", fetch = FetchType.LAZY)
    private List<Order> orders = new ArrayList<>();

    @OneToMany(mappedBy = "company", fetch = FetchType.LAZY)
    private List<Budget> budgets = new ArrayList<>();

    @OneToMany(mappedBy = "company", fetch = FetchType.LAZY)
    private List<OptionBudget> optionBudgets = new ArrayList<>();

    @OneToMany(mappedBy = "company", fetch = FetchType.LAZY)
    private List<OptionComponent> optionComponents = new ArrayList<>();

    @OneToMany(mappedBy = "company", fetch = FetchType.LAZY)
    private List<Device> devices = new ArrayList<>();

    @OneToMany(mappedBy = "company", fetch = FetchType.LAZY)
    private List<DeviceCategory> deviceCategories = new ArrayList<>();

    @OneToMany(mappedBy = "company", fetch = FetchType.LAZY)
    private List<DeviceModel> deviceModels = new ArrayList<>();

    @OneToMany(mappedBy = "company", fetch = FetchType.LAZY)
    private List<Product> products = new ArrayList<>();

    @OneToMany(mappedBy = "company", fetch = FetchType.LAZY)
    private List<ServiceArt> serviceArts = new ArrayList<>();

    @OneToMany(mappedBy = "company", fetch = FetchType.LAZY)
    private List<Brand> brands = new ArrayList<>();

    @OneToMany(mappedBy = "company", fetch = FetchType.LAZY)
    private List<Category> categories = new ArrayList<>();

    @OneToMany(mappedBy = "company", fetch = FetchType.LAZY)
    private List<Iva> ivas = new ArrayList<>();

    @OneToMany(mappedBy = "company", fetch = FetchType.LAZY)
    private List<Comment> comments = new ArrayList<>();

    @OneToMany(mappedBy = "company", fetch = FetchType.LAZY)
    private List<Dolar> dolars = new ArrayList<>();






//    public void addClient(Client client){
//        client.setCompany(this);
//        clients.add(client);
//    }
//
//    public void addNeighborhood(Neighborhood neighborhood){
//        neighborhood.setCompany(this);
//        neighborhoods.add(neighborhood);
//    }
//
//    public void addStaff(Staff staff){
//        staff.setCompany(this);
//        staffs.add(staff);
//    }
//
//    public void addOrder(Order order){
//        order.setCompany(this);
//        orders.add(order);
//    }
//
//    public void addBudget(Budget budget){
//        budget.setCompany(this);
//        budgets.add(budget);
//    }



}
