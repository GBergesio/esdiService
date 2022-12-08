package esdi.Services.dtos.devices;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class DeviceModelDTO {

    private long id;
    private String name;
    private Boolean deleted;
}
