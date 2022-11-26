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

    @GetMapping()
    ResponseEntity<?> moreStats(Authentication authentication) {
        return statsService.stats(authentication);
    }


    @GetMapping("/obw")
    ResponseEntity<?> obw(Authentication authentication) {
        return statsService.ordersByWeek(authentication);
    }


    @GetMapping("/obt")
    ResponseEntity<?> ordersByTechnician(Authentication authentication) {
        return statsService.oBt(authentication);
    }

    @GetMapping("/oWbt")
    ResponseEntity<?> warrantyByTechnician(Authentication authentication) {
        return statsService.owBt(authentication);
    }

    @GetMapping("/topDevices")
    ResponseEntity<?> topDevices(Authentication authentication) {
        return statsService.topDevice(authentication);
    }

}
