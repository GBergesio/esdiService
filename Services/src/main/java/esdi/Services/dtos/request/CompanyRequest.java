package esdi.Services.dtos.request;

import esdi.Services.enums.UserType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CompanyRequest {

    private String cuit;
    private String name;
    private String email;
    private String phone;
    private String sector;
    private boolean active;

}
