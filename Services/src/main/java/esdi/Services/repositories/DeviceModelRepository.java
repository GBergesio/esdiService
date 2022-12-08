package esdi.Services.repositories;

import esdi.Services.models.devices.DeviceModel;
import esdi.Services.models.users.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource
public interface DeviceModelRepository extends JpaRepository<DeviceModel, Long> {

    DeviceModel findByName(String name);
    public List<DeviceModel> findAllByCompany(Company company);
}
