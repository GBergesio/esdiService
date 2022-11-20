package esdi.Services.services.budget;

import esdi.Services.dtos.budget.OptionBudgetDTO;
import esdi.Services.dtos.request.OptionRequest;
import esdi.Services.enums.StatusBudget;
import esdi.Services.mappers.OptionBudgetMapper;
import esdi.Services.mappers.OptionComponentMapper;
import esdi.Services.models.budgets.Budget;
import esdi.Services.models.budgets.OptionBudget;
import esdi.Services.models.budgets.OptionComponent;
import esdi.Services.models.users.Company;
import esdi.Services.repositories.BudgetRepository;
import esdi.Services.repositories.CompanyRepository;
import esdi.Services.repositories.OptionBudgetRepository;
import esdi.Services.repositories.OptionComponentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class OptionBudgetServiceImpl implements OptionBudgetService{

    @Autowired
    OptionBudgetRepository optionBudgetRepository;

    @Autowired
    OptionComponentRepository optionComponentRepository;

    @Autowired
    OptionBudgetMapper optionBudgetMapper;

    @Autowired
    OptionComponentMapper optionComponentMapper;

    @Autowired
    CompanyRepository companyRepository;

    @Autowired
    BudgetRepository budgetRepository;

    @Override
    public ResponseEntity<?> allOptionBudgets() {
        List<OptionBudget> optionsBudget = optionBudgetRepository.findAll();

        return new ResponseEntity<>(optionBudgetMapper.toDTO(optionsBudget), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> allOptionBudgetsByCompany(Authentication authentication) {
        Company company = companyRepository.findByUsername(authentication.getName());
        List<OptionBudget> optionsBudget = optionBudgetRepository.findAllByCompany(company);
        List<OptionBudget> optionsBudgetAvailables = optionsBudget.stream().filter(optionBudget -> !optionBudget.getDeleted()).collect(Collectors.toList());

        return new ResponseEntity<>(optionBudgetMapper.toDTO(optionsBudgetAvailables), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> findById(Long id) {
        OptionBudgetDTO optionBudgetDTO = optionBudgetMapper.toDTO(optionBudgetRepository.findById(id).orElse(null));

        if (id.equals(null) || id == null){
            return new ResponseEntity<>("Ingresar dato",HttpStatus.BAD_REQUEST);
        }

        if (optionBudgetDTO == null){
            return new ResponseEntity<>("No se encontró variable con el ID ingresado",HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(optionBudgetDTO, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> optionsByBudget(Authentication authentication, Long id) {
        Company company = companyRepository.findByUsername(authentication.getName());
        Budget budget = budgetRepository.findById(id).orElse(null);
        List<Budget> budgets = budgetRepository.findAllByCompany(company);

        List<OptionBudget> optionBudgets = optionBudgetRepository.findAllByBudget(budget);

        if(!budgets.contains(budget)){
            return new ResponseEntity<>("No se encontró presupuesto con el ID ingresado",HttpStatus.BAD_REQUEST);
        }
        if(optionBudgets.size() == 0){
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(optionBudgetMapper.toDTO(optionBudgets),HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> createOptionBudget(Authentication authentication, OptionRequest optionRequest, Long idBudget) {
        Company company = companyRepository.findByUsername(authentication.getName());
        OptionBudget newOption = new OptionBudget();
        Budget budget = budgetRepository.findById(idBudget).orElse(null);
        List<Budget> budgets = budgetRepository.findAllByCompany(company);

        if(!budgets.contains(budget)){
            return new ResponseEntity<>("No se encontró presupuesto con el ID ingresado",HttpStatus.BAD_REQUEST);
        }

        newOption.setCompany(company);
        newOption.setBudget(budget);
        newOption.setSelected(false);
        newOption.setComment(optionRequest.getComment());
        newOption.setDeleted(false);

        optionBudgetRepository.save(newOption);
        List<OptionComponent> optionComponents = optionComponentMapper.toEntity(optionRequest.getOptionComponents());
        for(OptionComponent optionComponent : optionComponents){
            optionComponent.setOptionBudget(newOption);
            optionComponent.setTotalPrice(optionComponent.getQuantity() * optionComponent.getPricePoS());
            optionComponentRepository.save(optionComponent);
        }

        double sum = optionComponents.stream().mapToDouble(OptionComponent::getTotalPrice).sum();

        newOption.setTotal(sum);
        optionBudgetRepository.save(newOption);

        return new ResponseEntity<>("Opcion creada correctamente",HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<?> updateOptionBudget(Authentication authentication, OptionRequest optionRequest,Long id) {
        Company company = companyRepository.findByUsername(authentication.getName());
        OptionBudget optionBudget = optionBudgetRepository.findById(id).orElse(null);
        List<OptionBudget> optionBudgets = optionBudgetRepository.findAllByCompany(company);

        if(!optionBudgets.contains(optionBudget)){
            return new ResponseEntity<>("No se encontró opcion con el ID ingresado",HttpStatus.BAD_REQUEST);
        }

        assert optionBudget != null;
        optionBudget.setComment(optionRequest.getComment());
        optionBudget.setSelected(optionRequest.getSelected());

        optionBudgetRepository.save(optionBudget);

        return new ResponseEntity<>("Opcion modificada correctamente",HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<?> updateTotalOptionBudget(Authentication authentication, OptionRequest optionRequest,Long id) {
        Company company = companyRepository.findByUsername(authentication.getName());
        OptionBudget optionBudget = optionBudgetRepository.findById(id).orElse(null);
        List<OptionBudget> optionBudgets = optionBudgetRepository.findAllByCompany(company);

        if(!optionBudgets.contains(optionBudget)){
            return new ResponseEntity<>("No se encontró opcion con el ID ingresado",HttpStatus.BAD_REQUEST);
        }

        assert optionBudget != null;
        optionBudget.setSelected(optionRequest.getSelected());
        optionBudget.setComment(optionRequest.getComment());
        optionBudget.setCompany(company);
        optionBudget.setDeleted(false);

        optionBudgetRepository.save(optionBudget);

        List<OptionComponent> previus = optionComponentRepository.findAllByOptionBudget(optionBudget);
        for(OptionComponent optionComponent : previus){
            optionComponentRepository.delete(optionComponent);
        }

        List<OptionComponent> optionComponents = optionComponentMapper.toEntity(optionRequest.getOptionComponents());

        for(OptionComponent optionComponent : optionComponents){
            optionComponent.setOptionBudget(optionBudget);
            optionComponent.setTotalPrice(optionComponent.getQuantity() * optionComponent.getPricePoS());
            optionComponentRepository.save(optionComponent);
        }
        double sum = optionComponents.stream().mapToDouble(OptionComponent::getTotalPrice).sum();
        optionBudget.setTotal(sum);
        optionBudgetRepository.save(optionBudget);

        return new ResponseEntity<>("Opcion modificada correctamente",HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> deleteOptionBudget(Authentication authentication,Long id) {
        Company company = companyRepository.findByUsername(authentication.getName());
        OptionBudget optionBudget = optionBudgetRepository.findById(id).orElse(null);
        List<OptionBudget> optionBudgets = optionBudgetRepository.findAllByCompany(company);

        if(!optionBudgets.contains(optionBudget)){
            return new ResponseEntity<>("No se encontró opcion con el ID ingresado",HttpStatus.BAD_REQUEST);
        }
        assert optionBudget != null;
        Budget budget = optionBudget.getBudget();
        List<OptionComponent> optionComponent = optionBudget.getOptionComponents();
        if(!budget.getStatusBudget().equals(StatusBudget.ON_HOLD)){
            return new ResponseEntity<>("No se puede eliminar la opcion porque el presupuesto ya obtuvo alguna confirmacion",HttpStatus.BAD_REQUEST);
        }
        else{
            optionComponentRepository.deleteAll(optionComponent);
            optionBudgetRepository.delete(optionBudget);
        }

        return new ResponseEntity<>("Eliminado exitosamente",HttpStatus.OK);
    }
}
