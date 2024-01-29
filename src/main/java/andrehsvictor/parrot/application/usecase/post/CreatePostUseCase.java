package andrehsvictor.parrot.application.usecase.post;

import andrehsvictor.parrot.application.dto.AuthorDetails;
import andrehsvictor.parrot.application.dto.PostRequest;
import andrehsvictor.parrot.application.dto.PostResponse;
import andrehsvictor.parrot.application.gateway.AuthenticatedUserProvider;
import andrehsvictor.parrot.application.gateway.PostGateway;
import andrehsvictor.parrot.domain.post.Post;
import andrehsvictor.parrot.domain.post.PostFactory;
import andrehsvictor.parrot.domain.user.User;

public class CreatePostUseCase {
    private final PostGateway postGateway;
    private final AuthenticatedUserProvider authenticatedUserProvider;

    public CreatePostUseCase(PostGateway postGateway, AuthenticatedUserProvider authenticatedUserProvider) {
        this.postGateway = postGateway;
        this.authenticatedUserProvider = authenticatedUserProvider;
    }

    public PostResponse createPost(PostRequest postRequest) {
        User currentUser = authenticatedUserProvider.getUser();
        Post post = PostFactory.createPost(postRequest.title(), postRequest.content(), currentUser);

        AuthorDetails author = new AuthorDetails(currentUser.getId(),
                currentUser.getName());

        if (post.isValid()) {
            throw new RuntimeException("Post is not valid");
        }
        Post createdPost = postGateway.save(post);
        return PostResponse.builder()
                .id(createdPost.getId())
                .title(createdPost.getTitle())
                .content(createdPost.getContent())
                .author(author)
                .createdAt(createdPost.getCreatedAt().toString())
                .build();
    }
}
