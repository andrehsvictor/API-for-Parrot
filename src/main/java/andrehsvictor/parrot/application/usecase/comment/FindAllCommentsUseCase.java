package andrehsvictor.parrot.application.usecase.comment;

import java.util.ArrayList;
import java.util.Collection;

import andrehsvictor.parrot.application.dto.CommentResponse;
import andrehsvictor.parrot.application.gateway.CommentGateway;
import andrehsvictor.parrot.domain.comment.Comment;

public class FindAllCommentsUseCase {
    private final CommentGateway commentGateway;

    public FindAllCommentsUseCase(CommentGateway commentGateway) {
        this.commentGateway = commentGateway;
    }

    public Collection<CommentResponse> findAllCommentsByUserId(Long userId) {
        Collection<Comment> comments = commentGateway.findAllByUserId(userId);
        Collection<CommentResponse> commentResponses = new ArrayList<>();
        for (Comment comment : comments) {
            commentResponses.add(CommentResponse.builder()
                    .id(comment.getId())
                    .content(comment.getContent())
                    .createdAt(comment.getCreatedAt().toString())
                    .build());
        }
        return commentResponses;
    }

    public Collection<CommentResponse> findAllCommentsByPostId(Long postId) {
        Collection<Comment> comments = commentGateway.findAllByPostId(postId);
        Collection<CommentResponse> commentResponses = new ArrayList<>();
        for (Comment comment : comments) {
            commentResponses.add(CommentResponse.builder()
                    .id(comment.getId())
                    .content(comment.getContent())
                    .createdAt(comment.getCreatedAt().toString())
                    .build());
        }
        return commentResponses;
    }
}
