package esdi.Services.services;

import esdi.Services.dtos.CompanyDTO;
import esdi.Services.dtos.request.CompanyRequest;
import esdi.Services.models.users.Company;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;

import java.util.List;

public interface CompanyService {

    Company saveCompany(Company company);
    CompanyDTO saveCompanyDTO(Company company);
    Company getCurrentCompany(Authentication authentication);
    List<CompanyDTO> findAllDTO();
    ResponseEntity<?> allCompanies();
    ResponseEntity<?> findById(Long id);
    ResponseEntity<?> findByCuit(String cuit);
    ResponseEntity<?> findByEmail(String email);
    ResponseEntity<?> findByUser(String user);
    ResponseEntity<?> createCompany(CompanyRequest companyRequest);

}
