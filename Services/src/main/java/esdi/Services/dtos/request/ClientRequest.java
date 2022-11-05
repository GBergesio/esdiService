package esdi.Services.dtos.request;

import esdi.Services.enums.UserType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class ClientRequest {
    private String dni;
    private String firstName;
    private String lastName;
    private String address;
    private Long neighborhoodId;
    private String phone;
    private String cellphone;
    private String email;
    private UserType userType;
}
