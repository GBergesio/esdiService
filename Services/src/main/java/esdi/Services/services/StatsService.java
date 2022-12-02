package esdi.Services.services;

import esdi.Services.dtos.stats.StatsOBT;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;

public interface StatsService {
    ResponseEntity<?> stats(Authentication authentication);

    ResponseEntity<?> ordersByWeekT(Authentication authentication);

    ResponseEntity<?> ordersByWeek(Authentication authentication);

    ResponseEntity<?> oBt(Authentication authentication);
    ResponseEntity<?> owBt(Authentication authentication);
    ResponseEntity<?> topDevice(Authentication authentication);
}
