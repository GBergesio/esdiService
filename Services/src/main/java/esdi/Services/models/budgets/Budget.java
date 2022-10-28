package esdi.Services.models.budgets;

import esdi.Services.enums.StatusBudget;
import esdi.Services.models.Order;
import esdi.Services.models.users.Company;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "budgets")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Budget {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private long id;

    private int budgetNumber;
    private int orderNumber;
    private String client;
    private StatusBudget statusBudget;
    private LocalDateTime issueDate;
    private LocalDateTime confirmationDate;
    private LocalDateTime dueDate;
    private double subtotal;
    private double total;

    @OneToOne
    @JoinColumn(name="order_orderNumber")
    private Order order;

    @OneToMany(mappedBy = "budget", fetch = FetchType.EAGER)
    private Set<OptionBudget> options = new HashSet<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="company_id")
    private Company company;

//    @OneToMany(mappedBy = "budget", fetch = FetchType.EAGER)
//    private Set<OptionBudget> options = new HashSet<>();
//
        public void addOption(OptionBudget optionBudget){
            optionBudget.setBudget(this);
            options.add(optionBudget);
        }


}

