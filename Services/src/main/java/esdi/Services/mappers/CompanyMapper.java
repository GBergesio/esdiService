package esdi.Services.mappers;

import esdi.Services.common.GenericaMapper;
import esdi.Services.dtos.CompanyDTO;
import esdi.Services.models.users.Company;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public abstract class CompanyMapper implements GenericaMapper<CompanyDTO, Company> {
}
