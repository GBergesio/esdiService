package esdi.Services.models.budgets;

import esdi.Services.models.Comment;
import esdi.Services.models.products.Product;
import esdi.Services.models.products.ServiceArt;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "optionBudgets")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OptionBudget {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private long id;

    private double total;
    private String comment;
    private Boolean selected;
    private Boolean deleted;

    @OneToMany(mappedBy = "optionBudget", fetch = FetchType.EAGER)
    private Set<OptionComponent> optionComponents = new HashSet<>();

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="budget_id")
    private Budget budget;

    public void addOption(OptionComponent optionComponent){
        optionComponent.setOptionBudget(this);
        optionComponents.add(optionComponent);
    }



//    private String comment;
//    private String description;
//    private double price;
//    private double subtotal;
//    private double iva;
//    private double total;
//    private double subtotalGeneral;
//    private double totalGeneral;


//
//    @ManyToOne(fetch = FetchType.LAZY)
//    private ServiceArt serviceArt;

//    @OneToMany(mappedBy = "optionBudget", fetch = FetchType.EAGER)
//    private Set<Product> products = new HashSet<>();

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name="budget_id")
//    private Budget budget;
//
//    public void setBudget(Budget budget) {
//        this.budget = budget;
//    }

//        public void addProduct(Product product){
//            product.setOp;
//            products.add(option);
//        }


}
