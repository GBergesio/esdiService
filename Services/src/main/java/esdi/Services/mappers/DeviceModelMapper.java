package esdi.Services.mappers;

import esdi.Services.common.GenericaMapper;
import esdi.Services.dtos.devices.DeviceModelDTO;
import esdi.Services.models.devices.DeviceModel;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public abstract class DeviceModelMapper implements GenericaMapper<DeviceModelDTO, DeviceModel> {

}