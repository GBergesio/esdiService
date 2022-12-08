package esdi.Services.services.implement;

import esdi.Services.dtos.CommentDTO;
import esdi.Services.dtos.request.CommentRequest;
import esdi.Services.mappers.CommentMapper;
import esdi.Services.models.Comment;
import esdi.Services.models.Order;
import esdi.Services.models.users.Company;
import esdi.Services.models.users.Staff;
import esdi.Services.repositories.CommentRepository;
import esdi.Services.repositories.CompanyRepository;
import esdi.Services.repositories.OrderRepository;
import esdi.Services.repositories.StaffRepository;
import esdi.Services.services.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    CommentRepository commentRepository;

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    StaffRepository staffRepository;

    @Autowired
    CompanyRepository companyRepository;

    @Autowired
    CommentMapper commentMapper;


    @Override
    public Comment saveComment(Comment comment) {
        return commentRepository.save(comment);
    }

    @Override
    public List<CommentDTO> getAllComments() {
        return commentMapper.toDTO(commentRepository.findAll());
    }

    @Override
    public List<CommentDTO> getAllActiveComments() {

        List<CommentDTO> listActiveComments = commentMapper.toDTO(commentRepository.findAll().stream().filter(comment -> comment.getEdited() == false && comment.getDeleted() == false).collect(Collectors.toList()));

        return listActiveComments;
    }

    @Override
    public ResponseEntity<?> allComments() {
        return new ResponseEntity<>(getAllComments(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> allActiveComments() {
        return new ResponseEntity<>(getAllActiveComments(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> allCommentsByCompany(Authentication authentication) {
        Company company = companyRepository.findByUsername(authentication.getName());
        List<Comment> CommentsByCompany = commentRepository.findAllByCompany(company);

        return new ResponseEntity<>(commentMapper.toDTO(CommentsByCompany), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> allActiveCommentsByCompany(Authentication authentication) {

        Company company = companyRepository.findByUsername(authentication.getName());

        List<CommentDTO> listActiveComments = commentMapper.toDTO(commentRepository.findAllByCompany(company).stream().filter(comment -> comment.getEdited() == false && comment.getDeleted() == false).collect(Collectors.toList()));

        return new ResponseEntity<>(listActiveComments, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> createComment(CommentRequest commentRequest, Long idOrder, Authentication authentication) {
        Staff activeUser = staffRepository.findByUser(authentication.getName());
        Company company = companyRepository.findByStaffs(activeUser);
        Order order = orderRepository.findById(idOrder).orElse(null);
        List<Order> orders = orderRepository.findAllByCompany(company);

        if (order == null)
            return new ResponseEntity<>("Ingrese orden", HttpStatus.BAD_REQUEST);

        if(!orders.contains(order))
            return new ResponseEntity<>("Orden no encontrada", HttpStatus.BAD_REQUEST);

        if(order != null){

            if (commentRequest.getComment().isBlank() || commentRequest.getComment().isEmpty() || commentRequest.getComment() == null)
                return new ResponseEntity<>("Verifique campo vacio", HttpStatus.BAD_REQUEST);

            Comment newComment = new Comment();
            newComment.setComment(commentRequest.getComment());
            newComment.setOrder(order);
            newComment.setActiveUser(activeUser.getUser());
            newComment.setDate(LocalDateTime.now());
            newComment.setEdited(false);
            newComment.setDeleted(false);
            newComment.setIdUser(activeUser.getId());

            saveComment(newComment);
        }

        return new ResponseEntity<>("Comentario creado", HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> deleteComment(Long idComment, Authentication authentication) {
        Staff activeUser = staffRepository.findByUser(authentication.getName());
        Company company = companyRepository.findByStaffs(activeUser);
        Comment comment = commentRepository.findById(idComment).orElse(null);
        List<Comment> comments = commentRepository.findAllByCompany(company);

        if (!comments.contains(comment))
            return new ResponseEntity<>("No se puede eliminar ya que no se encuentra el comentario",HttpStatus.BAD_REQUEST);

        comment.setDeleted(true);

        commentRepository.save(comment);

        return new ResponseEntity<>("Comentario eliminado exitosamente",HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> deleteCommentCompany(Long idComment, Authentication authentication) {
        Company company = companyRepository.findByUsername(authentication.getName());

        Comment comment = commentRepository.findById(idComment).orElse(null);

        if (comment == null)
            return new ResponseEntity<>("No se puede eliminar ya que no se encuentra el comentario",HttpStatus.BAD_REQUEST);

        commentRepository.delete(comment);

        return new ResponseEntity<>("Comentario eliminado exitosamente",HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> editComment(CommentRequest commentRequest, Long idComment) {

        Comment commentToUpdate = commentRepository.findById(idComment).orElse(null);

        // Reemplazar por usuario activo ↓
//        Technician activeUser = technicianRepository.findById(idActiveUser).orElse(null);

        if (commentToUpdate == null)
            return new ResponseEntity<>("No se puede editar ya que no se encuentra el comentario",HttpStatus.BAD_REQUEST);

        if (commentRequest.getComment().isBlank() || commentRequest.getComment().isEmpty() || commentRequest.getComment() == null)
            return new ResponseEntity<>("Verifique campo vacio", HttpStatus.BAD_REQUEST);

        // Solo quien creo el comentario puede editarlo ↓
//        if(commentToUpdate.getIdUser() != idActiveUser)
//            return new ResponseEntity<>("El comentario solo puede editarse por quien fue creado", HttpStatus.BAD_REQUEST);

        commentToUpdate.setEdited(true);

        commentRepository.save(commentToUpdate);

            Comment newComment = new Comment();
            newComment.setComment(commentRequest.getComment());
            newComment.setOrder(commentToUpdate.getOrder());
//            newComment.setActiveUser(activeUser.getUser());
//            newComment.setIdUser(activeUser.getId());
            newComment.setDate(LocalDateTime.now());
            newComment.setEdited(false);
            newComment.setDeleted(false);

            saveComment(newComment);

        return new ResponseEntity<>("Comentario editado exitosamente", HttpStatus.OK);
    }


}
