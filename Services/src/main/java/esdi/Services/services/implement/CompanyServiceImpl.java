package esdi.Services.services.implement;

import esdi.Services.dtos.CompanyDTO;
import esdi.Services.dtos.budget.BudgetDTO;
import esdi.Services.dtos.request.CompanyRequest;
import esdi.Services.enums.UserType;
import esdi.Services.mappers.CompanyMapper;
import esdi.Services.models.users.Company;
import esdi.Services.repositories.CompanyRepository;
import esdi.Services.services.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
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
    public ResponseEntity<?> getCurrentCompany(Authentication authentication) {
        Company company = companyRepository.findByUsername(authentication.getName());
        CompanyDTO companyDTO = companyMapper.toDTO(company);

        return new ResponseEntity<>(companyDTO, HttpStatus.OK);
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
    public ResponseEntity<?> findByEmail(String email) {
        CompanyDTO companyDTO = companyMapper.toDTO(companyRepository.findByEmail(email));

        if (email.isEmpty() || email.isBlank() || email == null){
            return new ResponseEntity<>("Ingresar dato",HttpStatus.BAD_REQUEST);
        }

        if (companyDTO == null){
            return new ResponseEntity<>("No se encontró la compañía con el email ingresado",HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(companyDTO, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> findByUser(String user) {
        CompanyDTO companyDTO = companyMapper.toDTO(companyRepository.findByUsername(user));

        if (user.isEmpty() || user.isBlank() || user == null){
            return new ResponseEntity<>("Ingresar dato",HttpStatus.BAD_REQUEST);
        }

        if (companyDTO == null){
            return new ResponseEntity<>("No se encontró la compañía con el nombre de usuario ingresado",HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(companyDTO, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> createCompany(CompanyRequest companyRequest) {

        if(companyRequest.getName() == null || companyRequest.getName().equals(null) || companyRequest.getName().isBlank()){
            return new ResponseEntity<>("Ingresar datos",HttpStatus.BAD_REQUEST);
        }

        if(companyRequest.getCuit() == null || companyRequest.getCuit().equals(null) || companyRequest.getCuit().isBlank()){
            return new ResponseEntity<>("Ingresar datos",HttpStatus.BAD_REQUEST);
        }

        if(companyRequest.getEmail() == null || companyRequest.getEmail().equals(null) || companyRequest.getEmail().isBlank()){
            return new ResponseEntity<>("Ingresar datos",HttpStatus.BAD_REQUEST);
        }

        if(companyRequest.getPhone() == null || companyRequest.getPhone().equals(null) || companyRequest.getPhone().isBlank()){
            return new ResponseEntity<>("Ingresar datos",HttpStatus.BAD_REQUEST);
        }

        if(companyRequest.getSector() == null || companyRequest.getSector().equals(null) || companyRequest.getSector().isBlank()){
            return new ResponseEntity<>("Ingresar datos",HttpStatus.BAD_REQUEST);
        }

        Company company = new Company();
        company.setPlan(null);
        company.setActive(false);
        company.setUserType(UserType.COMPANY);
        company.setUsername(null);
        company.setPassword(null);
        company.setName(companyRequest.getName());
        company.setCuit(companyRequest.getCuit());
        company.setEmail(companyRequest.getEmail());
        company.setPhone(companyRequest.getPhone());
        company.setSector(companyRequest.getSector());

        companyRepository.save(company);

        return new ResponseEntity<>("Solicitud enviada correctamente",HttpStatus.CREATED);
    }
}
