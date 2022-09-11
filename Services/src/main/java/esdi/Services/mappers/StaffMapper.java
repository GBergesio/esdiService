package esdi.Services.mappers;

import esdi.Services.common.GenericaMapper;
import esdi.Services.dtos.StaffDTO;
import esdi.Services.models.users.Staff;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public abstract class StaffMapper implements GenericaMapper<StaffDTO, Staff> {
}
