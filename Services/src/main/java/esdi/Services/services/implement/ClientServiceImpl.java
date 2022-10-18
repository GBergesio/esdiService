package esdi.Services.services.implement;

import esdi.Services.dtos.ClientDTO;
import esdi.Services.dtos.request.ClientRequest;
import esdi.Services.mappers.ClientMapper;
import esdi.Services.models.Order;
import esdi.Services.models.devices.Device;
import esdi.Services.models.users.Client;
import esdi.Services.models.users.Neighborhood;
import esdi.Services.repositories.ClientRepository;
import esdi.Services.repositories.DeviceRepository;
import esdi.Services.repositories.NeighborhoodRepository;
import esdi.Services.repositories.OrderRepository;
import esdi.Services.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;



@Service
public class ClientServiceImpl implements ClientService {
    @Autowired
    ClientRepository clientRepository;
    @Autowired
    DeviceRepository deviceRepository;

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    ClientMapper clientMapper;
    @Autowired
    NeighborhoodRepository neighborhoodRepository;

    @Override
    public List<ClientDTO> findAllDTO(){
        List<Client> allClients = clientRepository.findAll();
        return clientMapper.toDTO(allClients);
    }

    @Override
    public ResponseEntity<?> allClients() {
        return new ResponseEntity<>(findAllDTO(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> getClientById(Long id) {
        Client client = clientRepository.findById(id).orElse(null);
        if (client == null) {
            return new ResponseEntity<>("Cliente no encontrado", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(clientMapper.toDTO(client), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> getClientByDni(String dni) {
        Client client = clientRepository.findByDni(dni);
        if (client == null) {
            return new ResponseEntity<>("Cliente no encontrado", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(clientMapper.toDTO(client), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> createNewClient(ClientRequest clientRequest) {

        if (clientRequest.getDni() == null || clientRequest.getDni().isEmpty() || clientRequest.getDni().isBlank())
            return new ResponseEntity<>("Dni requerido", HttpStatus.BAD_REQUEST);
        if (clientRepository.findByDni(clientRequest.getDni()) != null)
            return new ResponseEntity<>("Dni en uso", HttpStatus.BAD_REQUEST);
        if (clientRequest.getEmail() == null || clientRequest.getEmail().isEmpty() || clientRequest.getEmail().isBlank())
            return new ResponseEntity<>("Email requerida", HttpStatus.BAD_REQUEST);
        if (clientRepository.findByEmail(clientRequest.getEmail().toLowerCase()) != null)
            return new ResponseEntity<>("Email en uso", HttpStatus.BAD_REQUEST);
        if (clientRequest.getFirstName() == null || clientRequest.getFirstName().isEmpty() || clientRequest.getFirstName().isBlank())
            return new ResponseEntity<>("Nombre requerido", HttpStatus.BAD_REQUEST);
        if (clientRequest.getLastName() == null || clientRequest.getLastName().isEmpty() || clientRequest.getLastName().isBlank())
            return new ResponseEntity<>("Apellido requerido", HttpStatus.BAD_REQUEST);
        if (clientRequest.getAddress() == null || clientRequest.getAddress().isEmpty() || clientRequest.getAddress().isBlank())
            return new ResponseEntity<>("Direccion requerida", HttpStatus.BAD_REQUEST);
        if (clientRequest.getCellphone() == null || clientRequest.getCellphone().isEmpty() || clientRequest.getCellphone().isBlank())
            return new ResponseEntity<>("Celular requerido", HttpStatus.BAD_REQUEST);
        if (clientRequest.getPassword() == null || clientRequest.getPassword().isEmpty() || clientRequest.getPassword().isBlank())
            return new ResponseEntity<>("Contraseña requerida", HttpStatus.BAD_REQUEST);
        if (clientRequest.getUser() == null || clientRequest.getUser().isEmpty() || clientRequest.getUser().isBlank())
            return new ResponseEntity<>("Usuario requerido", HttpStatus.BAD_REQUEST);
        if (clientRepository.findByUser(clientRequest.getUser().toLowerCase()) != null)
            return new ResponseEntity<>("Usuario en uso", HttpStatus.BAD_REQUEST);

        Neighborhood neighborhood = neighborhoodRepository.findById(clientRequest.getNeighborhoodId()).orElse(null);
        if (neighborhood == null)
            return new ResponseEntity<>("Barrio no encontrado", HttpStatus.BAD_REQUEST);

        Client newClient = new Client();
        newClient.setDni(clientRequest.getDni());
        newClient.setFirstName(clientRequest.getFirstName());
        newClient.setLastName(clientRequest.getLastName());
        newClient.setAddress(clientRequest.getAddress());
        newClient.setNeighborhood(neighborhood);
        newClient.setPhone(clientRequest.getPhone());
        newClient.setCellphone(clientRequest.getCellphone());
        newClient.setEmail(clientRequest.getEmail().toLowerCase());
        newClient.setUser(clientRequest.getUser());
        newClient.setPassword(clientRequest.getPassword());
        newClient.setUserType(clientRequest.getUserType());

        clientRepository.save(newClient);
        return new ResponseEntity<>(clientMapper.toDTO(newClient), HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<?> updateClient(Long id, ClientRequest clientRequest) {
        Client clientDB = clientRepository.findById(id).orElse(null);
        if (clientDB == null)
            return new ResponseEntity<>("Cliente no encontrado", HttpStatus.BAD_REQUEST);


        return new ResponseEntity<>(clientMapper.toDTO(clientDB), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> deleteClient(Long id) {
        Client clientDB = clientRepository.findById(id).orElse(null);

        List<Device> allDevices = deviceRepository.findAll().stream().filter(device -> device.getClient().getId() == id).collect(Collectors.toList());
        List<Order> allOrders = orderRepository.findAll().stream().filter(order -> order.getClient().getId() == id).collect(Collectors.toList());

        if (clientDB == null)
            return new ResponseEntity<>("Cliente no encontrado", HttpStatus.BAD_REQUEST);

        if(allOrders.size() >= 1)
            return new ResponseEntity<>("El cliente no puede eliminarse ya que posee ordenes a su nombre", HttpStatus.BAD_REQUEST);

        if(allDevices.size() >= 1)
            return new ResponseEntity<>("El cliente no puede eliminarse ya que posee dispositivos a su nombre", HttpStatus.BAD_REQUEST);

        clientRepository.delete(clientDB);

        return new ResponseEntity<>("Usuario eliminado correctamente", HttpStatus.OK);
    }


}
