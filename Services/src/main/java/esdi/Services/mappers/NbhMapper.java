package esdi.Services.mappers;

import esdi.Services.common.GenericaMapper;
import esdi.Services.dtos.NeighborhoodDTO;
import esdi.Services.models.users.Neighborhood;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public abstract class NbhMapper implements GenericaMapper<NeighborhoodDTO, Neighborhood> {
}
