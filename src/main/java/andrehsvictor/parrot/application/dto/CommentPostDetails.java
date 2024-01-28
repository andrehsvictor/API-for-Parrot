package andrehsvictor.parrot.application.dto;

import andrehsvictor.parrot.domain.post.Post;

public record CommentPostDetails(Long id, String content, String createdAt) {
    public CommentPostDetails from(Post post) {
        return new CommentPostDetails(post.getId(), post.getContent(), post.getCreatedAt().toString());
    }
}
