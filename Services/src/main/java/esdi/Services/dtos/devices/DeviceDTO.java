package esdi.Services.dtos.devices;

import esdi.Services.dtos.BrandDTO;
import esdi.Services.dtos.ClientDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Setter
@Getter
@NoArgsConstructor
public class DeviceDTO {

    private long id;
    private DeviceCategoryDTO category;
    private BrandDTO brand;
    private DeviceModelDTO model;
    private String description;
    private String serial;
    private Boolean deleted;
    private ClientDTO client;

}
