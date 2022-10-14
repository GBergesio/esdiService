package esdi.Services.repositories;

import esdi.Services.models.Order;
import esdi.Services.models.devices.Device;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource
public interface OrderRepository extends JpaRepository<Order,Long> {

    public Order findByOrderNumber(int orderNumber);

    public List<Order> findAllByDevice(Device device);
}
