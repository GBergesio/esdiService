package esdi.Services.services.implement;

import esdi.Services.dtos.TechnicianDTO;
import esdi.Services.enums.UserType;
import esdi.Services.models.Client;
import esdi.Services.models.Technician;
import esdi.Services.repositories.TechnicianRepository;
import esdi.Services.services.TechnicianService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TechnicianServiceImpl implements TechnicianService {

    @Autowired
    TechnicianRepository technicianRepository;

    @Override
    public List<Technician> getAllTech() {
        return technicianRepository.findAll();
    }

    @Override
    public void saveTechnician(TechnicianDTO technicianDTO) {
        technicianRepository.save(new Technician(technicianDTO.getDni(),technicianDTO.getFirstName(),technicianDTO.getLastName(), technicianDTO.getAddress(),technicianDTO.getNeighborhood(), technicianDTO.getPhone(),technicianDTO.getCellphone(), technicianDTO.getEmail(), technicianDTO.getUser(), technicianDTO.getPassword(), UserType.TECHNICIAN));
    }

    @Override
    public Technician getTechDNI(String dni) {
        return technicianRepository.findByDni(dni);
    }

    @Override
    public Technician getTechUserName(String user) {
        return technicianRepository.findByUser(user);
    }

    @Override
    public void saveChanges(Technician technician) {
        technicianRepository.save(technician);
    }
    @Override
    public void updateFirstName(Technician technician, String firstName) {
        technician.setFirstName(firstName);
    }
    @Override
    public void updateLastName(Technician technician, String lastName) {
        technician.setLastName(lastName);
    }
    @Override
    public void updateEmail(Technician technician, String email) {
        technician.setEmail(email);
    }
    @Override
    public void updateUserName(Technician technician, String userName) {
        technician.setUser(userName);
    }
    @Override
    public void updatePassword(Technician technician, String password) {
        technician.setPassword(password);
    }
}
