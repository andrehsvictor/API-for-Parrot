package andrehsvictor.parrot.application.usecase.comment;

import andrehsvictor.parrot.application.dto.CommentRequest;
import andrehsvictor.parrot.application.dto.CommentResponse;
import andrehsvictor.parrot.application.gateway.AuthenticatedUserProvider;
import andrehsvictor.parrot.application.gateway.CommentGateway;
import andrehsvictor.parrot.application.gateway.PostGateway;
import andrehsvictor.parrot.domain.comment.Comment;
import andrehsvictor.parrot.domain.comment.CommentFactory;
import andrehsvictor.parrot.domain.post.Post;
import andrehsvictor.parrot.domain.user.User;

public class CreateCommentUseCase {
    private final CommentGateway commentGateway;
    private final AuthenticatedUserProvider authenticatedUserProvider;

    public CreateCommentUseCase(CommentGateway commentGateway, AuthenticatedUserProvider authenticatedUserProvider) {
        this.commentGateway = commentGateway;
        this.authenticatedUserProvider = authenticatedUserProvider;
    }

    public CommentResponse createComment(Long postId, CommentRequest commentRequest) {
        User currentUser = authenticatedUserProvider.getUser();
        Comment comment = CommentFactory.createComment(commentRequest.content(), currentUser);
        comment = commentGateway.save(postId, comment);
        return CommentResponse.builder()
                .id(comment.getId())
                .content(comment.getContent())
                .createdAt(comment.getCreatedAt().toString())
                .build();
    }
}
