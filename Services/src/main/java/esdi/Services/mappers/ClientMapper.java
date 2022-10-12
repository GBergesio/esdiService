package esdi.Services.mappers;

import esdi.Services.common.GenericaMapper;
import esdi.Services.dtos.ClientDTO;
import esdi.Services.models.users.Client;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public abstract class ClientMapper implements GenericaMapper<ClientDTO, Client> {
}
