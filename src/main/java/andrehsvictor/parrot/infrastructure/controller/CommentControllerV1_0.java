package andrehsvictor.parrot.infrastructure.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import andrehsvictor.parrot.application.usecase.comment.CreateCommentUseCase;
import andrehsvictor.parrot.application.usecase.comment.DeleteCommentUseCase;
import andrehsvictor.parrot.application.usecase.comment.FindCommentUseCase;

@RestController
@RequestMapping("/api/v1.0/comments")
public class CommentControllerV1_0 {
    @Autowired
    private CreateCommentUseCase createCommentUseCase;

    @Autowired
    private FindCommentUseCase findCommentUseCase;

    @Autowired
    private DeleteCommentUseCase deleteCommentUseCase;

    @GetMapping("/{id}")
    public ResponseEntity<?> findCommentById(@PathVariable Long id) {
        return ResponseEntity.ok(findCommentUseCase.findCommentById(id));
    }

}
