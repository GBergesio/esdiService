package esdi.Services.dtos.request;

import esdi.Services.enums.UserType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class StaffRequest {
    private String dni;
    private String firstName;
    private String lastName;
    private String email;
    private String user;
    private String password;
    private UserType userType;
}
