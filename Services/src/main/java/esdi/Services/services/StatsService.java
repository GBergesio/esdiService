package esdi.Services.services;

import esdi.Services.dtos.stats.StatsOBT;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;

public interface StatsService {

    ResponseEntity<?> totalOrders(Authentication authentication);

    ResponseEntity<?> totalRepairs(Authentication authentication);

    ResponseEntity<?> totalWr(Authentication authentication);

    ResponseEntity<?> totalClients(Authentication authentication);

    ResponseEntity<?> totalOrdersByTechnician(Authentication authentication);

    ResponseEntity<?> ordersByWeek(Authentication authentication);

}
