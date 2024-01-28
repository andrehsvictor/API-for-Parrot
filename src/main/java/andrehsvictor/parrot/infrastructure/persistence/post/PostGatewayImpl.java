package andrehsvictor.parrot.infrastructure.persistence.post;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import andrehsvictor.parrot.application.gateway.PostGateway;
import andrehsvictor.parrot.domain.post.Post;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;

@Repository
public class PostGatewayImpl implements PostGateway {

    @Autowired
    private EntityManager entityManager;

    @Override
    public Post save(Post post) {
        PostEntity postEntity = PostMapper.toEntity(post);
        entityManager.persist(postEntity);
        return PostMapper.toDomain(postEntity);
    }

    @Override
    public void delete(Long id) {
        PostEntity postEntity = entityManager.find(PostEntity.class, id);
        entityManager.remove(postEntity);
    }

    @Override
    public Post findById(Long id) {
        PostEntity postEntity = entityManager.find(PostEntity.class, id);
        return PostMapper.toDomain(postEntity);
    }

    @Override
    public Post update(Long id, Post post) {
        PostEntity postEntity = entityManager.find(PostEntity.class, id);
        postEntity.setTitle(post.getTitle());
        postEntity.setContent(post.getContent());
        return PostMapper.toDomain(postEntity);
    }

    @Override
    public Collection<Post> findAllOrderByCreatedAtDesc() {
        Query query = entityManager.createQuery("SELECT p FROM PostEntity p ORDER BY p.createdAt DESC");
        Collection<PostEntity> postEntities = query.getResultList();
        Collection<Post> posts = new ArrayList<>();
        for (PostEntity postEntity : postEntities) {
            posts.add(PostMapper.toDomain(postEntity));
        }
        return posts;
    }
    
}
