package esdi.Services.mappers;

import esdi.Services.common.GenericaMapper;
import esdi.Services.dtos.OptionComponentDTO;
import esdi.Services.dtos.budget.OptionBudgetDTO;
import esdi.Services.models.budgets.OptionBudget;
import esdi.Services.models.budgets.OptionComponent;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public abstract class OptionComponentMapper implements GenericaMapper<OptionComponentDTO, OptionComponent>{
}

