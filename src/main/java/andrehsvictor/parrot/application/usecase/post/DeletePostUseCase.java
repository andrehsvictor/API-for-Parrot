package andrehsvictor.parrot.application.usecase.post;

import andrehsvictor.parrot.application.gateway.AuthenticatedUserProvider;
import andrehsvictor.parrot.application.gateway.PostGateway;
import andrehsvictor.parrot.domain.post.Post;
import andrehsvictor.parrot.domain.user.User;

public class DeletePostUseCase {
    private final PostGateway postGateway;
    private final AuthenticatedUserProvider authenticatedUserProvider;

    public DeletePostUseCase(PostGateway postGateway, AuthenticatedUserProvider authenticatedUserProvider) {
        this.postGateway = postGateway;
        this.authenticatedUserProvider = authenticatedUserProvider;
    }

    public void deletePost(Long id) {
        Post post = postGateway.findById(id);
        User currentUser = authenticatedUserProvider.getUser();
        if (!currentUser.equals(post.getAuthor())) {
            throw new RuntimeException("User is not the author of the post");
        }
        postGateway.delete(id);
    }
}
