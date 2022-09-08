package esdi.Services.models;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;

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

    @ManyToOne(fetch = FetchType.LAZY)
    private Iva iva;

    public Product(String productNumber, String description, double costPrice, double salePrice, double utility, Iva iva){
        this.productNumber = productNumber;
        this.description = description;
        this.costPrice = costPrice;
        this.salePrice = salePrice;
        this.utility = utility;
        this.iva = iva;
    }


}
