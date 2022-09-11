package esdi.Services.models.products;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import javax.persistence.*;

@Entity
@Table(name = "iva")
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Iva {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private long id;
    private double iva;

    public Iva(double iva) {
        this.iva = iva;
    }
}