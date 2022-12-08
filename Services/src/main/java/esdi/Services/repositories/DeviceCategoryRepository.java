package esdi.Services.repositories;

import esdi.Services.models.devices.DeviceCategory;
import esdi.Services.models.users.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource
public interface DeviceCategoryRepository extends JpaRepository<DeviceCategory, Long> {

    DeviceCategory findByName(String name);
    public List<DeviceCategory> findAllByCompany(Company company);
}
