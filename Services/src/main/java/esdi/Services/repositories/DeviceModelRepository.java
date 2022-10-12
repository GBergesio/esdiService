package esdi.Services.repositories;

import esdi.Services.models.devices.DeviceModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface DeviceModelRepository extends JpaRepository<DeviceModel, Long> {

    DeviceModel findByModel(String model);
}
