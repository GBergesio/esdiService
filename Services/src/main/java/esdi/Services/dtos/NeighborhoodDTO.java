package esdi.Services.dtos;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class NeighborhoodDTO {
    private long id;
    private String name;
    private boolean deleted;
}
