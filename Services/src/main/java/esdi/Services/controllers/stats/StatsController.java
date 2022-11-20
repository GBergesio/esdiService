package esdi.Services.controllers.stats;

import esdi.Services.services.StatsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin()
@RestController
@RequestMapping("/stats")
public class StatsController {

    @Autowired
    StatsService statsService;

    @GetMapping("/orders")
    ResponseEntity<?> sizeOrders(Authentication authentication) {
        return statsService.totalOrders(authentication);
    }

    @GetMapping("/ordersR")
    ResponseEntity<?> sizeOrdersR(Authentication authentication) {
        return statsService.totalRepairs(authentication);
    }

    @GetMapping("/ordersWR")
    ResponseEntity<?> sizeOrdersWR(Authentication authentication) {
        return statsService.totalWr(authentication);
    }

    @GetMapping("/clients")
    ResponseEntity<?> clients(Authentication authentication) {
        return statsService.totalClients(authentication);
    }

    @GetMapping("/obt")
    ResponseEntity<?> obt(Authentication authentication) {
        return statsService.totalOrdersByTechnician(authentication);
    }

    @GetMapping("/obw")
    ResponseEntity<?> obw(Authentication authentication) {
        return statsService.ordersByWeek(authentication);
    }

}
