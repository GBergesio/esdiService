package esdi.Services.repositories;

import esdi.Services.models.devices.DeviceCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface DeviceCategoryRepository extends JpaRepository<DeviceCategory, Long> {

    DeviceCategory findByNameCategory(String nameCategory);
}
