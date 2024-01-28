package andrehsvictor.parrot.infrastructure.persistence.post;

import org.modelmapper.ModelMapper;

import andrehsvictor.parrot.domain.post.Post;

public class PostMapper {
    private static final ModelMapper modelMapper = new ModelMapper();

    public static PostEntity toEntity(Post post) {
        return modelMapper.map(post, PostEntity.class);
    }

    public static Post toDomain(PostEntity postEntity) {
        return modelMapper.map(postEntity, Post.class);
    }
}
