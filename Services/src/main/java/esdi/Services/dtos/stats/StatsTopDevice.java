package esdi.Services.dtos.stats;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Set;
@Setter
@Getter
@NoArgsConstructor
public class StatsTopDevice {

    private List<String> device;
    private Set<Integer> qty;
}
