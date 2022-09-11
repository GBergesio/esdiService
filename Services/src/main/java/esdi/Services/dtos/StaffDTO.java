package esdi.Services.dtos;

import esdi.Services.enums.UserType;
import esdi.Services.models.users.Client;
import lombok.Data;

@Data
public class StaffDTO {
    private long id;
    private String dni;
    private String firstName;
    private String lastName;
    private String email;
    private String user;
    private String password;
    private UserType userType;

    public StaffDTO(){}

    public StaffDTO(Client client){
        this.id = client.getId();
        this.dni = client.getDni();
        this.firstName = client.getFirstName();
        this.lastName = client.getLastName();
        this.email = client.getEmail();
        this.user = client.getUser();
        this.password = client.getPassword();
        this.userType = client.getUserType();
    }


}