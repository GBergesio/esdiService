package esdi.Services.dtos.devices;

import esdi.Services.dtos.BrandDTO;
import esdi.Services.dtos.ClientDTO;

public class DeviceDTO {

    private Long id;
    private ClientDTO clientDTO;
    private DeviceCategoryDTO category;
    private BrandDTO brand;
    private DeviceModelDTO model;
    private String description;
    private String serial;

}
