package esdi.Services.services;

import esdi.Services.dtos.TechnicianDTO;
import esdi.Services.models.Technician;

import java.util.List;

public interface TechnicianService {

        List<Technician> getAllTech();

        void saveTechnician(TechnicianDTO technicianDTO);

        Technician getTechDNI(String dni);

        Technician getTechUserName(String user);
        //    void saveTechnician(ClientDTO clientDTO);
}
