package esdi.Services.models.budgets;

import esdi.Services.models.products.Product;
import esdi.Services.models.products.ServiceArt;
import esdi.Services.models.users.Company;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "optionComponents")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OptionComponent {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private long id;

    private double quantity;
    private double totalPrice;
    private long idPoS;
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="product_id")
    private Product product;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="serviceArt_id")
    private ServiceArt serviceArt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="optionBudget_id")
    private OptionBudget optionBudget;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="company_id")
    private Company company;

}
