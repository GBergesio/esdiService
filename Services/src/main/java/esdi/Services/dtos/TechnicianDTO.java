package esdi.Services.dtos;

import esdi.Services.enums.Neighborhood;
import esdi.Services.enums.UserType;
import esdi.Services.models.User;
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

    public TechnicianDTO(User user){
        this.id = user.getId();
        this.dni = user.getDni();
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.email = user.getEmail();
        this.user = user.getUser();
        this.password = user.getPassword();
        this.userType = user.getUserType();
    }


}