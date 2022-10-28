package esdi.Services.models.devices;

import esdi.Services.models.users.Company;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "deviceCategory")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DeviceCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private long id;
    private String nameCategory;

    public DeviceCategory(String nameCategory) {
        this.nameCategory = nameCategory;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="company_id")
    private Company company;
}