package esdi.Services.services;

import esdi.Services.dtos.CommentDTO;
import esdi.Services.dtos.request.CommentRequest;
import esdi.Services.models.Comment;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface CommentService {

    Comment saveComment(Comment comment);

    List<CommentDTO> getAllComments();
    List<CommentDTO> getAllActiveComments();

    ResponseEntity<?> allComments();

    ResponseEntity<?> allActiveComments();

    ResponseEntity<?> createComment(CommentRequest commentRequest, Long idOrder, Long idActiveUser);

    ResponseEntity<?> deleteComment(Long idComment);
    ResponseEntity<?> deleteCommentAdmin(Long idComment);

    ResponseEntity<?> editComment(CommentRequest commentRequest, Long idOrder);


    //    ResponseEntity<?> updateComment(Long idOrder,Long idDevice, Long idTechnician, OrderRequest orderRequest);
}
