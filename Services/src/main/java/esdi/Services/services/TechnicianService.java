package esdi.Services.services;

import esdi.Services.dtos.TechnicianDTO;
import esdi.Services.models.Technician;

public interface TechnicianService {

        void saveTechnician(TechnicianDTO technicianDTO);

        Technician getTechDNI(String dni);

        Technician getTechUserName(String user);
}
