package andrehsvictor.parrot.infrastructure.persistence.comment;

import org.modelmapper.ModelMapper;

import andrehsvictor.parrot.domain.comment.Comment;

public class CommentMapper {
    private final static ModelMapper modelMapper = new ModelMapper();

    public static CommentEntity toEntity(Comment comment) {
        return modelMapper.map(comment, CommentEntity.class);
    }

    public static Comment toDomain(CommentEntity commentEntity) {
        return modelMapper.map(commentEntity, Comment.class);
    }
}
