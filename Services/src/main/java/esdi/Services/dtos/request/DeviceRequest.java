package esdi.Services.dtos.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class DeviceRequest {

    private Long categoryId;
    private Long brandId;
    private Long modelId;
    private String description;
    private String serial;
    private Long clientId;

}
