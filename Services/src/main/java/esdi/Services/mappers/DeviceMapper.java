package esdi.Services.mappers;

import esdi.Services.common.GenericaMapper;
import esdi.Services.dtos.devices.DeviceDTO;
import esdi.Services.models.devices.Device;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public abstract class DeviceMapper implements GenericaMapper<DeviceDTO, Device> {

}


