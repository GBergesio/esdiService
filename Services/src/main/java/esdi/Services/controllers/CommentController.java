package esdi.Services.controllers;

import esdi.Services.dtos.request.CommentRequest;
import esdi.Services.services.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;

@RestController
@RequestMapping("/comments")
public class CommentController {

    @Autowired
    CommentService commentService;

    @GetMapping("/all")
    ResponseEntity<?> getAllComments(){
        return new ResponseEntity<>(commentService.getAllComments(), HttpStatus.OK);
    }

    @GetMapping()
    ResponseEntity<?> getAllActiveComments(){
        return new ResponseEntity<>(commentService.getAllActiveComments(), HttpStatus.OK);
    }

    @Transactional
    @PostMapping()
    ResponseEntity<?> newComment(@RequestBody CommentRequest commentRequest, @RequestParam Long idOrder, @RequestParam Long idActiveUser){
        return commentService.createComment(commentRequest, idOrder, idActiveUser);
    }

    @DeleteMapping("/{id}")
    ResponseEntity<?> deleteComment(@PathVariable Long id) {
        return commentService.deleteComment(id);
    }

    @DeleteMapping("/admin/{id}")
    ResponseEntity<?> deleteCommentAdmin(@PathVariable Long id) {
        return commentService.deleteCommentAdmin(id);
    }

    @PatchMapping("/edit/{idComment}")
    ResponseEntity<?> editComment(@PathVariable Long idComment, @RequestBody CommentRequest commentRequest) {
        return commentService.editComment(commentRequest,idComment);
    }

}