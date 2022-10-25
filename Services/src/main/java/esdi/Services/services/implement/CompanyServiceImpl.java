package esdi.Services.services.implement;

import esdi.Services.dtos.CompanyDTO;
import esdi.Services.dtos.budget.BudgetDTO;
import esdi.Services.dtos.request.CompanyRequest;
import esdi.Services.mappers.CompanyMapper;
import esdi.Services.models.users.Company;
import esdi.Services.repositories.CompanyRepository;
import esdi.Services.services.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyServiceImpl implements CompanyService {

    @Autowired
    CompanyRepository companyRepository;

    @Autowired
    CompanyMapper companyMapper;


    @Override
    public Company saveCompany(Company company) {
        return companyRepository.save(company);
    }

    @Override
    public CompanyDTO saveCompanyDTO(Company company) {
        return companyMapper.toDTO(companyRepository.save(company));
    }

    @Override
    public List<CompanyDTO> findAllDTO() {
        return companyMapper.toDTO(companyRepository.findAll());
    }

    @Override
    public ResponseEntity<?> allCompanies() {
        return new ResponseEntity<>(findAllDTO(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> findById(Long id) {
        CompanyDTO companyDTO = companyMapper.toDTO(companyRepository.findById(id).orElse(null));

        if (id.equals(null) || id == null){
            return new ResponseEntity<>("Ingresar dato",HttpStatus.BAD_REQUEST);
        }

        if (companyDTO == null){
            return new ResponseEntity<>("No se encontró la compañía con el ID ingresado",HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(companyDTO, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> findByCuit(String cuit) {
        CompanyDTO companyDTO = companyMapper.toDTO(companyRepository.findByCuit(cuit));

        if (cuit.isEmpty() || cuit.isBlank() || cuit == null){
            return new ResponseEntity<>("Ingresar dato",HttpStatus.BAD_REQUEST);
        }

        if (companyDTO == null){
            return new ResponseEntity<>("No se encontró la compañía con el Cuit ingresado",HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(companyDTO, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> createCompany(CompanyRequest companyRequest) {
        return null;
    }
}
