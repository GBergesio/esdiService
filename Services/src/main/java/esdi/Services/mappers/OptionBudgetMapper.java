package esdi.Services.mappers;
import esdi.Services.common.GenericaMapper;
import esdi.Services.dtos.budget.OptionBudgetDTO;
import esdi.Services.models.budgets.OptionBudget;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public abstract class OptionBudgetMapper implements GenericaMapper<OptionBudgetDTO, OptionBudget> {
}