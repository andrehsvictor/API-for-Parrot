package andrehsvictor.parrot.application.usecase;

import andrehsvictor.parrot.application.dto.AuthorDetails;
import andrehsvictor.parrot.application.dto.CommentRequest;
import andrehsvictor.parrot.application.dto.CommentResponse;
import andrehsvictor.parrot.application.gateway.AuthenticatedUserProvider;
import andrehsvictor.parrot.application.gateway.CommentGateway;
import andrehsvictor.parrot.application.gateway.PostGateway;
import andrehsvictor.parrot.domain.comment.Comment;
import andrehsvictor.parrot.domain.comment.CommentFactory;
import andrehsvictor.parrot.domain.post.Post;
import andrehsvictor.parrot.domain.user.User;

public class CommentService {
    private final CommentGateway commentGateway;
    private final PostGateway postGateway;
    private final AuthenticatedUserProvider authenticatedUserProvider;

    public CommentService(CommentGateway commentGateway, PostGateway postGateway,
            AuthenticatedUserProvider authenticatedUserProvider) {
        this.commentGateway = commentGateway;
        this.postGateway = postGateway;
        this.authenticatedUserProvider = authenticatedUserProvider;
    }

    public CommentResponse createComment(Long postId, CommentRequest request) {
        User currentUser = authenticatedUserProvider.getUser();
        Post currentPost = postGateway.findById(postId);
        Comment comment = CommentFactory.createComment(request.content(), currentUser, currentPost);
        Comment createdComment = commentGateway.save(postId, comment);
        return CommentResponse.builder()
                .id(createdComment.getId())
                .content(createdComment.getContent())
                .author(new AuthorDetails(currentUser.getId(), currentUser.getName()))
                .createdAt(createdComment.getCreatedAt())
                .build();
    }

    public void deleteComment(Long commentId) {
        User currentUser = authenticatedUserProvider.getUser();
        Comment comment = commentGateway.findById(commentId);
        if (!currentUser.equals(comment.getAuthor())) {
            throw new RuntimeException("User is not the author of the comment");
        }
        commentGateway.delete(commentId);
    }

    public CommentResponse updateComment(Long commentId, CommentRequest request) {
        User currentUser = authenticatedUserProvider.getUser();
        Comment comment = commentGateway.findById(commentId);
        if (!currentUser.equals(comment.getAuthor())) {
            throw new RuntimeException("User is not the author of the comment");
        }
        comment.setContent(request.content());
        Comment updatedComment = commentGateway.update(commentId, comment);
        return CommentResponse.builder()
                .id(updatedComment.getId())
                .content(updatedComment.getContent())
                .author(new AuthorDetails(currentUser.getId(), currentUser.getName()))
                .createdAt(updatedComment.getCreatedAt())
                .build();
    }
}
