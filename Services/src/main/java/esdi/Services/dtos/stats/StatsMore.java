package esdi.Services.dtos.stats;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.userdetails.UserDetails;

@Setter
@Getter
@NoArgsConstructor
public class StatsMore {

    private int pendingOrders;
    private int pendingBudgets;
    private int approvedBudgets;
    private int rejectedBudgets;
    private int clients;
    private int totalOrders;
    private int repaired;
    private int unrepaired;
    private String userName;
    private String role;
    private String sector;
}
