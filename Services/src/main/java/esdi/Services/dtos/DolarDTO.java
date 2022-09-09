package esdi.Services.dtos;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDateTime;

@Setter
@Getter
@NoArgsConstructor
public class DolarDTO {

    private double price;

    private LocalDateTime date;
}
