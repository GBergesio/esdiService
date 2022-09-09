package esdi.Services.mappers;

import esdi.Services.common.GenericaMapper;
import esdi.Services.dtos.DolarDTO;
import esdi.Services.models.Dolar;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public abstract class DolarMapper implements GenericaMapper<DolarDTO, Dolar> {
}


