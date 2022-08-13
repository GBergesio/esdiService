package esdi.Services.services;

import esdi.Services.dtos.TechnicianDTO;
import esdi.Services.models.Technician;

import java.util.List;

public interface TechnicianService {

        List<TechnicianDTO> getAllTech();

        void saveTechnician(TechnicianDTO technicianDTO);

        Technician getTechDNI(String dni);

        Technician getTechUserName(String user);
        void saveChanges(Technician technician);
        void updateFirstName(Technician technician, String firstName);
        void updateLastName(Technician technician, String lastName);
        void updateEmail(Technician technician,String email);
        void updateUserName(Technician technician,String userName);
        void updatePassword(Technician technician,String password);
}
