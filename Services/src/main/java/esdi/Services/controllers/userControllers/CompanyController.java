package esdi.Services.controllers.userControllers;

import esdi.Services.dtos.request.CompanyRequest;
import esdi.Services.services.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;

@CrossOrigin
@RestController
@RequestMapping("/companies")
public class CompanyController {

    @Autowired
    CompanyService companyService;

    @GetMapping()
    ResponseEntity<?> getAllCompanies() {
        return companyService.allCompanies();
    }

    @GetMapping("/id/{id}")
    ResponseEntity<?> getCompanyById(@PathVariable Long id) {
        return companyService.findById(id);
    }

    @GetMapping("/cuit/{cuit}")
    ResponseEntity<?> getCompanyByCuit(@PathVariable String cuit) {
        return companyService.findByCuit(cuit);
    }

    @GetMapping("/email/{email}")
    ResponseEntity<?> getCompanyByEmail(@PathVariable String email) {
        return companyService.findByEmail(email);
    }

    @GetMapping("/userName/{user}")
    ResponseEntity<?> getCompanyByUserName(@PathVariable String user) {
        return companyService.findByUser(user);
    }

    @PostMapping("/request")
    ResponseEntity<?> requestCompany(@RequestBody CompanyRequest companyRequest) {
        return companyService.createCompany(companyRequest);
    }

    @Transactional
    @GetMapping("/current")
    ResponseEntity<?> getCurrentCompany(Authentication authentication) {
        return companyService.getCurrentCompany(authentication);
    }


//    @PostMapping("/login")
//    public ResponseEntity<?> createStaff(@RequestParam String email, @RequestParam String password) {
//        return companyService.createStaff(staffRequest);
//    }
}
