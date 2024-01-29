package andrehsvictor.parrot.application.usecase.post;

import andrehsvictor.parrot.application.dto.AuthorDetails;
import andrehsvictor.parrot.application.dto.PostRequest;
import andrehsvictor.parrot.application.dto.PostResponse;
import andrehsvictor.parrot.application.gateway.AuthenticatedUserProvider;
import andrehsvictor.parrot.application.gateway.PostGateway;
import andrehsvictor.parrot.domain.post.Post;
import andrehsvictor.parrot.domain.user.User;

public class UpdatePostUseCase {
    private final PostGateway postGateway;
    private final AuthenticatedUserProvider authenticatedUserProvider;

    public UpdatePostUseCase(PostGateway postGateway, AuthenticatedUserProvider authenticatedUserProvider) {
        this.postGateway = postGateway;
        this.authenticatedUserProvider = authenticatedUserProvider;
    }

    public PostResponse updatePost(Long id, PostRequest postRequest) {
        Post post = postGateway.findById(id);
        post.setTitle(postRequest.title());
        post.setContent(postRequest.content());
        User currentUser = authenticatedUserProvider.getUser();
        if (!currentUser.equals(post.getAuthor())) {
            throw new RuntimeException("User is not the author of the post");
        }
        if (!post.isValid()) {
            throw new RuntimeException("Post is not valid");
        }
        Post updatedPost = postGateway.update(id, post);
        AuthorDetails author = new AuthorDetails(updatedPost.getAuthor().getId(),
                updatedPost.getAuthor().getName());
        return PostResponse.builder()
                .id(updatedPost.getId())
                .title(updatedPost.getTitle())
                .content(updatedPost.getContent())
                .author(author)
                .createdAt(updatedPost.getCreatedAt().toString())
                .build();
    }
}
