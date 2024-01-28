package andrehsvictor.parrot.application.gateway;

import java.util.Collection;

import andrehsvictor.parrot.domain.comment.Comment;

public interface CommentGateway {
    Comment save(Long postId, Comment comment);

    void delete(Long commentId);

    Comment findById(Long commentId);

    Comment update(Long commentId, Comment comment);

    Collection<Comment> findAllByUserId(Long userId);

    Collection<Comment> findAllByPostId(Long postId);
}
