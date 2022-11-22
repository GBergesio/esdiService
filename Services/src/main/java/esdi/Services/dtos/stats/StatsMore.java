package esdi.Services.dtos.stats;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class StatsMore {

    private int pendingOrders;
    private int pendingBudgets;
    private int approvedBudgets;
    private int rejectedBudgets;
}
