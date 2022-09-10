package esdi.Services.models.products;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "services")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ServiceArt {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private long id;

    private String description;

    private double costPrice;

    private double salePrice;

    private double utility;

    @ManyToOne(fetch = FetchType.LAZY)
    private Category category;

    @ManyToOne(fetch = FetchType.LAZY)
    private Iva iva;

    public ServiceArt(String description, double costPrice, double salePrice, double utility, Category category, Iva iva) {
        this.description = description;
        this.costPrice = costPrice;
        this.salePrice = salePrice;
        this.utility = utility;
        this.category = category;
        this.iva = iva;
    }
}
