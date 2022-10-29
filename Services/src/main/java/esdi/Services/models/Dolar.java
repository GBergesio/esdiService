package esdi.Services.models;
import esdi.Services.models.users.Company;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "dolar")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Dolar{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private long id;

    private double price;

    private LocalDateTime date;

    private String description;

    private String companyName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="company_id")
    private Company company;

    public Dolar(double price, LocalDateTime date) {
        this.price = price;
        this.date = date;
    }
}
