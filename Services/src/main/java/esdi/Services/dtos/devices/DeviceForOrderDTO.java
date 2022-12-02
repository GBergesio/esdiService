package esdi.Services.dtos.devices;

import esdi.Services.dtos.BrandDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class DeviceForOrderDTO {

    private long id;
    private DeviceCategoryDTO category;
    private BrandDTO brand;
    private DeviceModelDTO model;
    private String description;
    private String serial;

}
