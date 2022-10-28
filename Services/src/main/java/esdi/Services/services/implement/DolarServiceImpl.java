package esdi.Services.services.implement;

import esdi.Services.dtos.DolarDTO;
import esdi.Services.mappers.DolarMapper;
import esdi.Services.models.Dolar;
import esdi.Services.models.users.Company;
import esdi.Services.repositories.CompanyRepository;
import esdi.Services.repositories.DolarRepository;
import esdi.Services.services.DolarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class DolarServiceImpl implements DolarService {

    @Autowired
    DolarRepository dolarRepository;

    @Autowired
    CompanyRepository companyRepository;

    @Autowired
    DolarMapper dolarMapper;

    @Override
    public Dolar saveDolar(Dolar dolar) {
        return dolarRepository.save(dolar);
    }

    @Override
    public ResponseEntity<?> dolarByCompany(Authentication authentication) {
        Company company = companyRepository.findByUser(authentication.getName());
        Dolar dolarByCompany = dolarRepository.findByCompany(company);
        return new ResponseEntity<>(dolarMapper.toDTO(dolarByCompany), HttpStatus.OK);
    }
}
