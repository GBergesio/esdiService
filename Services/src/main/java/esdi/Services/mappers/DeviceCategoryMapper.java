package esdi.Services.mappers;

import esdi.Services.common.GenericaMapper;
import esdi.Services.dtos.devices.DeviceCategoryDTO;
import esdi.Services.models.devices.DeviceCategory;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public abstract class DeviceCategoryMapper implements GenericaMapper<DeviceCategoryDTO, DeviceCategory> {

}