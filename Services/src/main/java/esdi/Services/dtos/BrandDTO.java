package esdi.Services.dtos;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class BrandDTO {
    private Long id;
    private String name;
    private Boolean deleted;
}
