package andrehsvictor.parrot.application.usecase.post;

import java.util.ArrayList;
import java.util.Collection;

import andrehsvictor.parrot.application.dto.AuthorDetails;
import andrehsvictor.parrot.application.dto.PostCommentDetails;
import andrehsvictor.parrot.application.dto.PostResponse;
import andrehsvictor.parrot.application.gateway.PostGateway;
import andrehsvictor.parrot.domain.comment.Comment;
import andrehsvictor.parrot.domain.post.Post;

public class FindAllPostsUseCase {
    private final PostGateway postGateway;

    public FindAllPostsUseCase(PostGateway postGateway) {
        this.postGateway = postGateway;
    }

    public Collection<PostResponse> findAllOrderByCreatedAtDesc() {
        Collection<Post> posts = postGateway.findAllOrderByCreatedAtDesc();
        Collection<PostResponse> postResponses = new ArrayList<>();
        for (Post post : posts) {
            Collection<PostCommentDetails> comments = new ArrayList<>();
            for (Comment comment : post.getComments()) {
                AuthorDetails commentAuthor = new AuthorDetails(comment.getAuthor().getId(),
                        comment.getAuthor().getName());
                comments.add(new PostCommentDetails(comment.getId(), comment.getContent(), commentAuthor));
            }
            AuthorDetails author = new AuthorDetails(post.getAuthor().getId(),
                    post.getAuthor().getName());
            postResponses.add(PostResponse.builder()
                    .id(post.getId())
                    .title(post.getTitle())
                    .content(post.getContent())
                    .author(author)
                    .createdAt(post.getCreatedAt().toString())
                    .comments(comments)
                    .build());
        }
        return postResponses;
    }
}
