package esdi.Services.models.devices;

import esdi.Services.models.Order;
import esdi.Services.models.products.Brand;
import esdi.Services.models.users.Client;
import esdi.Services.models.users.Company;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "devices")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Device {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private long id;
    private String serial;
    private String description;

    private Boolean deleted;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="client_id")
    private Client client;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="device_id")
    private Order order;

    @ManyToOne(fetch = FetchType.LAZY)
    private DeviceCategory category;

    @ManyToOne(fetch = FetchType.LAZY)
    private Brand brand;

    @ManyToOne(fetch = FetchType.LAZY)
    private DeviceModel model;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="company_id")
    private Company company;

    public Device(String serial, String description, DeviceCategory category, Brand brand, DeviceModel model, Boolean deleted) {
        this.serial = serial;
        this.description = description;
        this.category = category;
        this.brand = brand;
        this.model = model;
        this.deleted = deleted;
    }


}
