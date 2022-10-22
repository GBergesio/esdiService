package esdi.Services.services.budget;

import esdi.Services.dtos.OptionComponentDTO;
import esdi.Services.dtos.budget.BudgetDTO;
import esdi.Services.dtos.budget.OptionBudgetDTO;
import esdi.Services.mappers.OptionBudgetMapper;
import esdi.Services.models.budgets.OptionBudget;
import esdi.Services.models.budgets.OptionComponent;
import esdi.Services.repositories.OptionBudgetRepository;
import esdi.Services.repositories.OptionComponentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class OptionBudgetServiceImpl implements OptionBudgetService{

    @Autowired
    OptionBudgetRepository optionBudgetRepository;

    @Autowired
    OptionComponentRepository optionComponentRepository;

    @Autowired
    OptionBudgetMapper optionBudgetMapper;


    @Override
    public OptionBudget saveOptionBudget(OptionBudget optionBudget) {
        return optionBudgetRepository.save(optionBudget);
    }

    @Override
    public OptionBudgetDTO saveOptionBudgetDTO(OptionBudget optionBudget) {
        return optionBudgetMapper.toDTO(optionBudgetRepository.save(optionBudget));
    }

    @Override
    public List<OptionBudgetDTO> findAllDTO()  {

        List<OptionBudget> optionsBudget = optionBudgetRepository.findAll().stream().filter(optionBudget -> optionBudget.getDeleted() == false).collect(Collectors.toList());

        return optionBudgetMapper.toDTO(optionsBudget);
    }

    @Override
    public ResponseEntity<?> allOptionBudgets() {
        return new ResponseEntity<>(findAllDTO(), HttpStatus.OK);
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
    public ResponseEntity<?> createOptionBudget(OptionBudgetDTO optionBudgetDTO) {
        return null;
    }

    @Override
    public ResponseEntity<?> updateOptionBudget(Long id) {
        return null;
    }

    @Override
    public ResponseEntity<?> deleteOptionBudget(Long id) {

        OptionBudget optionBudget = optionBudgetRepository.findById(id).orElse(null);
        Set<OptionComponent> optionComponent = optionBudget.getOptionComponents();

        if(optionBudget == null)
            return new ResponseEntity<>("No existe la opcion",HttpStatus.BAD_REQUEST);

        if(optionBudget != null){

            optionComponentRepository.deleteAll(optionComponent);
            optionBudgetRepository.delete(optionBudget);

        }

        return new ResponseEntity<>("Eliminado exitosamente",HttpStatus.OK);
    }
}
