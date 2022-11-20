package esdi.Services.dtos;

import esdi.Services.enums.CompanyPlan;
import esdi.Services.enums.UserType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class CompanyDTO {
    private Long id;
    private String cuit;
    private String name;
    private String email;
    private String phone;
    private String sector;
    private String username;
    private String password;
    private UserType userType;
    private CompanyPlan plan;
    private boolean active;
    private Set<StaffDTO> staffs;

}
