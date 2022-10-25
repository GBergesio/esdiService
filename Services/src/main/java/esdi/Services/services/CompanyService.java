package esdi.Services.services;

import esdi.Services.dtos.CompanyDTO;
import esdi.Services.dtos.request.CompanyRequest;
import esdi.Services.models.users.Company;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface CompanyService {

    Company saveCompany(Company company);
    CompanyDTO saveCompanyDTO(Company company);
    List<CompanyDTO> findAllDTO();
    ResponseEntity<?> allCompanies();
    ResponseEntity<?> findById(Long id);
    ResponseEntity<?> findByCuit(String cuit);
    ResponseEntity<?> findByEmail(String email);
    ResponseEntity<?> findByUser(String user);
    ResponseEntity<?> createCompany(CompanyRequest companyRequest);


}

//        OptionComponent saveOptionComponent(OptionComponent optionComponent);
//
//        OptionComponentDTO saveOptionComponentDTO(OptionComponent optionComponent);
//
//        List<OptionComponentDTO> findAllDTO();
//
//        ResponseEntity<?> allOptionsComponent();
//
//        ResponseEntity<?> findById(Long id);
//
//        ResponseEntity<?> createOptionComponent(OptionRequest optionRequest, Long idBudget, Long idPoS);
//    //
//    //    ResponseEntity<?> updateOptionBudget(Long id);
//    //
//        ResponseEntity<?> deleteOption(Long id);