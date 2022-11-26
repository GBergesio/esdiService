package esdi.Services.services.implement;

import esdi.Services.dtos.ClientDTO;
import esdi.Services.dtos.request.ClientRequest;
import esdi.Services.enums.UserType;
import esdi.Services.mappers.ClientMapper;
import esdi.Services.models.Order;
import esdi.Services.models.devices.Device;
import esdi.Services.models.users.Client;
import esdi.Services.models.users.Company;
import esdi.Services.models.users.Neighborhood;
import esdi.Services.models.users.Staff;
import esdi.Services.repositories.*;
import esdi.Services.services.ClientService;
import esdi.Services.services.CompanyService;
import esdi.Services.utils.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
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
    CompanyRepository companyRepository;
    @Autowired
    StaffRepository staffRepository;

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    ClientMapper clientMapper;

    @Autowired
    PasswordEncoder passwordEncoder;

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
    public ResponseEntity<?> getAllClientsAuth(Authentication authentication) {
        Company company = companyRepository.findByUsername(authentication.getName());
        Staff staff = staffRepository.findByUser(authentication.getName());

        if(company != null){
            List<Client> clients = clientRepository.findAllByCompany(company);
            return new ResponseEntity<>(clientMapper.toDTO(clients), HttpStatus.OK);
        }
        if(staff !=null){
            Company companyStaff = staff.getCompany();
            List<Client> clients = clientRepository.findAllByCompany(companyStaff);
            return new ResponseEntity<>(clientMapper.toDTO(clients), HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
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
    public ResponseEntity<?> getClientByIdCC(Long id, Authentication authentication) {
        Company company = companyRepository.findByUsername(authentication.getName());
        Client client = clientRepository.findById(id).orElse(null);
        List<Client> clients = clientRepository.findAllByCompany(company);

        if (!clients.contains(client)) {
            return new ResponseEntity<>("Cliente no encontrado", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(clientMapper.toDTO(client), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> getClientByDni(String dni, Authentication authentication) {
        Company company = companyRepository.findByUsername(authentication.getName());
        Client client = clientRepository.findByDni(dni);
        List<Client> clients = clientRepository.findAllByCompany(company);
        if (!clients.contains(client)) {
            return new ResponseEntity<>("Cliente no encontrado", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(clientMapper.toDTO(client), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> getClientsByCompany(Long id) {
        Company company = companyRepository.findById(id).orElse(null);
        List<Client> allClients = clientRepository.findAllByCompany(company);

        return new ResponseEntity<>(clientMapper.toDTO(allClients), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> createNewClient(ClientRequest clientRequest, Authentication authentication) {
        Company company = companyRepository.findByUsername(authentication.getName());
        List<Client> clients = clientRepository.findAllByCompany(company);
        Boolean clientExist = clients.stream().anyMatch(client -> client.getDni().equals(clientRequest.getDni()));

        if (clientRequest.getDni() == null || clientRequest.getDni().isEmpty() || clientRequest.getDni().isBlank())
            return new ResponseEntity<>("Dni requerido", HttpStatus.BAD_REQUEST);
        if (clientExist)
            return new ResponseEntity<>("Dni en uso", HttpStatus.BAD_REQUEST);
        if (clientRequest.getFirstName() == null || clientRequest.getFirstName().isEmpty() || clientRequest.getFirstName().isBlank())
            return new ResponseEntity<>("Nombre requerido", HttpStatus.BAD_REQUEST);
        if (clientRequest.getLastName() == null || clientRequest.getLastName().isEmpty() || clientRequest.getLastName().isBlank())
            return new ResponseEntity<>("Apellido requerido", HttpStatus.BAD_REQUEST);
        if (clientRequest.getAddress() == null || clientRequest.getAddress().isEmpty() || clientRequest.getAddress().isBlank())
            return new ResponseEntity<>("Direccion requerida", HttpStatus.BAD_REQUEST);
        if (clientRequest.getCellphone() == null || clientRequest.getCellphone().isEmpty() || clientRequest.getCellphone().isBlank())
            return new ResponseEntity<>("Celular requerido", HttpStatus.BAD_REQUEST);

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
        newClient.setUser(clientRequest.getDni());
        newClient.setPassword(passwordEncoder.encode(clientRequest.getDni()));
        newClient.setMoreDetails(clientRequest.getMoreDetails());
        newClient.setUserType(UserType.CLIENT);
        newClient.setCompany(company);
        clientRepository.save(newClient);
        return new ResponseEntity<>(clientMapper.toDTO(newClient), HttpStatus.CREATED);

    }

    @Override
    public ResponseEntity<?> updateClient(Long id, ClientRequest clientRequest, Authentication authentication) {
        Company company = companyRepository.findByUsername(authentication.getName());
        List<Client> clients = clientRepository.findAllByCompany(company);
        Client client = clientRepository.findById(id).orElse(null);

        if (!clients.contains(client))
            return new ResponseEntity<>("Cliente no encontrado", HttpStatus.BAD_REQUEST);

//        Boolean clientExist = clients.stream().anyMatch(cl -> cl.getDni().equals(clientRequest.getDni()));

        if (clientRequest.getDni() == null || clientRequest.getDni().isEmpty() || clientRequest.getDni().isBlank() || clientRequest.getDni() == client.getDni()){
            client.setDni(client.getDni());
        }

//        if (clientExist){
//            return new ResponseEntity<>("Dni en uso", HttpStatus.BAD_REQUEST);
//        }

        if (clientRequest.getFirstName() == null || clientRequest.getFirstName().isEmpty() || clientRequest.getFirstName().isBlank()){
            return new ResponseEntity<>("Nombre requerido", HttpStatus.BAD_REQUEST);
        }

        if (clientRequest.getLastName() == null || clientRequest.getLastName().isEmpty() || clientRequest.getLastName().isBlank()){
            return new ResponseEntity<>("Apellido requerido", HttpStatus.BAD_REQUEST);
        }
        if (clientRequest.getAddress() == null || clientRequest.getAddress().isEmpty() || clientRequest.getAddress().isBlank()){
            return new ResponseEntity<>("Direccion requerida", HttpStatus.BAD_REQUEST);
        }

        if (clientRequest.getCellphone() == null || clientRequest.getCellphone().isEmpty() || clientRequest.getCellphone().isBlank()){
            return new ResponseEntity<>("Celular requerido", HttpStatus.BAD_REQUEST);
        }

        Neighborhood neighborhood = neighborhoodRepository.findById(clientRequest.getNeighborhoodId()).orElse(null);
        if (neighborhood == null)
            return new ResponseEntity<>("Barrio no encontrado", HttpStatus.BAD_REQUEST);

        client.setDni(clientRequest.getDni());
        client.setFirstName(clientRequest.getFirstName());
        client.setLastName(clientRequest.getLastName());
        client.setAddress(clientRequest.getAddress());
        client.setNeighborhood(neighborhood);
        client.setPhone(clientRequest.getPhone());
        client.setCellphone(clientRequest.getCellphone());
        client.setEmail(clientRequest.getEmail().toLowerCase());
        client.setUser(clientRequest.getDni());
        client.setUserType(UserType.CLIENT);
        client.setCompany(company);
        clientRepository.save(client);

        return new ResponseEntity<>(clientMapper.toDTO(client), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> deleteClient(Long id, Authentication authentication) {
        Company company = companyRepository.findByUsername(authentication.getName());
        List<Client> clients = clientRepository.findAllByCompany(company);

        Client client = clientRepository.findById(id).orElse(null);

        if (!clients.contains(client)) {
            return new ResponseEntity<>("Cliente no encontrado", HttpStatus.BAD_REQUEST);
        }

        List<Device> allDevices = deviceRepository.findAll().stream().filter(device -> device.getClient().getId() == id).collect(Collectors.toList());
        List<Order> allOrders = orderRepository.findAll().stream().filter(order -> order.getClient().getId() == id).collect(Collectors.toList());

        if(allOrders.size() >= 1)
            return new ResponseEntity<>("El cliente no puede eliminarse ya que el cliente posee ordenes a su nombre", HttpStatus.BAD_REQUEST);

        if(allDevices.size() >= 1)
            return new ResponseEntity<>("El cliente no puede eliminarse ya que el cliente posee dispositivos a su nombre", HttpStatus.BAD_REQUEST);

        clientRepository.delete(client);

        return new ResponseEntity<>("Usuario eliminado correctamente", HttpStatus.OK);
    }


}
