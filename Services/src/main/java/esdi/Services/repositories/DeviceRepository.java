package esdi.Services.repositories;


import esdi.Services.models.devices.Device;
import esdi.Services.models.devices.DeviceCategory;
import esdi.Services.models.devices.DeviceModel;
import esdi.Services.models.products.Product;
import esdi.Services.models.users.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource
public interface DeviceRepository extends JpaRepository<Device, Long> {

    public List<Device> findByClient(Client client);
    public List<Device> findByCategory(DeviceCategory category);
    public List<Device> findByModel(DeviceModel model);
    public Product findByDescription(String description);
    public Product findBySerial(String serial);
}
