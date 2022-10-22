package esdi.Services.services.budget;

import esdi.Services.dtos.budget.BudgetDTO;
import esdi.Services.dtos.budget.BudgetRequest;
import esdi.Services.enums.StatusBudget;
import esdi.Services.mappers.BudgetMapper;
import esdi.Services.models.Order;
import esdi.Services.models.budgets.Budget;
import esdi.Services.models.budgets.OptionBudget;
import esdi.Services.models.budgets.OptionComponent;
import esdi.Services.models.users.Client;
import esdi.Services.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class BudgetServiceImpl implements BudgetService{

    @Autowired
    BudgetRepository budgetRepository;

    @Autowired
    OptionBudgetRepository optionBudgetRepository;

    @Autowired
    OptionComponentRepository optionComponentRepository;

    @Autowired
    ClientRepository clientRepository;

    @Autowired
    OrderRepository orderRepository;


    @Autowired
    BudgetMapper budgetMapper;

    @Override
    public Budget saveBudget(Budget budget) {
        return budgetRepository.save(budget);
    }

    @Override
    public BudgetDTO saveBudgetDTO(Budget budget) {
        return budgetMapper.toDTO(budgetRepository.save(budget));
    }

    @Override
    public List<BudgetDTO> findAllDTO() {
        return budgetMapper.toDTO(budgetRepository.findAll());
    }

    @Override
    public ResponseEntity<?> allBudgets() {
        return new ResponseEntity<>(findAllDTO(),HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> findById(Long id) {
        BudgetDTO budgetDTO = budgetMapper.toDTO(budgetRepository.findById(id).orElse(null));

        if (id.equals(null) || id == null){
            return new ResponseEntity<>("Ingresar dato",HttpStatus.BAD_REQUEST);
        }

        if (budgetDTO == null){
            return new ResponseEntity<>("No se encontró presupuesto con el ID ingresado",HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(budgetDTO, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> createBudget(BudgetRequest request, Long idClient, Long idOrder) {

        try{
            Client client = clientRepository.findById(idClient).orElse(null);
            Order order = orderRepository.findById(idOrder).orElse(null);

            Set<Budget> budgets = budgetRepository.findAll().stream().filter(budget -> budget.getOrder().getId() == idOrder).collect(Collectors.toSet());

            Budget newBudget = new Budget();
            if(client == null)
                return new ResponseEntity<>("Error, no se encuentra la orden o el cliente", HttpStatus.BAD_REQUEST);
            if(order == null)
                return new ResponseEntity<>("Error, no se encuentra el cliente", HttpStatus.BAD_REQUEST);

            if(budgets.size() >= 1){
                return new ResponseEntity<>("Error, la orden ya posee un presupuesto", HttpStatus.BAD_REQUEST);
            }

            if(client != null && order != null){

                newBudget.setBudgetNumber(999);
                newBudget.setClient(client.getFirstName() + " " + client.getLastName());
                newBudget.setOrder(order);
                newBudget.setOrderNumber(order.getOrderNumber());
                newBudget.setStatusBudget(StatusBudget.ON_HOLD);
                newBudget.setIssueDate(LocalDateTime.now());
            }
            return new ResponseEntity<>(this.saveBudgetDTO(newBudget),HttpStatus.CREATED);
        }
        catch(Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,e.getMessage());
        }
    }

    @Override
    public ResponseEntity<?> updateBudget(Long id) {
        return null;
    }

    @Override
    public ResponseEntity<?> deleteBudget(Long id) {

        Budget budget = budgetRepository.findById(id).orElse(null);

        if(budget == null)
            return new ResponseEntity<>("No se encontró presupuesto con el ID ingresado",HttpStatus.BAD_REQUEST);

        if(budget != null){
                if(budget.getOptions().size() > 0)
                    return new ResponseEntity<>("No se puede eliminar el presupuesto porque tiene opciones creadas asociadas",HttpStatus.BAD_REQUEST);
                else budgetRepository.delete(budget);
        }


        return new ResponseEntity<>("Eliminado exitosamente",HttpStatus.OK);
    }
}
