package esdi.Services.services;

import esdi.Services.dtos.CommentDTO;
import esdi.Services.dtos.request.CommentRequest;
import esdi.Services.models.Comment;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;

import java.util.List;

public interface CommentService {

    Comment saveComment(Comment comment);

    List<CommentDTO> getAllComments();
    List<CommentDTO> getAllActiveComments();

    ResponseEntity<?> allComments();
    ResponseEntity<?> allActiveComments();

    ResponseEntity<?> allCommentsByCompany(Authentication authentication);

    ResponseEntity<?> allActiveCommentsByCompany(Authentication authentication);

    ResponseEntity<?> createComment(CommentRequest commentRequest, Long idOrder, Authentication authentication);

    ResponseEntity<?> deleteComment(Long idComment, Authentication authentication);
    ResponseEntity<?> deleteCommentCompany(Long idComment, Authentication authentication);

    ResponseEntity<?> editComment(CommentRequest commentRequest, Long idOrder);

    //    ResponseEntity<?> updateComment(Long idOrder,Long idDevice, Long idTechnician, OrderRequest orderRequest);
}
