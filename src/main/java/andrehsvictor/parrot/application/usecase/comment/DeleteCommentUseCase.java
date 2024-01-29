package andrehsvictor.parrot.application.usecase.comment;

import andrehsvictor.parrot.application.gateway.AuthenticatedUserProvider;
import andrehsvictor.parrot.application.gateway.CommentGateway;
import andrehsvictor.parrot.domain.comment.Comment;
import andrehsvictor.parrot.domain.user.User;

public class DeleteCommentUseCase {
    private final CommentGateway commentGateway;
    private final AuthenticatedUserProvider authenticatedUserProvider;

    public DeleteCommentUseCase(CommentGateway commentGateway, AuthenticatedUserProvider authenticatedUserProvider) {
        this.commentGateway = commentGateway;
        this.authenticatedUserProvider = authenticatedUserProvider;
    }

    public void deleteComment(Long id) {
        User currentUser = authenticatedUserProvider.getUser();
        Comment comment = commentGateway.findById(id);
        if (!currentUser.equals(comment.getAuthor())) {
            throw new RuntimeException("User is not the author of the comment");
        }
        commentGateway.delete(id);
    }
}
