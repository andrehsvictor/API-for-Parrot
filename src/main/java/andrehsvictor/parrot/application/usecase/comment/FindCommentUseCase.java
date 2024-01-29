package andrehsvictor.parrot.application.usecase.comment;

import andrehsvictor.parrot.application.dto.CommentResponse;
import andrehsvictor.parrot.application.gateway.CommentGateway;
import andrehsvictor.parrot.domain.comment.Comment;

public class FindCommentUseCase {
    private final CommentGateway commentGateway;

    public FindCommentUseCase(CommentGateway commentGateway) {
        this.commentGateway = commentGateway;
    }

    public CommentResponse findCommentById(Long id) {
        Comment comment = commentGateway.findById(id);
        if (comment == null)
            return null;
        return CommentResponse.builder()
                .id(comment.getId())
                .content(comment.getContent())
                .createdAt(comment.getCreatedAt().toString())
                .build();
    }
}
