package esdi.Services.dtos;

import esdi.Services.enums.Neighborhood;
import esdi.Services.enums.UserType;
import esdi.Services.models.User;
import lombok.Data;

@Data
public class ClientDTO {
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

    public ClientDTO(){}

    public ClientDTO(User user){
        this.id = user.getId();
        this.dni = user.getDni();
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.address = user.getAddress();
        this.neighborhood = user.getNeighborhood();
        this.phone = user.getPhone();
        this.cellphone = user.getCellphone();
        this.email = user.getEmail();
        this.user = user.getUser();
        this.password = user.getPassword();
        this.userType = user.getUserType();
    }


}
