package esdi.Services.models.products;
import esdi.Services.models.Currency;
import esdi.Services.models.Dolar;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import javax.persistence.*;

@Entity
@Table(name = "product")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private long id;

    private String productNumber;

    private String description;

    private double costPrice;

    private double salePrice;

    private double utility;

    private Currency currency;

    @ManyToOne(fetch = FetchType.LAZY)
    private Iva iva;

    @ManyToOne(fetch = FetchType.LAZY)
    private Category category;

    private Double dolar;

    @ManyToOne(fetch = FetchType.LAZY)
    private Brand brand;

    public Product(String productNumber, String description, Brand brand, Category category,Currency currency, Double dolar,Iva iva,double costPrice,double utility,double salePrice){
        this.productNumber = productNumber;
        this.description = description;
        this.costPrice = costPrice;
        this.salePrice = salePrice;
        this.utility = utility;
        this.currency = currency;
        this.iva = iva;
        this.category = category;
        this.dolar = dolar;
        this.brand = brand;
    }


}
