package esdi.Services.dtos;

import esdi.Services.enums.Neighborhood;
import esdi.Services.enums.UserType;
import esdi.Services.models.Client;
import lombok.Data;

@Data
public class AdminDTO {
    private long id;

    private String dni;
    private String firstName;
    private String lastName;
    private String email;
    private String user;
    private String password;
    private UserType userType;

    public AdminDTO(){}

    public AdminDTO(Client client){
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