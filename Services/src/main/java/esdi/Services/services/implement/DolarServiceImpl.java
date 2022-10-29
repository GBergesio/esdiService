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

import java.time.LocalDateTime;
import java.util.List;

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
    public ResponseEntity<?> allDollars() {
        List<Dolar> dollarList = dolarRepository.findAll();

        return new ResponseEntity<>(dolarMapper.toDTO(dollarList), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> dollarByCompany(Authentication authentication) {
        Company company = companyRepository.findByUser(authentication.getName());
        Dolar dolarCompany = dolarRepository.findByCompany(company);
        return new ResponseEntity<>(dolarMapper.toDTO(dolarCompany), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> newDollarByCompany(Authentication authentication, DolarDTO dolarDTO) {
        Company company = companyRepository.findByUser(authentication.getName());
        Dolar dolarCompany = dolarRepository.findByCompany(company);

        dolarCompany.setPrice(dolarDTO.getPrice());
        dolarCompany.setDate(LocalDateTime.now());
        dolarCompany.setCompany(company);
        dolarCompany.setDescription("");
        dolarCompany.setCompanyName(company.getName());

        dolarRepository.save(dolarCompany);

        return new ResponseEntity<>(dolarMapper.toDTO(dolarCompany), HttpStatus.OK);
    }


}
