package esdi.Services.dtos;

import esdi.Services.enums.Neighborhood;
import esdi.Services.enums.UserType;
import esdi.Services.models.Client;
import lombok.Data;

@Data
public class TechnicianDTO {
    private long id;

    private String dni;
    private String firstName;
    private String lastName;
    private String address;
    private Neighborhood neighborhood;
    private String phone;
    private String cellphone;
    private String email;
    private String user;
    private String password;
    private UserType userType;

    public TechnicianDTO(){}

    public TechnicianDTO(Client client){
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