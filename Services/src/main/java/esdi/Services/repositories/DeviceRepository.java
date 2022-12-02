package esdi.Services.repositories;

import esdi.Services.models.Order;
import esdi.Services.models.devices.Device;
import esdi.Services.models.devices.DeviceCategory;
import esdi.Services.models.devices.DeviceModel;
import esdi.Services.models.users.Client;
import esdi.Services.models.users.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource
public interface DeviceRepository extends JpaRepository<Device, Long> {

    public List<Device> findAllByClient(Client client);
    public List<Device> findAllByCategory(DeviceCategory category);
    public List<Device> findAllByModel(DeviceModel model);
    public List<Device> findAllBySerial(String serial);
    public List<Device> findAllByCompany(Company company);
}
