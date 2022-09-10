package esdi.Services.mappers;
import esdi.Services.common.GenericaMapper;
import esdi.Services.dtos.ServiceDTO;
import esdi.Services.models.products.ServiceArt;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public abstract class ServiceMapper implements GenericaMapper<ServiceDTO, ServiceArt> {
}
