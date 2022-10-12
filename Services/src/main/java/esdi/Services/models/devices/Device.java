package esdi.Services.models.devices;

import esdi.Services.models.products.Brand;
import esdi.Services.models.users.Client;
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="client_id")
    private Client client;

    @ManyToOne(fetch = FetchType.LAZY)
    private DeviceCategory category;

    @ManyToOne(fetch = FetchType.LAZY)
    private Brand brand;

    @ManyToOne(fetch = FetchType.LAZY)
    private DeviceModel model;

    private String serial;

    private String description;

    public Device(Client client, DeviceCategory category, Brand brand, DeviceModel model, String serial, String description) {
        this.client = client;
        this.category = category;
        this.brand = brand;
        this.model = model;
        this.serial = serial;
        this.description = description;
    }
}
