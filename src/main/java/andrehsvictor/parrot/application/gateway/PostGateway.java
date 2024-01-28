package andrehsvictor.parrot.application.gateway;

import java.util.Collection;

import andrehsvictor.parrot.domain.post.Post;

public interface PostGateway {
    Post save(Post post);

    void delete(Long id);

    Post findById(Long id);

    Post update(Long id, Post post);

    Collection<Post> findAllOrderByCreatedAtDesc();
}
