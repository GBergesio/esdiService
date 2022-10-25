package esdi.Services.controllers.userControllers;
import esdi.Services.services.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @GetMapping("/id/{cuit}")
    ResponseEntity<?> getCompanyByCuit(@PathVariable String cuit) {
        return companyService.findByCuit(cuit);
    }

}
