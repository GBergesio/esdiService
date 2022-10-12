package esdi.Services.models.devices;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

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

}