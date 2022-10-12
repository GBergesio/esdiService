package esdi.Services.dtos;

import esdi.Services.models.users.Neighborhood;
import esdi.Services.enums.UserType;
import esdi.Services.models.users.Client;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ClientDTO {
    private long id;
    private String dni;
    private String firstName;
    private String lastName;
    private String address;
    private NeighborhoodDTO neighborhood;
    private String phone;
    private String cellphone;
    private String email;
    private String user;
    private String password;
    private UserType userType;


}
