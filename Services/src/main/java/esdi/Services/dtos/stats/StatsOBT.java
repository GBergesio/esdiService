package esdi.Services.dtos.stats;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Set;

@Setter
@Getter
@NoArgsConstructor
public class StatsOBT {

    private List<String> staffName;
    private Set<Integer> qty;

}
