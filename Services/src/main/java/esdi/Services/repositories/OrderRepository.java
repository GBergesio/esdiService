package esdi.Services.repositories;

import esdi.Services.models.Order;
import esdi.Services.models.devices.Device;
import esdi.Services.models.users.Client;
import esdi.Services.models.users.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource
public interface OrderRepository extends JpaRepository<Order,Long> {

    public Order findByOrderNumber(int orderNumber);
    public List<Order> findAllByDevice(Device device);
    public List<Order> findAllByClient(Client client);
    public List<Order> findAllByCompany(Company company);
    public Order findByCompany(Company company);
}

