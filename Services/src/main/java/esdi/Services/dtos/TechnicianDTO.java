package esdi.Services.dtos;

import esdi.Services.enums.UserType;
import esdi.Services.models.users.Technician;
import lombok.Data;

@Data
public class TechnicianDTO {
    private long id;

    private String dni;
    private String firstName;
    private String lastName;
    private String email;
    private String user;
    private String password;
    private UserType userType;

    public TechnicianDTO(){}

    public TechnicianDTO(Technician technician){
        this.id = technician.getId();
        this.dni = technician.getDni();
        this.firstName = technician.getFirstName();
        this.lastName = technician.getLastName();
        this.email = technician.getEmail();
        this.user = technician.getUser();
        this.password = technician.getPassword();
        this.userType = technician.getUserType();
    }

}