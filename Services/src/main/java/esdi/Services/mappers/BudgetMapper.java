package esdi.Services.mappers;

import esdi.Services.common.GenericaMapper;
import esdi.Services.dtos.budget.BudgetDTO;
import esdi.Services.models.budgets.Budget;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public abstract class BudgetMapper implements GenericaMapper<BudgetDTO, Budget> {
}
