package esdi.Services.dtos.stats;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Set;
@Setter
@Getter
@NoArgsConstructor
public class StatsTopF {

    private List<String> f;
    private Set<Integer> qty;
}
