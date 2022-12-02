package esdi.Services.services.budget;

import esdi.Services.dtos.budget.BudgetDTO;
import esdi.Services.dtos.budget.BudgetRequest;
import esdi.Services.enums.StatusBudget;
import esdi.Services.mappers.BudgetMapper;
import esdi.Services.models.Order;
import esdi.Services.models.budgets.Budget;
import esdi.Services.models.users.Company;
import esdi.Services.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class BudgetServiceImpl implements BudgetService{

    @Autowired
    BudgetRepository budgetRepository;

    @Autowired
    OrderRepository orderRepository;
    @Autowired
    CompanyRepository companyRepository;

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
    public ResponseEntity<?> allBudgetsByCompany(Authentication authentication) {
        Company company = companyRepository.findByUsername(authentication.getName());
        List<Budget> budgetsByCompany = budgetRepository.findAllByCompany(company);

        return new ResponseEntity<>(budgetMapper.toDTO(budgetsByCompany), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> createBudget(BudgetRequest request,Long idOrder, Authentication authentication) {
        Company company = companyRepository.findByUsername(authentication.getName());
        Order order = orderRepository.findById(idOrder).orElse(null);
        List<Order> orders = orderRepository.findAllByCompany(company);
        boolean existBudget = budgetRepository.findAllByCompany(company).stream().anyMatch(budget -> budget.getOrder().equals(order));

        try{
            Budget newBudget = new Budget();

            if (!orders.contains(order)){
                return new ResponseEntity<>("No se encontró orden con el id seleccionado", HttpStatus.BAD_REQUEST);
            }
            if(existBudget){
                return new ResponseEntity<>("La orden ya posee un presupuesto", HttpStatus.BAD_REQUEST);
            }
            if(request.getDueDate() != null){
                newBudget.setDueDate(request.getDueDate());
            }
            if(request.getDueDate() == null){
                newBudget.setDueDate(null);
            }

            newBudget.setBudgetNumber(999);
            newBudget.setClient(order.getClient().getFirstName() + " " + order.getClient().getLastName());
            newBudget.setOrder(order);
            newBudget.setOrderNumber(order.getOrderNumber());
            newBudget.setStatusBudget(StatusBudget.ON_HOLD);
            newBudget.setIssueDate(LocalDateTime.now());
            newBudget.setConfirmationDate(null);
            newBudget.setCompany(company);

            budgetRepository.save(newBudget);

            return new ResponseEntity<>("Presupuesto creado correctamente",HttpStatus.CREATED);
        }
        catch(Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,e.getMessage());
        }
    }

    @Override
    public ResponseEntity<?> updateBudget(BudgetRequest request,Long idBudget, Authentication authentication) {
        Company company = companyRepository.findByUsername(authentication.getName());
        Budget budget = budgetRepository.findById(idBudget).orElse(null);
        List<Budget> budgets = budgetRepository.findAllByCompany(company);

        if (!budgets.contains(budget)){
            return new ResponseEntity<>("No se encontró presupuesto con el id seleccionado", HttpStatus.BAD_REQUEST);
        }
        if(request.getStatusBudget() != budget.getStatusBudget()){
            budget.setStatusBudget(request.getStatusBudget());
        }
        if(request.getConfirmationDate() != null){
            budget.setConfirmationDate(LocalDateTime.now());
        }
        if(request.getConfirmationDate() == null){
            budget.setConfirmationDate(null);
        }
        if(request.getDueDate() != null){
            budget.setDueDate(request.getDueDate());
        }
        if(request.getDueDate() == null){
            budget.setDueDate(null);
        }

        budget.setIssueDate(budget.getIssueDate());

        budgetRepository.save(budget);

        return new ResponseEntity<>("Presupuesto actualizado correctamente",HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> deleteBudget(Long id, Authentication authentication) {
        Company company = companyRepository.findByUsername(authentication.getName());
        Budget budget = budgetRepository.findById(id).orElse(null);
        List<Budget> budgets = budgetRepository.findAllByCompany(company);
        boolean optionsExists = budgets.stream().anyMatch(b -> b.getOptions().size() > 0);

        if(!budgets.contains(budget)){
            return new ResponseEntity<>("No se encontró presupuesto con el ID ingresado",HttpStatus.BAD_REQUEST);
        }
        if(optionsExists){
            return new ResponseEntity<>("No se puede eliminar el presupuesto porque tiene opciones creadas",HttpStatus.BAD_REQUEST);
        }
        if(!budget.getStatusBudget().equals(StatusBudget.ON_HOLD)){
            return new ResponseEntity<>("No se puede eliminar el presupuesto porque el presupuesto ya tuvo confirmacion",HttpStatus.BAD_REQUEST);
        }
        else{
            budgetRepository.delete(budget);
        }

        return new ResponseEntity<>("Eliminado correctamente",HttpStatus.OK);
    }
}
