package esdi.Services.services.budget;

import esdi.Services.dtos.OptionComponentDTO;
import esdi.Services.dtos.request.OptionRequest;
import esdi.Services.enums.StatusBudget;
import esdi.Services.mappers.OptionComponentMapper;
import esdi.Services.models.budgets.Budget;
import esdi.Services.models.budgets.OptionBudget;
import esdi.Services.models.budgets.OptionComponent;
import esdi.Services.models.products.Product;
import esdi.Services.models.products.ServiceArt;
import esdi.Services.models.users.Company;
import esdi.Services.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class OptionComponentServiceImp implements OptionComponentService{

    @Autowired
    OptionComponentRepository optionComponentRepository;
    @Autowired
    OptionComponentMapper optionComponentMapper;
    @Autowired
    CompanyRepository companyRepository;

    @Override
    public ResponseEntity<?> allOptionsComponent() {
        return new ResponseEntity<>(optionComponentMapper.toDTO(optionComponentRepository.findAll()),HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> findById(Long id) {

        OptionComponentDTO optionComponentDTO = optionComponentMapper.toDTO(optionComponentRepository.findById(id).orElse(null));

        if (id.equals(null) || id == null){
            return new ResponseEntity<>("Ingresar dato",HttpStatus.BAD_REQUEST);
        }

        if (optionComponentDTO == null){
            return new ResponseEntity<>("No se encontró variable con el ID ingresado",HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(optionComponentDTO, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> allOptionsComponentByCompany(Authentication authentication) {
        Company company = companyRepository.findByUsername(authentication.getName());
        List<OptionComponent> components = optionComponentRepository.findAllByCompany(company);

        return new ResponseEntity<>(optionComponentMapper.toDTO(components), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> updateOption(Authentication authentication, Long id) {
        OptionComponent optionComponent = optionComponentRepository.findById(id).orElse(null);

        return null;
    }


    @Override
    public ResponseEntity<?> deleteOption(Authentication authentication, Long id) {
        Company company = companyRepository.findByUsername(authentication.getName());
        OptionComponent optionComponent = optionComponentRepository.findById(id).orElse(null);
        List<OptionComponent> components = optionComponentRepository.findAllByCompany(company);

        if(components.indexOf(optionComponent) == -1){
            return new ResponseEntity<>("No se encontró opcion con el ID ingresado",HttpStatus.BAD_REQUEST);
        }
        Budget budget = optionComponent.getOptionBudget().getBudget();
        if(!budget.getStatusBudget().equals(StatusBudget.ON_HOLD)){
            return new ResponseEntity<>("No se puede eliminar la opcion porque el presupuesto ya obtuvo alguna confirmacion",HttpStatus.BAD_REQUEST);
        } else{
            optionComponentRepository.delete(optionComponent);
        }

        return new ResponseEntity<>("Eliminado exitosamente",HttpStatus.OK);
    }




}
