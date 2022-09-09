package esdi.Services.models.products;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import javax.persistence.*;


@Entity
@Table(name = "brand")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Brand{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private long id;

    private String nameBrand;

    public Brand(String nameBrand) {
        this.nameBrand = nameBrand;
    }
}