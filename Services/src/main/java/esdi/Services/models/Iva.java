package esdi.Services.models;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "iva")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Iva {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private long id;

    private double iva;

//    @OneToMany(fetch = FetchType.LAZY)
//    private Set<Product> products = new HashSet<>();

    public Iva(double iva) {
        this.iva = iva;
    }



}
