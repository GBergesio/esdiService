package esdi.Services.models.products;
import esdi.Services.models.users.Company;
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="company_id")
    private Company company;

    public Iva(double iva) {
        this.iva = iva;
    }
}