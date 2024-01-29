package andrehsvictor.parrot.application.usecase.post;

import java.util.ArrayList;
import java.util.Collection;

import andrehsvictor.parrot.application.dto.AuthorDetails;
import andrehsvictor.parrot.application.dto.PostCommentDetails;
import andrehsvictor.parrot.application.dto.PostResponse;
import andrehsvictor.parrot.application.gateway.PostGateway;
import andrehsvictor.parrot.domain.comment.Comment;
import andrehsvictor.parrot.domain.post.Post;

public class FindPostUseCase {
    private final PostGateway postGateway;

    public FindPostUseCase(PostGateway postGateway) {
        this.postGateway = postGateway;
    }

    public PostResponse findPostById(Long id) {
        Post post = postGateway.findById(id);
        if (post == null)
            return null;
        AuthorDetails author = new AuthorDetails(post.getAuthor().getId(),
                post.getAuthor().getName());
        Collection<PostCommentDetails> comments = new ArrayList<>();
        for (Comment comment : post.getComments()) {
            AuthorDetails commentAuthor = new AuthorDetails(comment.getAuthor().getId(),
                    comment.getAuthor().getName());
            comments.add(new PostCommentDetails(comment.getId(), comment.getContent(), commentAuthor));
        }
        return PostResponse.builder()
                .id(post.getId())
                .title(post.getTitle())
                .content(post.getContent())
                .author(author)
                .createdAt(post.getCreatedAt().toString())
                .comments(comments)
                .build();
    }
}
