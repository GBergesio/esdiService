package esdi.Services.dtos;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class CommentDTO {

    private long id;
    private String comment;
    private String activeUser;
    private Date date;
    private Boolean edited;
    private Boolean deleted;
    private Long idUser;

}
