package esdi.Services.dtos.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CommentRequest {

    private String comment;
    private Boolean edited;
    private Boolean deleted;
    private Long idUser;
}
