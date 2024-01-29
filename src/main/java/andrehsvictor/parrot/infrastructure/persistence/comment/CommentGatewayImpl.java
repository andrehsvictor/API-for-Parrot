package andrehsvictor.parrot.infrastructure.persistence.comment;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import andrehsvictor.parrot.application.gateway.CommentGateway;
import andrehsvictor.parrot.domain.comment.Comment;
import andrehsvictor.parrot.infrastructure.persistence.post.PostEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;

@Repository
public class CommentGatewayImpl implements CommentGateway {
    @Autowired
    private EntityManager entityManager;

    @Override
    public Comment save(Long postId, Comment comment) {
        Query query = entityManager.createQuery("SELECT p FROM Post p WHERE p.id = :postId")
                .setParameter("postId", postId);
        PostEntity postEntity = (PostEntity) query.getSingleResult();
        CommentEntity commentEntity = CommentMapper.toEntity(comment);
        commentEntity.setPost(postEntity);
        entityManager.persist(commentEntity);
        return CommentMapper.toDomain(commentEntity);
    }

    @Override
    public void delete(Long commentId) {
        PostEntity postEntity = entityManager.find(PostEntity.class, commentId);
        entityManager.remove(postEntity);
    }

    @Override
    public Comment findById(Long commentId) {
        CommentEntity commentEntity = entityManager.find(CommentEntity.class, commentId);
        if (commentEntity == null) {
            return null;
        }
        return CommentMapper.toDomain(commentEntity);
    }

    @Override
    public Comment update(Long commentId, Comment comment) {
        CommentEntity commentEntity = entityManager.find(CommentEntity.class, commentId);
        commentEntity.setContent(comment.getContent());
        commentEntity = entityManager.merge(commentEntity);
        return CommentMapper.toDomain(commentEntity);
    }

    @Override
    public Collection<Comment> findAllByUserId(Long userId) {
        Query query = entityManager.createQuery("SELECT c FROM Comment c WHERE c.author.id = :userId")
                .setParameter("userId", userId);
        Collection<CommentEntity> commentEntities = query.getResultList();
        Collection<Comment> comments = new ArrayList<>();
        for (CommentEntity commentEntity : commentEntities) {
            comments.add(CommentMapper.toDomain(commentEntity));
        }
        return comments;
    }

    @Override
    public Collection<Comment> findAllByPostId(Long postId) {
        Query query = entityManager.createQuery("SELECT c FROM Comment c WHERE c.post.id = :postId")
                .setParameter("postId", postId);
        Collection<CommentEntity> commentEntities = query.getResultList();
        Collection<Comment> comments = new ArrayList<>();
        for (CommentEntity commentEntity : commentEntities) {
            comments.add(CommentMapper.toDomain(commentEntity));
        }
        return comments;
    }
}
