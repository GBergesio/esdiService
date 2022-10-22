package esdi.Services.services.budget;

import esdi.Services.dtos.OptionComponentDTO;
import esdi.Services.dtos.request.OptionRequest;
import esdi.Services.mappers.OptionComponentMapper;
import esdi.Services.models.budgets.OptionBudget;
import esdi.Services.models.budgets.OptionComponent;
import esdi.Services.models.products.Product;
import esdi.Services.models.products.ServiceArt;
import esdi.Services.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class OptionComponentServiceImp implements OptionComponentService{

    @Autowired
    OptionComponentRepository optionComponentRepository;

    @Autowired
    OptionBudgetRepository optionBudgetRepository;

    @Autowired
    BudgetRepository budgetRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    ServiceRepository serviceRepository;

    @Autowired
    OptionComponentMapper optionComponentMapper;

    @Override
    public OptionComponent saveOptionComponent(OptionComponent optionComponent) {
        return optionComponentRepository.save(optionComponent);
    }

    @Override
    public OptionComponentDTO saveOptionComponentDTO(OptionComponent optionComponent) {
        return optionComponentMapper.toDTO(optionComponentRepository.save(optionComponent));
    }

    @Override
    public List<OptionComponentDTO> findAllDTO()  {
        return optionComponentMapper.toDTO(optionComponentRepository.findAll());
    }

    @Override
    public ResponseEntity<?> allOptionsComponent() {
        return new ResponseEntity<>(findAllDTO(),HttpStatus.OK);
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
    public ResponseEntity<?> createOptionComponent(OptionRequest optionRequest, Long idBudget, Long idPoS) {
        try{
            OptionBudget optionBudget = optionBudgetRepository.findById(idBudget).orElse(null);
            OptionComponent newOptionComponent = new OptionComponent();

            if(optionBudget == null)
                return new ResponseEntity<>("Error, no se encuentra opcion dentro del presupuesto", HttpStatus.BAD_REQUEST);

            if(optionBudget != null){

                newOptionComponent.setIdPoS(idPoS);
                newOptionComponent.setName(optionRequest.getName());
                newOptionComponent.setQuantity(optionRequest.getQuantity());
                newOptionComponent.setTotalPrice(optionRequest.getTotalPrice());
                newOptionComponent.setOptionBudget(optionBudget);

            }
            return new ResponseEntity<>(this.saveOptionComponentDTO(newOptionComponent),HttpStatus.CREATED);
        }
        catch(Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,e.getMessage());
        }
    }

    @Override
    public ResponseEntity<?> deleteOption(Long id) {
        OptionComponent optionComponent = optionComponentRepository.findById(id).orElse(null);

        if(optionComponent == null)
            return new ResponseEntity<>("No existe la opcion",HttpStatus.BAD_REQUEST);
            optionComponentRepository.delete(optionComponent);
// VERIFICAR QUE NO SE PUEDA BORRAR UNA OPCION SI EL PRESUPUESTO YA ESTA APROBADO
//        Set<OptionBudget> optionBudgets = optionBudgetRepository.findAll().stream().filter(op -> op.getOptionComponents().contains(optionComponent)).collect(Collectors.toSet());
//        Set<Budget> budget = budgetRepository.findAll().stream().filter(budget1 -> budget1.getOptions() == optionBudgets && budget1.getStatusBudget() == StatusBudget.APPROVED).collect(Collectors.toSet());
//        if(budget.size() >= 1)
//            return new ResponseEntity<>("No se puede borrar",HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>("Eliminado exitosamente",HttpStatus.OK);
    }


}
